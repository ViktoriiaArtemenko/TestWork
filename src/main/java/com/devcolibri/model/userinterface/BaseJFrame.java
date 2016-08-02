package com.devcolibri.model.userinterface;

import com.devcolibri.model.entities.CollaboratorsEntity;
import com.devcolibri.model.entities.FreelancersEntity;
import com.devcolibri.model.entities.PositionsEntity;
import com.devcolibri.model.modules.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseJFrame extends JFrame implements ActionListener {
    protected String title = "TestWork";
    protected String textHour = "09:00";
    protected String infoText = "Выберите кто будет выполнять задание. Затем выберите одно из распоряжений";
    protected String messageDialogTitle = "Важная информация";
    protected String messageDialogPosition = "Данный сотрудник не может выполнить это распоряжение";
    protected String messageDialogPriority = "Нужно выбрать распоряжение с большим приоритетом";
    protected String messageDialogSalary = "Нужно выбрать распоряжение, которое имеет большую оплату";
    protected String fileSaved = "Файл сохранен";
    protected String timeText = "4 июля 2016 понедельник";

    boolean flagValid;
    boolean flagCollEmpty;
    boolean flagFreeEmpty;

    protected PositionsModule positionsModule;
    protected WorkingModule workingModule;
    protected WorkScheduleModule workScheduleModule;
    protected CollaboratorsEntity collaboratorsEntity;
    protected FreelancersEntity freelancersEntity;
    protected PositionsEntity positionsEntity;
    protected DataBaseModule dataBaseModule = new DataBaseModule();

    protected DefaultListModel listCollaboratorsModel = new DefaultListModel();
    protected DefaultListModel listFreelancersModel = new DefaultListModel();
    protected DefaultListModel listTasksModel = new DefaultListModel();
    protected DefaultListModel listExecuteModel = new DefaultListModel();

    protected Calendar calendar = Calendar.getInstance();
    protected Locale locale = Locale.getDefault();

    protected JPanel jPanel = new JPanel();
    protected JScrollPane jScrollPane;
    protected JViewport jViewport;

    protected JLabel jLabelTimer = new JLabel();
    protected JLabel jLabelDate = new JLabel(timeText);
    protected JLabel jLabelHour = new JLabel(textHour);
    protected JLabel jLabelTask = new JLabel();
    protected JLabel jLabelCollaborator = new JLabel();
    protected JLabel jLabelFreelancers = new JLabel();
    protected JLabel jLabelInfo = new JLabel(infoText);
    protected JLabel jLabel1 = new JLabel("Сотрудники");
    protected JLabel jLabel2 = new JLabel("Фрилансеры");
    protected JLabel jLabel3 = new JLabel("Распоряжения директора");
    protected JLabel jLabel4 = new JLabel("Работники, которые выполняют распоряжения");

    protected JButton jButtonCollaborators = new JButton("Сотрудники");
    protected JButton jButtonPositions = new JButton("Должности");
    protected JButton jButtonReports = new JButton("Отчеты");
    protected JButton jButtonNext = new JButton("Следующий день");
    protected JButton jButtonExit = new JButton("Выход");
    protected JButton jButtonStart = new JButton("Старт");
    protected JButton jButtonExecute = new JButton("Выполнить");
    protected JButton jButtonChange = new JButton("Выбрать другое распоряжение");

    protected JList<String> jListCollaborators;
    protected JList<String> jListFreelancers;
    protected JList<String> jListTasks;
    protected JList<String> jListExecute;

    protected BaseJFrame(CollaboratorsEntity collaboratorsEntity, FreelancersEntity freelancersEntity,
                         PositionsEntity positionsEntity, WorkingModule workingModule,
                         WorkScheduleModule workScheduleModule) {
        this.collaboratorsEntity = collaboratorsEntity;
        this.freelancersEntity = freelancersEntity;
        this.positionsEntity = positionsEntity;
        this.workingModule = workingModule;
        this.workScheduleModule = workScheduleModule;
        positionsModule = new PositionsModule(positionsEntity, workingModule);
        calendar.set(Calendar.DAY_OF_MONTH, TimerModule.countDay);
        calendar.set(Calendar.MONTH, 6);
    }

    protected void setButton(JButton jButton, boolean enabled, Point point, Dimension dimension, Font font,
                             Color color, String action, JPanel jPanel) {
        jButton.setFocusPainted(false);
        jButton.setEnabled(enabled);
        jButton.setSize(dimension);
        jButton.setLocation(point);
        jButton.setFont(font);
        jButton.setBackground(color);
        jButton.setActionCommand(action);
        jButton.addActionListener(this);
        jPanel.add(jButton);
    }

    protected void setList(JList<String> jList, JScrollPane jScrollPane, JViewport jViewport, boolean enabled,
                           JLabel jLabel, Color color, Dimension dimension, Point point, JPanel jPanel) {
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setEnabled(enabled);
        jList.setFocusable(false);
        jScrollPane = new JScrollPane(jList);
        jViewport = new JViewport();
        jLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        jViewport.setView(jLabel);
        jViewport.setBackground(color);
        jScrollPane.setColumnHeader(jViewport);
        jScrollPane.setBackground(color);
        jScrollPane.setSize(dimension);
        jScrollPane.setLocation(point);
        jPanel.add(jScrollPane);
    }

    protected void setLabel(JLabel jLabel, Dimension dimension, Point point,
                            Color color, Font font, JPanel jPanel) {
        jLabel.setSize(dimension);
        jLabel.setLocation(point);
        jLabel.setForeground(color);
        jLabel.setFont(font);
        jPanel.add(jLabel);
    }

    protected void setListModel(DefaultListModel listModel, String[] data) {
        if (!listModel.isEmpty()) listModel.clear();
        for (int i = 0; i < data.length; i++) {
            listModel.addElement(data[i]);
        }
    }

    protected void setListModelGroup(DefaultListModel listModel1, String[] data1, DefaultListModel listModel2,
                                     String[] data2, DefaultListModel listModel3, String[] data3, DefaultListModel
                                             listModel4, String[] data4) {
        setListModel(listModel1, data1);
        setListModel(listModel2, data2);
        setListModel(listModel3, data3);
        setListModel(listModel4, data4);
    }

    protected String[] getSortedCollaboratorsWithPositions(int date) {
        LinkedList<String> sort = new LinkedList();
        Iterator<TreeSet<Integer>> iterator = workScheduleModule.getListOfSchedules().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.next().contains(date)) {
                sort.add(workingModule.getCollaboratorsWithPositions()[i]);
            }
            i++;
        }
        String str[] = sort.toArray(new String[sort.size()]);
        return str;
    }

    protected void setDate() {
        calendar.set(Calendar.DAY_OF_MONTH, TimerModule.countDay);
        calendar.set(Calendar.MONTH, 6);
        String day = calendar.get(Calendar.DAY_OF_MONTH) + " ";
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " ";
        String year = calendar.get(Calendar.YEAR) + " ";
        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
        jLabelDate.setText(day + month + year + dayOfWeek);
    }


    protected void execute() {
        flagValid = true;
        flagCollEmpty = jListCollaborators.isSelectionEmpty();
        flagFreeEmpty = jListFreelancers.isSelectionEmpty();

        jButtonExecute.setEnabled(false);
        jButtonChange.setEnabled(false);

        if (!flagCollEmpty)
            flagValid = positionsModule.getFlagPositionAndInstruction(jListCollaborators.getSelectedValue(),
                    jListTasks.getSelectedValue(), listTasksModel);
        if (!flagFreeEmpty) {
            String value = jListTasks.getSelectedValue();
            String str = "Выполнить уборку в офисе";
            Pattern pat = Pattern.compile(str);
            Matcher mat = pat.matcher(value);
            if (mat.find()) flagValid = false;
        }
        if (!flagValid || workingModule.isFlagPriority() || workingModule.isFlagSalary()) {
            if (!flagValid) {
                JOptionPane.showMessageDialog(this, messageDialogPosition, messageDialogTitle,
                        JOptionPane.INFORMATION_MESSAGE);
                jButtonChange.setEnabled(true);
                return;
            } else if (workingModule.isFlagPriority() && !flagCollEmpty) {
                JOptionPane.showMessageDialog(this, messageDialogPriority,
                        messageDialogTitle, JOptionPane.INFORMATION_MESSAGE);
                jButtonChange.setEnabled(true);
                return;
            } else if (workingModule.isFlagSalary() && !flagCollEmpty && workingModule.isFlagPriority()) {
                JOptionPane.showMessageDialog(this, messageDialogSalary,
                        messageDialogTitle, JOptionPane.INFORMATION_MESSAGE);
                jButtonChange.setEnabled(true);
                return;
            }
        }

        if (!flagCollEmpty)
            workingModule.recordingDataColl(jListTasks.getSelectedValue(), jListCollaborators.getSelectedValue());
        if (!flagFreeEmpty)
            workingModule.recordingDataFree(jListTasks.getSelectedValue(), jListFreelancers.getSelectedValue());

        jLabelCollaborator.setText("");
        jLabelFreelancers.setText("");
        jLabelTask.setText("");

        String str = "";
        if (!flagCollEmpty) str = jListCollaborators.getSelectedValue();
        if (!flagFreeEmpty) str = jListFreelancers.getSelectedValue();
        listExecuteModel.addElement(str + " (" + jListTasks.getSelectedValue() + ")");
        int index = listExecuteModel.size() - 1;
        jListExecute.setSelectedIndex(index);
        jListExecute.ensureIndexIsVisible(index);

        if (!flagCollEmpty) {
            listCollaboratorsModel.remove(jListCollaborators.getSelectedIndex());
            jListCollaborators.clearSelection();
        }
        if (!flagFreeEmpty) {
            listFreelancersModel.remove(jListFreelancers.getSelectedIndex());
            jListFreelancers.clearSelection();
        }

        listTasksModel.remove(jListTasks.getSelectedIndex());
        jListTasks.clearSelection();
        jListTasks.setEnabled(false);
    }


    public void saveFile(LinkedList<String> text) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT", "*.*");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            ListIterator<String> listIterator = text.listIterator();
            while (listIterator.hasNext()) {
                try (FileWriter fw = new FileWriter(fc.getSelectedFile(), true)) {
                    fw.write(listIterator.next());
                    fw.write(System.lineSeparator());
                } catch (IOException e) {
                }
            }
            JOptionPane.showMessageDialog(this, fileSaved);
        }
    }
}
