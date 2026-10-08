import java.util.ArrayList;
import java.util.List;

/** Singly linked list (with tail pointer) that stores Task objects. */
public class TaskLinkedList {

    private static class Node {
        Task data;
        Node next;
        Node(Task data) { this.data = data; }
    }

    private Node head;
    private Node tail;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /** Insert at the end. O(1) thanks to the tail pointer. */
    public void addLast(Task task) {
        Node node = new Node(task);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /** Insert at a given index (used when undoing a delete). O(n). */
    public void insertAt(int index, Task task) {
        if (index >= size) { addLast(task); return; }
        Node node = new Node(task);
        if (index <= 0) {
            node.next = head;
            head = node;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.next;
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }

    /** Linear search by ID. Returns index or -1. O(n). */
    public int indexOf(int id) {
        Node current = head;
        int i = 0;
        while (current != null) {
            if (current.data.getId() == id) return i;
            current = current.next;
            i++;
        }
        return -1;
    }

    /** Returns the task at an index, or null. O(n). */
    public Task getAt(int index) {
        if (index < 0 || index >= size) return null;
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    /** Remove the node at an index and unlink it fully. O(n). */
    public Task removeAt(int index) {
        if (index < 0 || index >= size) return null;
        Node removed;
        if (index == 0) {
            removed = head;
            head = head.next;
            if (head == null) tail = null;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) prev = prev.next;
            removed = prev.next;
            prev.next = removed.next;
            if (removed == tail) tail = prev;
        }
        removed.next = null;   // detach node so it can be garbage collected
        size--;
        return removed.data;
    }

    /** Linear search by keyword in title (case-insensitive). O(n). */
    public List<Task> searchByTitle(String keyword) {
        List<Task> results = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Node c = head; c != null; c = c.next) {
            if (c.data.getTitle().toLowerCase().contains(key)) results.add(c.data);
        }
        return results;
    }

    /** Bubble sort by priority (1 = High first). Stable. O(n^2). */
    public void bubbleSortByPriority() {
        if (size < 2) return;
        boolean swapped;
        Node end = null;
        do {
            swapped = false;
            Node current = head;
            while (current.next != end) {
                if (current.data.getPriority() > current.next.data.getPriority()) {
                    Task temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
            end = current;   // last node of this pass is now in place
        } while (swapped);
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("  (no tasks)");
            return;
        }
        for (Node c = head; c != null; c = c.next) System.out.println("  " + c.data);
    }
}
