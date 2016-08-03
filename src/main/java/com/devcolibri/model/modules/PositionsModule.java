package com.devcolibri.model.modules;

import com.devcolibri.model.entities.PositionsEntity;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionsModule {
    private PositionsEntity positionsEntity;
    private String rateText = "Ставка: ";
    private String priorityText = "приоритет";
    private String fixRateText = " - фиксированная ставка, ";
    private String hourRateText = " - почасовая ставка";
    private Random random = new Random();
    private WorkingModule workingModule;
    private int priority1;
    private int salary1;
    private int priority;
    private int salary;
    private int countPriority;
    private int countSalary;


    public PositionsModule(PositionsEntity positionsEntity, WorkingModule workingModule) {
        this.positionsEntity = positionsEntity;
        this.workingModule = workingModule;
    }

    public String[] getRateArray() {
        String[] rateArray;
        LinkedList<Integer> listH = new LinkedList(positionsEntity.getRateHour().values());
        LinkedList<Integer> listF = new LinkedList(positionsEntity.getRateFix().values());
        ListIterator<Integer> iteratorH = listH.listIterator();
        ListIterator<Integer> iteratorF = listF.listIterator();
        rateArray = new String[positionsEntity.getRateFix().size()];
        for (int i = 0; i < rateArray.length; i++) {
            rateArray[i] = String.valueOf(iteratorF.next()) + fixRateText +
                    String.valueOf(iteratorH.next()) + hourRateText;
        }
        return rateArray;
    }

    public String[] getListOfPositionsAndRate() {
        String[] array = new String[positionsEntity.getPosition().size()];
        ListIterator<String> listIterator = positionsEntity.getPosition().listIterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = "<html>" + listIterator.next() + " - " + getActionArray()[i] + ".<br> " + rateText +
                    getRateArray()[i] + "<br><br><html>";
        }
        return array;
    }

    public String[] getActionArray() {
        String[] actionArray;
        LinkedList<String> linkedList = new LinkedList(positionsEntity.getAction().values());
        actionArray = linkedList.toArray(new String[positionsEntity.getAction().size()]);
        return actionArray;
    }

    public String[] getInstructions() {
        ArrayList<String> arrayList = new ArrayList(positionsEntity.getAction().values());
        arrayList.addAll(arrayList);
        arrayList.addAll(arrayList);
        ArrayList<String> act = new ArrayList();
        ListIterator<String> listIterator = arrayList.listIterator();
        String strInstruction;
        int pr, price;
        while (listIterator.hasNext()) {
            strInstruction = listIterator.next();
            pr = 1 + random.nextInt(6);
            price = 50 + random.nextInt(200);
            act.add(strInstruction + " (" + priorityText + " " + pr + "; " + price + " $)");
        }
        String[] instructionsArray = act.toArray(new String[act.size()]);
        return instructionsArray;
    }

    public boolean getFlagPositionAndInstruction(String collaborators, String tasks, DefaultListModel<String>
            listTasksModel) {
        boolean flag = false;
        String position1, position2;
        StringTokenizer stringTokenizer1 = new StringTokenizer(collaborators, "(,) ");
        stringTokenizer1.nextToken();
        position1 = stringTokenizer1.nextToken();
        if (stringTokenizer1.hasMoreTokens()) position2 = stringTokenizer1.nextToken();
        else position2 = "null";

        StringTokenizer stringTokenizer2 = new StringTokenizer(tasks, ")(;$");
        String str = stringTokenizer2.nextToken();
        String action = str.substring(0, str.length() - 1);

        checkPrioritySalary(position1, position2, tasks, listTasksModel);

        try {
            if (positionsEntity.getAction().get(position1).equals(action) || positionsEntity.getAction().get(position2).
                    equals(action)) flag = true;
        } catch (Exception ex) {
            if (positionsEntity.getAction().get(position1).equals(action)) flag = true;
        }
        return flag;
    }

    public void checkPrioritySalary(String position1, String position2, String tasks, DefaultListModel<String>
            listTasksModel) {
        countPriority = 0;
        countSalary = 0;
        String action1, action2;
        Pattern pat;
        Matcher mat;
        StringTokenizer stringTokenizer = new StringTokenizer(tasks, ")(;$");
        stringTokenizer.nextElement();

        workingModule.setFlagPriority(false);
        workingModule.setFlagSalary(false);

        String str = stringTokenizer.nextToken();
        str = str.substring(10, 11);
        priority1 = Integer.parseInt(str);

        str = stringTokenizer.nextToken();
        str = str.substring(1, str.length() - 1);
        salary1 = Integer.parseInt(str);

        action1 = positionsEntity.getAction().get(position1);
        action2 = positionsEntity.getAction().get(position2);

        LinkedList<String> linkedList = new LinkedList();
        for (int i = 0; i < listTasksModel.size(); i++) {
            linkedList.add(listTasksModel.getElementAt(i));
        }

        ListIterator<String> listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            str = listIterator.next();

            stringTokenizer = new StringTokenizer(str, ")(;$");
            stringTokenizer.nextElement();

            String str1 = stringTokenizer.nextToken();
            str1 = str1.substring(10, 11);
            priority = Integer.parseInt(str1);

            str1 = stringTokenizer.nextToken();
            str1 = str1.substring(1, str1.length() - 1);
            salary = Integer.parseInt(str1);

            pat = Pattern.compile(action1);
            mat = pat.matcher(str);
            comparePriorityAndSalary(mat);

            if (action2 != null) {
                pat = Pattern.compile(action2);
                mat = pat.matcher(str);
                comparePriorityAndSalary(mat);
            }
        }

        if (countPriority != 0) workingModule.setFlagPriority(true);
        if (countSalary != 0) workingModule.setFlagSalary(true);
    }

    public void comparePriorityAndSalary(Matcher mat) {
        if (mat.find()) {
            if (priority1 < priority) countPriority++;
            else if (salary1 < salary) countSalary++;
        }
    }

}
