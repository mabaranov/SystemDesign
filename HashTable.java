package com.company;

import java.lang.reflect.Array;

public class HashTable<T>
{
    public int size;
    public int step;
    public T[] slots;
    private int counter;
    private Class clazz;

    private int put_status;
    private int remove_status;

    public final int PUT_OK = 1;
    public final int PUT_ERR = 2;
    public final int REMOVE_OK = 1;
    public final int REMOVE_ERR = 2;


    // Конструктор
    // Постусловие: создана пустая хэш-таблица максимального размера
    public HashTable(int sz, Class clz) {
        clazz = clz;
        size = sz;
        step = stp;
        counter = 0;
        slots = (T[]) Array.newInstance(this.clazz, size);;
        for (int i = 0; i < size; i++)
            slots[i] = null;
    }

    // Команды:

    // Предусловие: в хэш-таблице есть место
    // Постусловие: добавлен элемент в хэш-таблицу
    public void put(T value) {

        int index = seekSlot(value);

        if (index == -1) {
            put_status = PUT_ERR;
            return;
        }

        slots[index] = value;
        counter++;
        put_status = PUT_OK;
    }

    // Предусловие: хэш-таблица не пустая
    // Постусловие: элемент удален из хэш-таблицы
    public void remove(T value) {

        int slot = find(value);

        if (slot == -1 || counter == 0) {
            remove_status = REMOVE_ERR;
            return;
        }

        slots[slot] = null;
        counter--;
        remove_status = REMOVE_OK;
    }

    private int hashFun(T value) {
        return Math.abs(value.hashCode() % size);
    }

    private int seekSlot(T value) {
        int hashIndex = hashFun(value);

        if (slots[hashIndex] == null)
            return hashIndex;


        int offset = hashIndex;
        boolean loopEnds = false;

        while (slots[offset] != null) {
            offset += this.step;
            if (offset >= slots.length) {
                loopEnds = true;
                offset -= slots.length;
            }
            if (loopEnds && offset >= hashIndex)
                break;
            if (slots[offset] == null)
                return offset;
        }

        return -1;
    }

    // Запросы:

    // Предусловие:  таблица содержит элемент
    private int find(T value) { // возвращает индекс
        int hashIndex = hashFun(value);

        if (slots[hashIndex] == value)
            return hashIndex;


        int offset = hashIndex;
        boolean loopEnds = false;

        while (slots[offset] != value) {
            offset += this.step;
            if (offset >= slots.length) {
                loopEnds = true;
                offset -= slots.length;
            }
            if (loopEnds && offset >= hashIndex)
                break;
            if (slots[offset] == value)
                return offset;
        }

        return -1;
    }

    public boolean is_value(T value) {
        if (counter > 0 && find(value) > -1) {
            return true;
        }
        return false;
    }

    public int get_put_status() {
        return put_status;
    }

    public int get_remove_status() {
        return remove_status;
    }
}