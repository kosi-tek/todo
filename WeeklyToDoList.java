import java.util.ArrayList;
import java.util.Scanner;

public class WeeklyToDoList {
    private static final String[] DAYS_OF_WEEK = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday" };
    private static final int MAX_WEEK_NUMBER = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<String>> weeklyLists = new ArrayList<>(MAX_WEEK_NUMBER);

        System.out.println("Welcome to the Weekly To-Do List App!");

        String userName = getUserName(scanner);
        System.out.println("Hello, " + userName + "!");

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    addTask(scanner, weeklyLists);
                    break;
                case 2:
                    viewAllTasks(weeklyLists);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static String getUserName(Scanner scanner) {
        System.out.print("Please enter your name: ");
        return scanner.nextLine();
    }

    private static void displayMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add task");
        System.out.println("2. View all tasks");
        System.out.println("3. Exit");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        return choice;
    }

    private static void addTask(Scanner scanner, ArrayList<ArrayList<String>> weeklyLists) {
        System.out.println("Enter the week number (1-4):");
        int weekNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        if (weekNumber < 1 || weekNumber > MAX_WEEK_NUMBER) {
            System.out.println("Invalid week number. Please enter a valid week number.");
            return;
        }

        ArrayList<String> toDoList = weeklyLists.size() >= weekNumber ? weeklyLists.get(weekNumber - 1)
                : new ArrayList<>();

        System.out.println("Enter the day of the week (Monday-Sunday):");
        String day = scanner.nextLine();
        if (!isValidDay(day)) { // Validate day input
            System.out.println("Invalid day. Please enter a valid day of the week.");
            return;
        }

        System.out.println("Enter the new task:");
        String newTask = scanner.nextLine();
        toDoList.add(day + ": " + newTask); // Add new task with day information
        if (weeklyLists.size() < weekNumber) {
            weeklyLists.add(toDoList);
        }
    }

    private static boolean isValidDay(String day) {
        for (String validDay : DAYS_OF_WEEK) {
            if (validDay.equalsIgnoreCase(day)) {
                return true; // Check if the input day is valid
            }
        }
        return false;
    }

    private static void viewAllTasks(ArrayList<ArrayList<String>> weeklyLists) {
        System.out.println("Viewing all tasks:");
        if (weeklyLists.size() > 0) {
            for (int i = 0; i < weeklyLists.size(); i++) {
                ArrayList<String> week = weeklyLists.get(i);
                System.out.println("Week " + (i + 1) + ":");
                for (String task : week) {
                    System.out.println("- " + task);
                }
            }
        } else {
            System.out.println("No Task Available");
        }

    }
}
