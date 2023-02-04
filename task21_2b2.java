import java.lang.reflect.Array;

// Наследование реализации
public class ArrayedStack<T> extends java.util.Stack<T>
{
    private T[] array;
    private int count;
    private int size;
    Class clazz;

    public ArrayedStack(Class clz) {
        clazz = clz;
        size = 10;
        array = (T[]) Array.newInstance(this.clazz, size);
        count = 0;
    }

    public ArrayedStack(Class clz, int size) {
        this.size = size;
        array = (T[]) Array.newInstance(this.clazz, size);
        count = 0;
    }

    public T Top() { return array[count - 1]; }
    public Boolean IsEmpty() { return count == 0; }
    public Boolean IsFull() { return count == size; }
}

// Льготное наследование
public class MyException extends Exception
{
    public MyException() { }
    public MyException(String message) { }
}