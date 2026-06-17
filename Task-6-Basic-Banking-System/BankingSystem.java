import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ─────────────────────────────────────────────
//  Custom Exceptions
// ─────────────────────────────────────────────

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount, double balance) {
        super(String.format("Insufficient funds. Requested: ₹%.2f | Available: ₹%.2f",
            amount, balance));
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accNo) {
        super("Account not found: " + accNo);
    }
}

// ─────────────────────────────────────────────
//  Transaction Record
// ─────────────────────────────────────────────

class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL, TRANSFER_OUT, TRANSFER_IN, INTEREST }

    private final Type   type;
    private final double amount;
    private final double balanceAfter;
    private final String timestamp;
    private final String note;

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction(Type type, double amount, double balanceAfter, String note) {
        this.type         = type;
        this.amount       = amount;
        this.balanceAfter = balanceAfter;
        this.note         = note;
        this.timestamp    = LocalDateTime.now().format(FMT);
    }

    @Override
    public String toString() {
        String sign = (type == Type.WITHDRAWAL || type == Type.TRANSFER_OUT) ? "-" : "+";
        return String.format("  %s  %-15s  %s₹%10.2f  Balance: ₹%10.2f  %s",
            timestamp, type, sign, amount, balanceAfter,
            note.isEmpty() ? "" : "(" + note + ")");
    }
}

// ─────────────────────────────────────────────
//  Abstract Base Account
// ─────────────────────────────────────────────

abstract class BankAccount {
    private static int counter = 1000;

    private final String        accountNumber;
    private final String        holderName;
    private       double        balance;
    private final List<Transaction> history = new ArrayList<>();

    public BankAccount(String holderName, double initialDeposit) {
        this.accountNumber = "ACC" + (++counter);
        this.holderName    = holderName;
        this.balance       = 0;
        deposit(initialDeposit, "Account opened");
    }

    // ── Accessors ─────────────────────────────

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName()    { return holderName; }
    public double getBalance()       { return balance; }
    public List<Transaction> getHistory() { return history; }

    // ── Core Operations ────────────────────────

    public void deposit(double amount, String note) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        history.add(new Transaction(Transaction.Type.DEPOSIT, amount, balance, note));
    }

    public void withdraw(double amount, String note) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > getWithdrawableBalance()) {
            throw new InsufficientFundsException(amount, getWithdrawableBalance());
        }
        balance -= amount;
        history.add(new Transaction(Transaction.Type.WITHDRAWAL, amount, balance, note));
    }

    public void addTransferIn(double amount, String fromAcc) {
        balance += amount;
        history.add(new Transaction(Transaction.Type.TRANSFER_IN, amount, balance, "From " + fromAcc));
    }

    public void recordTransferOut(double amount, String toAcc) {
        balance -= amount;
        history.add(new Transaction(Transaction.Type.TRANSFER_OUT, amount, balance, "To " + toAcc));
    }

    protected void addInterest(double interestAmount) {
        balance += interestAmount;
        history.add(new Transaction(Transaction.Type.INTEREST, interestAmount, balance, "Interest credited"));
    }

    // ── Abstract ──────────────────────────────

    public abstract String getAccountType();
    public abstract double getWithdrawableBalance(); // may differ (overdraft etc.)
    public abstract void   applyInterest();          // called monthly

    // ── Display ───────────────────────────────

    public String getSummary() {
        return String.format("  Account : %s  [%s]%n  Holder  : %s%n  Balance : ₹%.2f",
            accountNumber, getAccountType(), holderName, balance);
    }
}

// ─────────────────────────────────────────────
//  Savings Account (4% annual interest, no overdraft)
// ─────────────────────────────────────────────

class SavingsAccount extends BankAccount {
    private static final double ANNUAL_RATE    = 0.04; // 4%
    private static final double MONTHLY_RATE   = ANNUAL_RATE / 12;
    private static final double MIN_BALANCE    = 500.0;

    public SavingsAccount(String holderName, double initialDeposit) {
        super(holderName, initialDeposit);
    }

    @Override public String getAccountType() { return "SAVINGS"; }

    @Override
    public double getWithdrawableBalance() {
        return Math.max(0, getBalance() - MIN_BALANCE); // keep min balance
    }

