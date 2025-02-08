package htt.selector;

import lombok.NoArgsConstructor;
import java.util.List;
import static htt.Util.random;

@NoArgsConstructor
public class TournamentSelector implements ParentSelector{

    @Override
    public int[] select(List<int[]> population, List<Integer> fitness) {
        int index1 = random.nextInt(population.size());
        int index2 = random.nextInt(population.size());
        return fitness.get(index1) < fitness.get(index2) ? population.get(index1) : population.get(index2);
    }
}
