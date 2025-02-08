package htt;

import htt.adaptativelearningstrategy.AdaptiveLearningStrategy;
import htt.crossover.CrossoverOperator;
import htt.fitness.FitnessCalculator;
import htt.mutation.MutationOperator;
import htt.selector.ParentSelector;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class GeneticAlgorithmQAPCoordinator {
    private final FitnessCalculator fitnessCalculator;
    private final ParentSelector parentSelector;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private final AdaptiveLearningStrategy adaptiveLearningStrategy;

    public List<Integer> evaluatePopulation(List<int[]> population) {
        List<Integer> fitness = new ArrayList<>();
        for (int[] individual : population) {
            int fitnessValue = fitnessCalculator.calculate(individual);
            if (adaptiveLearningStrategy != null) {
                individual = adaptiveLearningStrategy.improve(individual, fitnessValue);
                fitnessValue = fitnessCalculator.calculate(individual);
            }
            fitness.add(fitnessValue);
        }
        return fitness;
    }

    public int[] selectParent(List<int[]> population, List<Integer> fitness) {
        return parentSelector.select(population, fitness);
    }

    public int[] crossover(int[] parent1, int[] parent2) {
        return crossoverOperator.crossover(parent1, parent2);
    }

    public void mutate(int[] individual) {
        mutationOperator.mutate(individual);
    }
}
