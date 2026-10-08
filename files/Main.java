import java.util.List;
import java.util.Scanner;

/** Menu-driven console interface for the Task Manager. */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        int choice;
        do {
            printMenu();
            choice = readInt("Enter choice: ", 0, 8);
            System.out.println();
            switch (choice) {
                case 1:
                    String title = readNonEmpty("Task title: ");
                    int priority = readInt("Priority (1=High, 2=Medium, 3=Low): ", 1, 3);
                    Task t = manager.addTask(title, priority);
                    System.out.println("Added: " + t);
                    break;
                case 2:
                    if (manager.taskCount() == 0) { System.out.println("No tasks to delete."); break; }
                    int delId = readInt("Task ID to delete: ", 1, Integer.MAX_VALUE);
                    Task removed = manager.deleteTask(delId);
                    System.out.println(removed == null ? "Task not found." : "Deleted: " + removed);
                    break;
                case 3:
                    System.out.println("Search by: 1) ID   2) Title keyword");
                    if (readInt("Choice: ", 1, 2) == 1) {
                        Task found = manager.findById(readInt("ID: ", 1, Integer.MAX_VALUE));
                        System.out.println(found == null ? "Task not found." : "Found: " + found);
                    } else {
                        List<Task> results = manager.searchByTitle(readNonEmpty("Keyword: "));
                        if (results.isEmpty()) System.out.println("No matching tasks.");
                        for (Task r : results) System.out.println("  " + r);
                    }
                    break;
                case 4:
                    manager.sortByPriority();
                    System.out.println("Tasks sorted by priority (High -> Low).");
                    manager.printAll();
                    break;
                case 5:
                    System.out.println("All tasks (" + manager.taskCount() + "):");
                    manager.printAll();
                    break;
                case 6:
                    System.out.println(manager.undo());
                    break;
                case 7:
                    int doneId = readInt("Task ID to mark complete: ", 1, Integer.MAX_VALUE);
                    System.out.println(manager.completeTask(doneId)
                            ? "Task marked complete." : "Task not found or already complete.");
                    break;
                case 8:
                    System.out.println(manager.peekLastAction()
                            + "  | Undo stack size: " + manager.undoCount());
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n===== TASK MANAGER =====");
        System.out.println("1. Add task");
        System.out.println("2. Delete task");
        System.out.println("3. Search task (linear search)");
        System.out.println("4. Sort tasks by priority (bubble sort)");
        System.out.println("5. View all tasks");
        System.out.println("6. Undo last action");
        System.out.println("7. Mark task complete");
        System.out.println("8. Peek last action (stack top)");
        System.out.println("0. Exit");
    }

    /** Keeps asking until the user enters an integer within [min, max]. */
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) System.exit(0);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println("Invalid input. Enter a number from " + min
                    + (max == Integer.MAX_VALUE ? " upward." : " to " + max + "."));
        }
    }

    /** Keeps asking until the user enters non-blank text. */
    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) System.exit(0);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("Input cannot be empty.");
        }
    }
}
