
<h3 align="center">SaiKet Systems | Java Internship Program</h3>

<p align="center">
Building Real-World Java Applications with Core Java, OOP, File Handling & Software Design
</p>

<div align="center">

# ☕ Java Project Portfolio

### Successfully Completed During the SaiKet Systems Java Training Program

<img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" />
<img src="https://img.shields.io/badge/OOP-Concepts-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/File-I/O-green?style=for-the-badge" />
<img src="https://img.shields.io/badge/Collections-Framework-red?style=for-the-badge" />
<img src="https://img.shields.io/badge/Status-Completed-success?style=for-the-badge" />

### 👨‍💻 AMIT KUMAR MAITY

### Java Developer | Software Engineering Student | Open Source Enthusiast

---

```text
    _    __  __ ___ _____   _  ___   _ __  __    _    ____    __  __    _    ___ _______   __
   / \  |  \/  |_ _|_   _| | |/ / | | |  \/  |  / \  |  _ \  |  \/  |  / \  |_ _|_   _\ \ / /
  / _ \ | |\/| || |  | |   | ' /| | | | |\/| | / _ \ | |_) | | |\/| | / _ \  | |  | |  \ V /
 / ___ \| |  | || |  | |   | . \| |_| | |  | |/ ___ \|  _ <  | |  | |/ ___ \ | |  | |   | |
/_/   \_\_|  |_|___| |_|   |_|\_\\___/|_|  |_/_/   \_\_| \_\ |_|  |_/_/   \_\___| |_|   |_|
````

### 💡 From Java Fundamentals to Advanced Object-Oriented Programming

</div>

---

# 📌 About This Repository

This repository contains six complete Java console applications developed as part of the SaiKet Systems Java Training Program.

The projects demonstrate:

✅ Core Java Programming

✅ Object-Oriented Programming (OOP)

✅ Collections Framework

✅ File Handling & Data Persistence

✅ Exception Handling

✅ Business Logic Development

✅ Software Design Principles

✅ Real-World Application Development

---

```
```

---

## 📖 Overview

This repository contains **six complete Java console applications** developed as
part of the **SaiKet Systems Java Internship Program**. Each task progressively
builds on the previous, taking learners from basic Java syntax all the way through
full object-oriented design with inheritance, polymorphism, and custom exceptions.

---

## 📁 Full Project Structure

```
SaiketSystems/
│
├── README.md                          ← This file (master overview)
│
├── Task1_Calculator/
│   ├── Calculator.java
│   └── README.md
│
├── Task2_TempConverter/
│   ├── TemperatureConverter.java
│   └── README.md
│
├── Task3_TodoList/
│   ├── TodoListApp.java
│   └── README.md
│
├── Task4_NumberGuessing/
│   ├── NumberGuessingGame.java
│   └── README.md
│
├── Task5_TextAnalyzer/
│   ├── TextFileAnalyzer.java
│   ├── sample.txt                ← Auto-generated on first run
│   └── README.md
│
└── Task6_BankingSystem/
    ├── BankingSystem.java
    └── README.md
```

---

## 🗺️ Task Overview

| # | Task | File | Difficulty | Key Concept |
|---|------|------|------------|-------------|
| 1 | Calculator Application | `Calculator.java` | ⭐ Beginner | Methods, Exception Handling |
| 2 | Temperature Converter  | `TemperatureConverter.java` | ⭐ Beginner | Methods, Math Operations |
| 3 | To-Do List Application | `TodoListApp.java` | ⭐⭐ Intermediate | OOP, ArrayList, Enums |
| 4 | Number Guessing Game   | `NumberGuessingGame.java` | ⭐⭐ Intermediate | Random, Loops, Logic |
| 5 | Text File Analyzer     | `TextFileAnalyzer.java` | ⭐⭐⭐ Advanced | File I/O, String Manipulation |
| 6 | Basic Banking System   | `BankingSystem.java` | ⭐⭐⭐ Advanced | Inheritance, Polymorphism |

---

## 🛠️ Skills Progression Map

```
TASK 1 ──► TASK 2 ──► TASK 3 ──────────► TASK 4 ──► TASK 5 ──► TASK 6
  │           │           │                  │           │           │
Methods    Math Ops    Classes &          Random &    File I/O    Inheritance
Exceptions  Formulas   ArrayList          Loops       Strings     Polymorphism
User Input  Validation  Enums             Statistics  HashMap     Custom Exceptions
Switch-Case Multi-scale CRUD ops          Difficulty  BufferedIO  Abstract Classes
```

---

## ⚡ Quick Start — Compile & Run All

### Prerequisites
- Java JDK 8 or higher
- Verify: `java -version` and `javac -version`

### One-liner compile script (Linux/macOS)

```bash
# From the SaiketSystems root folder:
for dir in Task*/; do
  echo "=== Compiling $dir ==="
  javac "$dir"*.java -d "$dir"
