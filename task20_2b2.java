// Наследование вариаций
class Car {
    public Double Spending(Integer s) {}; // затраты
}
class ElectricCar extends Car {
    public Double Spending(Integer s, Integer kwt) {}; // затраты на электрический автомобиль будут явно считаться по другому от бензинового
}

// Наследование с конкретизацией
abstract class Animal {
    public abstract void Voice();
}
class Cat extends Animal {
    public void Voice() { System.out.println("May");}
}

// Структурное наследование
//Класс Integer наследуется от класса Number и поддерживает интерфейс Comparable. Т.е. с помощью Comparable добавляется новая абстракция сравнения.