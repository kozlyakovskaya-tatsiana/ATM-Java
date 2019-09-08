import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WorkingWithConsole {
    private static Scanner in = new Scanner(System.in);
    public static void finishWork(){
        System.out.println("You have fnished work");
    }
    public static String startMenu() {
        System.out.print("Select a necessary point\n" +
                "Enter 1 to Start\n" +
                "Enter any key to finish work\n");

        return in.nextLine();
    }
    public static String inputCard() {
        System.out.println("Please input the number of your card");
        return in.nextLine();
    }
    public static Account getAccount(String cardNumber, ArrayList<Account> accounts) throws Exception {
        for (Account account : accounts) if (account.getCardNumber().equals(cardNumber)) return account;
        throw new Exception("There is no such account");
    }
  }