import java.util.Scanner;

public class Calculator {

    // ─────────────────────────────────────────────
    //  Arithmetic Methods
    // ─────────────────────────────────────────────

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * @throws ArithmeticException if divisor is zero
     */
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
        return a / b;
    }

    /**
     * @throws ArithmeticException if divisor is zero
     */
    public static double modulus(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Modulus by zero is not allowed!");
        }
        return a % b;
    }

    public static double power(double base, double exp) {
        return Math.pow(base, exp);
    }

    // ─────────────────────────────────────────────
    //  Display Helpers
    // ─────────────────────────────────────────────

    private static void printBanner() {
        System.out.println("====================================");
        System.out.println("     Amit Maity - Calculator    ");
        System.out.println("====================================");
    }

    private static void printMenu() {
        System.out.println("\n-----------------------------------");
        System.out.println("|         SELECT OPERATION        |");
        System.out.println("-----------------------------------");
        System.out.println("|  1. Addition       (+)          |");
        System.out.println("|  2. Subtraction    (-)          |");
        System.out.println("|  3. Multiplication (*)          |");
        System.out.println("|  4. Division       (/)          |");
        System.out.println("|  5. Modulus        (%)          |");
        System.out.println("|  6. Power          (^)          |");
        System.out.println("|  0. Exit                        |");
        System.out.println("-----------------------------------");
        System.out.print("  Enter choice: ");
    }

    private static void printResult(double a, String op, double b, double result) {
        System.out.printf("%n  Result: %.2f %s %.2f = %.6f%n", a, op, b, result);
    }

    // ─────────────────────────────────────────────
    //  Input Helper — safely reads a double
    // ─────────────────────────────────────────────

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = sc.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Invalid number. Please try again.");
            }
        }
    }

    // ─────────────────────────────────────────────
    //  Main
    // ─────────────────────────────────────────────

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printBanner();

        boolean running = true;
        while (running) {
            printMenu();

            String choiceStr = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Please enter a valid menu number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("\n  Thank you for using SaiKet Calculator. Goodbye!");
                running = false;
                break;
            }

            if (choice < 1 || choice > 6) {
                System.out.println("  [ERROR] Invalid option. Choose between 0–6.");
                continue;
            }

            double a = readDouble(sc, "  Enter first number : ");
            double b = readDouble(sc, "  Enter second number: ");

            try {
                switch (choice) {
                    case 1: printResult(a, "+", b, add(a, b));      break;
                    case 2: printResult(a, "-", b, subtract(a, b)); break;
                    case 3: printResult(a, "*", b, multiply(a, b)); break;
                    case 4: printResult(a, "/", b, divide(a, b));   break;
                    case 5: printResult(a, "%", b, modulus(a, b));  break;
                    case 6: printResult(a, "^", b, power(a, b));    break;
                }
            } catch (ArithmeticException e) {
                System.out.println("  [MATH ERROR] " + e.getMessage());
            }
        }

        sc.close();
    }
}