package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Fenetre extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, helpMenu;
    JMenuItem newItem, saveItem, exitItem, undoItem, redoItem, copyItem, cutItem, pasteItem;
    ImageIcon newIcon, saveIcon, exitIcon, undoIcon, redoIcon, copyIcon, cutIcon, pasteIcon;
    JTextField textField;
    JButton search;

    public Fenetre() {
        //JFileChooser fc = new JFileChooser();
        JFrame frame = new JFrame("ACCUEIL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 600);

        // create the menu bar
        menuBar = new JMenuBar();

        // create the menu element
        fileMenu = new JMenu("Fichier");
        editMenu = new JMenu("Modification");
        helpMenu = new JMenu("Aide");

        // create the sub menu for fileMenu
        newItem = new JMenuItem("Nouveau");
        saveItem = new JMenuItem("Sauvegarder");
        exitItem = new JMenuItem("Quitter");

        // create the sub menu for editMenu
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        copyItem = new JMenuItem("Copy");
        cutItem = new JMenuItem("Cut");
        pasteItem = new JMenuItem("Paste");

        // adding the icon for the file sub menu
        newIcon = new ImageIcon(getClass().getResource("images/new.png"));
        saveIcon = new ImageIcon(getClass().getResource("images/save.png"));
        exitIcon = new ImageIcon(getClass().getResource("images/exit.png"));

        // adding the icon for the edit sub menu
        undoIcon = new ImageIcon(getClass().getResource("images/undo.png"));
        redoIcon = new ImageIcon(getClass().getResource("images/redo.png"));
        copyIcon = new ImageIcon(getClass().getResource("images/copy.png"));
        cutIcon = new ImageIcon(getClass().getResource("images/cut.png"));
        pasteIcon = new ImageIcon(getClass().getResource("images/paste.png"));

        // set the icon
        newItem.setIcon(newIcon);
        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);
        undoItem.setIcon(undoIcon);
        redoItem.setIcon(redoIcon);
        copyItem.setIcon(copyIcon);
        cutItem.setIcon(cutIcon);
        pasteItem.setIcon(pasteIcon);

        // adding the menu in the menu bar
        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // adding the submenu item in fileMenu
        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        //adding the submenu item in editMenu
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.add(copyItem);
        editMenu.add(cutItem);
        editMenu.add(pasteItem);

        // adding the action listener
        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        // set Mnemonic key
        newItem.setMnemonic(KeyEvent.VK_N); // N
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        saveItem.setMnemonic(KeyEvent.VK_S); // S
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        exitItem.setMnemonic(KeyEvent.VK_Q); // Q
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
        copyItem.setMnemonic(KeyEvent.VK_C); // N
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        cutItem.setMnemonic(KeyEvent.VK_X); // S
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        pasteItem.setMnemonic(KeyEvent.VK_V); // Q
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        fileMenu.setMnemonic(KeyEvent.VK_F); // Alt + F
        editMenu.setMnemonic(KeyEvent.VK_M); // Alt + M
        helpMenu.setMnemonic(KeyEvent.VK_A); // Alt + A

        menuBar.add(Box.createHorizontalGlue());

        textField = new JTextField();
        textField.setSize(20, 20);
        search = new JButton("Recherche");

        menuBar.add(textField);
        menuBar.add(search);


        JPanel panel = new JPanel();
        frame.setVisible(true);
        JLabel studentData = new JLabel("CSV File");
        panel.add(studentData);


        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            System.out.println("New Item");
        } else if (e.getSource() == saveItem) {
            System.out.println("Item saved");
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }
}
