package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCardPanel extends JPanel implements ActionListener {

    private static AddCardPanel instance;

    public static AddCardPanel getInstance() {
        if (instance == null) {
            instance = new AddCardPanel();
        }
        return instance;
    }

    private AddCardPanel() {
        this.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;

        JLabel headerTitleLabel = new JLabel();
        headerTitleLabel.setFont(getFont().deriveFont(Font.BOLD, 12));
        headerTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerTitleLabel.setText("Enter the Card-name");

        JTextField titleTextField = new JTextField();

        // Create constraints for components
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Specify that components should fill the space both horizontally
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Add padding around components
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);

        gridBagConstraints.gridx = 0; // Specify the column in grid for button
        gridBagConstraints.gridy = 0; // Specify the row in grid for button
        gridBagConstraints.gridheight = 1; // Specify the height in grid terms
        gridBagConstraints.gridwidth = 1; // Specify the width in grid terms

        headerPanel.add(headerTitleLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        headerPanel.add(titleTextField, gridBagConstraints);

        JLabel cardFrontLabel = new JLabel();
        cardFrontLabel.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardFrontLabel.setHorizontalAlignment(JLabel.CENTER);
        cardFrontLabel.setText("A Question for the Card-front:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        headerPanel.add(cardFrontLabel, gridBagConstraints);

        JTextArea cardFrontTextArea = new JTextArea();
        cardFrontTextArea.setRows(5);
        cardFrontTextArea.setColumns(20);
        cardFrontTextArea.setLineWrap(true);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        headerPanel.add(cardFrontTextArea, gridBagConstraints);

        JLabel cardBackLabel = new JLabel();
        cardBackLabel.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardBackLabel.setHorizontalAlignment(JLabel.CENTER);
        cardBackLabel.setText("Enter the Correct Answer:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        headerPanel.add(cardBackLabel, gridBagConstraints);

        JTextField rightAnswerField = new JTextField();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        headerPanel.add(rightAnswerField, gridBagConstraints);

        JLabel cardBackWrongLabelA = new JLabel();
        cardBackWrongLabelA.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardBackWrongLabelA.setHorizontalAlignment(JLabel.CENTER);
        cardBackWrongLabelA.setText("Enter a Wrong Answer 1:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        headerPanel.add(cardBackWrongLabelA, gridBagConstraints);

        JTextField wrongAnswerFieldA = new JTextField();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        headerPanel.add(wrongAnswerFieldA, gridBagConstraints);

        JLabel cardBackWrongLabelB = new JLabel();
        cardBackWrongLabelB.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardBackWrongLabelB.setHorizontalAlignment(JLabel.CENTER);
        cardBackWrongLabelB.setText("Enter a Wrong Answer 2:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        headerPanel.add(cardBackWrongLabelB, gridBagConstraints);

        JTextField wrongAnswerFielB = new JTextField();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        headerPanel.add(wrongAnswerFielB, gridBagConstraints);



        JButton addButton = new JButton();
        addButton.setText("Add Card");
        addButton.setSize(100, 40);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        headerPanel.add(addButton, gridBagConstraints);

        contentPanel.add(headerPanel, c);  // add headerPanel to contentPanel
        this.add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
