package by.ai;

import java.util.List;
import java.util.Scanner;

/**
 * Класс экспертной системы по определению
 * объекта по его характеристикам.
 * Система спрашивает у пользователя имеет ли
 * загаданный объект выбранную характеристику.
 * Пользователь отвечает и система на основе
 * его ответа делает заключения об объекте.
 */
class ExpertSystem {

    /**
     * Матрица элементов экспертной системы.
     * Содержит объекты класса {@link Element}
     * @see  Element
     */
    private Element[][] system;

    ExpertSystem() {

    }

    /**
     * Считывает данные из файлов и заполняет ими
     * матрицу экспертной системы.
     *
     * @param  objFilename     имя файла
     * @param  charFilename    имя файла
     * @param  matrixFilename  имя файла
     */
    void fillSystemWithData(String objFilename, String charFilename, String matrixFilename) {
        FileReaderUtil fileReaderUtil = new FileReaderUtil();
        List<String> objects = fileReaderUtil.getWordsFromFile(objFilename);
        List<String> chars = fileReaderUtil.getWordsFromFile(charFilename);
        int rows = chars.size();
        int columns = objects.size();
        int[][] matrix = fileReaderUtil.getMatrixFromFile(matrixFilename, rows, columns);
        system = new Element[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Element element = new Element();
                element.setCharacteristic(chars.get(i));
                element.setObject(objects.get(j));
                element.setValue(matrix[i][j]);
                system[i][j] = element;
            }
        }
    }

    /**
     * Задаёт вопрос о наличии характеристики у объекта пользователя.
     * Перед этим удаляет все пустые строки (характеристики,
     * которых нет ни у одного объекта).
     * Если пользователь отвечает 'Да', то удаляет все
     * объекты, которые не имеют данной характеристики.
     * Если пользователь отвечает 'Нет', то удаляет данную характеристику.
     * После этого вызывает функцию заново.
     * Если в матрице остался только один объект, то
     * система даёт ответ.
     */
    void askQuestion() {
        if(system[0].length == 1) {
            System.out.println("Your object is " + system[0][0].getObject());
            return;
        }

        deleteEmptyRows();

        printTable();

        Scanner in = new Scanner(System.in);
        int rowIndex = findMinRow();
        String aChar = system[rowIndex][0].getCharacteristic();

        System.out.println("Does it have " + aChar + "?");
        System.out.print("Yes(1)/No(0): ");

        int answer = in.nextInt();

        if (answer != 0) {
            deleteUnsuitableObjects(rowIndex);
            askQuestion();
        } else {
            deleteUnsuitableObjectsAndChar(rowIndex);
            askQuestion();
        }
    }

    /**
     * Выводит матрицу с объектами и
     * характеристиками в консоль.
     */
    private void printTable() {
        System.out.println("_____________________________");
        System.out.print("      ");

        for (int j = 0; j < system[0].length; j++) {
            System.out.print(system[0][j].getObject() + " ");
        }

        System.out.println();

        for (Element[] aChar : system) {
            System.out.print(aChar[0].getCharacteristic() + " ");
            for (Element el : aChar) {
                System.out.print(el.getValue() + " ");
            }
            System.out.println();
        }
        System.out.println("_____________________________");
    }

    /**
     * Удаляет объекты из матрицы, которые не имееют
     * характеристику под заданным индексом.
     *
     * @param  charIndex  индекс характеристики
     */
    private void deleteUnsuitableObjects(int charIndex) {
        int columnsNumber = system[charIndex].length;
        int iterator = 0;
        int j = 0;
        while(iterator != columnsNumber) {
            if (system[charIndex][j].getValue() == 0) {
                deleteColumn(j);
            } else {
                j++;
            }
            iterator++;
        }
    }

    /**
     * Удаляет заданную характеристику и объекты,
     * которые её имеют.
     *
     * @param  charIndex  индекс характеристики
     */
    private void deleteUnsuitableObjectsAndChar(int charIndex) {
        int columnsNumber = system[charIndex].length;
        int iterator = 0;
        int j = 0;
        while(iterator != columnsNumber) {
            if (system[charIndex][j].getValue() == 1) {
                deleteColumn(j);
            } else {
                j++;
            }
            iterator++;
        }
        deleteRow(charIndex);
    }

    /**
     * Удаляет пустые строки в матрице.
     * Пустая строка та, если все элементы в ней
     * имею значение 0 в поле {@link Element#value}.
     */
    private void deleteEmptyRows() {
        int i;
        while (true) {
            i = findEmptyRow();
            if (i == -1) {
                break;
            } else {
                deleteRow(i);
            }
        }
    }

    /**
     * Удаляет строку в матрице по её индексу.
     *
     * @param  deli  индекс строки матрицы
     */
    private void deleteRow(int deli) {
        int rows = system.length - 1;
        int columns = system[0].length;
        Element[][] newMatrix = new Element[rows][columns];

        for (int i = 0, ln = 0; ln < rows; ) {
            if (i != deli) {
                for (int j = 0; j < columns; j++) {
                    newMatrix[ln][j] = system[i][j];
                }
                i++;
                ln++;
            } else {
                i++;
            }
        }
        system = newMatrix;
    }

    /**
     * Удаляет столбец в матрице по его индексу
     *
     * @param  delj  индекс столбца матрицы
     */
    private void deleteColumn(int delj) {
        int rows = system.length;
        int columns = system[0].length - 1;
        Element[][] newMatrix = new Element[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0, cn = 0; cn < columns; j++, cn++) {
                if (j == delj) {
                    j++;
                }
                newMatrix[i][cn] = system[i][j];
            }
        }
        system = newMatrix;
    }

    /**
     * Находит строку с нулевыми значениями поля
     * {@link Element#value}.
     *
     * @return  индекс пустой строки
     */
    private int findEmptyRow() {
        int indexOfEmpty = -1;
        boolean isEmpty;
        for (int i = 0; i < system.length; i++) {
            isEmpty = true;
            for (int j = 0; j < system[i].length; j++) {
                if (system[i][j].getValue() == 1) {
                    isEmpty = false;
                }
            }
            if (isEmpty) {
                indexOfEmpty = i;
            }
        }
        return indexOfEmpty;
    }

    /**
     * Находит индекс минимального элемента
     * в строке матрицы.
     *
     * @return  индекс минимального элемента
     */
    private int findMinRow() {
        int[] sumValues = new int[system.length];
        int sum;

        for (int i = 0; i < system.length; i++) {
            sum = 0;
            for (int j = 0; j < system[i].length; j++) {
                sum += system[i][j].getValue();
            }
            sumValues[i] = sum;
        }

        int minSum = findMinValue(sumValues);
        int indexOfMin = -1;
        for (int i = 0; i < system.length; i++) {
            sum = 0;
            for (int j = 0; j < system[i].length; j++) {
                sum += system[i][j].getValue();
            }
            if (minSum == sum) {
                indexOfMin = i;
                break;
            }
        }
        return indexOfMin;
    }

    /**
     * Находит минимальный элемент в
     * целочисленном массиве.
     *
     * @param   arr  одномерный целочисленный массив
     * @return       целое число
     */
    private int findMinValue(int[] arr) {
        int min = arr[0];

        for (int el : arr) {
            if (el < min) {
                min = el;
            }
        }

        return min;
    }
}
