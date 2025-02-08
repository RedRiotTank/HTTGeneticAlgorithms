package htt;

import htt.crossover.CrossoverOperator;
import htt.crossover.PMXCrossover;
import htt.data.DataLoader;
import htt.fitness.FitnessCalculator;
import htt.mutation.MutationOperator;
import htt.mutation.SwapMutation;
import htt.population.PopulationManager;
import htt.selector.*;

import java.util.ArrayList;
import java.util.List;

import static htt.Util.calculateBestSolutionListString;

public class Main {
    private static final int MAX_GENERATIONS = 5000;
    private static final double CROSSOVER_RATE = 0.8;
    private static final double MUTATION_RATE = 0.1;

    public static void main(String[] args) {
        
        DataLoader dataLoader = new DataLoader("tai256c.dat");

        PopulationManager populationManager = new PopulationManager(dataLoader, 500);

        FitnessCalculator fitnessCalculator = new FitnessCalculator(
                dataLoader.getFlowMatrix(),
                dataLoader.getDistanceMatrix()
        );
        ParentSelector parentSelector = new TournamentSelector();
        CrossoverOperator crossoverOperator = new PMXCrossover();
        MutationOperator mutationOperator = new SwapMutation();

        GeneticAlgorithmQAPCoordinator geneticAlgorithmQAPCoordinator = new GeneticAlgorithmQAPCoordinator(
                fitnessCalculator,
                parentSelector,
                crossoverOperator,
                mutationOperator,
                null
        );

        int[] bestSolution = null;
        int bestCost = Integer.MAX_VALUE;

        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            List<Integer> fitness = geneticAlgorithmQAPCoordinator.evaluatePopulation(populationManager.getPopulation());

            for (int i = 0; i < populationManager.getPopulation().size(); i++) {
                if (fitness.get(i) < bestCost) {
                    bestCost = fitness.get(i);
                    bestSolution = populationManager.getPopulation().get(i);
                }
            }

            List<int[]> newPopulation = new ArrayList<>();
            while (newPopulation.size() < populationManager.getPopulation().size()) {
                int[] parent1 = geneticAlgorithmQAPCoordinator.selectParent(populationManager.getPopulation(), fitness);
                int[] parent2 = geneticAlgorithmQAPCoordinator.selectParent(populationManager.getPopulation(), fitness);

                int[] offspring;
                if (Math.random() < CROSSOVER_RATE) {
                    offspring = geneticAlgorithmQAPCoordinator.crossover(parent1, parent2);
                } else {
                    offspring = parent1.clone();
                }

                if (Math.random() < MUTATION_RATE) {
                    geneticAlgorithmQAPCoordinator.mutate(offspring);
                }

                newPopulation.add(offspring);
            }

            populationManager.setPopulation(newPopulation);

            System.out.println("Generación: " + generation + " Mejor costo: " + bestCost);
        }

        printSolution(bestSolution, bestCost);
    }

    private static void printSolution(int[] bestSolution, int bestCost){
        calculateBestSolutionListString(bestSolution);

        System.out.println("Costo de la mejor solución: " + bestCost);
        System.out.println("resultado: " + (5 - 100.0 * (bestCost - 44759294) / 44759294));

    }


}
