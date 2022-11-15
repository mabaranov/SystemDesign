package com.company;

public class PowerSet<T> extends HashTable<T> {

    // Конструктор
    // Постусловие: создает пустое множество
    public PowerSet(Class clazz, int size) {
        super(clazz, size);
    }

    // Команды
    // Предусловие: множества не пусты
    // Постусловие: возвращает пересечение множеств
    public PowerSet<T> intersection(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<T>(getClazz(), size());
        for (int i = 0; i < set.size(); i++) {
            if (set.getSlots()[i] != null && get(set.getSlots()[i])) {
                result.put(set.getSlots()[i]);
            }
        }
        return result;
    }

    // Предусловие: множества не пусты
    // Постусловие: возвращает объединение множеств
    public PowerSet<T> union(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<T>(getClazz(), size() + set.size());
        for (int i = 0; i < size(); i++) {
            if (getSlots()[i] != null) {
                result.put(getSlots()[i]);
            }
        }
        for (int i = 0; i < set.size(); i++) {
            if (set.getSlots()[i] != null) {
                result.put(set.getSlots()[i]);
            }
        }
        return result;
    }

    // Предусловие: множества не пусты
    // Постусловие: возвращает разность множеств
    public PowerSet<T> difference(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<T>(getClazz(), size());
        for (int i = 0; i < set.size(); i++) {
            if (getSlots()[i] != null && !set.get(getSlots()[i])) {
                result.put(getSlots()[i]);
            }
        }
        return result;
    }

    // Запросы

    public boolean is_subset(PowerSet<T> set) {
        for (int i = 0; i < set.size(); i++) {
            if (set.getSlots()[i] != null && !get(set.getSlots()[i])) {
                return false;
            }
        }
        return true;
    }
}

class HashTable<T> {
    private int size;
    private int counter;
    private T[] slots;
    private Class clazz;
    private final int STEP;

    private int put_status;
    private int remove_status;
    private int find_status;

    public final int PUT_OK = 1;
    public final int PUT_ERR = 2;
    public final int REMOVE_OK = 1;
    public final int REMOVE_ERR = 2;
    public final int FIND_OK = 1;
    public final int FIND_ERR = 2;

    // Конструктор
    // Постусловие: создана пустая хэш-таблица максимального размера
    public HashTable(Class clazz, int size) {
        this.clazz = clazz;
        this.size = size;
        counter = 0;
        STEP = 3;
        slots = (T[]) Array.newInstance(this.clazz, size);
    }

    private int hash_func(T value) {
        return Math.abs(value.hashCode() % size);
    }

    private int seekSlot(T value) {
        int slot = hash_func(value);
        for (int i = 0; i < size; i++) {
            if (slots[slot] == null) {
                return slot;
            }
            slot = slot + STEP;
            if (slot >= size) {
                slot = slot - size;
            }
        }
        return -1;
    }

    // Команды:

    // Предусловие: в хэш-таблице есть место
    // Постусловие: добавлен элемент в хэш-таблицу
    public void put(T value) {
        if (counter < size && !get(value)) {
            int slot = seekSlot(value);
            if (slot > -1) {
                slots[slot] = value;
                counter++;
                put_status = PUT_OK;
                return;
            }
        }
        put_status = PUT_ERR;
    }

    // Предусловие: хэш-таблица не пустая
    // Постусловие: элемент удален из хэш-таблицы
    public void remove(T value) {
        if (counter > 0) {
            int slot = find(value);
            if (slot > -1) {
                slots[slot] = null;
                counter--;
                remove_status = REMOVE_OK;
                return;
            }
        }
        remove_status = REMOVE_ERR;
    }

    // Запросы:

    // Предусловие:  таблица содержит элемент
    public boolean get(T value) {
        if (counter > 0 && find(value) > -1) {
            find_status = FIND_OK;
            return true;
        }
        find_status = FIND_ERR;
        return false;
    }

    public int size() {
        return counter;
    }

    private int find(T value) {
        int slot = hash_func(value);
        for (int i = 0; i < size; i++) {
            if (slots[slot] == null) {
                return -1;
            } else if (slots[slot].equals(value)) {
                return slot;
            }
            slot = slot + STEP;
            if (slot >= size) {
                slot = slot - size;
            }
        }
        return -1;
    }

    public T[] getSlots() { return slots; }
    public Class getClazz() { return clazz; }

    public int get_put_status() { return put_status; }
    public int get_remove_status() { return remove_status; }
    public int get_find_status() { return find_status; }
}