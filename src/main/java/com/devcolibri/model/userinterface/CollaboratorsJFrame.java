package com.devcolibri.model.userinterface;

import com.devcolibri.model.modules.ModelWorking;
import com.devcolibri.model.modules.WorkSchedule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;

public class CollaboratorsJFrame extends BaseJFrame {
    private JTabbedPane jTabbedPane = new JTabbedPane(SwingConstants.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
    private String title = "Сотрудники";
    private String work = "Рабочий график:";
    private String month = "Июль: ";
    private JPanel jPanel;
    private JLabel jLabel;

    public CollaboratorsJFrame(ModelWorking modelWorking, WorkSchedule workSchedule) {
        super(modelWorking, workSchedule);
        setLocation(370, 180);
        setSize(625, 350);
        setTitle(title);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(jTabbedPane);
        setVisible(true);
        setTabbedPane();
    }

    private void setTabbedPane() {
        LinkedList<String> linkedList = concatOneAndTwoListOfPositions();
        ListIterator<String> listIterator = linkedList.listIterator();
        Iterator<String> iterator = modelWorking.getListOfNameCollaborators().iterator();
        Iterator<TreeSet<Integer>> iterator1 = workSchedule.getListOfSchedules().iterator();
        while (iterator.hasNext()) {
            jLabel = new JLabel("<html>" + listIterator.next() + "<br>" + work + "<br>" + month + iterator1.next()
                    + "<html>");
            jPanel = new JPanel();
            jPanel.add(jLabel);
            jTabbedPane.addTab(iterator.next(), jPanel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
