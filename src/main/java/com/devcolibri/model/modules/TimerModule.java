package com.devcolibri.model.modules;

import javax.swing.*;
import java.util.TimerTask;

public class TimerModule extends TimerTask {
    public static final int AMOUNT_HOUR_IN_DAY = 8;
    public static final int LAST_DAY_IN_MONTH = 31;
    public static final int hour = 9;

    public static int countHour = 0;
    public static int countDay = 4;
    private int secondTimer = 120;       //120 seconds; 120 seconds = 1 hour

    private String infoText = "<html>Для продолжения нажмите на кнопку \"Следующий день\" или для посмотра <br> " +
            "отчетов нажмите на кнопку \"Отчеты\"<html>";

    private java.util.Timer timer = new java.util.Timer();

    private JLabel labelTimer;
    private JLabel jLabelHour;
    private JLabel jLabelInfo;
    private JList<String> jListCollaborators;
    private JList<String> jListFreelancers;
    private JList<String> jListTasks;
    private JButton jButtonReports = new JButton("Отчеты");
    private JButton jButtonNext = new JButton("Следующий день");
    private JButton jButtonExecute = new JButton("Выполнить");
    private JButton jButtonChange = new JButton("Выбрать другое распоряжение");
    private JButton jButtonStart = new JButton("Старт");

    public TimerModule(JLabel labelTimer, JLabel jLabelHour, JLabel jLabelInfo, JList<String> jListCollaborators,
                       JList<String> jListFreelancers, JList<String> jListTasks, JButton jButtonReports,
                       JButton jButtonNext, JButton jButtonExecute, JButton jButtonChange, JButton jButtonStart) {
        this.labelTimer = labelTimer;
        this.jLabelHour = jLabelHour;
        this.jLabelInfo = jLabelInfo;
        this.jListCollaborators = jListCollaborators;
        this.jListFreelancers = jListFreelancers;
        this.jListTasks = jListTasks;
        this.jButtonReports = jButtonReports;
        this.jButtonNext = jButtonNext;
        this.jButtonExecute = jButtonExecute;
        this.jButtonChange = jButtonChange;
        this.jButtonStart = jButtonStart;

        labelTimer.setText("120");
        timer.schedule(this, 1000, 1000);
        countHour++;
    }

    @Override
    public void run() {
        whenTimeIsFinish();
        labelTimer.setText("" + (--secondTimer));
    }

    private void whenTimeIsFinish() {
        if (secondTimer == 1) {
            timer.cancel();

            if (!jListCollaborators.isSelectionEmpty()) jListCollaborators.clearSelection();
            if (!jListFreelancers.isSelectionEmpty()) jListFreelancers.clearSelection();
            if (!jListTasks.isSelectionEmpty()) jListTasks.clearSelection();

            jListCollaborators.setEnabled(false);
            jListFreelancers.setEnabled(false);
            jListTasks.setEnabled(false);
            jButtonExecute.setEnabled(false);
            jButtonChange.setEnabled(false);
            jButtonStart.setEnabled(true);
            jLabelHour.setText("" + (hour + countHour) + ":00");

            if (countHour == AMOUNT_HOUR_IN_DAY) {
                jButtonReports.setEnabled(true);
                jButtonNext.setEnabled(true);
                jButtonStart.setEnabled(false);
                jLabelInfo.setText(infoText);
                countHour = 0;
                countDay++;
            }
        }
    }
}
