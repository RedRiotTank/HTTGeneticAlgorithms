package htt.selector;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RouletteSelector implements ParentSelector {

    @Override
    public int[] select(List<int[]> population, List<Integer> fitness) {
        double totalFitness = fitness.stream().mapToDouble(f -> 1.0 / f).sum();
        double[] probabilities = fitness.stream().mapToDouble(f -> (1.0 / f) / totalFitness).toArray();

        double r = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (r <= cumulativeProbability) {
                return population.get(i);
            }
        }

        return population.get(population.size() - 1);
    }
}

