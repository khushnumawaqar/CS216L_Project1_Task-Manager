import java.util.List;

/** Core logic: ties the linked list and the undo stack together. */
public class TaskManager {
    private final TaskLinkedList tasks = new TaskLinkedList();
    private final ActionStack undoStack = new ActionStack();
    private int nextId = 1;

    public Task addTask(String title, int priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.addLast(task);
        undoStack.push(new Action(Action.Type.ADD, task, tasks.size() - 1));
        return task;
    }

    public Task deleteTask(int id) {
        int index = tasks.indexOf(id);
        if (index == -1) return null;
        Task removed = tasks.removeAt(index);
        undoStack.push(new Action(Action.Type.DELETE, removed, index));
        return removed;
    }

    public boolean completeTask(int id) {
        int index = tasks.indexOf(id);
        if (index == -1) return false;
        Task task = tasks.getAt(index);
        if (task.isCompleted()) return false;
        task.setCompleted(true);
        undoStack.push(new Action(Action.Type.COMPLETE, task, index));
        return true;
    }

    public Task findById(int id) {
        return tasks.getAt(tasks.indexOf(id));
    }

    public List<Task> searchByTitle(String keyword) {
        return tasks.searchByTitle(keyword);
    }

    public void sortByPriority() { tasks.bubbleSortByPriority(); }

    /** Reverses the most recent add / delete / complete. Returns a message. */
    public String undo() {
        Action action = undoStack.pop();
        if (action == null) return "Nothing to undo.";
        Task task = action.getTask();
        switch (action.getType()) {
            case ADD:
                tasks.removeAt(tasks.indexOf(task.getId()));
                return "Undid ADD: removed task #" + task.getId();
            case DELETE:
                tasks.insertAt(action.getIndex(), task);
                return "Undid DELETE: restored task #" + task.getId();
            default:
                task.setCompleted(false);
                return "Undid COMPLETE: task #" + task.getId() + " is pending again";
        }
    }

    public String peekLastAction() {
        Action a = undoStack.peek();
        if (a == null) return "Undo stack is empty.";
        return "Last action: " + a.getType() + " on task #" + a.getTask().getId()
                + " (" + a.getTask().getTitle() + ")";
    }

    public int undoCount() { return undoStack.size(); }
    public void printAll() { tasks.printAll(); }
    public int taskCount() { return tasks.size(); }
}
