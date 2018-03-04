package by.ai;

/**
 * Класс элемента матрицы экспертной системы.
 * Было решено реализовать хранение данных через
 * матрицу объектов. Каждый элемент матрицы
 * содержит объект, который имеет 3 поля:
 * объект, характеристика и логическое значение
 * true/false. Если объект имеет характеристику,
 * то значение переменной равно 1. Если не имееет,
 * то 0.
 */
class Element {

    private String object;
    private String characteristic;
    private int value;

    Element() {}

    String getObject() {
        return object;
    }

    void setObject(String object) {
        this.object = object;
    }

    String getCharacteristic() {
        return characteristic;
    }

    void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
