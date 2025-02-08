package htt.selector;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LinearRankingSelector implements ParentSelector {

    @Override
    public int[] select(List<int[]> population, List<Integer> fitness) {
        List<Integer> sortedIndices = new ArrayList<>();
        for (int i = 0; i < fitness.size(); i++) sortedIndices.add(i);
        sortedIndices.sort((a, b) -> fitness.get(b) - fitness.get(a));

        double totalRank = (fitness.size() * (fitness.size() + 1)) / 2.0;
        List<Double> rankProbabilities = new ArrayList<>();
        for (int i = 0; i < sortedIndices.size(); i++) {
            rankProbabilities.add((double) (i + 1) / totalRank);
        }

        double r = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < rankProbabilities.size(); i++) {
            cumulativeProbability += rankProbabilities.get(i);
            if (r <= cumulativeProbability) {
                return population.get(sortedIndices.get(i));
            }
        }

        return population.get(sortedIndices.get(sortedIndices.size() - 1));
    }
}
