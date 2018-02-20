package by.ai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileReaderUtil {

    List<String> getWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        String s;

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(filename));
            while ((s = br.readLine()) != null) {
                words.add(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return words;
    }

    int[][] getMatrixFromFile(String filename, int numberOfChars, int numberOfObj) {
        int[][] matrix = new int[numberOfChars][numberOfObj];
        String els;
        int i = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((els = br.readLine()) != null) {
                int[] line = convertFromStringToInt(els.split(" "));
                System.arraycopy(line, 0, matrix[i], 0, line.length);
                i++;
            }
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return matrix;
    }

    void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    private int[] convertFromStringToInt(String[] arr) {
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }
}
