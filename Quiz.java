
    import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {

    private static final long ANSWER_TIME = 10000; // 10 seconds
    private static int score = 0;
    private static int totalQuestions = 5;
    private static Scanner scanner = new Scanner(System.in);
    private static int currentQuestionIndex = 0;

    public static void main(String[] args) {
        String[] questions = {
            "What is the capital of France?",
            "Who wrote the novel '1984'?",
            "What is the chemical symbol for gold?",
            "Which planet is known as the Red Planet?",
            "What is the largest ocean on Earth?"
        };
        String[][] options = {
            {"A) London", "B) Paris", "C) Madrid", "D) Berlin"},
            {"A) J.K. Rowling", "B) George Orwell", "C) F. Scott Fitzgerald", "D) Charles Dickens"},
            {"A) Ag", "B) Au", "C) Fe", "D) Pb"},
            {"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
            {"A) Atlantic Ocean", "B) Indian Ocean", "C) Pacific Ocean", "D) Arctic Ocean"}
        };
        char[] answers = {'B', 'B', 'B', 'B', 'C'};

        while (currentQuestionIndex < totalQuestions) {
            displayQuestion(questions[currentQuestionIndex], options[currentQuestionIndex]);
            char userAnswer = getUserAnswer(ANSWER_TIME);
            checkAnswer(userAnswer, answers[currentQuestionIndex]);
            currentQuestionIndex++;
        }

        displayResult(score, totalQuestions);
    }

    private static void displayQuestion(String question, String[] options) {
        System.out.println(question);
        for (String option : options) {
            System.out.println(option);
        }
    }

    private static char getUserAnswer(long time) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Please press Enter to continue to the next question.");
            }
        };
        timer.schedule(task, time);

        System.out.print("Your answer: ");
        char userAnswer;
        if (scanner.hasNextLine()) {
            userAnswer = scanner.nextLine().toUpperCase().charAt(0);
        } else {
            userAnswer = ' '; // Invalid input, consider handling this case
        }
        timer.cancel();
        task.cancel();
        return userAnswer;
    }

    private static void checkAnswer(char userAnswer, char correctAnswer) {
        if (userAnswer == correctAnswer) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
    }

    private static void displayResult(int score, int totalQuestions) {
        System.out.println("Quiz finished!");
        System.out.println("Your final score is " + score + " out of " + totalQuestions);
    }
}

