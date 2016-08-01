package com.devcolibri.model.entities;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CollaboratorsEntity {
    private LinkedHashMap<String, Integer> id = new LinkedHashMap();
    private HashSet<String> name = new HashSet();
    private LinkedList<Integer> salary = new LinkedList();
    private LinkedList<Integer> hour = new LinkedList();

    public CollaboratorsEntity() {
    }

    public HashSet getName() {
        return name;
    }

    public LinkedList<Integer> getSalary() {
        return salary;
    }

    public void setSalary(LinkedList<Integer> salary) {
        this.salary = salary;
    }

    public LinkedList<Integer> getHour() {
        return hour;
    }

    public void setHour(LinkedList<Integer> hour) {
        this.hour = hour;
    }

    public LinkedHashMap<String, Integer> getId() {
        return id;
    }

    public void setId(LinkedHashMap<String, Integer> id) {
        this.id = id;
    }
}
