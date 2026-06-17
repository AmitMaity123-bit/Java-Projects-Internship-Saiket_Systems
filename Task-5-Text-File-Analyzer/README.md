# Task 5 — Text File Analyzer

> **SaiKet Systems | Java Internship Program**

---

## 📋 Description

Analyzes any `.txt` file and reports: line count, word count, character counts,
sentence/paragraph counts, top-10 word frequency, longest line, reading level
estimate, and keyword search with line numbers.

---

## 📁 File Structure

```
Task5_TextAnalyzer/
├── TextFileAnalyzer.java    ← Source code
├── sample.txt          ← Auto-created by option 3 (after first run)
└── README.md                ← This file
```

---

## 🛠️ Skills Demonstrated

| Skill | Details |
|-------|---------|
| File I/O | `BufferedReader`, `FileReader`, `FileWriter` |
| String Manipulation | `split()`, `replaceAll()` with regex, `toLowerCase()`, `indexOf()` |
| Exception Handling | `FileNotFoundException`, `IOException` |
| Data Structures | `HashMap` for frequency, `ArrayList` for search results |
| Sorting | `List.sort()` with custom comparator |

---

## ▶️ How to Compile & Run

```bash
cd Task5_TextAnalyzer
javac TextFileAnalyzer.java
java TextFileAnalyzer
```

---

## 💻 Sample Output

```
╔════════════════════════════════════════════╗
║    SaiKet Systems — Text File Analyzer     ║
╚════════════════════════════════════════════╝

  ── Main Menu ─────────────────────────────
  1. Analyze a text file
  2. Search word in file
  3. Create & analyze sample file
  0. Exit
  Choice: 3

  [INFO] Sample file created: sample_text.txt

  ┌──────────────────────────────────────────┐
  │              FILE ANALYSIS               │
  ├──────────────────────────────────────────┤
  │  File        : sample.txt           │
  ├──────────────────────────────────────────┤
  │  Lines       : 12                        │
  │  Words       : 147                       │
  │  Sentences   : 9                         │
  │  Paragraphs  : 5                         │
  ├──────────────────────────────────────────┤
  │  Chars (w/ spaces)   : 869               │
  │  Chars (w/o spaces)  : 727               │
  │  Avg word length     : 4.95              │
  │  Longest line (chars): 77                │
  ├──────────────────────────────────────────┤
  │  Reading Level: High School              │
  └──────────────────────────────────────────┘

  ── Top 10 Most Frequent Words ──────────────
   1. java                  8 occurrence(s)
   2. programming           5 occurrence(s)
   3. is                    4 occurrence(s)
   4. a                     4 occurrence(s)
   ...

  Choice: 2
  Enter file path  : sample_text.txt
  Enter search word: java

  Found "java" 8 time(s) across 6 line(s):
    Line    1 — 1 occurrence(s)
    Line    4 — 2 occurrence(s)
    Line    5 — 1 occurrence(s)
    ...
```

---

## 📊 Analysis Metrics

| Metric | How it's Calculated |
|--------|---------------------|
| Words | Tokens split by whitespace, punctuation stripped |
| Characters w/ spaces | `line.length() + 1` per line |
| Characters w/o spaces | Length of each cleaned word token |
| Sentences | Count of `.`, `!`, `?` characters |
| Paragraphs | Sequences of non-blank lines separated by blank lines |
| Avg Word Length | `charsWithoutSpaces / words` |
| Reading Level | Avg word length thresholds (4/5/6/7) |

---

## 🔍 Search Feature

- Case-insensitive substring search
- Reports every line number and occurrence count per line
- Uses `String.indexOf()` in a loop for overlapping-safe counting