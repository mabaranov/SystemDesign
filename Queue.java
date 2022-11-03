**Рефлексия:**
        4.1. Лишнее прописал предусловие: в i-позиции есть элемент - как я это собрался проверять?? Я использовал команды append, insert, remove; в эталоне: put, put_left, put_right, append, remove. Команды put у меня явно не хватает, но insert мне кажется вполне заменяет put_left put_right.

package com.company;

public class Queue<T>
{
    public final int DEQUEUE_OK = 1;
    public final int DEQUEUE_ERR = 2;

    private int enqueueStatus;
    private int dequeueStatus;

    LinkedList<T> _items;

    // Конструктор
    // Постусловие: создана новая пустая очередь
    public Queue()
    {
        _items = new LinkedList<>();
    }

    // Команды
    // Постусловие: добавлен новый элемент в конец очереди
    public void enqueue(T item)
    {
        _items.addLast( item );
    }

    // Предусловие: очередь не пуста
    // Постусловие: удален последний элемент из очереди
    public T dequeue()
    {
        if (_items.size() == 0) {
            dequeueStatus = DEQUEUE_ERR;
            return null;
        }

        dequeueStatus = DEQUEUE_OK;
        return _items.pollFirst(); // null если очередь пустая
    }

    // Запросы

    public int size() // размер очереди
    {
        return _items.size();
    }

    public int getDequeueStatus() { return dequeueStatus; }

}