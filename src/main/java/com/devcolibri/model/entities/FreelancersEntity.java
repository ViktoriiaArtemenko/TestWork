package com.devcolibri.model.entities;

import java.util.LinkedHashMap;
import java.util.TreeSet;

public class FreelancersEntity {
    private LinkedHashMap<String, Integer> id = new LinkedHashMap();
    private TreeSet<String> name = new TreeSet();
    private TreeSet<String> salary = new TreeSet();

    public FreelancersEntity() {
    }

    public TreeSet getName() {
        return name;
    }

    public TreeSet<String> getSalary() {
        return salary;
    }

    public void setSalary(TreeSet<String> salary) {
        this.salary = salary;
    }

    public LinkedHashMap<String, Integer> getId() {
        return id;
    }

    public void setId(LinkedHashMap<String, Integer> id) {
        this.id = id;
    }
}
