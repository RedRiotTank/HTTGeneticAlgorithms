package htt;

public class FitnessCalculator {
    private final int[][] flowMatrix;
    private final int[][] distanceMatrix;

    public FitnessCalculator(int[][] flowMatrix, int[][] distanceMatrix) {
        this.flowMatrix = flowMatrix;
        this.distanceMatrix = distanceMatrix;
    }

    public int calculate(int[] solution) {
        int cost = 0;
        for (int i = 0; i < flowMatrix.length; i++) {
            for (int j = 0; j < flowMatrix.length; j++) {
                cost += flowMatrix[i][j] * distanceMatrix[solution[i]][solution[j]];
            }
        }
        return cost;
    }
}
