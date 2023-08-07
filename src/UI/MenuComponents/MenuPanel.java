package UI.MenuComponents;

import UI.CustomComponents.CustomTextArea;

import javax.swing.*;
import java.awt.*;

/**
 * MenuPanel class extends JPanel and represents the main menu of the application.
 * It includes panels for high scores and a tutorial section.
 * Utilizes the Singleton pattern to ensure only one instance.
 */
public class MenuPanel extends JPanel {
    private static MenuPanel instance; // Singleton instance

    // Tutorial text block to guide the user through the application
    private final String tutorialText = """
            Hi, welcome to my Flashcard Application. I developed this over the course of 3 weeks. If you notice any bugs, please contact me, and I will do my best to fix them. The same goes for new feature requests. :)

            The E-mail for reaching out to me is: joelbern006+projectF@gmail.com



            How it works:

            First, go to 'Select Deck' and create a new deck, this will create your first deck.

            Next, you'll need to add cards. You can give them a name and provide 3 answers, 1 of which is the correct one, while the other 2 are false. Please be sure to put the correct answer in the right field, otherwise, you'll always lose.Save the cards once you're done.

            After that, you can start a new game by clicking on 'Play!' Then, choose the correct answer. You'll earn 10 points if the answer is correct. If the answer is false, you'll lose 2 points, and over time, you'll lose more.

            You can stop the timer if you get the right answer! Good luck, smart soldier!☃︎""";

    private MenuPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Creation of a new Score Panel
        JPanel scorePanel = new JPanel();
        JPanel scoreLabelPanel = new JPanel();
        scoreLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        scoreLabelPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.LIGHT_GRAY));
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.LIGHT_GRAY));
        JLabel scoreLabel = new JLabel("     Highscore     ");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(getFont().deriveFont(Font.ITALIC, 20));
        scoreLabelPanel.add(scoreLabel);
        scorePanel.add(Box.createVerticalStrut(10)); // Space between elements
        scorePanel.add(scoreLabelPanel);

        JLabel testLabel = new JLabel("Test");
        testLabel.setHorizontalAlignment(JLabel.CENTER);
        scorePanel.add(testLabel);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 2;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(scorePanel, constraints);

        // Creation of a Content Panel with BoxLayout
        JPanel contentPanel = new JPanel();
        JPanel menuLabelPanel = new JPanel();
        menuLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        menuLabelPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.LIGHT_GRAY));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        menuLabel.setFont(getFont().deriveFont(Font.ITALIC, 20));

        menuLabelPanel.add(menuLabel);
        contentPanel.add(Box.createVerticalStrut(10)); // Space between elements
        contentPanel.add(menuLabelPanel);

        CustomTextArea descriptionTArea = new CustomTextArea();
        descriptionTArea.setText(tutorialText);
        contentPanel.add(Box.createVerticalStrut(20)); // Space between elements
        contentPanel.add(descriptionTArea);
        contentPanel.add(Box.createVerticalStrut(40)); // Space between elements

        constraints.gridx = 0;
        constraints.weightx = 3;
        this.add(contentPanel, constraints);
    }

    /**
     * Singleton pattern applied to get the MenuPanel instance.
     *
     * @return the unique instance of MenuPanel.
     */
    public static MenuPanel getInstance() {
        if (instance == null) {
            instance = new MenuPanel();
        }
        return instance;
    }
}
