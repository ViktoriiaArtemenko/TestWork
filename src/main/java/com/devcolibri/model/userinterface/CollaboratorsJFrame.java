package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.WorkingModule;
import com.devcolibri.model.modules.WorkScheduleModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

public class CollaboratorsJFrame extends BaseJFrame {
    protected JTabbedPane jTabbedPane = new JTabbedPane(SwingConstants.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
    protected String title = "Сотрудники";
    protected String work = "Рабочий график:";
    protected String month = "Июль: ";

    protected JPanel jPanel;
    protected JLabel jLabel;

    public CollaboratorsJFrame(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity, PositionsEntity
            positionsEntity, WorkingModule workingModule, WorkScheduleModule workScheduleModule) {
        super(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule, workScheduleModule);
        setLocation(370, 180);
        setSize(625, 350);
        setTitle(title);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(jTabbedPane);
        setVisible(true);
        setTabbedPane();
    }

    protected void setTabbedPane() {
        ListIterator<String> listIterator = workingModule.getOneAndTwoListOfPositions().listIterator();
        Iterator<String> iterator = workingModule.getListOfNameCollaborators().iterator();
        Iterator<TreeSet<Integer>> iterator1 = workScheduleModule.getListOfSchedules().iterator();
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
