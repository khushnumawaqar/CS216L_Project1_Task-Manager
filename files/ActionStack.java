/** Stack (LIFO) of Actions, implemented with linked nodes. */
public class ActionStack {

    private static class Node {
        Action data;
        Node next;
        Node(Action data) { this.data = data; }
    }

    private Node top;
    private int size;

    /** Push an action on top. O(1). */
    public void push(Action action) {
        Node node = new Node(action);
        node.next = top;
        top = node;
        size++;
    }

    /** Remove and return the top action, or null if empty. O(1). */
    public Action pop() {
        if (isEmpty()) return null;
        Node removed = top;
        top = top.next;
        removed.next = null;
        size--;
        return removed.data;
    }

    /** Look at the top action without removing it. O(1). */
    public Action peek() {
        return isEmpty() ? null : top.data;
    }

    public boolean isEmpty() { return top == null; }
    public int size() { return size; }
}
