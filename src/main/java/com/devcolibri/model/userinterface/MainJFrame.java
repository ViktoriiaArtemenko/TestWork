package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.modules.ModelWorking;
import com.devcolibri.model.modules.WorkSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainJFrame extends BaseJFrame implements ActionListener {
    private String title = "TestWork";
    private FreelancersEntity freelancersEntity = new FreelancersEntity();
    private JButton jButtonCollaborators = new JButton("Сотрудники");
    private JButton jButtonPositions = new JButton("Должности");
    private JButton jButtonReports = new JButton("Отчеты");
    private JButton jButtonNext = new JButton("Следующий день", new ImageIcon("arrow.gif"));
    private JButton jButtonExit = new JButton("Выход");
    private JButton jButtonStart = new JButton("Старт");
    private JButton jButtonExecute = new JButton("Выполнить");
    private JList<String> jListCollaborators = new JList();
    private JList<String> jListFreelancers = new JList();
    private JList<String> jListTasks = new JList();
    private JLabel jLabelTime = new JLabel("time");
    private JLabel jLabelDate = new JLabel("date");
    private JLabel jLabelTask = new JLabel("Task");
    private JLabel jLabelCollaborator = new JLabel("Сотрудники");
    private JLabel jLabelFreelancers = new JLabel("Фрилансеры");
    private JLabel jLabel1 = new JLabel("Сотрудники");
    private JLabel jLabel2 = new JLabel("Фрилансеры");
    private JLabel jLabel3 = new JLabel("Распоряжения директора");

    public MainJFrame(ModelWorking modelWorking, WorkSchedule workSchedule) {
        super(modelWorking, workSchedule);
        setLocation(60, 12);
        setSize(1250, 700);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel.setLayout(null);
        setContentPane(jPanel);
        setVisible(true);
        getInstructions();

        setLabel(jLabelCollaborator, new Dimension(220, 50), new Point(875, 250), Color.BLUE, new Font("TimesRoman",
                Font.BOLD, 25), jPanel);
        setLabel(jLabelFreelancers, new Dimension(220, 50), new Point(525, 250), Color.BLUE, new Font("TimesRoman",
                Font.BOLD, 25), jPanel);
        setLabel(jLabelTask, new Dimension(220, 50), new Point(75, 250), Color.RED, new Font("TimesRoman",
                Font.BOLD, 25), jPanel);
        setLabel(jLabelTime, new Dimension(300, 30), new Point(1000, 25), Color.RED, new Font("TimesRoman",
                Font.BOLD, 25), jPanel);
        setLabel(jLabelDate, new Dimension(100, 30), new Point(325, 25), Color.BLUE, new Font("TimesRoman",
                Font.BOLD, 25), jPanel);

        setList(jListCollaborators, getCollaboratorsWithPositions(), jScrollPane, jViewport, jLabel1,
                Color.CYAN, new Dimension(320, 190), new Point(875, 300), jPanel);
        setList(jListFreelancers, freelancersEntity.getNameArray(), jScrollPane, jViewport, jLabel2,
                Color.CYAN, new Dimension(320, 190), new Point(525, 300), jPanel);
        jListTasks.setFont(new Font("TimesRoman", Font.ITALIC, 15));
        setList(jListTasks, getInstructions(), jScrollPane, jViewport, jLabel3,
                Color.PINK, new Dimension(420, 290), new Point(75, 300), jPanel);

        setButton(jButtonCollaborators, new Point(300, 150), new Dimension(300, 50), new Font("TimesRoman", Font.BOLD, 20),
                Color.CYAN, "collaborators", jPanel);
        setButton(jButtonPositions, new Point(700, 150), new Dimension(300, 50), new Font("TimesRoman", Font.BOLD, 20),
                Color.CYAN, "positions", jPanel);
        setButton(jButtonReports, new Point(525, 600), new Dimension(200, 30), new Font("TimesRoman", Font.BOLD, 20),
                Color.GREEN, "reports", jPanel);
        setButton(jButtonNext, new Point(400, 25), new Dimension(200, 30), new Font("TimesRoman", Font.ITALIC, 15),
                Color.PINK, "next", jPanel);
        setButton(jButtonExit, new Point(900, 600), new Dimension(200, 30), new Font("TimesRoman", Font.BOLD, 20),
                Color.RED, "exit", jPanel);
        setButton(jButtonStart, new Point(700, 25), new Dimension(200, 30), new Font("TimesRoman", Font.ITALIC, 15),
                Color.BLUE, "start", jPanel);
        setButton(jButtonExecute, new Point(300, 225), new Dimension(200, 30), new Font("TimesRoman", Font.BOLD, 20),
                Color.GREEN, "execute", jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "exit":
                System.exit(0);
                break;
            case "start":

                break;
            case "execute":

                break;
            case "next":

                break;
            case "collaborators":
                new CollaboratorsJFrame(modelWorking, workSchedule);
                break;
            case "positions":
                new PositionsJFrame(modelWorking, workSchedule);
                break;
            case "reports":
                new ReportsJFrame(modelWorking, workSchedule);
                break;
        }
    }
}
