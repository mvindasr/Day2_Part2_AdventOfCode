package Entities;

public class Subset {
    private int numberRedCubes;
    private int numberGreenCubes;
    private int numberBlueCubes;



    public Subset(int numberRedCubes, int numberGreenCubes, int numberBlueCubes) {
        this.numberRedCubes = numberRedCubes;
        this.numberGreenCubes = numberGreenCubes;
        this.numberBlueCubes = numberBlueCubes;
    }

    public int getNumberRedCubes() {
        return numberRedCubes;
    }

    public void setNumberRedCubes(int numberRedCubes) {
        this.numberRedCubes = numberRedCubes;
    }

    public int getNumberGreenCubes() {
        return numberGreenCubes;
    }

    public void setNumberGreenCubes(int numberGreenCubes) {
        this.numberGreenCubes = numberGreenCubes;
    }

    public int getNumberBlueCubes() {
        return numberBlueCubes;
    }

    public void setNumberBlueCubes(int numberBlueCubes) {
        this.numberBlueCubes = numberBlueCubes;
    }

    public boolean evaluateSubset(int pTotalRedPerParty, int pTotalGreenPerParty, int pTotalBluePerParty) {
        return this.numberRedCubes <= pTotalRedPerParty && this.numberGreenCubes <= pTotalGreenPerParty && this.numberBlueCubes <= pTotalBluePerParty;
    }
}
