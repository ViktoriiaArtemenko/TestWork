package com.devcolibri.model.userinterface;

import com.devcolibri.model.modules.ModelWorking;
import com.devcolibri.model.modules.WorkSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ReportsJFrame extends BaseJFrame {
    private String title = "Отчеты";
    private String actionCollaborators = "ReportCollaborators";
    private String actionFreelancers = "ReportFreelancers";
    private String actionGeneral = "ReportGeneral";
    private JButton jButtonCollaborators = new JButton("Отчет работы сотрудников за неделю");
    private JButton jButtonFreelancers = new JButton("Отчет работы фрилансеров за день");
    private JButton jButtonGeneral = new JButton("Общий отчет за все время");

    public ReportsJFrame(ModelWorking modelWorking, WorkSchedule workSchedule) {
        super(modelWorking, workSchedule);
        setLocation(370, 180);
        setSize(625, 250);
        setTitle(title);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(jPanel);
        setVisible(true);

        setButton(jButtonCollaborators, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                        Font.BOLD, 25), Color.orange, actionCollaborators,
                jPanel);
        setButton(jButtonFreelancers, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                        Font.BOLD, 25), Color.orange, actionFreelancers,
                jPanel);
        setButton(jButtonGeneral, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                        Font.BOLD, 25), Color.orange, actionGeneral,
                jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
