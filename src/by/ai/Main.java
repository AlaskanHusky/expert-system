package by.ai;

public class Main {

    private final static String OBJECTS_FILE_NAME = "src/files/objects.txt";
    private final static String CHARACTERISTICS_FILE_NAME = "src/files/characteristics.txt";
    private final static String MATRIX_FILE_NAME = "src/files/matrix.txt";

    public static void main(String[] args) {
        ExpertSystem expertSystem = new ExpertSystem();

        expertSystem.fillSystemWithData(OBJECTS_FILE_NAME, CHARACTERISTICS_FILE_NAME, MATRIX_FILE_NAME);
        expertSystem.askQuestion();
    }
}
