import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    public enum Priority { LOW, MEDIUM, HIGH }
    public enum Status   { PENDING, COMPLETED }

    private static int idCounter = 1;

    private final int    id;
    private       String title;
    private       String description;
    private       Priority priority;
    private       Status   status;

    public Task(String title, String description, Priority priority) {
        this.id          = idCounter++;
        this.title       = title;
        this.description = description;
        this.priority    = priority;
        this.status      = Status.PENDING;
    }

    // Getters
    public int      getId()          { return id; }
    public String   getTitle()       { return title; }
    public String   getDescription() { return description; }
    public Priority getPriority()    { return priority; }
    public Status   getStatus()      { return status; }
    public boolean  isCompleted()    { return status == Status.COMPLETED; }

    // Actions
    public void markComplete()           { this.status   = Status.COMPLETED; }
    public void setTitle(String t)       { this.title    = t; }
    public void setDescription(String d) { this.description = d; }
    public void setPriority(Priority p)  { this.priority = p; }

    // Display
    private String priorityIcon() {
        switch (priority) {
            case HIGH:   return "[!!!]";
            case MEDIUM: return "[ ! ]";
            default:     return "[   ]";
        }
    }

    @Override
    public String toString() {
        String check = isCompleted() ? "Right" : " ";
        return String.format("[%s] #%02d %s %-30s | %s | %s",
            check, id, priorityIcon(), title,
            String.format("%-9s", priority),
            status);
    }

    public String toDetailString() {
        return String.format(
            "  ID          : %d%n" +
            "  Title       : %s%n" +
            "  Description : %s%n" +
            "  Priority    : %s%n" +
            "  Status      : %s%n",
            id, title, description, priority, status);
    }
}

// ─────────────────────────────────────────────
//  TaskManager — Business Logic
// ─────────────────────────────────────────────
class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task t)   { tasks.add(t); }

    public Task findById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public boolean markComplete(int id) {
        Task t = findById(id);
        if (t == null) return false;
        t.markComplete();
        return true;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    public List<Task> getAllTasks()       { return new ArrayList<>(tasks); }
    public int        size()             { return tasks.size(); }

    public List<Task> getByStatus(Task.Status s) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) if (t.getStatus() == s) result.add(t);
        return result;
    }

    public List<Task> getByPriority(Task.Priority p) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) if (t.getPriority() == p) result.add(t);
        return result;
    }

    public int pendingCount()   { return getByStatus(Task.Status.PENDING).size(); }
    public int completedCount() { return getByStatus(Task.Status.COMPLETED).size(); }
}

