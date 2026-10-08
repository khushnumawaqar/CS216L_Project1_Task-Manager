/** Represents a single task in the Task Manager. */
public class Task {
    private final int id;
    private String title;
    private int priority;        // 1 = High, 2 = Medium, 3 = Low
    private boolean completed;

    public Task(int id, String title, int priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public String getPriorityName() {
        switch (priority) {
            case 1: return "High";
            case 2: return "Medium";
            default: return "Low";
        }
    }

    @Override
    public String toString() {
        return String.format("[ID: %-3d] %-30s | Priority: %-6s | %s",
                id, title, getPriorityName(), completed ? "Done" : "Pending");
    }
}
