/*package gui;

import data.Bloc;
import data.Program;
import data.Student;
import data.StudentComparator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class comboList extends JFrame {
    private final HashMap<String, JCheckBox> checkBoxHash = new HashMap<>();
    private final List<String> selectedBox = new ArrayList<>();
    private final Table table;
    private final JPanel checkBoxPanel;
    List<String> el;
    private JComboBox list1;

    comboList(Table table) {
        this.table = table;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        JTextField programID = new JTextField();
        JTextField programName = new JTextField();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        Object[] elements = new Object[]{initCheckBlocs(gbc)};
        list1 = new JComboBox(elements);
        Object[] message = {
                "Identifiant du programme :", programID,
                "Nom du programme :", programName,
                list1
        };

        JPanel contentPane = (JPanel)this.getContentPane();
        JOptionPane.showConfirmDialog(this, message, "Ajouter Programme", JOptionPane.OK_CANCEL_OPTION);
        contentPane.setLayout(new BorderLayout());
        el = new ArrayList<>();
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridBagLayout());
        // theses panel Button not seen in the right Layout
        JPanel panelButton = new JPanel((new FlowLayout(FlowLayout.RIGHT)));

        //initCheckBlocs(gbc);


        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setPreferredSize(new Dimension(400,450));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panelButton, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public List<String> selectedBoxList() {
            for(Map.Entry<String, JCheckBox> entry : checkBoxHash.entrySet()) {
                String blocID = entry.getKey();
                JCheckBox checkBox = entry.getValue(); {
                    if (checkBox.isSelected()) {
                        selectedBox.add(blocID);
                    }
                }
            }
            if(!selectedBox.isEmpty()) {
                table.checkBoxBlocs(selectedBox);
            }
            this.dispose();
            return selectedBox;
    }

    public JCheckBox createCheckBox(Bloc bloc) {
        String text = bloc.getID() + " - " + bloc.getName();
        return createCheckBox(bloc.getID(), text);
    }

    public JCheckBox createCheckBox(String id, String text) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setText(text);
        checkBox.setFocusable(false);
        checkBox.setFont(new Font("Consolas", Font.PLAIN, 10));
        checkBoxHash.put(id, checkBox);
        return checkBox;
    }

    public List<String> initCheckBlocs(GridBagConstraints gbc) {
        for (Map.Entry<String, Bloc> entry : table.getXmlReader().getMapBlocs().entrySet()) {
            Bloc bloc = entry.getValue();
            checkBoxPanel.add(createCheckBox(bloc), gbc);
            el.add(String.valueOf(createCheckBox(bloc)));
        }
        return el;
    }

}*/