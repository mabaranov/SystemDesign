package com.company;

public class task2_2b2 {

    public class Animal {
        public Animal(int id, String name) {}
        String voice() { return ""; }
    }

    public class Dog extends Animal { // специализация класса-родителя
        public Dog(int id, String name) { super(id, name); }
        public String voice() { return "Gav"; }
    }

    public class HuntingDog extends Dog { // расширение класса-родителя
        private String typeChaseAnimal; // на кого охотиться
        public HuntingDog(int id, String name) { super(id, name); }
        public void Chase() {} // преследовать, охотиться
    }

}