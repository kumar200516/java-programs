package madhukar;
import java.util.Random;

import java.util.Scanner;

public class java1 {

    private static final int MAX_ATTEMPTS = 5;
    private static final int MIN = 1;
    private static final int MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int attempts = 0;
            boolean guessedCorrectly = false;

            Random random = new Random();
            int randomNumber = random.nextInt(MAX - MIN + 1) + MIN;

            System.out.println("I'm thinking of a number between " + MIN + " and " + MAX + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the number!");

            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                attempts++;
                System.out.print("Attempt " + attempts + ": Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    score++;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + randomNumber);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine();
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Game over! Your final score is: " + score);
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
