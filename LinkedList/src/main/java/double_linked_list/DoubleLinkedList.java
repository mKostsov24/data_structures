package double_linked_list;

import java.util.Objects;

public class DoubleLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    private int size;

    public ListItem<T> popHeadElement() {
        ListItem<T> second = head;
        removeHeadElement();
        return second;
    }

    public ListItem<T> popTailElement() {
        ListItem<T> second = tail;
        removeTailElement();

        return second;
    }

    public void removeHeadElement() {

        if (head.next == null) {
            head = null;
            tail = null;
            size = 0;
        } else if((head.next.next == null)) {
            head = head.next;
            head.prev = null;
            head.next = null;
            tail = head;
            size = 1;
        } else {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    public void removeTailElement() {
        if (tail.prev == null) {
            head = null;
            tail = null;
            size = 0;
        } else if((tail.prev.prev == null)) {
            tail = tail.prev;
            tail.prev = null;
            tail.next = null;
            head = tail;
            size = 1;
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
    }

    public void addToHead(T data) {

        if (head == null) {
            head = new ListItem<>(data);
            tail = head;
            size = 1;
        } else if (head.next == null) {
            ListItem<T> second = new ListItem<>(data);
            second.prev = null;
            second.next = tail;
            head = second;
            tail.prev = head ;
            size = 2;
        }else {
            ListItem<T> second = head;
            head = new ListItem<>(data);
            head.next = second;
            head.prev = null;
            second.prev = head;
            size++;
        }
    }

    public void addToTail(T data) {
        if (tail == null) {
            tail = new ListItem<T>(data);
            head = tail;
            size = 1;
        } else if (tail.prev == null) {
            ListItem<T> second = new ListItem<>(data);
            second.next = null;
            second.prev = head;
            tail = second;
            head.next = tail;
            size = 2;
        } else {
            ListItem<T> second = tail;
            tail = new ListItem<>(data);
            tail.prev = second;
            tail.next = null;
            second.next = tail;
            size++;
        }
    }


    public ListItem<T> get(int index) {
        if (index == 0){
            return head;}
        else if (index == size - 1){
            return tail;
        }
        ListItem<T> item = head;
        while (index > 0) {
            item = item.next;
            index--;
        }
        return item;
    }

    public void remove(int index) {

        if (index == 0) {
            removeHeadElement();
        } else if (index == size - 1) {
            removeTailElement();
        } else {
            ListItem<T> previous = null;
            ListItem<T> finger = head;
            while (index > 0) {
                previous = finger;
                finger = finger.next;
                index--;
            }
            previous.next = finger.next;
            finger.next.prev = previous;
            size--;

        }
    }

    public void add(int index, T data) {

        if (index == 0) {
            addToHead(data);
        } else if (index == size-1) {
            addToTail(data);
        } else {
            ListItem<T> previous = null;
            ListItem<T> next = head;
            while (index > 0) {
                previous = next;
                next = next.next;
                index--;
            }
            ListItem<T> current = new ListItem<>(data);
            current.next = next;
            current.prev = previous;
            previous.next = current;
            next.prev = current;
            size++;
        }
    }

    public int getSize() {
        return size;
    }

    public ListItem<T> getHeadElement() {
        return head;
    }

    public ListItem<T> getTailElement() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<T> that = (DoubleLinkedList<T>) o;
        return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    @Override
    public String toString() {
        if (head == null) {
            return "DoubleLinkedList is empty size = " + size;
        }

        StringBuilder stringBuilder = new StringBuilder(head.toString());
        ListItem<T> item = head;
        while (item.next != null) {
            if (item.next.prev == item) {
                stringBuilder.append("<-");
            }

            stringBuilder.append(" -> ").append(item.next);
            item = item.next;
        }

        return "DoubleLinkedList{size=" + size + "\n" + stringBuilder.toString() + "}";
    }
}