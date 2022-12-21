class Animal
{
    public void say()
    {
        System.out.println("Oops");
    }
}

class Dog extends Animal
{
    public void say()
    {
        System.out.println("Gav");
    }
}

void main()
{
    Animal tmp = new Dog();
    tmp.say(); // здесь произойдет динамическое связывание
}