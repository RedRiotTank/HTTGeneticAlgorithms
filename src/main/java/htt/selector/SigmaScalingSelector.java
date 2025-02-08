package htt.selector;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class SigmaScalingSelector implements ParentSelector {

    @Override
    public int[] select(List<int[]> population, List<Integer> fitness) {
        double meanFitness = fitness.stream().mapToDouble(f -> f).average().orElse(0.0);
        double stdDev = Math.sqrt(fitness.stream().mapToDouble(f -> Math.pow(f - meanFitness, 2)).average().orElse(0.0));

        List<Double> scaledFitness = fitness.stream()
                .map(f -> Math.max(0.0, 1.0 + (f - meanFitness) / (2 * stdDev)))
                .toList();
        double totalScaledFitness = scaledFitness.stream().mapToDouble(f -> f).sum();

        double r = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < scaledFitness.size(); i++) {
            cumulativeProbability += scaledFitness.get(i) / totalScaledFitness;
            if (r <= cumulativeProbability) {
                return population.get(i);
            }
        }

        return population.get(population.size() - 1);
    }
}
