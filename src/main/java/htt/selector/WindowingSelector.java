package htt.selector;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class WindowingSelector implements ParentSelector {

    @Override
    public int[] select(List<int[]> population, List<Integer> fitness) {
        int minFitness = fitness.stream().min(Integer::compare).orElse(0);
        List<Double> adjustedFitness = fitness.stream().map(f -> f - minFitness + 1.0).toList();
        double totalAdjustedFitness = adjustedFitness.stream().mapToDouble(f -> f).sum();

        double r = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < adjustedFitness.size(); i++) {
            cumulativeProbability += adjustedFitness.get(i) / totalAdjustedFitness;
            if (r <= cumulativeProbability) {
                return population.get(i);
            }
        }

        return population.get(population.size() - 1);
    }
}
