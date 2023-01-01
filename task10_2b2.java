Методы объявленные с ключевым словом final не могут быть переопределены.

class А
{
    final void meth() { System.out.println("Это метод final."); }
}

class В extends A
{
    void meth() { System.out.println("Не допускается!"); } // ошибка
}