done
echo "All tasks compiled!"
```

### Windows (Command Prompt)

```cmd
for /d %d in (Task*) do (
  echo === Compiling %d ===
  javac %d\*.java -d %d
)
```

### Run individual tasks

```bash
# Task 1
cd Task1_Calculator && javac Calculator.java && java Calculator

# Task 2
cd Task2_TempConverter && javac TemperatureConverter.java && java TemperatureConverter

# Task 3
cd Task3_TodoList && javac TodoListApp.java && java TodoListApp

# Task 4
cd Task4_NumberGuessing && javac NumberGuessingGame.java && java NumberGuessingGame

# Task 5
cd Task5_TextAnalyzer && javac TextFileAnalyzer.java && java TextFileAnalyzer

# Task 6
cd Task6_BankingSystem && javac BankingSystem.java && java BankingSystem
```

---

## 📋 Detailed Task Descriptions

---

### Task 1 — Calculator Application

**Goal:** Build an arithmetic calculator with a user-friendly menu.

**Features:**
- Six operations: Addition, Subtraction, Multiplication, Division, Modulus, Power
- Division-by-zero and modulus-by-zero exception handling
- Non-numeric input recovery (re-prompts without crashing)
- Continuous operation until user exits

**Sample Interaction:**
```
Enter choice: 4
Enter first number : 22
Enter second number: 0
[MATH ERROR] Division by zero is not allowed!

Enter choice: 6
Enter first number : 2
Enter second number: 8
Result: 2.00 ^ 8.00 = 256.000000
```

**Core Skills:** `static` methods, `switch-case`, `try-catch`, `Scanner`

---

### Task 2 — Temperature Converter

**Goal:** Convert between Celsius, Fahrenheit, and Kelvin.

**Features:**
- Any-to-any conversion via Celsius pivot
- Absolute zero boundary validation for all scales
- Shows conversion result AND all three scale equivalents
- Continuous conversion loop

**Conversion Formulas Used:**
```
°C → °F : (C × 9/5) + 32
°F → °C : (F − 32) × 5/9
°C →  K : C + 273.15
 K → °C : K − 273.15
