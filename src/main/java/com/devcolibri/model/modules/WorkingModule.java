package com.devcolibri.model.modules;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;

import java.util.*;

import static java.util.Collections.shuffle;

public class WorkingModule {
    private int amountOfCollaborators;
    private String[] listOfNameCollaboratorsArray;
    private String[] collaboratorsWithPositions;

    private boolean flagPriority = false;
    private boolean flagSalary = false;

    private String director = "Директор";
    private String manager = "Менеджер";
    private String accountant = "Бухгалтер";
    private String cleaner = "Уборщик";

    private TreeSet<String> listOfNameCollaborators;
    private LinkedList<String> listOfPositionCollaboratorsOne;
    private LinkedList<String> listOfPositionCollaboratorsTwo;

    private LinkedHashMap<String, String> listOfPositionsOneAndNamesMap;
    private LinkedHashMap<String, String> listOfPositionsTwoAndNamesMap;
    private LinkedList<String> oneAndTwoListOfPositions;

    private CollaboratorsEntity collaboratorsEntity;
    private FreelancersEntity freelancersEntity;
    private PositionsEntity positionsEntity;

    protected TreeMap<String, Integer> saveCollaboratorsHour = new TreeMap();
    protected TreeMap<String, Double> saveCollaboratorsSalary = new TreeMap();
    protected TreeMap<String, Integer> saveFreelancersSalary = new TreeMap();

    protected Calendar calendar = Calendar.getInstance();

    private Random random = new Random();

    public WorkingModule(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity,
                         PositionsEntity positionsEntity) {
        this.collaboratorsEntity = collaboratorsEntity;
        this.freelancersEntity = freelancersEntity;
        this.positionsEntity = positionsEntity;

        amountOfCollaborators = 3 + random.nextInt(98);
        listOfNameCollaborators = setListOfNameCollaborators();
        listOfNameCollaboratorsArray = listOfNameCollaborators.toArray(new String[listOfNameCollaborators.size()]);
        listOfPositionsOneAndNamesMap = new LinkedHashMap();
        listOfPositionsTwoAndNamesMap = new LinkedHashMap();
        listOfPositionCollaboratorsTwo = new LinkedList();
        setListOfPositionCollaborators();
        compareTwoListOfPosition();
        concatOneAndTwoListOfPositions();
        concatCollaboratorsWithPositions();
        setSaveCollAndSaveFree(true, true);
        concatTwoListOfPositionsAndName();

    }

    private void concatTwoListOfPositionsAndName() {
        Iterator<String> iterator = listOfNameCollaborators.iterator();
        Iterator<String> iterator1 = listOfPositionCollaboratorsTwo.iterator();
        while (iterator.hasNext()) {
            listOfPositionsTwoAndNamesMap.put(iterator.next(), iterator1.next());
        }
    }

    public void setSaveCollAndSaveFree(boolean flagColl, boolean flagFree) {
        Iterator<String> iterator = listOfNameCollaborators.iterator();
        String str;
        if (flagColl) while (iterator.hasNext()) {
            str = iterator.next();
            saveCollaboratorsHour.put(str, 0);
            saveCollaboratorsSalary.put(str, 0.0);
        }

        iterator = freelancersEntity.getName().iterator();
        if (flagFree) while (iterator.hasNext()) {
            saveFreelancersSalary.put(iterator.next(), 0);
        }
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
            listOfPositionsOneAndNamesMap.put(iterator.next(), str);
        }

        LinkedList<String> listOfPositions = new LinkedList(positionsEntity.getRateFix().keySet());
        ListIterator<String> listIterator = listOfPositions.listIterator();
        String position;

