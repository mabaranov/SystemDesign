package com.company;


class ParentQueue<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int counter;
    private int getFront_status;
    private int removeFront_status;

    public final int GET_FRONT_OK = 1;
    public final int GET_FRONT_ERR = 2;
    public final int REMOVE_FRONT_OK = 1;
    public final int REMOVE_FRONT_ERR = 2;

    // Конструктор
    // Постусовие: создана пустая очередь
    public ParentQueue()
    {
        clear();
    }

    // Команды

    // Постусловие: вставляет элемента в хвост
    public void addTail(T value)
    {
        Node<T> item = new Node<>(value);
        if (size() == 0) {
            head = item;
        } else {
            item.prev = tail;
            item.prev.next = item;
        }
        tail = item;
        counter++;
    }

    // Предусловие: очерендь не пуста
    // Постусловие: удалет первый элемент из головы
    public void removeFront()
    {
        if (size() > 0) {
            T value = getFront();
            removeFront_status = REMOVE_FRONT_OK;
            if (counter == 1) {
                clear();
                return;
            }
            head = head.next;
            head.prev = null;
            counter--;
        } else {
            removeFront_status = REMOVE_FRONT_ERR;
        }
    }

    // Постусловие: очищает очередь
    public void clear() {
        head = null;
        tail = null;
        counter = 0;
    }

    // Запросы

    // Предусловие: очерендь не пуста
    // Постусловие: выдает первый элемент из головы
    public T getFront()
    {
        if (size() > 0) {
            getFront_status = GET_FRONT_OK;
            return head.value;
        }
        getFront_status = GET_FRONT_ERR;
        return null;
    }

    // Постусловие: возращает размер очереди
    public int size() { return counter; }
    public int get_getFront_status() { return getFront_status; }
    public int get_removeFront_status() { return removeFront_status; }
    Node<T> getHead() { return head; }
    void setHead(Node<T> head) { this.head = head; }
    Node<T> getTail() { return tail; }
    void setTail(Node<T> tail) { this.tail = tail; }
    void setCounter(int counter) { this.counter = counter; }
}


class Node<T>
{
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value) {
        this.value = value;
        next = null;
        prev = null;
    }
}


class Queue<T> extends ParentQueue<T> {

}

class Deque<T> extends ParentQueue<T>
{
    private int removeLast_status;
    private int getLast_status;

    public final int GET_LAST_OK = 1;
    public final int GET_LAST_ERR = 2;
    public final int REMOVE_LAST_OK = 1;
    public final int REMOVE_LAST_ERR = 2;

    // Команды

    // Постусловие: вставляет элемента в голову
    public void addFront(T value)
    {
        Node<T> item = new Node<T>(value);
        if (size() == 0) {
            setTail(item);
        } else {
            item.next = getHead();
            item.next.prev = item;
        }
        setHead(item);
        setCounter(size() + 1);
    }

    // Предусловие: очерендь не пуста
    // Постусловие: удалет первый элемент из хвоста
    public void removeLast()
    {
        if (size() > 0) {
            T value = getLast();
            removeLast_status = REMOVE_LAST_OK;
            if (size() == 1) {
                clear();
                return;
            }
            setTail(getTail().prev);
            getTail().next = null;
            setCounter(size() - 1);
        } else {
            removeLast_status = REMOVE_LAST_ERR;
        }
    }

    // Запросы

    // Предусловие: очерендь не пуста
    // Постусловие: возвращает первый элемент из хвоста
    public T getLast() {
        if (size() > 0) {
            getLast_status = GET_LAST_OK;
            return getTail().value;
        }
        getLast_status = GET_LAST_ERR;
        return null;
    }
    
    // Дополнительные запросы
    public int getGetLastStatus() { return getLast_status; }
    public int getRemoveLastStatus() { return removeLast_status; }
}