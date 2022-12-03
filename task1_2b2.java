public class task1_2b2 {

        public class Animal {

            private Chip chip; // композиция

            public Animal(int id, String name) {
                chip = new Chip(id, name);
            }

            String voice() {
                return "";
            }
        }

        public class Chip {
            private int id;
            private String label;

            public Chip(int id, String label) {
                this.id = id;
                this.label = label;
            }

            public int getId() {
                return id;
            }

            public String getLabel() {
                return label;
            }
        }

        public class Cat extends com.company.Animal {// наследование

            public Cat(int id, String name) {
                super(id, name);
            }

            public String voice() { // полиморфизм
                return "Meow";
            }
        }

        public class Dog extends com.company.Animal { // наследование
            public Dog(int id, String name) {
                super(id, name);
            }
            
            public String voice() { // полиморфизм
                return "Gaw";
            }
        }

        public static void main(String[] args) {

            Animal animal1 = new Animal(1, "animal");
            Cat cat1 = new Cat(2, "Barsik");
            Dog dog1 = new Dog( 3, "Sharik");

            Animal arr[] = {animal1, cat1, dog1};

            for(int i=0; i<arr.length; i++) {
                System.out.println( arr[i].voice() );
            }
        }

}