# Task 6 — Basic Banking System

> **SaiKet Systems | Java Internship Program**

---

## 📋 Description

A multi-account banking system supporting Savings and Current accounts.
Implements full OOP with inheritance, custom exceptions, and transaction history.

---

## 📁 File Structure

```
Task6_BankingSystem/
├── BankingSystem.java    ← Source (all classes in one file)
└── README.md             ← This file
```

After compilation, `.class` files generated for:
`InsufficientFundsException`, `AccountNotFoundException`, `Transaction`,
`Transaction$Type`, `BankAccount`, `SavingsAccount`, `CurrentAccount`,
`Bank`, `BankingSystem`

---

## 🛠️ Skills Demonstrated

| Skill | Details |
|-------|---------|
| Inheritance | `SavingsAccount` and `CurrentAccount` extend abstract `BankAccount` |
| Encapsulation | Private fields, public getters, protected `addInterest()` |
| Polymorphism | `applyInterest()` overridden differently per account type |
| Custom Exceptions | `InsufficientFundsException`, `AccountNotFoundException` |
| Collections | `List<Transaction>` per account; `List<BankAccount>` in Bank |
| Java Time API | `LocalDateTime` and `DateTimeFormatter` for timestamps |
| Enums | `Transaction.Type` enum |

---

## ▶️ How to Compile & Run

```bash
cd Task6_BankingSystem
javac BankingSystem.java
java BankingSystem
```

---

## 💻 Sample Output

```
╔══════════════════════════════════════════════╗
║     Amit Maity — Basic Banking System    ║
║     Bank: Amit National Bank               ║
╚══════════════════════════════════════════════╝

┌──────────────────────────────────────────────┐
│                  MAIN MENU                   │
├──────────────────────────────────────────────┤
│  1. Open Savings Account                     │
│  2. Open Current Account                     │
│  3. Deposit           6. Check Balance       │
│  4. Withdraw          7. Transaction History │
│  5. Transfer          8. List All Accounts   │
│  9. Apply Monthly Interest   0. Exit         │
└──────────────────────────────────────────────┘
  Choice: 8

  ── All Accounts ─────────────────────────────────
  ACC1001  [SAVINGS]  Arjun Sharma          ₹  50000.00
  ACC1002  [SAVINGS]  Priya Patel           ₹  25000.00
  ACC1003  [CURRENT]  SaiKet Tech Ltd       ₹ 200000.00

  Total deposits in bank: ₹275000.00

  Choice: 3
  Account number : ACC1001
  Amount (₹)     : 5000
  Note (optional): Salary credit

  ✓ ₹5000.00 deposited. New balance: ₹55000.00

  Choice: 4
  Account number : ACC1001
  Amount (₹)     : 60000

  [ERROR] Insufficient funds. Requested: ₹60000.00 | Available: ₹54500.00

  Choice: 7
  Account number: ACC1001

  ── Transaction History: ACC1001 ─────────────
  2026-06-17 10:01:22  DEPOSIT          +₹  50000.00  Balance: ₹  50000.00  (Account opened)
  2026-06-17 10:03:45  DEPOSIT          +₹   5000.00  Balance: ₹  55000.00  (Salary credit)
```

---

## 🏗️ Class Hierarchy

```
BankAccount  (abstract)
│   ├── accountNumber, holderName, balance
│   ├── deposit(), withdraw(), transfer helpers
│   ├── abstract: getAccountType(), getWithdrawableBalance(), applyInterest()
│
├── SavingsAccount
│   ├── MIN_BALANCE = ₹500
│   ├── ANNUAL_RATE = 4%
│   └── getWithdrawableBalance() = balance - MIN_BALANCE
│
└── CurrentAccount
    ├── OVERDRAFT_LIMIT = ₹10,000
    ├── No interest
    └── getWithdrawableBalance() = balance + OVERDRAFT_LIMIT

Bank
└── List<BankAccount>
    ├── addAccount, findAccount, transfer
    └── applyMonthlyInterest()
```

---

## 💡 Account Rules

| Feature | Savings | Current |
|---------|---------|---------|
| Interest | 4% p.a. (monthly) | None |
| Minimum Balance | ₹500 | None |
| Overdraft | Not allowed | ₹10,000 |
| Use Case | Personal savings | Business operations |