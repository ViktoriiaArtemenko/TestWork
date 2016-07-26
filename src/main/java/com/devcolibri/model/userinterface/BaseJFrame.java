package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.ModelWorking;
import com.devcolibri.model.modules.WorkSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import static java.util.Collections.shuffle;

public abstract class BaseJFrame extends JFrame implements ActionListener {
    protected String instructions = "давать распоряжения";
    protected String priority = "приоритет";
    protected PositionsEntity positionsEntity = new PositionsEntity();
    protected Random random = new Random();
    protected ModelWorking modelWorking;
    protected WorkSchedule workSchedule;
    protected JPanel jPanel = new JPanel();
    protected JScrollPane jScrollPane;
    protected JViewport jViewport;

    protected BaseJFrame(ModelWorking modelWorking, WorkSchedule workSchedule){
        this.modelWorking = modelWorking;
        this.workSchedule = workSchedule;
    }

    protected void setButton(JButton jButton, Point point, Dimension dimension, Font font, Color color, String action,
                             JPanel jPanel) {
        jButton.setFocusPainted(false);
        jButton.setSize(dimension);
        jButton.setLocation(point);
        jButton.setFont(font);
        jButton.setBackground(color);
        jButton.setActionCommand(action);
        jButton.addActionListener(this);
        jPanel.add(jButton);
    }

    protected void setList(JList<String> jList, String[] data, JScrollPane jScrollPane, JViewport jViewport,
                           JLabel jLabel, Color color, Dimension dimension, Point point, JPanel jPanel) {
        jList.setListData(data);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane = new JScrollPane(jList);
        jViewport = new JViewport();
        jLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        jViewport.setView(jLabel);
        jViewport.setBackground(color);
        jScrollPane.setColumnHeader(jViewport);
        jScrollPane.setBackground(color);
        jScrollPane.setSize(dimension);
        jScrollPane.setLocation(point);
        jPanel.add(jScrollPane);
    }

    protected void setLabel(JLabel jLabel, Dimension dimension, Point point, Color color, Font font, JPanel jPanel) {
        jLabel.setSize(dimension);
        jLabel.setLocation(point);
        jLabel.setForeground(color);
        jLabel.setFont(font);
        jPanel.add(jLabel);
    }

    protected LinkedList<String> concatOneAndTwoListOfPositions() {
        LinkedList<String> linkedList = new LinkedList();
        ListIterator<String> listIteratorOne = modelWorking.getListOfPositionCollaboratorsOne().listIterator();
        ListIterator<String> listIteratorTwo = modelWorking.getListOfPositionCollaboratorsTwo().listIterator();
        while (listIteratorOne.hasNext()) {
            String str1 = listIteratorOne.next();
            String str2 = listIteratorTwo.next();
            if (!str2.equals("NULL"))
                linkedList.add(str1 + ", " + str2);
            else linkedList.add(str1);
        }
        return linkedList;
    }

    String[] getInstructions() {
        ArrayList<String> arrayList = new ArrayList(positionsEntity.getAction().values());
        arrayList.addAll(arrayList);
        arrayList.addAll(arrayList);
        ArrayList<String> act = new ArrayList();
        ListIterator<String> listIterator = arrayList.listIterator();
        String str;
        while (listIterator.hasNext()) {
            str = listIterator.next();
            if (str.equals(instructions)) continue;
            act.add(str + " (" + priority + " " + (1 + random.nextInt(6)) + "; " + (50 + random.nextInt(200)) + " $)");
        }
        shuffle(act);
        String[] instructionsArray = act.toArray(new String[act.size()]);
        return instructionsArray;
    }

    protected String[] getCollaboratorsWithPositions() {
        String[] arrayName = modelWorking.getListOfNameCollaboratorsArray();
        String[] arrayPos = concatOneAndTwoListOfPositions().toArray(new String[concatOneAndTwoListOfPositions().size()]);
        for (int i = 0; i < arrayName.length; i++) {
            arrayName[i] += " (" + arrayPos[i] + ")";
        }
        return arrayName;
    }
}
