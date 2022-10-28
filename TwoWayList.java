
**Рефлексия:**
        2.1. Описал АТД правильно, на команды, запросы разделил правильно, со статусами намудрил, пред- и постусловия большую часть верно сделал: Не правильно предусловие right() - список не непустой, а справа есть элемент. put_left(значение) и put_right(значение) - отсутствует предусловие, а должно быть список не пустой.
        2.2. Основную мысль указал верно, но надо было еще отметить что потребовалось бы О(N) ресурсов.
        2.3. Правильно ответил но очень кратко, в эталоне описан подробно механизм.


package com.company;


class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value) {
        this.value = value;
        next = null;
        prev = null;
    }
}


class ParentList<T> {

    private Node<T> head;
    private Node<T> tail;
    private Node<T> pointer;

    private int size;
    private int head_status;
    private int tail_status;
    private int right_status;
    private int put_right_status;
    private int put_left_status;
    private int remove_status;
    private int replace_status;
    private int find_status;
    private int get_status;

    public final int NIL = 0; // еще не устанавливался
    public final int HEAD_OK = 1; // курсор корректно установлен
    public final int HEAD_ERR = 2; // список пуст
    public final int TAIL_OK = 1; // курсор корректно установлен
    public final int TAIL_ERR = 2; // список пуст
    public final int RIGHT_OK = 1; // курсор корректно установлен
    public final int RIGHT_ERR = 2; // правее нет элемента
    public final int PUT_RIGHT_OK = 1; // успешно
    public final int PUT_RIGHT_ERR = 2;// список пуст
    public final int PUT_LEFT_OK = 1; // успешно
    public final int PUT_LEFT_ERR = 2;// список пуст
    public final int REMOVE_OK = 1; // успешно
    public final int REMOVE_ERR = 2;// список пуст
    public final int REPLACE_OK = 1; // успешно
    public final int REPLACE_ERR = 2;// список пуст
    public final int FIND_OK = 1; // успешно
    public final int FIND_ERR = 2;// список пуст/ следующий не найден
    public final int GET_OK = 1; // успешно
    public final int GET_ERR = 2;// список пуст

    // Конструктор
    // Постусловие: создан новый пустой список
    public ParentList()
    {
        clear();
    }

    // Команды

    // Предусловие: список не пуст
    // Постусловие: курсор установлен на первый элемент списка
    public void head()
    {
        if (size() > 0) {
            pointer = head;
            head_status = HEAD_OK;
        } else {
            head_status = HEAD_ERR;
        }
    }

    // Предусловие: список не пуст
    // Постусловие: курсор установлен на последний элемент списка
    public void tail()
    {
        if (size() > 0) {
            pointer = tail;
            tail_status = TAIL_OK;
        } else {
            tail_status = TAIL_ERR;
        }
    }

    // Предусловие: справа от курсора есть элемент
    // Постусловие: курсор сдвинут на один элемент вправо
    public void right()
    {
        if (is_value() && pointer.next != null) {
            pointer = pointer.next;
            right_status = RIGHT_OK;
        } else {
            right_status = RIGHT_ERR;
        }
    }

    // Предусловие: список не пуст
    // Постусловие: следом за текущим элементов добавлен новый узел с заданным значением
    public void put_right(T value)
    {
        if ( size() > 0 ) {
            Node<T> node = new Node<>(value);
            if (pointer.next != null) {
                node.next = pointer.next;
                pointer.next.prev = node;
            } else {
                tail = node;
            }
            pointer.next = node;
            node.prev = pointer;
            size += 1;
            put_right_status = PUT_RIGHT_OK;
        } else {
            put_right_status = PUT_RIGHT_ERR;
        }
    }

    // Предусловие: список не пуст
    // Постусловие: перед текущим элементов добавлен новый узел с заданным значчением
    public void put_left(T value)
    {
        if ( size() > 0 ) {
            Node<T> node = new Node<>(value);
            if (pointer.prev != null) {
                node.prev.next = node;
                node.prev = pointer;
            } else {
                head = node;
            }
            pointer.prev = node;
            node.next = pointer;
            size++;
            put_left_status = PUT_LEFT_OK;
        } else {
            put_left_status = PUT_LEFT_ERR;
        }
    }

