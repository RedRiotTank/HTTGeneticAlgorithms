package htt;

import htt.crossoveroperator.CrossoverOperator;
import htt.mutationoperator.MutationOperator;
import htt.selector.ParentSelector;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;



@AllArgsConstructor
@Data
public class GeneticAlgorithmQAP {

    //private DataLoader data;
    //private PopulationManager populationManager;


    private final FitnessCalculator fitnessCalculator;
    private final ParentSelector parentSelector;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;

    public List<Integer> evaluatePopulation(List<int[]> population) {
        List<Integer> fitness = new ArrayList<>();
        for (int[] individual : population) {
            fitness.add(fitnessCalculator.calculate(individual));
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
