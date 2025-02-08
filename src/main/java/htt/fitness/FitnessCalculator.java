package htt.fitness;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FitnessCalculator {
    private final int[][] flowMatrix;
    private final int[][] distanceMatrix;

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
