package htt.mutationoperator;

import static htt.Util.random;

public class SwapMutation implements MutationOperator {

    @Override
    public void mutate(int[] individual) {
        int index1 = random.nextInt(individual.length);
        int index2 = random.nextInt(individual.length);

        int temp = individual[index1];
        individual[index1] = individual[index2];
        individual[index2] = temp;
    }
}
