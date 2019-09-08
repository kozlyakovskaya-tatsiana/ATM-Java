import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithFiles {
    public static ArrayList<String> readFromFile(String path) {
            ArrayList<String> accountsString = new ArrayList<>();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
                String buffer;
                while ((buffer=bufferedReader.readLine())!=null) {
                    accountsString.add(buffer.toString());
                }
            }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            return accountsString;
    }
    public static ArrayList<Account> extractAccounts(String path) throws Exception{
       ArrayList<Account> accounts = new ArrayList<>();
           ArrayList<String> accountsString = readFromFile(path);
           for (String accountString : accountsString) {
               String[] accountInfo = accountString.split("\\s"); //[0]-cardNumber, [1]-PIN, [2]-balance , [3]-active, [4]-attempts
               if (!(PatternsForRegularExpressions.isCardNumber(accountInfo[0]) &&
                       PatternsForRegularExpressions.isPIN(accountInfo[1]) &&
                       PatternsForRegularExpressions.isBalance(accountInfo[2]) &&
                       (PatternsForRegularExpressions.isActive(accountInfo[3]) || PatternsForRegularExpressions.isBlocked(accountInfo[3])) &&
                    PatternsForRegularExpressions.isAttempt(accountInfo[4])))
                   throw new Exception("The data in the file which contains information about cards is not in correct format");
               else {
                   accounts.add(new Account(accountInfo[0], accountInfo[1], Integer.parseInt(accountInfo[2]),
                           PatternsForRegularExpressions.isActive(accountInfo[3]), Integer.parseInt(accountInfo[4])));
               }
           }
        return accounts;
    }
    public static void updateInfo(String path,ArrayList<Account> accounts){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path,false))){
            for(Account account:accounts) bufferedWriter.write(String.format("%s\n",account));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static int readFromCashmachine  (String path) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String cash=bufferedReader.readLine(); 
        bufferedReader.close();
        if (cash!=null && PatternsForRegularExpressions.isBalance(cash)) {
            return Integer.parseInt(cash);
        }
        throw new Exception("The data in the file which contains information about cash in the cashmachine is not in correct format");
    }
    public static void updateCashmachine(String path,CashMachine cashMachine){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path,false))){
            bufferedWriter.write(Integer.toString(cashMachine.getCash()));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


}
