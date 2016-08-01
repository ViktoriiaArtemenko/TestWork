package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.DataBaseModule;
import com.devcolibri.model.modules.WorkScheduleModule;
import com.devcolibri.model.modules.WorkingModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

public class ReportsCollaborators extends CollaboratorsJFrame {
    private String name;
    private int id;
    private int salary;
    private int hourColl;
    private LinkedList<String> listGeneral = new LinkedList();
    private String hourText = "часы";
    private String salaryText = " зарплата: ";

    public ReportsCollaborators(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity,
                                PositionsEntity positionsEntity, WorkingModule workingModule,
                                WorkScheduleModule workScheduleModule) {
        super(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule, workScheduleModule);

    }

    protected void setTabbedPane() {
        Iterator<String> iterator = workingModule.getListOfNameCollaborators().iterator();
        JButton jButton;
        while (iterator.hasNext()) {
            name = iterator.next();
            jButton = new JButton("Сформулировать отчет");
            jButton.setFocusPainted(false);
            jButton.setActionCommand(name);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    name = e.getActionCommand();
                    id = collaboratorsEntity.getId().get(name);
                    salary = dataBaseModule.readDataBaseSalary(DataBaseModule.QUERY_SELECT_COLLABORATORS_SALARY, id);
                    hourColl = dataBaseModule.readDataBaseHours(id);
                    listGeneral.add(name + ": " + salaryText + "- " + salary + ", " + hourText + "- " + hourColl);
                    saveFile(listGeneral);

                }
            });
            jPanel = new JPanel();
            jPanel.add(jButton);
            jTabbedPane.addTab(name, jPanel);
        }

    }
}
