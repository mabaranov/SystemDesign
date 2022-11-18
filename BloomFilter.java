package com.company;

import java.util.BitSet;

public class BloomFilter<T> {
    private int filter_len;
    private BitSet bloomFilter;
    private final int multiplier1 = 17;
    private final int multiplier2 = 223;

    private int add_status;
    
    public final int ADD_OK = 1;
    public final int ADD_ERR = 2;

    // Конструктор
    public BloomFilter(int f_len) {
        filter_len = f_len;
        bloomFilter = new BitSet(f_len); // создаём битовый массив длиной f_len ...
    }


    // Команды
    // Постусловие: добавляет элемент в фильтр
    public void add(T val) {
        if (val == null) {
            add_status = ADD_ERR;
            return;
        }

        int index1 = (val.hashCode() * multiplier1) % filter_len;
        int index2 = (val.hashCode() * multiplier2) % filter_len;
        bloomFilter.set(index1);
        bloomFilter.set(index2);
        add_status = ADD_OK;
    }

    // Запросы
    public boolean isValue(T val) { // возвращает истина если элемент есть
        // проверка, имеется ли строка str1 в фильтре
        int index1 = (val.hashCode() * multiplier1) % filter_len;
        int index2 = (val.hashCode() * multiplier2) % filter_len;

        return bloomFilter.get(index1) && bloomFilter.get(index2);
    }

    // дополнительные запросы:
    public int get_add_status()
    {
        return add_status;
    }
}