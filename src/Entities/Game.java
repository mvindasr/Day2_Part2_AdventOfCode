package Entities;

import java.util.ArrayList;

public class Game {
    private int id;
    private final ArrayList<Subset> subsets;

    public Game(int id) {
        this.id = id;
        this.subsets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Subset> getSubsets() {
        return subsets;
    }

    public void addSubsets(Subset pSubset) {
        subsets.add(pSubset);
    }

    public int evaluateGame(int pTotalRedPerParty, int pTotalGreenPerParty, int pTotalBluePerParty) {
        boolean evaluation = true;
        for (Subset subset : subsets) {
            if (!subset.evaluateSubset(pTotalRedPerParty, pTotalGreenPerParty, pTotalBluePerParty)) {
                evaluation = false;
                break;
            }
        }
        if (evaluation) {
            return this.id;
        }
        else {
            return 0;
        }
    }

    public int getFewestNumberRedCubes () {
        int maxNumberRedCubesInSubsets = -1;
        for (Subset subset : subsets) {
            if (subset.getNumberRedCubes() > maxNumberRedCubesInSubsets) {
                maxNumberRedCubesInSubsets = subset.getNumberRedCubes();
            }
        }
        return maxNumberRedCubesInSubsets;
    }

    public int getFewestNumberGreenCubes () {
        int maxNumberGreenCubesInSubsets = -1;
        for (Subset subset : subsets) {
            if (subset.getNumberGreenCubes() > maxNumberGreenCubesInSubsets) {
                maxNumberGreenCubesInSubsets = subset.getNumberGreenCubes();
            }
        }
        return maxNumberGreenCubesInSubsets;
    }

    public int getFewestNumberBlueCubes () {
        int maxNumberBlueCubesInSubsets = -1;
        for (Subset subset : subsets) {
            if (subset.getNumberBlueCubes() > maxNumberBlueCubesInSubsets) {
                maxNumberBlueCubesInSubsets = subset.getNumberBlueCubes();
            }
        }
        return maxNumberBlueCubesInSubsets;
    }
}
