package by.ai;

public class Main {
    /**
     * Имя файла с объектами
     */
    private final static String OBJECTS_FILE_NAME = "src/files/objects.txt";
    /**
     * Имя файла с характеристиками
     */
    private final static String CHARACTERISTICS_FILE_NAME = "src/files/characteristics.txt";
    /**
     * Имя файла с матрицой значений
     */
    private final static String MATRIX_FILE_NAME = "src/files/matrix.txt";

    public static void main(String[] args) {
        ExpertSystem expertSystem = new ExpertSystem();

        expertSystem.fillSystemWithData(OBJECTS_FILE_NAME, CHARACTERISTICS_FILE_NAME, MATRIX_FILE_NAME);
        expertSystem.askQuestion();
    }
}
