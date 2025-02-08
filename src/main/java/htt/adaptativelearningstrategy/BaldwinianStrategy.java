package htt.adaptativelearningstrategy;

import htt.fitness.FitnessCalculator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaldwinianStrategy implements AdaptiveLearningStrategy {
    private final FitnessCalculator fitnessCalculator;

    @Override
    public int[] improve(int[] individual, int fitness) {
        int[] temporaryPhenotype = individual.clone();

        performLocalSearch(temporaryPhenotype);

        fitness = fitnessCalculator.calculate(temporaryPhenotype);

        return individual;
    }

    private void performLocalSearch(int[] phenotype) {
        boolean improved;
        do {
            improved = false;
            int bestFitness = fitnessCalculator.calculate(phenotype);

            for (int i = 0; i < phenotype.length; i++) {
                for (int j = i + 1; j < phenotype.length; j++) {
                    swap(phenotype, i, j);

                    int newFitness = fitnessCalculator.calculate(phenotype);
                    if (newFitness < bestFitness) {
                        bestFitness = newFitness;
                        improved = true;
                    } else {
                        swap(phenotype, i, j);
                    }
                }
            }
        } while (improved);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}