    @Override
    public void applyInterest() {
        double interest = getBalance() * MONTHLY_RATE;
        addInterest(interest);
    }

    public double getMinBalance() { return MIN_BALANCE; }
}

// ─────────────────────────────────────────────
//  Current Account (no interest, overdraft up to 10 000)
// ─────────────────────────────────────────────

class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 10_000.0;

    public CurrentAccount(String holderName, double initialDeposit) {
        super(holderName, initialDeposit);
    }

    @Override public String getAccountType() { return "CURRENT"; }

    @Override
    public double getWithdrawableBalance() {
        return getBalance() + OVERDRAFT_LIMIT;
    }

    @Override
    public void applyInterest() {
        // Current accounts earn no interest in this system
    }

    public double getOverdraftLimit() { return OVERDRAFT_LIMIT; }
}

// ─────────────────────────────────────────────
//  Bank — manages all accounts
// ─────────────────────────────────────────────

class Bank {
    private final String            bankName;
    private final List<BankAccount> accounts = new ArrayList<>();

    public Bank(String name) { this.bankName = name; }
    public String getName()  { return bankName; }

    public void addAccount(BankAccount acc) { accounts.add(acc); }

    public BankAccount findAccount(String accNo) throws AccountNotFoundException {
        for (BankAccount a : accounts) {
            if (a.getAccountNumber().equals(accNo)) return a;
        }
        throw new AccountNotFoundException(accNo);
    }

    public List<BankAccount> getAllAccounts() { return new ArrayList<>(accounts); }

    public void transfer(String fromAccNo, String toAccNo, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        BankAccount from = findAccount(fromAccNo);
        BankAccount to   = findAccount(toAccNo);
        if (amount > from.getWithdrawableBalance()) {
            throw new InsufficientFundsException(amount, from.getWithdrawableBalance());
        }
        from.recordTransferOut(amount, toAccNo);
        to.addTransferIn(amount, fromAccNo);
    }

    public void applyMonthlyInterest() {
        for (BankAccount a : accounts) a.applyInterest();
    }

    public double totalDeposits() {
        double sum = 0;
        for (BankAccount a : accounts) sum += a.getBalance();
        return sum;
    }
}

// ─────────────────────────────────────────────
//  Main Application
// ─────────────────────────────────────────────

public class BankingSystem {

    private static final Scanner sc   = new Scanner(System.in);
    private static final Bank    bank = new Bank("Amit National Bank");

    // ── Helpers ───────────────────────────────

    private static void printBanner() {
        System.out.println("================================================");
        System.out.println("|     AMit Maity — Basic Banking System    |");
        System.out.printf ("|     %s%-27s      |%n", "Bank: ", bank.getName());
        System.out.println("================================================");
    }

