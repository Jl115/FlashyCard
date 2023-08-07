package UI.MainWindowComponents;

import FolderController.SetDeckName;

import javax.swing.*;
import java.awt.*;


public class HeaderMainWindow extends JPanel implements SetDeckName {
    private static HeaderMainWindow instance ;
    private String deckName;
    private JLabel deckNameLabel = new JLabel();
    private Integer score = 0;
    private JLabel scoreLabel;
    private Boolean isTimeDecrease = false;

    private HeaderMainWindow() {
        this.setLayout(new BorderLayout());
        this.setSize(200, 200);

        deckNameLabel.setText("No Deck Selected");
        this.scoreLabel = new JLabel("Score: " + score.toString());

        this.add(deckNameLabel, BorderLayout.LINE_START);
        this.add(scoreLabel, BorderLayout.LINE_END);
    }

    public static HeaderMainWindow getInstance() {
        if (instance == null) {
            instance = new HeaderMainWindow();
        }
        return instance;
    }

    @Override
    public void setDeckName(String folderName) {
        deckName = folderName;
        deckNameLabel.setText(deckName); // Update the title text
        this.revalidate();
        this.repaint(); // Ask the system to repaint the component
    }

    public void increaseScore(int amount) {
        this.score += amount;
        System.out.println(amount);
        scoreLabel.setText("Score: " + score);
        repaint();
        revalidate();
    }

    public void decreaseScore(int amount) {
        this.score -= amount;
        System.out.println("Score: " + score);
        scoreLabel.setText("Score: " + score);
        repaint();
        revalidate();
    }

    public void setTimeDecrease(Boolean timeDecrease) {
        isTimeDecrease = timeDecrease;
        if (isTimeDecrease) {
            timeDecreaseScore();
        }
        repaint();
        revalidate();
    }

    public Integer getScore() {
        return score;
    }

    private void timeDecreaseScore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isTimeDecrease) {
                    score--;
                    if (score <= 0) {
                        score = 0;
                    }
                    System.out.println("decreased Score: " + score);
                    scoreLabel.setText("Score: " + score);
                    repaint();
                    revalidate();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



}
