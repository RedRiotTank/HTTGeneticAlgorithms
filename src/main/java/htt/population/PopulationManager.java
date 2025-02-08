package htt;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static htt.Util.random;
import static htt.Util.shuffleArray;


@Data
public class PopulationManager {

    @NonNull
    private final DataLoader data;

    private List<int[]> population;

    public PopulationManager(DataLoader data, int populationSize) {
        this.data = data;
        initializePopulation(populationSize);
    }


    private void initializePopulation(int populationSize) {
        population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            int[] individual = new int[data.getN()];
            for (int j = 0; j < data.getN(); j++) {
                individual[j] = j;
            }
            shuffleArray(individual);
            population.add(individual);
        }
    }


}

