package com.devcolibri.model.main;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.DataBaseModule;
import com.devcolibri.model.modules.WorkingModule;
import com.devcolibri.model.modules.WorkScheduleModule;
import com.devcolibri.model.userinterface.MainJFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        DataBaseModule dataBaseModule = new DataBaseModule();

        final CollaboratorsEntity collaboratorsEntity = new CollaboratorsEntity();
        final FreelancersEntity freelancersEntity = new FreelancersEntity();
        final PositionsEntity positionsEntity = new PositionsEntity();

        dataBaseModule.readDataBaseNameId(DataBaseModule.QUERY_SELECT_COLLABORATORS, collaboratorsEntity.getId(),
                collaboratorsEntity.getName());
        dataBaseModule.readDataBaseNameId(DataBaseModule.QUERY_SELECT_FREELANCERS,freelancersEntity.getId(),
                freelancersEntity.getName());
        dataBaseModule.readDataBasePositionsTable(positionsEntity.getRateHour(), positionsEntity.getRateFix(),
                positionsEntity.getPosition(), positionsEntity.getAction());

        dataBaseModule.deleteData(DataBaseModule.QUERY_DELETE_COLLABORATORS, collaboratorsEntity.getName().size());
        dataBaseModule.deleteData(DataBaseModule.QUERY_DELETE_FREELANCERS, freelancersEntity.getName().size());

        final WorkingModule workingModule = new WorkingModule(collaboratorsEntity, freelancersEntity, positionsEntity);
        final WorkScheduleModule workScheduleModule = new WorkScheduleModule(workingModule);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJFrame(collaboratorsEntity, freelancersEntity, positionsEntity, workingModule,
                        workScheduleModule);
            }
        });
    }
}
