# Task 4 — Number Guessing Game

> **SaiKet Systems | Java Internship Program**

---

## 📋 Description

A classic number guessing game with difficulty levels, a hot/cold hint system,
a visual attempt progress bar, and cross-round session statistics.

---

## 📁 File Structure

```
Task4_NumberGuessing/
├── NumberGuessingGame.java    ← Source code
└── README.md                  ← This file
```

---

## 🛠️ Skills Demonstrated

| Skill | Details |
|-------|---------|
| Random Numbers | `java.util.Random` to generate secret number |
| Loops | `while` loop for game loop and attempt loop |
| Conditional Statements | Comparison of guess vs secret; hot/cold warmth calc |
| User Input Handling | `Scanner` with range & type validation |
| Core Java Concepts | Constants, static fields, helper methods |

---

## ▶️ How to Compile & Run

```bash
cd Task4_NumberGuessing
javac NumberGuessingGame.java
java NumberGuessingGame
```

---

## 💻 Sample Output

```
╔════════════════════════════════════════╗
║  SaiKet Systems — Number Guessing Game ║
╚════════════════════════════════════════╝
  Range: 1 – 100  |  Max attempts: 7

  ── Choose Difficulty ──────────────────
  1. Easy   (1–50,  10 attempts)
  2. Normal (1–100,  7 attempts)  [default]
  3. Hard   (1–500,  7 attempts)
  4. Expert (1–1000, 5 attempts)
  Choice [2]: 2

  ════ Round 1 ════════════════════════════
  I'm thinking of a number between 1 and 100.

  Attempts [○○○○○○○] 0/7 used
  Guess #1: 50
  ⬆  Too LOW   🌤  Cool

  Attempts [●○○○○○○] 1/7 used
  Guess #2: 75
  ⬇  Too HIGH  ♨  Warm

  Attempts [●●○○○○○] 2/7 used
  Guess #3: 63
  ⬆  Too LOW   🔥 Very HOT!

  Attempts [●●●○○○○] 3/7 used
  Guess #4: 67

  🎉 CORRECT! You got it in 4 guess(es)!

  ── Session Statistics ──────────────────
  Rounds played : 1
  Rounds won    : 1
  Win rate      : 100%
  Best round    : 4 guess(es)
  Avg guesses   : 4.0
  ────────────────────────────────────────

  Play again? (y/n): n

  Thanks for playing! See you at SaiKet Systems. 👋
```

---

## 🌡️ Hint System

| Distance from Secret | Warmth Label |
|----------------------|-------------|
| ≤ 5 | 🔥 Very HOT! |
| ≤ 15 | ♨ Warm |
| ≤ 30 | 🌤 Cool |
| > 30 | ❄ Very COLD |

Plus directional arrow: ⬆ Too LOW or ⬇ Too HIGH.

---

## 🎮 Difficulty Levels

| Level | Range | Max Attempts | Optimal Strategy |
|-------|-------|-------------|-----------------|
| Easy | 1–50 | 10 | Binary search needs ≤6 |
| Normal | 1–100 | 7 | Binary search needs ≤7 |
| Hard | 1–500 | 7 | Challenging — hints critical |
| Expert | 1–1000 | 5 | Very tight; needs optimal play |