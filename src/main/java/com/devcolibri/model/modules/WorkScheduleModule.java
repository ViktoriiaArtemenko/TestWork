package com.devcolibri.model.modules;

import java.util.*;

import static java.util.Collections.shuffle;

public class WorkScheduleModule {
    private WorkingModule workingModule;
    private LinkedHashMap<String, TreeSet<Integer>> listCollaboratorsSchedule = new LinkedHashMap();
    private LinkedHashSet<TreeSet<Integer>> listOfSchedules;
    private Calendar calendar = Calendar.getInstance();

    public WorkScheduleModule(WorkingModule workingModule) {
        this.workingModule = workingModule;
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, 6);
        setWorkScheduleOfCollaborator();
        listOfSchedules = new LinkedHashSet(listCollaboratorsSchedule.values());
    }

    private Integer[] getWorkScheduleSevenDay() {
        int day = 7, dayWork = 5;
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 1; i <= day; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            arrayList.add(calendar.get(Calendar.DAY_OF_MONTH));
        }
        shuffle(arrayList);
        ListIterator<Integer> listIterator = arrayList.listIterator();
        Integer[] array = new Integer[dayWork];
        for (int i = 0; i < dayWork; i++) {
            array[i] = listIterator.next();
        }
        return array;
    }

    private TreeSet<Integer> getWorkScheduleMonth() {
        int week = 4;
        Integer mas[] = getWorkScheduleSevenDay();
        TreeSet<Integer> treeSet = new TreeSet();
        for (int i = 0; i < week; i++) {
            for (int j = 0; j < mas.length; j++) {
                treeSet.add(mas[j]);
            }
            mas = getWorkScheduleSevenDay();
        }
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, 6);
        return treeSet;
    }

    private void setWorkScheduleOfCollaborator() {
        Iterator<String> iterator = workingModule.getListOfNameCollaborators().iterator();
        while (iterator.hasNext()){
            listCollaboratorsSchedule.put(iterator.next(), getWorkScheduleMonth());
        }
    }

    public LinkedHashSet<TreeSet<Integer>> getListOfSchedules() {
        return listOfSchedules;
    }
}