    // Предусловие: список не пуст
    // Постусловие: текущий узел удалён, курсор смещён к правому соседу, если он есть, в противном случае курсор смещён к левому соседу, если он есть
    public void remove()
    {
        if ( size() > 0 ) {
            if (is_head() && is_tail()) {
                pointer = null;
                head = null;
                tail = null;
            } else if (is_head()) {
                head = pointer.next;
                pointer = head;
                pointer.prev = null;
            } else if (is_tail()) {
                tail = pointer.prev;
                pointer = tail;
                pointer.next = null;
            } else {
                pointer.prev.next = pointer.next;
                pointer.next.prev = pointer.prev;
                pointer = pointer.next;
            }
            size--;
            remove_status = REMOVE_OK;
        } else {
            remove_status = REMOVE_ERR;
        }
    }

    // Постусловие: из списка удалены все элементы
    public void clear()
    {
        head = null;
        tail = null;
        pointer = null;
        size = 0;
        head_status = NIL;
        tail_status = NIL;
        right_status = NIL;
        put_right_status = NIL;
        put_left_status = NIL;
        remove_status = NIL;
        replace_status = NIL;
        find_status = NIL;
    }

    // Постусловие: добавлен новый элемент в хвост списка
    public void add_tail(T value)
    {
        Node<T> node = new Node<>(value);
        node.prev = tail;
        tail = node;
        size++;
        if (is_value()) {
            node.prev.next = node;
        } else {
            head = node;
            pointer = node;
        }
    }

    // Постусловие: в списке удалены все элементы с заданным значением
    public void remove_all(T value)
    {
        head();
        while (is_value()) {
            if (pointer.value == value) {
                remove();
            } else {
                right();
            }
            if (is_tail()) {
                if (pointer.value == value) {
                    remove();
                }
                return;
            }
        }
    }

    // Предусловие: список не пуст
    // Постусловие: значение текущего элемента заменено на новое
    public void replace(T value)
    {
        if (is_value()) {
            pointer.value = value;
            replace_status = REPLACE_OK;
        } else {
            replace_status = REPLACE_ERR;
        }
    }

    // Постусловие: курсор установлен на следующий узел с искомым значением, если такой узел найден
    public void find(T value)
    {
        while (is_value()) {
            if (pointer.value == value) {
                find_status = FIND_OK;
                return;
            }
            if (is_tail()) {
                find_status = FIND_ERR;
                return;
            }
            right();
        }
        find_status = FIND_ERR;
    }


    // Запросы

    // Предусловие: список не пуст
    public T get()
    {
        if ( size() > 0 ) {
            get_status = GET_OK;
            return pointer.value;
        } else {
            get_status = GET_ERR;
            return null;
        }
    }

    public boolean is_head()
    {
        return is_value() && pointer == head;
    }

    public boolean is_tail()
    {
        return is_value() && pointer == tail;
    }

    public boolean is_value()
    {
        return pointer != null;
    }

    public int size()
    {
        return size;
    }

    // Запросы статусов
    public int get_head_status()
    {
        return head_status;
    }
    public int get_tail_status()
    {
        return tail_status;
    }
    public int get_right_status()
    {
        return right_status;
    }
    public int get_put_right_status()
    {
        return put_right_status;
    }
    public int get_put_left_status()
    {
        return put_left_status;
    }
    public int get_remove_status()
    {
        return remove_status;
    }
    public int get_replace_status()
    {
        return replace_status;
    }
    public int get_find_status()
    {
        return find_status;
    }
    public int get_get_status()
    {
        return get_status;
    }
    public Node<T> get_pointer()
    {
        return pointer;
    }
    public void set_pointer(Node<T> node)
    {
        pointer = node;
    }
}

class LinkedList<T> extends ParentList<T>
{
}

class TwoWayList<T> extends ParentList<T>
{
    private int left_status;

    public final int LEFT_OK = 1; // курсор корректно установлен
    public final int LEFT_ERR = 2; // левее нет элемента


    // Предусловие: левее курсора есть элемент
    // Постусловие: курсор сдвинут на один элемент влево
    public void left()
    {
        if (is_value() && get_pointer().prev != null) {
            set_pointer(get_pointer().prev);
            left_status = LEFT_OK;
        } else {
            left_status = LEFT_ERR;
        }
    }

    public int get_left_status()
    {
        return left_status;
    }
}