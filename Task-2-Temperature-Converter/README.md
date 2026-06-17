# Task 2 — Temperature Converter

> **SaiKet Systems | Java Internship Program**

---

## 📋 Description

A console-based temperature converter supporting **Celsius, Fahrenheit, and Kelvin**.
Uses a Celsius pivot for any-to-any conversion and validates against absolute zero.

---

## 📁 File Structure

```
Task2_TempConverter/
├── TemperatureConverter.java   ← Source code
└── README.md                   ← This file
```

---

## 🛠️ Skills Demonstrated

| Skill | Details |
|-------|---------|
| Core Java Concepts | Methods, conditionals, loops |
| User Input Handling | `Scanner`, re-prompting on bad input |
| Mathematical Operations | Conversion formulas, floating-point arithmetic |
| Validation | Absolute zero boundary checks |

---

## ▶️ How to Compile & Run

```bash
cd Task2_TempConverter
javac TemperatureConverter.java
java TemperatureConverter
```

---

## 💻 Sample Output

```
╔══════════════════════════════════════╗
║  SaiKet Systems — Temp Converter     ║
╚══════════════════════════════════════╝

══════════════════════════════════════
  INPUT SCALE — choose source:
  1. Celsius    (°C)
  2. Fahrenheit (°F)
  3. Kelvin     ( K)
  Your choice  : 1
  Enter temperature       : 100

  OUTPUT SCALE — choose target:
  1. Celsius    (°C)
  2. Fahrenheit (°F)
  3. Kelvin     ( K)
  Your choice  : 2

  ┌──────────────────────────────────┐
  │  100.0000 °C (Celsius)           │
  │           ↓ converts to           │
  │  212.0000 °F (Fahrenheit)        │
  └──────────────────────────────────┘

  ── All Scales ───────────────────────
  Celsius   :    100.0000 °C
  Fahrenheit:    212.0000 °F
  Kelvin    :    373.1500  K

  Convert another? (y/n): y

  INPUT SCALE — choose source:
  ...
  Enter temperature       : -300

  [VALIDATION ERROR] Temperature below absolute zero for Celsius (min: -273.15 °C)
```

---

## 📐 Conversion Formulas

| From → To | Formula |
|-----------|---------|
| °C → °F | `(C × 9/5) + 32` |
| °F → °C | `(F − 32) × 5/9` |
| °C → K  | `C + 273.15` |
| K  → °C | `K − 273.15` |
| °F → K  | Convert to °C first, then to K |
| K  → °F | Convert to °C first, then to °F |

---

## 🌡️ Famous Reference Points

| Temperature | °C | °F | K |
|-------------|----|----|---|
| Absolute Zero | −273.15 | −459.67 | 0 |
| Water Freezes | 0 | 32 | 273.15 |
| Body Temperature | 37 | 98.6 | 310.15 |
| Water Boils | 100 | 212 | 373.15 |