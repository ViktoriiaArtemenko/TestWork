package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.DataBaseModule;
import com.devcolibri.model.modules.TimerModule;
import com.devcolibri.model.modules.WorkingModule;
import com.devcolibri.model.modules.WorkScheduleModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

public class ReportsJFrame extends BaseJFrame {
    private String title = "Отчеты";
    private String hourText = "часы";
    private String salaryText = " зарплата: ";
    private String freelancers = "Фрилансеры:";
    private String collaborators = "Сотрудники:";
    private JButton jButtonCollaborators = new JButton("Отчет работы сотрудников");
    private JButton jButtonCollaboratorsGeneric = new JButton("Отчет работы сотрудников общий");
    private JButton jButtonFreelancers = new JButton("Отчет работы фрилансеров");
    private JButton jButtonGeneral = new JButton("Общий отчет за все время");
    private String nameColl;
    private int hourColl;
    private double salaryColl;
    private LinkedList<String> resultForWeekCollaborators = new LinkedList();
    private LinkedList<String> resultForDayFreelancers = new LinkedList();

    public ReportsJFrame(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity, PositionsEntity
            positionsEntity, WorkingModule workingModule, WorkScheduleModule workScheduleModule) {
        super(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule, workScheduleModule);
        setLocation(370, 180);
        setSize(625, 250);
        setTitle(title);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setContentPane(jPanel);
        setVisible(true);
        jButtonCollaborators.setVisible(false);

        setButton(jButtonCollaborators, true, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                Font.BOLD, 25), Color.orange, "coll", jPanel);

        setButton(jButtonCollaboratorsGeneric, false, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                Font.BOLD, 25), Color.orange, "collaboratorsGeneric", jPanel);

        setButton(jButtonFreelancers, true, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                Font.BOLD, 25), Color.orange, "freelancers", jPanel);

        setButton(jButtonGeneral, false, new Point(0, 0), new Dimension(0, 0), new Font("TimesRoman",
                Font.BOLD, 25), Color.orange, "general", jPanel);

        checkDay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "coll":
                new ReportsCollaborators(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule,
                        workScheduleModule);
                break;

            case "collaboratorsGeneric":
                saveFile(resultForWeekCollaborators);
                break;

            case "freelancers":
                recordFile();
                saveFile(resultForDayFreelancers);
                break;

            case "general":
                String name;
                int id;
                int salary;
                int hourColl;
                LinkedList<String> listGeneral = new LinkedList();
                listGeneral.add(collaborators);
                LinkedList<String> list = new LinkedList(collaboratorsEntity.getId().keySet());
                ListIterator<String> listIterator = list.listIterator();
                while (listIterator.hasNext()) {
                    name = listIterator.next();
                    id = collaboratorsEntity.getId().get(name);
                    salary = dataBaseModule.readDataBaseSalary(DataBaseModule.QUERY_SELECT_COLLABORATORS_SALARY, id);
                    hourColl = dataBaseModule.readDataBaseHours(id);
                    listGeneral.add(name + ": " + salaryText + "- " + salary + ", " + hourText + "- " + hourColl);
                }


                listGeneral.add(freelancers);
                list = new LinkedList(freelancersEntity.getId().keySet());
                listIterator = list.listIterator();
                while (listIterator.hasNext()){
                    name = listIterator.next();
                    id = freelancersEntity.getId().get(name);
                    salary = dataBaseModule.readDataBaseSalary(DataBaseModule.QUERY_SELECT_FREELANCERS_SALARY, id);
                    listGeneral.add(name + ": " + salaryText + "- " + salary);
                }

                saveFile(listGeneral);
                break;
        }
    }

    private void recordFile() {
        LinkedList<String> name = new LinkedList(workingModule.getSaveFreelancersSalary().keySet());
        ListIterator<String> listIterator = name.listIterator();
        String strName;
        String resultString;
        int id = 1;
        int finalSalary;
        while (listIterator.hasNext()) {
            strName = listIterator.next();
            resultString = strName + salaryText + workingModule.getSaveFreelancersSalary().get(strName);
            resultForDayFreelancers.add(resultString);
            id = freelancersEntity.getId().get(strName);

            finalSalary = dataBaseModule.readDataBaseSalary(DataBaseModule.QUERY_SELECT_FREELANCERS_SALARY, id) +
                    workingModule.getSaveFreelancersSalary().get(strName);
            dataBaseModule.updateDataBase(DataBaseModule.QUERY_UPDATE_FREELANCERS, finalSalary, id);
        }
    }

    private void checkDay() {

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            jButtonCollaboratorsGeneric.setEnabled(true);

            LinkedList<String> nameList = new LinkedList(workingModule.getSaveCollaboratorsHour().keySet());
            ListIterator<String> listIterator = nameList.listIterator();

            String str;
            String position1;
            String position2;
            int id;
            int finalSalary;
            int finalHours;

            while (listIterator.hasNext()) {
                nameColl = listIterator.next();
                hourColl = workingModule.getSaveCollaboratorsHour().get(nameColl);
                salaryColl = workingModule.getSaveCollaboratorsSalary().get(nameColl);

                if (salaryColl == 0) {
                    position1 = workingModule.getListOfPositionsOneAndNamesMap().get(nameColl);
                    position2 = workingModule.getListOfPositionsTwoAndNamesMap().get(nameColl);

                    if (positionsEntity.getRateFix().containsKey(position1)) salaryColl = positionsEntity.getRateFix().
                            get(position1) / 4.0;

                    if (positionsEntity.getRateFix().containsKey(position1) && position2 != null && positionsEntity.
                            getRateFix().containsKey(position2))
                        salaryColl = (positionsEntity.getRateFix().get(position1) / 4.0) +
                                (positionsEntity.getRateFix().get(position2) / 4.0);
                }

                str = salaryText + salaryColl + "; часы: " + hourColl + "\n";
                resultForWeekCollaborators.add(nameColl + " - " + str);

                int salary = (int) salaryColl;
                id = collaboratorsEntity.getId().get(nameColl);

                finalSalary = dataBaseModule.readDataBaseSalary(DataBaseModule.QUERY_SELECT_COLLABORATORS_SALARY, id)
                        + salary;

                finalHours = dataBaseModule.readDataBaseHours(id) + hourColl;

                dataBaseModule.updateDataBase(DataBaseModule.QUERY_UPDATE_COLLABORATORS_SALARY, finalSalary, id);
                dataBaseModule.updateDataBase(DataBaseModule.QUERY_UPDATE_COLLABORATORS_HOUR, finalHours, id);
            }
        }

        if (TimerModule.countDay == (TimerModule.LAST_DAY_IN_MONTH + 1)) {
            jButtonGeneral.setEnabled(true);
            jButtonCollaborators.setVisible(true);
            jButtonCollaboratorsGeneric.setVisible(true);
        }
    }
}
