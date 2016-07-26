package com.devcolibri.model.userinterface;

import com.devcolibri.model.modules.ModelWorking;
import com.devcolibri.model.modules.WorkSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ListIterator;

public class PositionsJFrame extends BaseJFrame {
    private String title = "Должности";
    private String rate = "Ставка: ";
    private JList<String> jList = new JList();
    private JLabel jLabel = new JLabel(title);

    public PositionsJFrame(ModelWorking modelWorking, WorkSchedule workSchedule) {
        super(modelWorking, workSchedule);
        setLocation(370, 180);
        setSize(625, 400);
        setTitle(title);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(jPanel);
        setVisible(true);

        setList(jList, getListOfPositions(), jScrollPane, jViewport, jLabel, Color.green, new Dimension(0, 0),
                new Point(0, 0), jPanel);
    }

    private String[] getListOfPositions() {
        String[] array = new String[positionsEntity.getPosition().size()];
        ListIterator<String> listIterator = positionsEntity.getPosition().listIterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = "<html>" + listIterator.next() + " - " + positionsEntity.getActionArray()[i] + ".<br> " + rate +
                    positionsEntity.getRateArray()[i] + "<br><br><html>";
        }
        return array;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
