# Task 3 — To-Do List Application

> **SaiKet Systems | Java Internship Program**

---

## 📋 Description

A full-featured console to-do list manager with **priority levels**, **status tracking**,
**filtering**, and detailed task views. Demonstrates OOP with two classes (`Task`, `TaskManager`)
in a single file for easy compilation.

---

## 📁 File Structure

```
Task3_TodoList/
├── TodoListApp.java    ← Source (contains Task, TaskManager, TodoListApp classes)
└── README.md           ← This file
```

After compilation:
```
Task3_TodoList/
├── TodoListApp.java
├── Task.class
├── Task$Priority.class
├── Task$Status.class
├── TaskManager.class
├── TodoListApp.class
└── README.md
```

---

## 🛠️ Skills Demonstrated

| Skill | Details |
|-------|---------|
| OOP — Classes & Objects | `Task` model class with encapsulation; `TaskManager` service class |
| Enums | `Task.Priority` (LOW/MEDIUM/HIGH) and `Task.Status` (PENDING/COMPLETED) |
| Data Structures | `ArrayList<Task>` with CRUD operations |
| Conditional Statements | Switch-case menu, enum-based filtering |
| Input Handling | `Scanner` with validation and re-prompting |

---

## ▶️ How to Compile & Run

```bash
cd Task3_TodoList
javac TodoListApp.java
java TodoListApp
```

---

## 💻 Sample Output

```
╔══════════════════════════════════════════╗
║    SaiKet Systems — To-Do List App       ║
╚══════════════════════════════════════════╝

┌────────────────────────────────────────┐
│  Tasks: 3   Pending: 3    Done: 0     │
├────────────────────────────────────────┤
│  1. Add Task           4. Delete Task  │
│  2. View All Tasks     5. Filter Tasks │
│  3. Mark Complete      6. Task Detail  │
│  0. Exit                               │
└────────────────────────────────────────┘
  Choice: 2

  ── All Tasks ──────────────────────────────────────────────────
  [✓] #ID [PRI] Title                           Priority   Status
  ──────────────────────────────────────────────────────────────────────
  [ ] #01 [!!!] Buy groceries                   HIGH       PENDING
  [ ] #02 [ ! ] Read Java book                  MEDIUM     PENDING
  [ ] #03 [   ] Morning run                     LOW        PENDING

  Choice: 3
  Enter Task ID to mark complete: 1
  ✓ Task #1 marked as COMPLETED!

  Choice: 5
  ── Filter By ─────────────────────────
  1. Pending tasks
  2. Completed tasks
  ...
  Choice: 2

  ── COMPLETED Tasks (1) ──
  [✓] #01 [!!!] Buy groceries                   HIGH       COMPLETED
```

---

## 🏗️ Class Design

```
TodoListApp.java
│
├── class Task
│   ├── enum Priority { LOW, MEDIUM, HIGH }
│   ├── enum Status   { PENDING, COMPLETED }
│   ├── Fields: id, title, description, priority, status
│   └── Methods: markComplete(), toString(), toDetailString()
│
├── class TaskManager
│   ├── List<Task> tasks (ArrayList)
│   └── Methods: addTask, findById, markComplete, deleteTask,
│                getAllTasks, getByStatus, getByPriority,
│                pendingCount, completedCount
│
└── class TodoListApp (main)
    └── Menu loop → delegates to TaskManager
```

---

## ✨ Features

- Auto-incrementing task IDs
- Priority icons: `[!!!]` HIGH · `[ ! ]` MEDIUM · `[   ]` LOW
- Filter tasks by status or priority
- Confirm before delete
- Live header showing total / pending / completed counts
- 3 sample tasks seeded on launch for instant demo