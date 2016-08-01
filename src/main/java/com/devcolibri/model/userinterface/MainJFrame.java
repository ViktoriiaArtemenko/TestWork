package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.WorkingModule;
import com.devcolibri.model.modules.TimerModule;
import com.devcolibri.model.modules.WorkScheduleModule;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainJFrame extends BaseJFrame implements ListCellRenderer {

    public MainJFrame(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity, PositionsEntity
            positionsEntity, WorkingModule workingModule, WorkScheduleModule workScheduleModule) {
        super(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule, workScheduleModule);
        setLocation(60, 12);
        setSize(1250, 700);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel.setLayout(null);
        setContentPane(jPanel);
        setVisible(true);

        setLabel(jLabelCollaborator, new Dimension(320, 50), new Point(875, 250), Color.BLUE, new
                Font("TimesRoman", Font.BOLD, 18), jPanel);

        setLabel(jLabelFreelancers, new Dimension(320, 50), new Point(525, 250), Color.BLUE, new
                Font("TimesRoman", Font.BOLD, 18), jPanel);

        setLabel(jLabelTask, new Dimension(420, 50), new Point(75, 250), Color.RED, new
                Font("TimesRoman", Font.BOLD, 15), jPanel);

        setLabel(jLabelTimer, new Dimension(300, 30), new Point(1050, 30), Color.RED, new
                Font("TimesRoman", Font.BOLD, 25), jPanel);

        setLabel(jLabelDate, new Dimension(400, 30), new Point(50, 30), Color.BLUE, new
                Font("TimesRoman", Font.BOLD, 25), jPanel);

        setLabel(jLabelInfo, new Dimension(900, 60), new Point(510, 220), Color.DARK_GRAY, new
                Font("TimesRoman", Font.ITALIC, 18), jPanel);

        setLabel(jLabelHour, new Dimension(400, 30), new Point(165, 70), Color.BLUE, new
                Font("TimesRoman", Font.BOLD, 25), jPanel);


        setListModel(listCollaboratorsModel, getSortedCollaboratorsWithPositions(TimerModule.countDay));
        jListCollaborators = new JList(listCollaboratorsModel);
        setList(jListCollaborators, jScrollPane, jViewport,
                false, jLabel1, Color.CYAN, new Dimension(320, 190), new Point(875, 300), jPanel);
        jListCollaborators.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jListCollaborators.getSelectedIndex();
                jListTasks.setEnabled(true);
                jLabelFreelancers.setText("");
                jLabelCollaborator.setText(jListCollaborators.getSelectedValue());
                jListFreelancers.clearSelection();
                jListCollaborators.setSelectedIndex(index);
            }
        });

        setListModel(listFreelancersModel, (String[]) freelancersEntity.getName().
                toArray(new String[freelancersEntity.getName().size()]));
        jListFreelancers = new JList(listFreelancersModel);
        setList(jListFreelancers, jScrollPane, jViewport, false, jLabel2, Color.CYAN, new Dimension(320, 190),
                new Point(525, 300), jPanel);
        jListFreelancers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = jListFreelancers.getSelectedIndex();
                jListTasks.setEnabled(true);
                jLabelCollaborator.setText("");
                jLabelFreelancers.setText(jListFreelancers.getSelectedValue());
                jListCollaborators.clearSelection();
                jListFreelancers.setSelectedIndex(index);
            }
        });


        setListModel(listTasksModel, positionsModule.getInstructions());
        jListTasks = new JList(listTasksModel);
        jListTasks.setFont(new Font("TimesRoman", Font.ITALIC, 15));
        setList(jListTasks, jScrollPane, jViewport, false, jLabel3, Color.PINK, new Dimension(420, 290),
                new Point(75, 300), jPanel);
        jListTasks.setCellRenderer(this);


        setListModel(listExecuteModel, new String[1]);
        jListExecute = new JList(listExecuteModel);
        setList(jListExecute, jScrollPane, jViewport, true, jLabel4, Color.PINK, new Dimension(500, 130),
                new Point(375, 100), jPanel);

        setButton(jButtonCollaborators, true, new Point(55, 150), new Dimension(300, 50), new Font("TimesRoman",
                Font.BOLD, 20), Color.CYAN, "collaborators", jPanel);

        setButton(jButtonPositions, true, new Point(890, 150), new Dimension(300, 50), new Font("TimesRoman",
                Font.BOLD, 20), Color.CYAN, "positions", jPanel);

        setButton(jButtonReports, false, new Point(525, 600), new Dimension(200, 30), new Font("TimesRoman",
                Font.BOLD, 20), Color.CYAN, "reports", jPanel);

        setButton(jButtonNext, false, new Point(400, 25), new Dimension(225, 50), new Font("TimesRoman",
                Font.BOLD, 20), Color.CYAN, "next", jPanel);

        setButton(jButtonExit, true, new Point(900, 600), new Dimension(200, 30), new Font("TimesRoman",
                Font.BOLD, 20), Color.RED, "exit", jPanel);
        jButtonExit.setForeground(Color.BLUE);

        setButton(jButtonStart, true, new Point(700, 25), new Dimension(200, 50), new Font("TimesRoman",
                Font.BOLD, 25), Color.BLUE, "start", jPanel);
        jButtonStart.setForeground(Color.RED);

        setButton(jButtonExecute, false, new Point(300, 233), new Dimension(200, 30), new Font("TimesRoman",
                Font.BOLD, 20), Color.CYAN, "execute", jPanel);

        setButton(jButtonChange, false, new Point(525, 525), new Dimension(275, 30), new Font("TimesRoman",
                Font.BOLD, 15), Color.CYAN, "change", jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "exit":
                System.exit(0);
                break;

            case "start":
                if (TimerModule.countHour != 0) {
                    setListModelGroup(listCollaboratorsModel, getSortedCollaboratorsWithPositions(TimerModule.countDay),
                            listFreelancersModel, (String[]) freelancersEntity.getName().
                                    toArray(new String[freelancersEntity.getName().size()]), listTasksModel,
                            positionsModule.getInstructions(), listExecuteModel, new String[1]);
                }

                new TimerModule(jLabelTimer, jLabelHour, jLabelInfo, jListCollaborators, jListFreelancers,
                        jListTasks, jButtonReports, jButtonNext, jButtonExecute, jButtonChange, jButtonStart);

                if (!jListCollaborators.isSelectionEmpty()) jListCollaborators.clearSelection();
                if (!jListFreelancers.isSelectionEmpty()) jListFreelancers.clearSelection();

                jListCollaborators.setEnabled(true);
                jListFreelancers.setEnabled(true);
                jListTasks.setEnabled(false);
                jButtonStart.setEnabled(false);
                jButtonReports.setEnabled(false);
                break;

            case "execute":
                execute();
                break;

            case "next":
                workingModule.setSaveCollAndSaveFree(false, true);
                if (TimerModule.countDay == 11 || TimerModule.countDay == 18 || TimerModule.countDay == 25)
                    workingModule.setSaveCollAndSaveFree(true, false);
                if (!jListCollaborators.isSelectionEmpty()) jListCollaborators.clearSelection();
                if (!jListFreelancers.isSelectionEmpty()) jListFreelancers.isSelectionEmpty();
                jButtonNext.setEnabled(false);
                jButtonReports.setEnabled(false);
                jButtonStart.setEnabled(true);
                jLabelInfo.setText(infoText);
                jLabelHour.setText(textHour);
                setListModelGroup(listCollaboratorsModel, getSortedCollaboratorsWithPositions(TimerModule.countDay),
                        listFreelancersModel, (String[]) freelancersEntity.getName().
                                toArray(new String[freelancersEntity.getName().size()]), listTasksModel,
                        positionsModule.getInstructions(), listExecuteModel, new String[1]);
                setDate();
                if (TimerModule.countDay == TimerModule.LAST_DAY_IN_MONTH) {
                    jButtonNext.setVisible(false);
                    jLabelInfo.setVisible(false);
                }
                if (!jListTasks.isSelectionEmpty()) jListTasks.clearSelection();
                jListTasks.setEnabled(false);
                break;

            case "change":
                jButtonChange.setEnabled(false);
                jButtonExecute.setEnabled(false);
                jListTasks.setEnabled(true);
                jLabelTask.setText("");
                jListTasks.clearSelection();
                break;

            case "collaborators":
                new CollaboratorsJFrame(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule,
                        workScheduleModule);
                break;

            case "positions":
                new PositionsJFrame(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule,
                        workScheduleModule);
                break;

            case "reports":
                new ReportsJFrame(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule,
                        workScheduleModule);
                break;
        }
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
        JLabel label = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        Icon icon = UIManager.getIcon("Tree.closedIcon");
        if (isSelected) {
            jListTasks.setEnabled(false);
            jButtonChange.setEnabled(true);
            jButtonExecute.setEnabled(true);

            jLabelTask.setText(jListTasks.getSelectedValue());

            label.setForeground(Color.RED);
            label.setEnabled(true);
            label.setIcon(icon);
        }
        return label;
    }
}
