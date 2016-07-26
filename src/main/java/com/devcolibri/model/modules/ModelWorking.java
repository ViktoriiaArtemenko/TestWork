package com.devcolibri.model.modules;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.PositionsEntity;

import java.util.*;

import static java.util.Collections.shuffle;

public class ModelWorking {
    private int amountOfCollaborators;
    private String[] listOfNameCollaboratorsArray;
    private String[] listOfPositionCollaboratorsOneArray;
    private String[] listOfPositionCollaboratorsTwoArray;

    private TreeSet<String> listOfNameCollaborators;
    private LinkedList<String> listOfPositionCollaboratorsOne;
    private LinkedList<String> listOfPositionCollaboratorsTwo;
    private LinkedHashMap<String, String> listMap;

    private CollaboratorsEntity collaboratorsEntity = new CollaboratorsEntity();
    private PositionsEntity positionsEntity = new PositionsEntity();

    private Random random = new Random();

    public ModelWorking() {
        amountOfCollaborators = 3 + random.nextInt(97);
        listOfNameCollaborators = setListOfNameCollaborators();
        listOfNameCollaboratorsArray = listOfNameCollaborators.toArray(new String[listOfNameCollaborators.size()]);
        listMap = new LinkedHashMap();
        listOfPositionCollaboratorsTwo = new LinkedList();
        setListOfPositionCollaborators();
        compareTwoListOfPosition();
        System.out.println("ModelWorking");
    }

    private TreeSet<String> setListOfNameCollaborators() {
        TreeSet<String> name = new TreeSet();
        Iterator<String> iterator = collaboratorsEntity.getName().iterator();
        for (int i = 0; i < amountOfCollaborators; i++) {
            name.add(iterator.next());
        }
        return name;
    }

    private void setListOfPositionCollaborators() {
        String pos[] = new String[positionsEntity.getPosition().size()];
        pos = positionsEntity.getPosition().toArray(pos);

        String name[] = new String[listOfNameCollaborators.size()];
        name = listOfNameCollaborators.toArray(name);

        Iterator<String> iterator = listOfNameCollaborators.iterator();
        String str;
        while (iterator.hasNext()) {
            str = pos[random.nextInt(positionsEntity.getPosition().size())];
            if (str.equals("Директор")) continue;
            listMap.put(iterator.next(), str);
        }

        int i = 0;
        listMap.put(name[i], "Директор");
        if (!listMap.containsValue("Бухгалтер")) {
            listMap.put(name[++i], "Бухгалтер");
        }
        if (!listMap.containsValue("Менеджер")) {
            listMap.put(name[++i], "Менеджер");
        }
        listOfPositionCollaboratorsOne = new LinkedList(listMap.values());
        listOfPositionCollaboratorsOneArray = listOfPositionCollaboratorsOne.
                toArray(new String[listOfPositionCollaboratorsOne.size()]);
    }

    private void compareTwoListOfPosition() {
        LinkedList<String> posShuffle = new LinkedList(listOfPositionCollaboratorsOne);
        shuffle(posShuffle);
        ListIterator iteratorOneList = listOfPositionCollaboratorsOne.listIterator();
        ListIterator iteratorShuffle = posShuffle.listIterator();
        String str1;
        String str2;
        while (iteratorOneList.hasNext()) {
            str1 = iteratorOneList.next().toString();
            str2 = iteratorShuffle.next().toString();
            if (str1.equals(str2)) listOfPositionCollaboratorsTwo.add("NULL");
            else
                switch (str1) {
                    case "Директор":
                        int i = random.nextInt(2);
                        if (i == 1) listOfPositionCollaboratorsTwo.add("NULL");
                        else listOfPositionCollaboratorsTwo.add("Менеджер");
                        break;
                    case "Уборщик":
                        listOfPositionCollaboratorsTwo.add("NULL");
                        break;
                    case "Бухгалтер":
                        int j = random.nextInt(2);
                        if (j == 1) listOfPositionCollaboratorsTwo.add("Менеджер");
                        else listOfPositionCollaboratorsTwo.add("NULL");
                        break;
                    case "Менеджер":
                        int k = random.nextInt(2);
                        if (k == 1) listOfPositionCollaboratorsTwo.add("Бухгалтер");
                        else listOfPositionCollaboratorsTwo.add("NULL");
                        break;
                    default:
                        switch (str2) {
                            case "Директор":
                                listOfPositionCollaboratorsTwo.add("NULL");
                                break;
                            case "Уборщик":
                                listOfPositionCollaboratorsTwo.add("NULL");
                                break;
                            case "Бухгалтер":
                                listOfPositionCollaboratorsTwo.add("NULL");
                                break;
                            case "Менеджер":
                                listOfPositionCollaboratorsTwo.add("NULL");
                                break;
                            default:
                                listOfPositionCollaboratorsTwo.add(str2);
                                break;
                        }
                        break;
                }
        }
        listOfPositionCollaboratorsTwoArray = listOfPositionCollaboratorsTwo.
                toArray(new String[listOfPositionCollaboratorsTwo.size()]);
    }

    public int getAmountOfCollaborators() {
        return amountOfCollaborators;
    }

    public String[] getListOfNameCollaboratorsArray() {
        return listOfNameCollaboratorsArray;
    }

    public String[] getListOfPositionCollaboratorsOneArray() {
        return listOfPositionCollaboratorsOneArray;
    }

    public String[] getListOfPositionCollaboratorsTwoArray() {
        return listOfPositionCollaboratorsTwoArray;
    }

    public TreeSet<String> getListOfNameCollaborators() {
        return listOfNameCollaborators;
    }

    public LinkedList<String> getListOfPositionCollaboratorsOne() {
        return listOfPositionCollaboratorsOne;
    }

    public LinkedList<String> getListOfPositionCollaboratorsTwo() {
        return listOfPositionCollaboratorsTwo;
    }
}
