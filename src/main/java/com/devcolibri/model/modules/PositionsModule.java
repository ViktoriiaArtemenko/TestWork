package com.devcolibri.model.modules;

import com.devcolibri.model.entities.PositionsEntity;

import java.util.*;

public class PositionsModule {
    private PositionsEntity positionsEntity;
    private String rate = "Ставка: ";
    private String priority = "приоритет";
    private Random random = new Random();
    private WorkingModule workingModule;

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
            rateArray[i] = String.valueOf(iteratorF.next()) + " - фиксированная ставка, " +
                    String.valueOf(iteratorH.next()) + " - почасовая ставка";
        }
        return rateArray;
    }

    public String[] getListOfPositionsAndRate() {
        String[] array = new String[positionsEntity.getPosition().size()];
        ListIterator<String> listIterator = positionsEntity.getPosition().listIterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = "<html>" + listIterator.next() + " - " + getActionArray()[i] + ".<br> " + rate +
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
            act.add(strInstruction + " (" + priority + " " + pr + "; " + price + " $)");
        }
        String[] instructionsArray = act.toArray(new String[act.size()]);
        return instructionsArray;
    }

    public boolean getFlagPositionAndInstruction(String collaborators, String tasks) {
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


        try {
            if (positionsEntity.getAction().get(position1).equals(action) || positionsEntity.getAction().get(position2).
                    equals(action)) flag = true;
        } catch (Exception ex) {
            if (positionsEntity.getAction().get(position1).equals(action)) flag = true;
        }
        return flag;
    }
}
