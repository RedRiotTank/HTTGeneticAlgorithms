package htt;

import java.util.Random;


public class Util {
    public static final Random random = new Random();

    private Util() {}

    public static void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void calculateBestSolutionListString(int[] bestSolution) {
        if (bestSolution == null) {
            System.out.println("No se encontró una solución.");
            return;
        }

        StringBuilder bestSolutionStr = new StringBuilder();
        for (int i = 0; i < bestSolution.length; i++) {
            bestSolutionStr.append(bestSolution[i]);
            if (i < bestSolution.length - 1) {
                bestSolutionStr.append(" ");
            }
        }
        System.out.println("Mejor solución encontrada: " + bestSolutionStr);
    }
}