        int i = 0;
        while (listIterator.hasNext()) {
            position = listIterator.next();
            if (!listOfPositionsOneAndNamesMap.containsValue(position) &&
                    positionsEntity.getRateFix().get(position) != 0) {
                listOfPositionsOneAndNamesMap.put(name[i++], position);
            }
            if (i == name.length) break;
        }
        listOfPositionCollaboratorsOne = new LinkedList(listOfPositionsOneAndNamesMap.values());
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
            if (str1.equals(str2) || str1.equals(cleaner) || str2.equals(cleaner))
                listOfPositionCollaboratorsTwo.add("NULL");
            else
                switch (str1) {
                    case "Директор":
                        int i = random.nextInt(2);
                        if (i == 1) listOfPositionCollaboratorsTwo.add("NULL");
                        else listOfPositionCollaboratorsTwo.add(manager);
                        break;
                    case "Бухгалтер":
                        int j = random.nextInt(2);
                        if (j == 1) listOfPositionCollaboratorsTwo.add(manager);
                        else listOfPositionCollaboratorsTwo.add("NULL");
                        break;
                    case "Менеджер":
                        int k = random.nextInt(2);
                        if (k == 1) listOfPositionCollaboratorsTwo.add(accountant);
                        if (k == 0) listOfPositionCollaboratorsTwo.add(director);
                        else listOfPositionCollaboratorsTwo.add("NULL");
                        break;
                    default:
                        int count = 0;
                        for (int l = 0; l < positionsEntity.getRateFix().size(); l++) {
                            if (positionsEntity.getRateFix().get(str2) != 0) count++;
                        }
                        if (count != 0) listOfPositionCollaboratorsTwo.add("NULL");
                        else listOfPositionCollaboratorsTwo.add(str2);
                        break;
                }
        }
    }

    private void concatOneAndTwoListOfPositions() {
        LinkedList<String> linkedList = new LinkedList();
        ListIterator<String> listIteratorOne = listOfPositionCollaboratorsOne.listIterator();
        ListIterator<String> listIteratorTwo = listOfPositionCollaboratorsTwo.listIterator();
        while (listIteratorOne.hasNext()) {
            String str1 = listIteratorOne.next();
            String str2 = listIteratorTwo.next();
            if (!str2.equals("NULL"))
                linkedList.add(str1 + ", " + str2);
            else linkedList.add(str1);
        }
        oneAndTwoListOfPositions = linkedList;
    }

    private void concatCollaboratorsWithPositions() {
        String[] arrayName = listOfNameCollaboratorsArray;
        String[] arrayPos = oneAndTwoListOfPositions.toArray(new String[oneAndTwoListOfPositions.size()]);
        for (int i = 0; i < arrayName.length; i++) {
            arrayName[i] += " (" + arrayPos[i] + ")";
        }
        collaboratorsWithPositions = arrayName;
    }

    public void recordingDataColl(String tasks, String collaborators) {
        calendar.set(Calendar.DAY_OF_MONTH, TimerModule.countDay);
        calendar.set(Calendar.MONTH, 6);

        StringTokenizer stringTokenizerColl = new StringTokenizer(collaborators, "(,) ");
        StringTokenizer stringTokenizerTask = new StringTokenizer(tasks, ")(;$");

        String nameColl = stringTokenizerColl.nextToken();
        String str = stringTokenizerTask.nextToken();
        String action = str.substring(0, str.length() - 1);

        int hour = saveCollaboratorsHour.get(nameColl) + 1;
        double rate = positionsEntity.getRateHour().get(action);
        double salary = saveCollaboratorsSalary.get(nameColl) + rate;

        if (salary == 0) hour = 0;

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) ==
                Calendar.SATURDAY) salary = saveCollaboratorsSalary.get(nameColl) + rate * 2;

        saveCollaboratorsHour.put(nameColl, hour);

        saveCollaboratorsSalary.put(nameColl, salary);
    }

    public void recordingDataFree(String tasks, String nameFree) {
        StringTokenizer stringTokenizer = new StringTokenizer(tasks, ")(;$ ");
        for (int i = 0; i <= (stringTokenizer.countTokens() + 1); i++) {
            stringTokenizer.nextToken();
        }
        int s = Integer.parseInt(stringTokenizer.nextToken());
        int salary = saveFreelancersSalary.get(nameFree) + s;
        saveFreelancersSalary.put(nameFree, salary);
    }

    public TreeSet<String> getListOfNameCollaborators() {
        return listOfNameCollaborators;
    }

    public String[] getCollaboratorsWithPositions() {
        return collaboratorsWithPositions;
    }

    public LinkedList<String> getOneAndTwoListOfPositions() {
        return oneAndTwoListOfPositions;
    }

    public TreeMap<String, Integer> getSaveCollaboratorsHour() {
        return saveCollaboratorsHour;
    }

    public TreeMap<String, Integer> getSaveFreelancersSalary() {
        return saveFreelancersSalary;
    }

    public TreeMap<String, Double> getSaveCollaboratorsSalary() {
        return saveCollaboratorsSalary;
    }

    public LinkedHashMap<String, String> getListOfPositionsOneAndNamesMap() {
        return listOfPositionsOneAndNamesMap;
    }

    public LinkedHashMap<String, String> getListOfPositionsTwoAndNamesMap() {
        return listOfPositionsTwoAndNamesMap;
    }

    public boolean isFlagPriority() {
        return flagPriority;
    }

    public void setFlagPriority(boolean flagPriority) {
        this.flagPriority = flagPriority;
    }

    public boolean isFlagSalary() {
        return flagSalary;
    }

    public void setFlagSalary(boolean flagSalary) {
        this.flagSalary = flagSalary;
    }
}
