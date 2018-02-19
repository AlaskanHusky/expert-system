package by.ai;

import java.util.List;

public class Main {

    private final static String OBJECTS_FILE_NAME = "src/files/objects.txt";
    private final static String CHARACTERISTICS_FILE_NAME = "src/files/characteristics.txt";
    private final static String MATRIX_FILE_NAME = "src/files/matrix.txt";

    public static void main(String[] args) {
        DataUtil dataUtil = new DataUtil();

        List<String> objects = dataUtil.getWordsFromFile(OBJECTS_FILE_NAME);
        List<String> chars = dataUtil.getWordsFromFile(CHARACTERISTICS_FILE_NAME);
        int[][] matrix = dataUtil.getMatrixFromFile(MATRIX_FILE_NAME, objects.size(), chars.size());

        dataUtil.printTable(matrix, objects, chars);
    }
}