// ─────────────────────────────────────────────
//  Main Application
// ─────────────────────────────────────────────
public class TodoListApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final TaskManager manager = new TaskManager();

    // ── Menu ──────────────────────────────────

    private static void printBanner() {
        System.out.println("============================================");
        System.out.println("|    Amit Maity — To-Do List App       |");
        System.out.println("============================================");
    }

    private static void printMenu() {
        System.out.println("\n------------------------------------------");
        System.out.printf ("|  Tasks: %-3d  Pending: %-3d  Done: %-3d   |%n",
            manager.size(), manager.pendingCount(), manager.completedCount());
        System.out.println("------------------------------------------");
        System.out.println("|  1. Add Task           4. Delete Task  |");
        System.out.println("|  2. View All Tasks     5. Filter Tasks |");
        System.out.println("|  3. Mark Complete      6. Task Detail  |");
        System.out.println("|  0. Exit                               |");
        System.out.println("------------------------------------------");
        System.out.print("  Choice: ");
    }

    // ── Input Helpers ─────────────────────────

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  [ERROR] Enter a valid number."); }
        }
    }

    private static Task.Priority readPriority() {
        while (true) {
            System.out.println("  Priority: 1=LOW  2=MEDIUM  3=HIGH");
            System.out.print("  Choice  : ");
            String in = sc.nextLine().trim();
            switch (in) {
                case "1": return Task.Priority.LOW;
                case "2": return Task.Priority.MEDIUM;
                case "3": return Task.Priority.HIGH;
                default: System.out.println("  [ERROR] Enter 1, 2, or 3.");
            }
        }
    }

    // ── Operations ────────────────────────────

    private static void addTask() {
        System.out.println("\n  -- Add New Task ----------------------");
        String title = readString("  Title       : ");
        if (title.isEmpty()) { System.out.println("  [ERROR] Title cannot be empty."); return; }
        String desc  = readString("  Description : ");
        Task.Priority priority = readPriority();
        manager.addTask(new Task(title, desc.isEmpty() ? "—" : desc, priority));
        System.out.println("  ✓ Task added successfully!");
    }

    private static void viewAllTasks() {
        System.out.println("\n  -- All Tasks -------------------------");
        List<Task> all = manager.getAllTasks();
        if (all.isEmpty()) {
            System.out.println("  (No tasks yet. Add one!)");
        } else {
            System.out.println("  [Find] #ID [PRI] Title                           Priority   Status");
            System.out.println("  " + "─".repeat(70));
            for (Task t : all) System.out.println("  " + t);
        }
    }

    private static void markComplete() {
        viewAllTasks();
        int id = readInt("\n  Enter Task ID to mark complete: ");
        Task t = manager.findById(id);
        if (t == null) { System.out.println("  [ERROR] Task #" + id + " not found."); return; }
        if (t.isCompleted()) { System.out.println("  [INFO] Task already completed."); return; }
        manager.markComplete(id);
        System.out.println("  Task #" + id + " marked as COMPLETED!");
    }

    private static void deleteTask() {
        viewAllTasks();
        int id = readInt("\n  Enter Task ID to delete: ");
        System.out.print("  Confirm delete Task #" + id + "? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) { System.out.println("  Cancelled."); return; }
        if (manager.deleteTask(id)) System.out.println("  ✓ Task #" + id + " deleted.");
        else System.out.println("  [ERROR] Task #" + id + " not found.");
    }

    private static void filterTasks() {
        System.out.println("\n  -- Filter By -------------------------");
        System.out.println("  1. Pending tasks");
        System.out.println("  2. Completed tasks");
        System.out.println("  3. HIGH priority");
        System.out.println("  4. MEDIUM priority");
        System.out.println("  5. LOW priority");
        System.out.print("  Choice: ");
        String c = sc.nextLine().trim();
        List<Task> filtered;
        String label;
        switch (c) {
            case "1": filtered = manager.getByStatus(Task.Status.PENDING);      label = "PENDING";        break;
            case "2": filtered = manager.getByStatus(Task.Status.COMPLETED);    label = "COMPLETED";      break;
            case "3": filtered = manager.getByPriority(Task.Priority.HIGH);     label = "HIGH priority";  break;
            case "4": filtered = manager.getByPriority(Task.Priority.MEDIUM);   label = "MEDIUM priority";break;
            case "5": filtered = manager.getByPriority(Task.Priority.LOW);      label = "LOW priority";   break;
            default:  System.out.println("  [ERROR] Invalid choice."); return;
        }
        System.out.println("\n  ── " + label + " Tasks (" + filtered.size() + ") ──");
        if (filtered.isEmpty()) System.out.println("  (None)");
        else for (Task t : filtered) System.out.println("  " + t);
    }

    private static void showDetail() {
        viewAllTasks();
        int id = readInt("\n  Enter Task ID for detail: ");
        Task t = manager.findById(id);
        if (t == null) { System.out.println("  [ERROR] Task #" + id + " not found."); return; }
        System.out.println("\n  -- Task Detail -----------------------");
        System.out.print(t.toDetailString());
    }

    // ── Main ──────────────────────────────────

    public static void main(String[] args) {
        printBanner();

        // Seed sample tasks
        manager.addTask(new Task("Buy groceries",   "Milk, eggs, bread, fruits", Task.Priority.HIGH));
        manager.addTask(new Task("Read Java book",  "Finish Chapter 5 on OOP",   Task.Priority.MEDIUM));
        manager.addTask(new Task("Morning run",     "5 km jog at the park",      Task.Priority.LOW));

        boolean running = true;
        while (running) {
            printMenu();
            String input = sc.nextLine().trim();
            switch (input) {
                case "1": addTask();      break;
                case "2": viewAllTasks(); break;
                case "3": markComplete(); break;
                case "4": deleteTask();   break;
                case "5": filterTasks();  break;
                case "6": showDetail();   break;
                case "0":
                    System.out.println("\n  Goodbye! Stay productive with SaiKet!");
                    running = false;
                    break;
                default:
                    System.out.println("  [ERROR] Invalid choice. Enter 0 to 6.");
            }
        }
        sc.close();
    }
}