    private static void printMenu() {
        System.out.println("\n------------------------------------------------");
        System.out.println("|                  MAIN MENU                    |");
        System.out.println("------------------------------------------------");
        System.out.println("|  1. Open Savings Account                       |");
        System.out.println("|  2. Open Current Account                       |");
        System.out.println("|  3. Deposit                                    |");
        System.out.println("|  4. Withdraw                                   |");
        System.out.println("|  5. Transfer                                   |");
        System.out.println("|  6. Check Balance                              |");
        System.out.println("|  7. Transaction History                        |");
        System.out.println("|  8. List All Accounts                          |");
        System.out.println("|  9. Apply Monthly Interest                     |");
        System.out.println("|  0. Exit                                       |");
        System.out.println("------------------------------------------------");
        System.out.print("  Choice: ");
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static double readAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = Double.parseDouble(sc.nextLine().trim());
                if (v > 0) return v;
                System.out.println("  [ERROR] Amount must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Enter a valid number.");
            }
        }
    }

    // ── Actions ───────────────────────────────

    private static void openAccount(boolean isSavings) {
        String type = isSavings ? "Savings" : "Current";
        System.out.println("\n  -- Open " + type + " Account ---------------------");
        String name   = readString("  Account holder name : ");
        if (name.isEmpty()) { System.out.println("  [ERROR] Name required."); return; }
        double initial = readAmount("  Initial deposit (Rupee) : ");

        BankAccount acc = isSavings
            ? new SavingsAccount(name, initial)
            : new CurrentAccount(name, initial);
        bank.addAccount(acc);

        System.out.println("\n  Account created successfully!");
        System.out.println(acc.getSummary());
        if (isSavings) {
            System.out.printf("  Min balance : Rupee %.2f | Interest rate: 4%% p.a.%n",
                ((SavingsAccount) acc).getMinBalance());
        } else {
            System.out.printf("  Overdraft limit: Rupee %.2f%n",
                ((CurrentAccount) acc).getOverdraftLimit());
        }
    }

    private static void deposit() {
        System.out.println("\n  -- Deposit ----------------------------- ");
        String accNo  = readString("  Account number : ");
        double amount = readAmount("  Amount (Rupee)     : ");
        String note   = readString("  Note (optional): ");
        try {
            BankAccount acc = bank.findAccount(accNo);
            acc.deposit(amount, note.isEmpty() ? "Cash deposit" : note);
            System.out.printf("  Rupee %.2f deposited. New balance: Rupee %.2f%n",
                amount, acc.getBalance());
        } catch (AccountNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println("\n  -- Withdraw ----------------------------- ");
        String accNo  = readString("  Account number : ");
        double amount = readAmount("  Amount (Rupee)     : ");
        String note   = readString("  Note (optional): ");
        try {
            BankAccount acc = bank.findAccount(accNo);
            acc.withdraw(amount, note.isEmpty() ? "Cash withdrawal" : note);
            System.out.printf("  Rupee %.2f withdrawn. New balance: Rupee %.2f%n",
                amount, acc.getBalance());
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void transfer() {
        System.out.println("\n  -- Transfer ----------------------------- ");
        String from   = readString("  From account  : ");
        String to     = readString("  To account    : ");
        double amount = readAmount("  Amount (Rupee)    : ");
        try {
            bank.transfer(from, to, amount);
            System.out.printf("  Rupee %.2f transferred from %s to %s.%n", amount, from, to);
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void checkBalance() {
        String accNo = readString("\n  Account number: ");
        try {
            BankAccount acc = bank.findAccount(accNo);
            System.out.println("\n" + acc.getSummary());
            if (acc instanceof SavingsAccount) {
                System.out.printf("  Withdrawable  : Rupee %.2f (after min balance)%n",
                    acc.getWithdrawableBalance());
            }
        } catch (AccountNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void showHistory() {
        String accNo = readString("\n  Account number: ");
        try {
            BankAccount acc = bank.findAccount(accNo);
            System.out.println("\n  -- Transaction History: " + accNo + " --------------");
            List<Transaction> hist = acc.getHistory();
            if (hist.isEmpty()) System.out.println("  (No transactions yet)");
            else for (Transaction t : hist) System.out.println(t);
        } catch (AccountNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void listAllAccounts() {
        System.out.println("\n  -- All Accounts ---------------------------------");
        List<BankAccount> all = bank.getAllAccounts();
        if (all.isEmpty()) { System.out.println("  (No accounts yet)"); return; }
        for (BankAccount a : all) {
            System.out.println("  " + a.getAccountNumber() + "  [" + a.getAccountType() + "]"
                + "  " + String.format("%-20s", a.getHolderName())
                + "  ₹" + String.format("%10.2f", a.getBalance()));
        }
        System.out.printf("%n  Total deposits in bank: ₹%.2f%n", bank.totalDeposits());
    }

    private static void applyInterest() {
        bank.applyMonthlyInterest();
        System.out.println("  Monthly interest applied to all Savings accounts.");
    }

    // ── Main ──────────────────────────────────

    public static void main(String[] args) {
        printBanner();

        // Seed demo accounts
        bank.addAccount(new SavingsAccount("Arjun Sharma",   50_000));
        bank.addAccount(new SavingsAccount("Priya Patel",    25_000));
        bank.addAccount(new CurrentAccount("Amit Tech Ltd", 2_00_000));

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": openAccount(true);    break;
                case "2": openAccount(false);   break;
                case "3": deposit();            break;
                case "4": withdraw();           break;
                case "5": transfer();           break;
                case "6": checkBalance();       break;
                case "7": showHistory();        break;
                case "8": listAllAccounts();    break;
                case "9": applyInterest();      break;
                case "0":
                    System.out.println("\n  Thank you for banking with " + bank.getName() + ". Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("  [ERROR] Invalid choice. Enter 0 to 9.");
            }
        }
        sc.close();
    }
}