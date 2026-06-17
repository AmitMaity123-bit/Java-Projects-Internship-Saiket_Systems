import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    // ── Constants ─────────────────────────────
    private static final int DEFAULT_MIN   = 1;
    private static final int DEFAULT_MAX   = 100;
    private static final int MAX_ATTEMPTS  = 7;

    private static final Random  random = new Random();
    private static final Scanner sc     = new Scanner(System.in);

    // ── Score Tracking ────────────────────────
    private static int roundsPlayed = 0;
    private static int roundsWon    = 0;
    private static int totalGuesses = 0;
    private static int bestRound    = Integer.MAX_VALUE; // fewest guesses in a win

    // ─────────────────────────────────────────────
    //  Display Helpers
    // ─────────────────────────────────────────────

    private static void printBanner() {
        System.out.println("========================================");
        System.out.println("|  Amit Maity - Number Guessing Game |");
        System.out.println("=========================================");
        System.out.printf ("  Range: %d to %d  |  Max attempts: %d%n%n",
            DEFAULT_MIN, DEFAULT_MAX, MAX_ATTEMPTS);
    }

    private static void printAttemptBar(int used) {
        System.out.print("  Attempts [");
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            System.out.print(i < used ? "i" : "o");
        }
        System.out.printf("] %d/%d used%n", used, MAX_ATTEMPTS);
    }

    private static void printStats() {
        System.out.println("\n  -- Session Statistics ------------------");
        System.out.printf ("  Rounds played : %d%n",   roundsPlayed);
        System.out.printf ("  Rounds won    : %d%n",   roundsWon);
        System.out.printf ("  Win rate      : %.0f%%%n",
            roundsPlayed == 0 ? 0 : (roundsWon * 100.0 / roundsPlayed));
        if (roundsWon > 0) {
            System.out.printf ("  Best round    : %d guess(es)%n", bestRound);
            System.out.printf ("  Avg guesses   : %.1f%n",
                (double) totalGuesses / roundsWon);
        }
        System.out.println("  ---------------------------------------");
    }

    // ─────────────────────────────────────────────
    //  Hint System
    // ─────────────────────────────────────────────

    private static String getHint(int guess, int secret) {
        int diff = Math.abs(guess - secret);
        String direction = guess < secret ? " Too LOW" : " Too HIGH";
        String warmth;
        if (diff <= 5)       warmth = " Very HOT!";
        else if (diff <= 15) warmth = " Warm";
        else if (diff <= 30) warmth = " Cool";
        else                 warmth = " Very COLD";
        return direction + "   " + warmth;
    }

    // ─────────────────────────────────────────────
    //  Single Round Logic
    // ─────────────────────────────────────────────

    private static void playRound(int min, int max) {
        int secret   = random.nextInt(max - min + 1) + min;
        int attempts = 0;
        boolean won  = false;

        System.out.printf("%n  ==== Round %d ============================%n", roundsPlayed + 1);
        System.out.printf("  I'm thinking of a number between %d and %d.%n%n", min, max);

        while (attempts < MAX_ATTEMPTS) {
            printAttemptBar(attempts);
            System.out.printf("  Guess #%d: ", attempts + 1);

            int guess;
            try {
                guess = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Enter a whole number.");
                continue;
            }

            if (guess < min || guess > max) {
                System.out.printf("  [ERROR] Please guess between %d and %d.%n", min, max);
                continue;
            }

            attempts++;
            totalGuesses++;

            if (guess == secret) {
                won = true;
                System.out.println("\n  CORRECT! You got it in " + attempts + " guess(es)!");
                if (attempts < bestRound) bestRound = attempts;
                break;
            }

            System.out.println("  " + getHint(guess, secret));

            int remaining = MAX_ATTEMPTS - attempts;
            if (remaining == 2) System.out.println("   Only 2 attempts remaining!");
            if (remaining == 1) System.out.println("   LAST CHANCE!");
        }

        roundsPlayed++;
        if (won) {
            roundsWon++;
        } else {
            System.out.printf("%n   Out of attempts! The number was %d. Better luck next time!%n", secret);
        }
    }

    // ─────────────────────────────────────────────
    //  Difficulty Chooser
    // ─────────────────────────────────────────────

    private static int[] chooseDifficulty() {
        System.out.println("  -- Choose Difficulty ------------------");
        System.out.println("  1. Easy   (1 to 50,  10 attempts)");
        System.out.println("  2. Normal (1 to 100,  7 attempts)  [default]");
        System.out.println("  3. Hard   (1 to 500,  7 attempts)");
        System.out.println("  4. Expert (1 to 1000, 5 attempts)");
        System.out.print("  Choice [2]: ");
        String c = sc.nextLine().trim();
        // returns {min, max, maxAttempts}
        switch (c) {
            case "1": return new int[]{1,  50,  10};
            case "3": return new int[]{1,  500,  7};
            case "4": return new int[]{1, 1000,  5};
            default:  return new int[]{1,  100,  7};
        }
    }

    // ─────────────────────────────────────────────
    //  Main
    // ─────────────────────────────────────────────

    public static void main(String[] args) {
        printBanner();

        // We adapt MAX_ATTEMPTS per difficulty via a local variable in this run
        boolean keepPlaying = true;
        while (keepPlaying) {
            int[] config = chooseDifficulty();
            playRound(config[0], config[1]);
            printStats();

            System.out.print("\n  Play again? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            keepPlaying = again.equals("y") || again.equals("yes");
        }

        System.out.println("\n  Thanks for playing! See you at Amit Maity Game Store.");
        sc.close();
    }
}