import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void testRegex(String pattern, String inputString) {
        Pattern p = Pattern.compile(pattern);
        System.out.printf("Case is: %s%n", inputString);
        Matcher m = p.matcher(inputString);
        boolean ifMatch = false;
        while(m.find()) {
            System.out.println("Found str:" + m.group());
            ifMatch = true;
        }
        if(!ifMatch) {
            System.out.println("NO MATCH!");
        }
        System.out.printf("Match? %b%n", ifMatch);

    }

    public static void main(String[] args) {
        // 1. Check if string starts with "Sun"
        String strPositive = "Sunday is sunny!";
        String strNegative = "Weather is cloudy!";
        String patternStr = "^Sun";
        System.out.printf("========== Testing Pattern: %s ==========%n", patternStr);
        System.out.println("Positive Case:--------");
        testRegex(patternStr, strPositive);
        System.out.println("Negative Case:--------");
        testRegex(patternStr, strNegative);

        // 2. Extract a decimal number (e.g., credit card balance)
        String dataPositive = "My bank card balance is: 10000000000.88";
        String dataNegative = "My bank card balance is: One Billion Dollars!";
        String patterData = "(-)?[0-9]+(\\.[0-9]{2})?";
        System.out.printf("========== Testing Pattern: %s ==========%n", patterData);
        System.out.println("Positive Case:--------");
        testRegex(patterData, dataPositive);
        System.out.println("Negative Case:--------");
        testRegex(patterData, dataNegative);

        // 3. Validate date MM-DD-YYYY or MM.DD.YYYY or MM/DD/YYYY
        String datePositive1 = "Today is: 11/15/2025";
        String datePositive2 = "Today is: 11-15-2025";
        String datePositive3 = "Today is: 11.15.2025";
        String dateNegative = "19-29-1878";
        String patternDate = "^(0?[1-9]|1[0-2])[-./](0?[1-9]|[12][0-9]|3[01])[-./](19|20)?[0-9]{2}$";
        System.out.printf("========== Testing Pattern: %s ==========%n", patternDate);
        System.out.println("Positive Case:--------");
        testRegex(patternDate, datePositive1);
        testRegex(patternDate, datePositive2);
        testRegex(patternDate, datePositive3);
        System.out.println("Negative Case:--------");
        testRegex(patternDate, dateNegative);
        // String pattern = "(.*)(\\d+)(.*)";

        // 4. Phone Number (123-456-7890 or 123.456.7890)
        String patternPhone = "[0-9]{3}[-.][0-9]{3}[-.][0-9]{4}";
        String phonePositive = "Call me at: 400-800-9555";
        String phonePositive2 = "call me at: 612-234-5634";
        String phoneNegative = "Call me at: 4008009555";
        System.out.printf("========== Testing Pattern: %s ==========%n", patternDate);
        System.out.println("Positive Case:--------");
        testRegex(patternPhone, phonePositive);
        testRegex(patternPhone, phonePositive2);
        System.out.println("Negative Case:--------");
        testRegex(patternPhone, phoneNegative);

        // 5. Email Address
        String patternEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
        String emialPositive = "Contact: hello@example.com";
        String emailNegative = "Contact: hello@example";
        System.out.printf("========== Testing Pattern: %s ==========%n", patternEmail);
        System.out.println("Positive Case:--------");
        testRegex(patternEmail, emialPositive);
        System.out.println("Negative Case:--------");
        testRegex(patternEmail, emailNegative);
    }
}
