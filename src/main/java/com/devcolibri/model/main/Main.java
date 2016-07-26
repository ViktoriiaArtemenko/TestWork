package com.devcolibri.model.main;

import com.devcolibri.model.modules.ModelWorking;
import com.devcolibri.model.modules.WorkSchedule;
import com.devcolibri.model.userinterface.MainJFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        final ModelWorking modelWorking = new ModelWorking();
        final WorkSchedule workSchedule = new WorkSchedule(modelWorking);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJFrame(modelWorking, workSchedule);
            }
        });
    }
}
