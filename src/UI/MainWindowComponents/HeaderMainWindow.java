package UI.MainWindowComponents;

import FolderController.SetDeckName;
import javax.swing.*;
import java.awt.*;

/**
 * HeaderMainWindow class represents the header of the main window.
 * It displays the selected deck name and the current score.
 * Implements SetDeckName to update the deck name.
 * Uses Singleton pattern to ensure only one instance.
 */
public class HeaderMainWindow extends JPanel implements SetDeckName {
    private static HeaderMainWindow instance;
    private final JLabel deckNameLabel = new JLabel();
    private Integer score = 0;
    private final JLabel scoreLabel = new JLabel("Score: " + score + "   ");
    private Boolean isTimeDecrease = false;

    private HeaderMainWindow() {
        this.setLayout(new BorderLayout());
        this.setSize(200, 200);

        deckNameLabel.setText("   No Deck Selected");
        deckNameLabel.setFont(getFont().deriveFont(Font.ITALIC, 18));

        scoreLabel.setFont(getFont().deriveFont(Font.ITALIC, 18));

        this.add(deckNameLabel, BorderLayout.LINE_START);
        this.add(scoreLabel, BorderLayout.LINE_END);
    }

    /**
     * Singleton pattern to get the HeaderMainWindow instance.
     *
     * @return the unique instance of HeaderMainWindow.
     */
    public static HeaderMainWindow getInstance() {
        if (instance == null) {
            instance = new HeaderMainWindow();
        }
        return instance;
    }

    /**
     * Sets the deck name label to the provided folderName.
     * Implements the method from SetDeckName interface.
     *
     * @param folderName Name of the selected deck.
     */
    @Override
    public void setDeckName(String folderName) {
        deckNameLabel.setText("   " + folderName);
        deckNameLabel.setFont(getFont().deriveFont(Font.ITALIC, 18));
        this.revalidate();
        this.repaint();
    }

    /**
     * Increases the score by the given amount.
     *
     * @param amount Amount to increase the score by.
     */
    public void increaseScore(int amount) {
        this.score += amount;
        updateScoreDisplay();
    }

    /**
     * Decreases the score by the given amount.
     *
     * @param amount Amount to decrease the score by.
     */
    public void decreaseScore(int amount) {
        this.score -= amount;
        updateScoreDisplay();
    }

    /**
     * Sets the time decrease for the score.
     * Starts a thread to decrease score over time if true.
     *
     * @param timeDecrease Boolean flag to control time-based score decrease.
     */
    public void setTimeDecrease(Boolean timeDecrease) {
        isTimeDecrease = timeDecrease;
        if (isTimeDecrease) {
            timeDecreaseScore();
        }
        repaint();
        revalidate();
    }

    /**
     * @return The current score.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Updates the score display on the label.
     */
    private void updateScoreDisplay() {
        scoreLabel.setText("Score: " + score + "   ");
        scoreLabel.setFont(getFont().deriveFont(Font.ITALIC, 18));
        repaint();
        revalidate();
    }

    /**
     * Decreases the score over time. Controlled by a separate thread.
     */
    private void timeDecreaseScore() {
        new Thread(() -> {
            while (isTimeDecrease) {
                score--;
                if (score <= 0) score = 0;
                updateScoreDisplay();
                try {
                    Thread.sleep(1400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
