package gui;

import data.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class BarMenu extends JFrame {
    Table table;
    JButton displayTree;
    Boolean isDisplayTree = true;

    public BarMenu (Table table) {
        super("MenuBar");
        this.table = table;
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setJMenuBar(createMenu());
    }

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier  ");
        JMenu menuAffichage = new JMenu("Affichage ");
        JMenu menuModification = new JMenu("Modification");

        //JMenuItem de menuFichier
        JMenuItem menuEnregistrer = new JMenuItem("Enregistrer", KeyEvent.VK_S);
        JMenuItem menuEnregistrerSous = new JMenuItem("Enregistrer sous...", KeyEvent.VK_S);
        JMenuItem menuOpen = new JMenuItem("Ouvrir...", KeyEvent.VK_S);
        JMenuItem menuCSV = new JMenuItem("Exporter en CSV");
        JMenuItem menuQuitter = new JMenuItem("Quitter");

        menuEnregistrer.addActionListener(this::MenuEnregistrerListener);
        menuEnregistrerSous.addActionListener(this::MenuEnregistrerSousListener);
        menuOpen.addActionListener(this::MenuOpenListener);
        menuCSV.addActionListener(this::MenuCSVListener);
        menuQuitter.addActionListener(this::MenuQuitterListener);

        //JMenuItem de menuAffichage
        JMenuItem menuChoisirBlocs = new JMenuItem("Choisir Blocs...");
        JMenuItem menuChoisirProgram = new JMenuItem("Choisir Programmes...");
        JMenuItem menuChoisirStudent = new JMenuItem("Choisir Étudiants...");
        JMenuItem menuTrier = new JMenuItem("Trier...");
        JCheckBoxMenuItem menuDeactivateStudent = new JCheckBoxMenuItem("Afficher les étudiant", true);

        menuChoisirBlocs.addActionListener(this::MenuChoisirBlocs);
        menuChoisirProgram.addActionListener(this::MenuChoisirProgram);
        menuChoisirStudent.addActionListener(this::MenuChoisirStudent);
        menuTrier.addActionListener(this::MenuTrierListener);
        menuDeactivateStudent.addActionListener(this::ButtonDisplayStudent);

        //JMenu Item de menuModification
        JMenuItem menuAddProgram = new JMenuItem("Ajouter un programme...");
        JMenuItem menuAddCourse = new JMenuItem("Ajouter un cours...");
        JMenuItem menuAddStudent = new JMenuItem("Ajouter un étudiant...");
        JMenuItem menuAddNote = new JMenuItem("Ajouter/Modifier une note...");
        JMenuItem menuDeletStudent = new JMenuItem("Supprimer un étudiant...");
        JMenuItem menuDeleteNote = new JMenuItem("Supprimer une note...");

        menuAddProgram.addActionListener(this::addProgram);
        menuAddCourse.addActionListener(this::addCourse);
        menuAddStudent.addActionListener(this::addStudent);
        menuAddNote.addActionListener(this::addNote);
        menuDeletStudent.addActionListener(this::deleteStudent);
        menuDeleteNote.addActionListener(this::removeNote);

        //Bouton Activer/Désactiver Arborescences
        displayTree = new JButton("Désactiver Arborescences");
        displayTree.addActionListener(this::buttonDisplayTree);


        //Quelques images pour quelques menus
        ImageIcon menuCSVIcon = new ImageIcon(getClass().getResource("images/save.png"));
        menuEnregistrer.setIcon(menuCSVIcon);
        ImageIcon menuQuitterIcon = new ImageIcon(getClass().getResource("images/exit.png"));
        menuQuitter.setIcon(menuQuitterIcon);
        ImageIcon blocIcon = new ImageIcon(getClass().getResource("images/bloc.png"));
        menuChoisirBlocs.setIcon(blocIcon);
        ImageIcon programIcon = new ImageIcon(getClass().getResource("images/program.png"));
        menuChoisirProgram.setIcon(programIcon);
        ImageIcon etudiantIcon = new ImageIcon(getClass().getResource("images/student.png"));
        menuChoisirStudent.setIcon(etudiantIcon);
        ImageIcon AjouterProgramIcon = new ImageIcon(getClass().getResource("images/addProgram.png"));
        menuAddProgram.setIcon(AjouterProgramIcon);
        ImageIcon AjouterCoursIcon = new ImageIcon(getClass().getResource("images/addCourse.png"));
        menuAddCourse.setIcon(AjouterCoursIcon);
        ImageIcon AjouterEtudiantIcon = new ImageIcon(getClass().getResource("images/addStudent.png"));
        menuAddStudent.setIcon(AjouterEtudiantIcon);
        ImageIcon ajouterNoteIcon = new ImageIcon(getClass().getResource("images/addNote.png"));
        menuAddNote.setIcon(ajouterNoteIcon);
        ImageIcon effacerEtudiantIcon = new ImageIcon(getClass().getResource("images/removeStudent.png"));
        menuDeletStudent.setIcon(effacerEtudiantIcon);
        ImageIcon effacerNote = new ImageIcon(getClass().getResource("images/removeNote.png"));
        menuDeleteNote.setIcon(effacerNote);

        //Quelques raccourcis clavier
        menuQuitter.setMnemonic(KeyEvent.VK_Q);
        menuQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
        menuEnregistrer.setMnemonic(KeyEvent.VK_S);
        menuEnregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        //Les ajouts des différents menus dans leurs menus respectif
        menuBar.add(menuFichier);
        menuBar.add(menuAffichage);
        menuBar.add(menuModification);

        menuFichier.add(menuEnregistrer);
        menuFichier.add(menuEnregistrerSous);
        menuFichier.add(menuOpen);
        menuFichier.add(menuCSV);
        menuFichier.add(menuQuitter);

        menuAffichage.add(menuChoisirBlocs);
        menuAffichage.add(menuChoisirProgram);
        menuAffichage.add(menuChoisirStudent);
        menuAffichage.add(menuTrier);
        menuAffichage.add(menuDeactivateStudent);

        menuModification.add(menuAddProgram);
        menuModification.add(menuAddCourse);
        menuModification.add(menuAddStudent);
        menuModification.add(menuAddNote);
        menuModification.add(menuDeletStudent);
        menuModification.add(menuDeleteNote);

        menuBar.add(displayTree);

        return menuBar;
    }

    private void MenuOpenListener(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            XMLReader xmlReader = new XMLReader(selectedFile.getAbsolutePath());
            table.setXmlReader(xmlReader);
            table.setListStudentFull(new ArrayList<>(xmlReader.getMapStudent().values()));
            table.buttonClickDisplay();
        }
    }

    private void MenuEnregistrerSousListener(ActionEvent event) {
        try{

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."+File.separator));
            int reponse = chooser.showDialog(chooser,"Enregistrer sous");

            // Si l'utilisateur clique sur OK
            if  (reponse == JFileChooser.APPROVE_OPTION){
                String  fichier= chooser.getSelectedFile().toString();
                ExportXML exportXML = new ExportXML(table);
                exportXML.exportXML(fichier);

            }
        }catch(HeadlessException he){
            he.printStackTrace();
        }
    }

    private void addCourse(ActionEvent event) {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField courseCredit = new JFormattedTextField(formatter);
        JTextField courseID = new JTextField();
        JTextField courseName = new JTextField();
        Object[] message = {
                "Identifiant du cours :", courseID,
                "Nom du cours :", courseName,
                "Nombre de crédit du cours :", courseCredit
        };
        int n = JOptionPane.showConfirmDialog(null, message, "Ajouter un cours", JOptionPane.OK_CANCEL_OPTION);
        if(n == JOptionPane.OK_OPTION) {
            int credit = Integer.parseInt(courseCredit.getText());
            Course course = new Course(courseName.getText(), courseID.getText(), credit);
            table.addCourse(course);
        }
    }

    private void MenuTrierListener(ActionEvent event) {
        Object[] options1 = {"OK", "Cancel" };
        boolean ascending = true;
        JPanel panel = new JPanel(new GridLayout(3,0));
        ButtonGroup groupAscending = new ButtonGroup();

        JRadioButton radioButtonDecroissant = new JRadioButton("Trier par ordre Decroissant");
        JRadioButton radioButtonCroissant = new JRadioButton("Trier par odre Croissant",true);
        groupAscending.add(radioButtonCroissant);
        groupAscending.add(radioButtonDecroissant);

        String trier[]={"Trier par identifiant","Trier par nom","Trier par programme","Trier par prenom","Trier par moyenne générale"};
        JComboBox trierComboBox =new JComboBox(trier);

        panel.add(trierComboBox);
        panel.add(radioButtonCroissant);
        panel.add(radioButtonDecroissant);

        int result = JOptionPane.showOptionDialog(null, panel, "Trier",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1 , null);
        if (result == JOptionPane.YES_OPTION){
            if(radioButtonDecroissant.isSelected()) {
                ascending = false;
            }
            String selected = (String) trierComboBox.getSelectedItem();
            if (selected.equals("Trier par identifiant")) {
                table.setComparator(StudentComparator.sortSurname(ascending));
            }
            if (selected.equals("Trier par nom")) {
                table.setComparator(StudentComparator.sortSurname(ascending));
            }
            if (selected.equals("Trier par prenom")) {
                table.setComparator(StudentComparator.sortName(ascending));
            }
            if (selected.equals("Trier par moyenne générale")) {
                table.setComparator(StudentComparator.sortMoyenneThenName(ascending));
            }
            if (selected.equals("Trier par programme")) {
                table.setComparator(StudentComparator.sortProgram(ascending));
            }
        }
    }

    private void MenuEnregistrerListener(ActionEvent event) {
        ExportXML exportXML = new ExportXML(table);
        exportXML.exportXML("src/data.xml");
    }

    private void addNote(ActionEvent event) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.setMinimumFractionDigits(-1);
        format.setMaximumFractionDigits(20);
        JFormattedTextField note = new JFormattedTextField(format);
        JTextField studentID = new JTextField();
        JTextField courseID = new JTextField();
        Object[] message = {
                "N° étudiant :", studentID,
                "Identifiant du cours:", courseID,
                "Note (Pour ABI veuillez donner -1) :", note
        };
        int n = JOptionPane.showConfirmDialog(null, message, "Ajouter une note", JOptionPane.OK_CANCEL_OPTION);
        if(n == JOptionPane.OK_OPTION) {
            if(studentID.getText().equals("") || studentID.getText().equals("") || note.getText().equals("") ) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les informations.", "ERROR"
                        , JOptionPane.ERROR_MESSAGE);
            } else {
                table.addNote(studentID.getText(), courseID.getText(), note.getText());
            }
        }
    }

    private void removeNote(ActionEvent event) {
        JTextField studentID = new JTextField();
        JTextField courseID = new JTextField();
        Object[] message = {
                "N° étudiant :", studentID,
                "Identifiant du Cours:", courseID,
        };
        int n = JOptionPane.showConfirmDialog(null, message, "Supprimer une note", JOptionPane.OK_CANCEL_OPTION);
        if(n == JOptionPane.OK_OPTION) {
            if (studentID.getText().equals("") || studentID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les informations.", "ERROR"
                        , JOptionPane.ERROR_MESSAGE);
            } else {
                table.removeNote(studentID.getText(), courseID.getText());
            }
        }
    }

    private void MenuCSVListener(ActionEvent event) {
        ExportCSV exportCSV = new ExportCSV();
        exportCSV.exportCSV(table.getData(), table.getColumnNames());
    }

    private void MenuChoisirBlocs(ActionEvent event) {
        CheckBlocs checkBlocs = new CheckBlocs(table, 0);
    }

    private void MenuChoisirProgram(ActionEvent event) {
        CheckBlocs checkBlocs = new CheckBlocs(table, 1);
    }

    private void MenuChoisirStudent(ActionEvent event) {
        CheckBlocs checkBlocs = new CheckBlocs(table, 2);
    }

    private void ButtonDisplayStudent(ActionEvent event) {
        Boolean isDisplay = !table.getDisplayStudent();
        table.setDisplayStudent(isDisplay);
        table.buttonClickDisplay();
    }

    private void buttonDisplayTree(ActionEvent event) {
        if (isDisplayTree) {
            table.getContentPane().getComponent(1).setVisible(false);
            displayTree.setText("Activer Arborescences");
        } else {
            table.getContentPane().getComponent(1).setVisible(true);
            displayTree.setText("Désactiver Arborescences");
        }
        isDisplayTree = !isDisplayTree;
        table.revalidate();
    }


    private void deleteStudent(ActionEvent event) {
        Object[] options1 = {"OK",
                "Cancel" };
        JPanel panel = new JPanel(new GridLayout(2,0));
        panel.add(new JLabel("Identifiant de l'étudiant : "));
        JTextField studentID = new JTextField();
        panel.add(studentID);
        int result = JOptionPane.showOptionDialog(null, panel, "Supprimer un étudiant",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options1, null);
        if(result == JOptionPane.YES_OPTION) {
            if (table.getXmlReader().getMapStudent().get(studentID.getText()) == null) {
                JOptionPane.showMessageDialog(this, "Aucun étudiant réfèrencé à cette Identifiant !", "Numéro étudiant INCORRECT"
                        , JOptionPane.ERROR_MESSAGE);
            } else {
                table.deleteStudent(studentID.getText());
            }
        }
    }

    private void addProgram(ActionEvent event) {
        Object[] options1 = {"OK",
                "Cancel" };

        JPanel panel = new JPanel(new GridLayout(2,0));
        panel.add(new JLabel("Nom du nouveau programme : "));
        JTextField programName = new JTextField();
        panel.add(programName);
        panel.add(new JLabel("ID du nouveau programme : "));
        JTextField programID = new JTextField();
        panel.add(programID);
        int result = JOptionPane.showOptionDialog(null, panel, "Nouveau programme",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1 , null);
        if (result == JOptionPane.YES_OPTION){
            if(programID.getText().equals("") || programName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les informations", "ERROR"
                        , JOptionPane.ERROR_MESSAGE);
            } else {
                Program program = new Program(programName.getText(), programID.getText());
                table.setNouveauProgram(program);
                CheckBlocs checkBlocs = new CheckBlocs(table, 3);
            }
        }
    }

    private void addStudent(ActionEvent event) {
        JTextField studentID = new JTextField();
        JTextField studentName = new JTextField();
        JTextField studentSurname = new JTextField();
        JTextField studentProgram = new JTextField();
        Object[] message = {
                "N° étudiant :", studentID,
                "Nom:", studentSurname,
                "Prénom :", studentName,
                "Programme :", studentProgram
        };
        int n = JOptionPane.showConfirmDialog(null, message, "Ajouter un étudiant", JOptionPane.OK_CANCEL_OPTION);
        if(n == JOptionPane.OK_OPTION) {
            Program program = table.getXmlReader().getMapProgram().get(studentProgram.getText());
            if(studentID.getText().equals("") || studentName.getText().equals("") || studentSurname.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir les informations", "ERROR"
                        , JOptionPane.ERROR_MESSAGE);
            }
            if (program == null && !(studentID.getText().equals("") || studentName.getText().equals("") || studentSurname.getText().equals(""))) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un programme existant", "ERROR"
                        , JOptionPane.ERROR_MESSAGE);
            }
            if (program != null && !(studentID.getText().equals("") || studentName.getText().equals("") || studentSurname.getText().equals(""))) {
                table.addStudent(new Student(studentID.getText(), studentName.getText(), studentSurname.getText(), program));
            }
        }
    }


    private void MenuQuitterListener(ActionEvent event) {
        System.exit(0);
    }
}