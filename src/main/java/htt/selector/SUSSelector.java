package htt.selector;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class SUSSelector implements ParentSelector {

    @Override
    public int[] select(List<int[]> population, List<Integer> fitness) {
        double totalFitness = fitness.stream().mapToDouble(f -> 1.0 / f).sum();
        double[] probabilities = fitness.stream().mapToDouble(f -> (1.0 / f) / totalFitness).toArray();

        double r = Math.random() / probabilities.length;
        double cumulativeProbability = 0.0;
        int index = 0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            while (r <= cumulativeProbability) {
                index = i;
                r += 1.0 / probabilities.length;
            }
        }

        return population.get(index);
    }
}
