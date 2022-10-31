**Рефлексия:**
        3.1. Очень похоже с эталоном). В эталоне правда нет реализации, да и статусы опущены как само собой разумеющееся.

package com.company;

import java.lang.reflect.Array;

public class DynArray<T> {

    private T[] array;
    private int count; // количество элементов
    private int capacity; // ёмкость массива
    private Class clazz;
    private final int MIN_CAPACITY = 16; // минимальная ёмкость массива

    private int insert_status;
    private int remove_status;
    private int get_status;

    public final int INSERT_OK = 1; // успешно
    public final int INSERT_ERR = 2; // индекс за границами массива
    public final int REMOVE_OK = 1; // успешно
    public final int REMOVE_ERR = 2; // массив пустой; индекс за границами массива
    public final int GET_OK = 1; // успешно
    public final int GET_ERR = 2; // массив пустой; индекс за границами массива


    // Конструктор

    // Постусловие: создан новый пустой массив
    public DynArray(Class clz) {
        this.clazz = clz;
        count = 0;
        makeArray(MIN_CAPACITY);
    }


    // Команды

    // Предусловие:
    // Постусловие: создан новый массив заданного значения но не меньше MIN_CAPACITY, предыдущие значения копируются в новый массив
    private void makeArray(int new_capacity)
    {
        if (new_capacity < MIN_CAPACITY)
            new_capacity = MIN_CAPACITY;

        T[] new_array = (T[]) Array.newInstance(this.clazz, new_capacity);

        for (int i=0; i<count; i++)
        {
            new_array[i] = array[i];
        }

        array = new_array;
        capacity = new_capacity;
    }

    // Постусловие: добавляется новый элемент в конец массива
    public void append(T item)
    {
        if (count+1 > capacity)
            makeArray(capacity*2);

        array[count] = item;
        count += 1;
    }

    // Предусловие: индекс находится в границах массива, в i-позиции есть элемент
    // Постусловие: новый элемент вставляется в i-позицию, смещая последующие элементы элементы
    public void insert(T item, int index)
    {
        if (index < 0 || index > count) {
            insert_status = INSERT_ERR;
            return;
        }


        if (index == count)
        {
            append(item);
            insert_status = INSERT_OK;
            return;
        }

        if (count+1 > capacity)
            makeArray(capacity*2);

        T[] new_array = (T[]) Array.newInstance(this.clazz, capacity*2);

        for (int i=0; i<index; i++)
            new_array[i] = array[i];

        new_array[index] = item;

        for (int i=index; i<count; i++)
            new_array[i+1] = array[i];

        array = new_array;
        count += 1;

        insert_status = INSERT_OK;
    }

    // Предусловие: индекс находится в границах массива, в i-позиции есть элемент
    // Постусловие: элемент i-позиции удален, при необходимости сжимается буфер
    public void remove(int index)
    {
        if (index < 0 || index > count-1) {
            remove_status = REMOVE_ERR;
            return;
        }


        for (int i=index+1; i<count; i++)
        {
            array[i - 1] = array[i];

            if (i == count-1)
                array[i] = null;
        }

        if (index == count-1)
            array[index] = null;

        count -= 1;
        float percent = (float)capacity/count;
        if (percent > 2.0)
            makeArray( (int)(capacity/1.5) );

        remove_status = REMOVE_OK;
    }


    // запросы

    // Предусловие: индекс находится в границах массива, в  i-позиции есть элемент
    // Постусловие: получение элемента по индексу
    public T getItem(int index)
    {
        if(index < 0 || index > count) {
            get_status = GET_ERR;
            return null;
        } else {
            get_status = GET_OK;
            return array[index];
        }
    }

    public int size() { return count; }
    public int get_insert_status() { return insert_status; }
    public int get_remove_status() { return remove_status; }
    public int get_get_status() { return get_status; }
}