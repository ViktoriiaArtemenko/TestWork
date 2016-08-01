package com.devcolibri.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PositionsEntity {
    private LinkedHashMap<String, Integer> rateHour = new LinkedHashMap();
    private LinkedHashMap<String, Integer> rateFix = new LinkedHashMap();
    private ArrayList<String> position = new ArrayList();
    private HashMap<String, String> action = new HashMap();

    public PositionsEntity() {
    }

    public LinkedHashMap<String, Integer> getRateHour() {
        return rateHour;
    }

    public LinkedHashMap<String, Integer> getRateFix() {
        return rateFix;
    }

    public ArrayList<String> getPosition() {
        return position;
    }

    public HashMap<String, String> getAction() {
        return action;
    }
}
