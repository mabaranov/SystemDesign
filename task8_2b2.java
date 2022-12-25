Ковариантность:
class Super {
    A getSomething(){}
}
class Sub extends Super {
    B getSomething() {} // возвращает более конкретный тип В чем тип А в родительском классе
}


Контравариантность:
class Super {
    void doSomething(B parameter)
}
class Sub extends Super {
    void doSomething(A parameter) // тип А параметра более общий чем тип В в родительском классе
}
