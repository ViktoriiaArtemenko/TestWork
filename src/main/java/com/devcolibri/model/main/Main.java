package com.devcolibri.model.main;

import com.devcolibri.model.entity.CollaboratorsEntity;
import com.devcolibri.model.entity.FreelancersEntity;
import com.devcolibri.model.entity.PositionsEntity;
import com.devcolibri.model.module.ModelWorking;

public class Main {
    public static void main(String[] args) {
        CollaboratorsEntity collaborators = new CollaboratorsEntity();
        FreelancersEntity freelancers = new FreelancersEntity();
        PositionsEntity positions = new PositionsEntity();
        ModelWorking modelWorking = new ModelWorking(collaborators, freelancers, positions);
    }
}
