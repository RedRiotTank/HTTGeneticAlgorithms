package htt.adaptativelearningstrategy;


public class LamarckianStrategy implements AdaptiveLearningStrategy {
    private int[][] distanceMatrix;
    private int[][] flowMatrix;

    public LamarckianStrategy(int[][] distanceMatrix, int[][] flowMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.flowMatrix = flowMatrix;
    }

    @Override
    public int[] improve(int[] individual, int fitness) {
        performLocalSearch(individual);
        return individual;
    }

    private void performLocalSearch(int[] genotype) {
        boolean improved = true;

        while (improved) {
            improved = false;
            int bestDelta = 0;
            int bestI = -1, bestJ = -1;

            for (int i = 0; i < genotype.length - 1; i++) {
                for (int j = i + 1; j < genotype.length; j++) {
                    int delta = calculateDelta(genotype, i, j);
                    if (delta < bestDelta) {
                        bestDelta = delta;
                        bestI = i;
                        bestJ = j;
                        improved = true;
                    }
                }
            }

            if (improved && bestI != -1 && bestJ != -1) {
                swap(genotype, bestI, bestJ);
            }
        }
    }

    private int calculateDelta(int[] genotype, int i, int j) {
        int delta = 0;

        for (int k = 0; k < genotype.length; k++) {
            if (k != i && k != j) {
                delta +=
                        (flowMatrix[genotype[i]][genotype[k]] - flowMatrix[genotype[j]][genotype[k]])
                                * (distanceMatrix[i][k] - distanceMatrix[j][k])
                                +
                                (flowMatrix[genotype[k]][genotype[i]] - flowMatrix[genotype[k]][genotype[j]])
                                        * (distanceMatrix[k][i] - distanceMatrix[k][j]);
            }
        }

        delta +=
                (flowMatrix[genotype[i]][genotype[j]] - flowMatrix[genotype[j]][genotype[i]])
                        * (distanceMatrix[i][j] - distanceMatrix[j][i]);

        return delta;
    }

    private void swap(int[] genotype, int i, int j) {
        int temp = genotype[i];
        genotype[i] = genotype[j];
        genotype[j] = temp;
    }
}
