package htt.crossover;

import lombok.NoArgsConstructor;

import static htt.Util.random;

@NoArgsConstructor
public class TwoPointCrossover implements CrossoverOperator {

    @Override
    public int[] crossover(int[] parent1, int[] parent2) {
        int n = parent1.length;
        int[] offspring = new int[n];

        int point1 = random.nextInt(n);
        int point2 = random.nextInt(n);

        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        System.arraycopy(parent1, point1, offspring, point1, point2 - point1 + 1);

        int currentIndex = (point2 + 1) % n;
        for (int i = 0; i < n; i++) {
            int gene = parent2[(point2 + 1 + i) % n];
            if (!contains(offspring, gene)) {
                offspring[currentIndex] = gene;
                currentIndex = (currentIndex + 1) % n;
            }
        }

        return offspring;
    }

    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }
}