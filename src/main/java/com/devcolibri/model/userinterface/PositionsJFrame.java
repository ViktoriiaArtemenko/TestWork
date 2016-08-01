package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.WorkingModule;
import com.devcolibri.model.modules.WorkScheduleModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PositionsJFrame extends BaseJFrame {
    private String title = "Должности";
    private JList<String> jList = new JList();
    private JLabel jLabel = new JLabel(title);

    public PositionsJFrame(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity, PositionsEntity
            positionsEntity, WorkingModule workingModule, WorkScheduleModule workScheduleModule) {
        super(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule, workScheduleModule);
        setLocation(370, 180);
        setSize(625, 400);
        setTitle(title);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(jPanel);
        setVisible(true);

        jList.setListData(positionsModule.getListOfPositionsAndRate());
        setList(jList, jScrollPane, jViewport, true, jLabel, Color.green,
                new Dimension(0, 0), new Point(0, 0), jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
