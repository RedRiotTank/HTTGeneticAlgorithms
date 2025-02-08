package htt.crossoveroperator;

import java.util.Arrays;

import static htt.Util.random;

public class PMXCrossover implements CrossoverOperator{
    @Override
    public int[] crossover(int[] parent1, int[] parent2) {
        int n = parent1.length;
        int[] offspring = new int[n];
        Arrays.fill(offspring, -1);

        int start = random.nextInt(n);
        int end = random.nextInt(n - start) + start;

        for (int i = start; i <= end; i++) {
            offspring[i] = parent1[i];
        }

        for (int i = 0; i < n; i++) {
            if (offspring[i] == -1) {
                for (int j = 0; j < n; j++) {
                    if (!contains(offspring, parent2[j])) {
                        offspring[i] = parent2[j];
                        break;
                    }
                }
            }
        }

        return offspring;
    }

    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }
}
