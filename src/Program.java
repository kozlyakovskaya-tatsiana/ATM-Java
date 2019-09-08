import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String pathToCards = "TextFiles\\InfoAboutCards.txt";
        String pathToCashimachine = "TextFiles\\Cashmachine.txt";
        ArrayList<Account> accounts ;
        CashMachine cashMachine;
        try {
            cashMachine = new CashMachine(WorkingWithFiles.readFromCashmachine(pathToCashimachine));
            accounts = WorkingWithFiles.extractAccounts(pathToCards);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Edit source file and try again");
            return;
        }
        Scanner in = new Scanner(System.in);
        boolean work = true;
        String action = "startmenu";
        Account account = new Account();
        do {
            switch (action) {
                case "startmenu":
                    action = (WorkingWithConsole.startMenu().equals("1")) ? "inputcard" : "finishwork";
                    break;
                case "inputcard":
                    String cardNumber = WorkingWithConsole.inputCard();
                    try {
                        if (cardNumber.isEmpty()) throw new Exception("You haven't entered the number of the card");
                        if (!(PatternsForRegularExpressions.isCardNumber(cardNumber)))
                            throw new Exception("Card has incorrect format. It must be ХХХХ-ХХХХ-ХХХХ-ХХХХ");
                        account = WorkingWithConsole.getAccount(cardNumber, accounts);
                        action = "inputpin";
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("If you want to try again enter 1, to go to a start menu press any key");
                        action = (in.nextLine().equals("1")) ? "inputcard" : "startmenu";
                        break;
                    }
                case "inputpin":
                    if (account.isActive()==false) {
                        System.out.println("Your card is blocked.");
                        action = "startmenu";
                        break;
                    }
                    System.out.println("Please input the pin of your card. There are left " + account.getAttemptsForPin() + " attempts");
                    if (in.nextLine().equals(account.getPin())) {
                        account.setAttemptsForPin(3);
                        action = "operationswithcard";
                        WorkingWithFiles.updateInfo(pathToCards,accounts);
                    } else {
                        account.setAttemptsForPin(account.getAttemptsForPin()-1);
                        WorkingWithFiles.updateInfo(pathToCards,accounts);
                        if (account.getAttemptsForPin() == 0) {
                            System.out.println("Your card is blocked.");
                            account.setActive(false);
                            WorkingWithFiles.updateInfo(pathToCards,accounts);
                            action = "startmenu";
                            break;
                        }
                        System.out.println("Pin is incorrect.\nEnter 1 to try one more time\n" +
                                "enter any key to go to start menu");
                        action = (in.nextLine().equals("1")) ? "inputpin" : "startmenu";
                    }
                    break;
                case "operationswithcard":
                    System.out.println("You are in the system");
                    System.out.println("Choose an item\n"+
                                        "1.Check a balance\n" +
                                        "2.Withdraw money\n" +
                                        "3.Top up balance\n" +
                                        "Enter any key to go to a start menu");
                    String choice = in.nextLine();
                        switch (choice){
                            case "1":
                                System.out.printf("You balance is %d rubles\n",account.getBalance());
                                action="operationswithcard";
                                break;
                            case "2":
                                System.out.println("How much money do you want to withdraw?");
                                try {
                                    int sum=Integer.parseInt(in.nextLine());
                                    if (sum > cashMachine.getCash()) throw new Exception ("There is not enough money in cashmachine");
                                    account.withdraw(sum);
                                    cashMachine.withdrawCash(sum);
                                    WorkingWithFiles.updateCashmachine(pathToCashimachine,cashMachine);
                                    WorkingWithFiles.updateInfo(pathToCards,accounts);
                                    System.out.println("Operation was successful");
                                }
                                catch (Exception ex){
                                    System.out.println("Operation was failed");
                                    System.out.println(ex.getMessage());
                                }
                                finally {
                                    action="operationswithcard";
                                }
                                break;
                            case "3":
                                System.out.println("How much money do you want to put it into account?\n" +
                                                    "(The maximum value to put 1000000 rubles)");
                                try {
                                    int sum = Integer.parseInt(in.nextLine());
                                    cashMachine.putMoney(sum);
                                    account.putMoney(sum);
                                    WorkingWithFiles.updateCashmachine(pathToCashimachine,cashMachine);
                                    WorkingWithFiles.updateInfo(pathToCards,accounts);
                                    System.out.println("Operation was successful");
                                }
                                catch (Exception ex){
                                    System.out.println("Operation was failed");
                                    System.out.println(ex.getMessage());
                                }
                                finally {
                                    action="operationswithcard";
                                }
                                break;
                             default: action = "startmenu";
                        }
                        break;
                case "finishwork":
                    WorkingWithConsole.finishWork();
                    work = false;
                    break;
            }
        } while (work);

    }
}
