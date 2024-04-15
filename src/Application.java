import Entities.Game;
import Entities.Subset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Application {
    static PrintStream out = System.out;

    // Local Game Party FilePath
    static final String partyFilePath = "src/games/input.txt";

    static final int totalRedCubes = 12;
    static final int totalGreenCubes = 13;
    static final int totalBlueCubes = 14;

    static ArrayList<Game> games = new ArrayList<>();

    public static void main(String[] args) {
        int sumPowerSets = 0;
        out.println("-------------------------------------------------------------");
        out.println("           Elves' Cubes Game - Color Challenge         ");
        out.println("-------------------------------------------------------------");

        out.println("Reading provided input file...");

        // Add game lines in text format into a Game array to process...
        createGamesArrayList();

        // Calculate the sum of power sets

        out.println("Calculating sum of power of sets...");
        for (Game game : games) {
            int subsetPower= calculateSubsetsPower(game);
            sumPowerSets = sumPowerSets + subsetPower;
        }

        out.println("***************************************************");
        out.println("           Sum of subset powers: " + sumPowerSets);
        out.println("***************************************************");
    }

    /**
     * Procedure that process the .txt game file and add all games into an ArrayList
     */
    static void createGamesArrayList () {
        try (BufferedReader br = new BufferedReader(new FileReader(partyFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Game game = processGameLine(line);
                games.add(game);
            }
            out.println("Cube Party uploaded!");
        } catch (IOException e) {
            out.println("Error uploading party info!");
        }
    }


    /**
     * Function that returns a game object created with raw information of the String line
     * @param pLine The line of the game and its subsets
     * @return A game object with the info detailed in the line
     */
    static Game processGameLine(String pLine) {
        // Separate game info part from subsets part...
        String[] parts = pLine.split(":");

        // Remove the "Game " string from game info part to get the game ID and create a game object...
        String gameIdStr = parts[0].trim().replace("Game ", "");
        int gameId = Integer.parseInt(gameIdStr);
        Game game = new Game(gameId);

        // Split the subsets part to get the individual subsets strings...
        String[] subsetsStr = parts[1].split(";");


        // Process each subset string to get the subset object...
        for (String subsetStr : subsetsStr) {
            Subset subset = processSubsetPart(subsetStr.trim());
            if (subset != null) {
                game.addSubsets(subset);
            }
        }

        return game;
    }

    /**
     * Function that returns a subset object containing the info passed in the subset String
     * @param pSubsetString the subset String
     * @return The subset object
     */
    static Subset processSubsetPart(String pSubsetString) {

        // Get the cubes strings from the subset...
        String[] cubeStrs = pSubsetString.split(",");
        int redCubes = 0;
        int greenCubes = 0;
        int blueCubes = 0;

        // Remove leading/trailing spaces from cubes strings and obtain the quantity per color...
        for (String cubeStr : cubeStrs) {
            cubeStr = cubeStr.trim();
            String[] parts = cubeStr.split("\\s+");

            int quantity = Integer.parseInt(parts[0]);
            String color = parts[1].toLowerCase();

            switch (color) {
                case "red":
                    redCubes += quantity;
                    break;
                case "green":
                    greenCubes += quantity;
                    break;
                case "blue":
                    blueCubes += quantity;
                    break;
                default:
                    return null;
            }
        }

        return new Subset(redCubes, greenCubes, blueCubes);
    }

    /**
     * Function that calculates the subsets power for a game (the multiplication of the fewest number of cubes per color
     * that makes the subsets possible)
     * @param pGame The game under evaluation
     * @return An integer with the calculated subsets power
     */
    static int calculateSubsetsPower (Game pGame) {
        int minNumberRedCubes = pGame.getFewestNumberRedCubes();
        int minNumberGreenCubes = pGame.getFewestNumberGreenCubes();
        int minNumberBlueCubes = pGame.getFewestNumberBlueCubes();
        return minNumberRedCubes * minNumberGreenCubes * minNumberBlueCubes;
    }
}
