import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        // Initialize variables
        int minRange = 1;
        int maxRange = 100;
        int attempts = 0;
        int maxAttempts = 5;
        int score = 0;

        // Generate a random number
        Random rand = new Random();
        int secretNumber = rand.nextInt(maxRange - minRange + 1) + minRange;

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Game loop
        while (attempts < maxAttempts) {
            // Prompt user to enter a number
            System.out.print("Enter a number between " + minRange + " and " + maxRange + ": ");
            int userNumber = scanner.nextInt();

            // Check if the user's number matches the secret number
            if (userNumber == secretNumber) {
                System.out.println(" Congratulations! You guessed the number in " + attempts + " attempts.");
                score = maxAttempts - attempts;
                break;
            } else if (userNumber < secretNumber) {
                System.out.println("Your number is too low. Try again!");
            } else {
                System.out.println("Your number is too high. Try again!");
            }

            // Increment attempts
            attempts++;
        }

        // Game over
        if (attempts == maxAttempts) {
            System.out.println("Game over! The secret number was " + secretNumber);
        }

        // Display score
        System.out.println("Your score is " + score + " out of " + maxAttempts);
    }
}