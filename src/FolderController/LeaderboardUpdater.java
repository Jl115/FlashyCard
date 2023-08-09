package FolderController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This class is responsible for updating the leaderboard.
 * It handles reading the existing leaderboard data, appending the new score,
 * and then overwriting the file with the updated information.
 */
public class LeaderboardUpdater {

    /**
     * Updates the leaderboard with the given score.
     *
     * @param score The score to be added to the leaderboard.
     */
    public void updateLeaderboard(Integer score) {
        // Retrieve the current date and time in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm");
        String currentDateAndTime = dateFormat.format(new Date());

        // Combine the score with the date and time
        String finalScoreLine = currentDateAndTime + " |Score: " + score.toString();

        try {
            // Path to the leaderboard file
            File root = new File("./flashyCard_DB/leaderboard.txt");

            // Read the old data if the file exists
            StringBuilder fileContent = new StringBuilder();
            if (root.exists()) {
                Scanner scanner = new Scanner(root);
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine());
                    fileContent.append("\n"); // Separate lines
                }
                scanner.close();
            }

            // Append the new score with the date and time to the existing data
            fileContent.append(finalScoreLine);

            // Overwrite the file with the updated content
            FileWriter fileWriter = new FileWriter(root);
            fileWriter.write(fileContent.toString());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace(); // Print the details of the exception
        }
    }
    /**
     * Reads the content of the leaderboard file and returns it as a string.
     *
     * @return A string containing the content of the leaderboard file, or an empty string if an error occurs.
     */
    public String readLeaderboard() {
        // Path to the leaderboard file
        File root = new File("./flashyCard_DB/leaderboard.txt");

        // Use a StringBuilder to accumulate the file content
        StringBuilder fileContent = new StringBuilder();

        try {
            if (root.exists()) {
                Scanner scanner = new Scanner(root);
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine());
                    fileContent.append("\n"); // Separate lines
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the leaderboard file");
            e.printStackTrace(); // Print the details of the exception
        }

        return fileContent.toString();
    }

}
