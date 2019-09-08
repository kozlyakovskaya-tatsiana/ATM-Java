import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsForRegularExpressions {
    private static String patternCardNumber="\\d{4}-\\d{4}-\\d{4}-\\d{4}";
    private  static String patternPIN="\\d{4}";
    private static  String patternBalance="\\d+";
    private static String patternActive="active";
    private static String patternAttempts="[0123]\\s*$";
    private static String patternBlocked="blocked";

    public static boolean isCardNumber(String text){
        Pattern pattern = Pattern.compile(patternCardNumber);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public static boolean isPIN(String text){
        Pattern pattern = Pattern.compile(patternPIN);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public static boolean isBalance(String text){
        Pattern pattern = Pattern.compile(patternBalance);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public static boolean isActive(String text){
        Pattern pattern = Pattern.compile(patternActive);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public static boolean isBlocked(String text){
        Pattern pattern = Pattern.compile(patternBlocked);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public static boolean isAttempt (String text){
        Pattern pattern = Pattern.compile(patternAttempts);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
