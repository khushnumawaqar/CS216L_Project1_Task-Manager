/** Records one undoable action so it can be reversed later. */
public class Action {
    public enum Type { ADD, DELETE, COMPLETE }

    private final Type type;
    private final Task task;
    private final int index;   // position in the list (used to undo a delete)

    public Action(Type type, Task task, int index) {
        this.type = type;
        this.task = task;
        this.index = index;
    }

    public Type getType() { return type; }
    public Task getTask() { return task; }
    public int getIndex() { return index; }
}
