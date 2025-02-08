package htt.data;

import lombok.Data;
import lombok.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

@Data
public class DataLoader {

    private int[][] distanceMatrix;

    private int[][] flowMatrix;

    private int n;

    @NonNull
    private final String fileName;

    public DataLoader(String fileName) {
        this.fileName = fileName;
        loadProblemData();
    }

    public void loadProblemData() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            n = scanner.nextInt();

            flowMatrix = new int[n][n];
            distanceMatrix = new int[n][n];

            IntStream.range(0, n).forEach(i ->
                IntStream.range(0, n).forEach(j ->
                    flowMatrix[i][j] = scanner.nextInt()
                )
            );

            IntStream.range(0, n).forEach(i ->
                IntStream.range(0, n).forEach(j ->
                    distanceMatrix[i][j] = scanner.nextInt()
                )
            );
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo.");
            e.printStackTrace();
        }
    }
}