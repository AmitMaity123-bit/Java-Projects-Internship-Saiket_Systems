import java.util.Scanner;

public class TemperatureConverter {

    // ─────────────────────────────────────────────
    //  Conversion Methods (Celsius ↔ Fahrenheit ↔ Kelvin)
    // ─────────────────────────────────────────────

    public static double celsiusToFahrenheit(double c) {
        return (c * 9.0 / 5.0) + 32.0;
    }

    public static double fahrenheitToCelsius(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    public static double celsiusToKelvin(double c) {
        return c + 273.15;
    }

    public static double kelvinToCelsius(double k) {
        return k - 273.15;
    }

    public static double fahrenheitToKelvin(double f) {
        return celsiusToKelvin(fahrenheitToCelsius(f));
    }

    public static double kelvinToFahrenheit(double k) {
        return celsiusToFahrenheit(kelvinToCelsius(k));
    }

    // ─────────────────────────────────────────────
    //  Validation
    // ─────────────────────────────────────────────

    /**
     * Absolute zero checks.
     * @throws IllegalArgumentException if temperature is below absolute zero for its scale.
     */
    public static void validateTemperature(double value, int scale) {
        switch (scale) {
            case 1: // Celsius
                if (value < -273.15)
                    throw new IllegalArgumentException(
                        "Temperature below absolute zero for Celsius (min: -273.15 °C)");
                break;
            case 2: // Fahrenheit
                if (value < -459.67)
                    throw new IllegalArgumentException(
                        "Temperature below absolute zero for Fahrenheit (min: -459.67 °F)");
                break;
            case 3: // Kelvin
                if (value < 0)
                    throw new IllegalArgumentException(
                        "Kelvin cannot be negative (min: 0 K)");
                break;
        }
    }

    // ─────────────────────────────────────────────
    //  Display Helpers
    // ─────────────────────────────────────────────

    private static void printBanner() {
        System.out.println("========================================");
        System.out.println("|  Amit Maity - Temp Converter     |");
        System.out.println("=========================================");
    }

    private static void printScaleMenu(String label) {
        System.out.println("\n  " + label);
        System.out.println("  1. Celsius    (°C)");
        System.out.println("  2. Fahrenheit (°F)");
        System.out.println("  3. Kelvin     ( K)");
        System.out.print("  Your choice  : ");
    }

    private static String scaleName(int s) {
        switch (s) {
            case 1: return "°C (Celsius)";
            case 2: return "°F (Fahrenheit)";
            case 3: return " K (Kelvin)";
            default: return "Unknown";
        }
    }

    private static String scaleSymbol(int s) {
        switch (s) { case 1: return "°C"; case 2: return "°F"; default: return "K"; }
    }

    // ─────────────────────────────────────────────
    //  Core Conversion Dispatcher
    // ─────────────────────────────────────────────

    public static double convert(double value, int from, int to) {
        if (from == to) return value;

        // Convert to Celsius first, then to target
        double celsius;
        switch (from) {
            case 1: celsius = value; break;
            case 2: celsius = fahrenheitToCelsius(value); break;
            case 3: celsius = kelvinToCelsius(value); break;
            default: throw new IllegalArgumentException("Unknown scale: " + from);
        }

        switch (to) {
            case 1: return celsius;
            case 2: return celsiusToFahrenheit(celsius);
            case 3: return celsiusToKelvin(celsius);
            default: throw new IllegalArgumentException("Unknown scale: " + to);
        }
    }

    // ─────────────────────────────────────────────
    //  Input Helpers
    // ─────────────────────────────────────────────

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Invalid number. Try again.");
            }
        }
    }

    private static int readScale(Scanner sc, String label) {
        while (true) {
            printScaleMenu(label);
            try {
                int c = Integer.parseInt(sc.nextLine().trim());
                if (c >= 1 && c <= 3) return c;
                System.out.println("  [ERROR] Enter 1, 2, or 3.");
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Enter 1, 2, or 3.");
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
            System.out.println("\n======================================");
            int fromScale = readScale(sc, "INPUT SCALE - choose source:");
            double value  = readDouble(sc, "  Enter temperature       : ");

            try {
                validateTemperature(value, fromScale);
            } catch (IllegalArgumentException e) {
                System.out.println("  [VALIDATION ERROR] " + e.getMessage());
                continue;
            }

            int toScale = readScale(sc, "OUTPUT SCALE — choose target:");

            double result = convert(value, fromScale, toScale);

            System.out.println("\n  ------------------------------------");
            System.out.printf ("  |  %.4f %s%n", value, scaleName(fromScale));
            System.out.println("  |           ↓ converts to           |");
            System.out.printf ("  |  %.4f %s%n", result, scaleName(toScale));
            System.out.println("  -------------------------------------");

            // Also print all three for reference
            double c = convert(value, fromScale, 1);
            System.out.println("\n  ── All Scales ───────────────────────");
            System.out.printf ("  Celsius   : %10.4f °C%n", c);
            System.out.printf ("  Fahrenheit: %10.4f °F%n", celsiusToFahrenheit(c));
            System.out.printf ("  Kelvin    : %10.4f  K%n", celsiusToKelvin(c));

            System.out.print("\n  Convert another? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("y") && !again.equals("yes")) {
                running = false;
            }
        }

        System.out.println("\n  Thanks for using SaiKet Temp Converter. Goodbye!");
        sc.close();
    }
}