```

**Core Skills:** Mathematical operations, method decomposition, input validation

---

### Task 3 — To-Do List Application

**Goal:** Build a full task manager with priorities, status tracking, and filtering.

**Features:**
- Add tasks with title, description, and priority (LOW/MEDIUM/HIGH)
- View all tasks with visual priority icons: `[!!!]` `[ ! ]` `[   ]`
- Mark tasks as COMPLETE
- Delete with confirmation prompt
- Filter by status or priority
- View detailed task info
- Live summary header (total / pending / completed)

**Class Design:**
```
Task (model) → TaskManager (service) → TodoListApp (UI)
```

**Core Skills:** OOP classes & objects, `ArrayList`, enums, encapsulation

---

### Task 4 — Number Guessing Game

**Goal:** Guess a randomly generated number with feedback and difficulty levels.

**Features:**
- 4 difficulty levels (Easy / Normal / Hard / Expert) with different ranges
- Hot/Cold proximity hints (Very Hot / Warm / Cool / Very Cold)
- Directional arrow hints (Too High / Too Low)
- Visual progress bar: `[●●●○○○○] 3/7 used`
- Warnings at 2 remaining and last attempt
- Cross-round session statistics (win rate, best round, avg guesses)

**Core Skills:** `java.util.Random`, `while` loops, conditionals, static fields

---

### Task 5 — Text File Analyzer

**Goal:** Analyze any `.txt` file for comprehensive statistics.

**Features:**
- Counts: lines, words, sentences, paragraphs
- Counts: characters with and without spaces
- Top-10 most frequent word ranking
- Longest line detection
- Reading level estimate (Elementary → Academic)
- Case-insensitive keyword search with line numbers and occurrence counts
- Built-in sample file creator for instant demo

**Core Skills:** `BufferedReader`, `FileReader`, `FileWriter`, `HashMap`, regex

---

### Task 6 — Basic Banking System

**Goal:** Simulate a multi-account banking system with full OOP.

**Features:**
- Open Savings (4% annual interest, ₹500 min balance) or Current accounts (₹10K overdraft)
- Deposit, Withdraw, Transfer between accounts
- Check balance (with withdrawable balance for savings)
- Full timestamped transaction history per account
- List all accounts with totals
- Apply monthly interest to all savings accounts
- 3 pre-seeded demo accounts on launch

**Inheritance Hierarchy:**
```
BankAccount (abstract)
├── SavingsAccount  → 4% interest, minimum balance rule
└── CurrentAccount  → overdraft facility, no interest
```

**Custom Exceptions:**
- `InsufficientFundsException` (with amount vs available balance detail)
- `AccountNotFoundException`

**Core Skills:** Abstract classes, inheritance, polymorphism, custom exceptions, `LocalDateTime`

---

## 📊 Skills Matrix

| Java Concept | T1 | T2 | T3 | T4 | T5 | T6 |
|---|:---:|:---:|:---:|:---:|:---:|:---:|
| Static Methods | ✅ | ✅ | ✅ | ✅ | ✅ | |
| Exception Handling | ✅ | ✅ | | | ✅ | ✅ |
| Custom Exceptions | | | | | | ✅ |
| Scanner / User Input | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Classes & Objects | | | ✅ | | ✅ | ✅ |
| Inheritance | | | | | | ✅ |
| Abstract Classes | | | | | | ✅ |
| Polymorphism | | | | | | ✅ |
| Enums | | | ✅ | | ✅ | ✅ |
| ArrayList | | | ✅ | | ✅ | ✅ |
| HashMap | | | | | ✅ | |
| Random | | | | ✅ | | |
| File I/O | | | | | ✅ | |
| String Manipulation | | ✅ | | | ✅ | |
| Math Operations | ✅ | ✅ | | | | |
| Java Time API | | | | | | ✅ |
| Switch-Case | ✅ | ✅ | ✅ | | ✅ | ✅ |
| Loops (while/for) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |

---

## 🧪 Testing Each Task

### Task 1 — Calculator
| Test | Input | Expected Output |
|------|-------|----------------|
| Addition | `1`, `25`, `17` | `42.000000` |
| Division by zero | `4`, `10`, `0` | `[MATH ERROR] Division by zero is not allowed!` |
| Power | `6`, `2`, `10` | `1024.000000` |
| Invalid input | `abc` | `[ERROR] Please enter a valid menu number.` |

### Task 2 — Temperature Converter
| Test | Input | Expected |
|------|-------|---------|
| Boiling point | `1` (°C), `100` | 212.0000 °F |
| Absolute zero | `1` (°C), `-274` | Validation error |
| Body temp | `2` (°F), `98.6` → K | 310.1500 K |

### Task 3 — To-Do List
| Action | Result |
|--------|--------|
| Add task with empty title | `[ERROR] Title cannot be empty.` |
| Mark already-complete task | `[INFO] Task already completed.` |
| Delete → n at confirm | `Cancelled.` |

### Task 4 — Number Guessing
| Scenario | Behaviour |
|----------|-----------|
| Out-of-range guess | Re-prompts with range reminder |
| Non-numeric input | `[ERROR] Enter a whole number.` |
| 2 attempts left | Warning displayed |
| Correct guess | Congratulation + stats update |

### Task 5 — Text File Analyzer
| Input | Expected |
|-------|---------|
| Non-existent file | `[ERROR] File not found: ...` |
| Option 3 (sample) | Creates + analyzes `sample_text.txt` |
| Search "java" | All line numbers and counts |

### Task 6 — Banking System
| Action | Result |
|--------|--------|
| Withdraw > (balance − min) from Savings | `InsufficientFundsException` |
| Find non-existent account | `AccountNotFoundException` |
| Current account overdraft | Allowed up to ₹10,000 |
| Monthly interest | All savings accounts updated |

---

## 👨‍💻 Author & Organization

| Field | Info |
|-------|------|
| Organization | **SaiKet Systems** |
| Program | Java Training Program |
| Language | Java 8+ |
| Paradigm | Procedural → Object-Oriented |
| IDE Recommended | IntelliJ IDEA / VS Code / Eclipse |

---

## 📚 Learning Path Recommendation

```
Week 1  ──► Tasks 1 & 2  (Syntax, methods, math, input)
Week 2  ──► Tasks 3 & 4  (OOP basics, collections, game logic)
Week 3  ──► Tasks 5 & 6  (File I/O, full OOP, inheritance)
```

---

## 🚀 Extension Challenges

Once comfortable with all 6 tasks, try these upgrades:

1. **Task 1+**: Add scientific calculator functions (sin, cos, log, sqrt)
2. **Task 3+**: Persist tasks to a file between sessions (File I/O)
3. **Task 4+**: Add a leaderboard stored in a file (top 5 scores)
4. **Task 5+**: Generate an HTML report of the analysis
5. **Task 6+**: Add PIN authentication and account locking after 3 wrong attempts
6. **Combined**: Build a CLI that integrates tasks 3 & 6 — a personal finance tracker

---
---

# 🏆 Internship Highlights

✔ Successfully completed all assigned Java Development tasks.

✔ Built multiple real-world applications using Java.

✔ Applied software engineering best practices.

✔ Demonstrated strong understanding of Core Java and OOP.

✔ Developed practical solutions for automation and data management.

---

# 🤝 Connect With Me

### 👨‍💻 Amit Kumar Maity

📧 Email: [your-email@example.com](mailto:maityamit5564@gmail.com)

💼 LinkedIn: [https://linkedin.com/in/your-profile](https://www.linkedin.com/in/amit-kumar-maity8976/)

🌐 GitHub: [https://github.com/your-github-username](https://github.com/AmitMaity123-bit/)

---

<div align="center">

## ⭐ If you found this repository useful, consider giving it a Star!

### Thank You For Visiting 🚀

</div>

*"Code is not just instructions for machines — it's a craft that reflects your thinking."*
*— SaiKet Systems Java Internship Program*
