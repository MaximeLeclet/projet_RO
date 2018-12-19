public class Solution{

    public int [] regles;
    public int fitness;

    public Solution() {
        int [] regles;
        int fitness;
    }

    public Solution(int [] regles){
        this.regles = regles;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getRegles() {
        return regles;
    }

    public void setRegles(int[] regles) {
        this.regles = regles;
    }

    @Override
    public String toString(){
        return "" + this.fitness + "";
    }
}