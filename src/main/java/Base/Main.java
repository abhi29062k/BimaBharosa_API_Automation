package Base;

import Request.Request;
import Utilities.ExportToExcel;
import Utilities.ExtentReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main extends ExtentReport {

    public static Random random;

    public String test_name;
    public String status;
    public String message;

    public static Logger logger = LogManager.getLogger(Main.class);


    @BeforeSuite
    public void setup() {
        ExtentReport.startTest();
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("-------------Test Execution started--------------");
    }

    public static String generateEntityReferenceNo() {
        random = new Random();
        String entityCompRefNo = "TEST" + String.valueOf(random.nextInt(Integer.parseInt("1000000")));

        return entityCompRefNo;
    }

    public static String generateRandomString(int length) {
        // Define the characters allowed in the random string (both uppercase and lowercase alphabet)
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Initialize a Random object
        Random random = new Random();

        // Initialize a StringBuilder to store the random string
        StringBuilder sb = new StringBuilder(length);

        // Generate random characters from the alphabet and append them to the StringBuilder until it reaches the desired length
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            sb.append(randomChar);
        }

        // Convert the StringBuilder to a String and return
        return sb.toString();
    }


    public static String getCurrentDate() {

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String presentDate = currentDate.format(dateFormat);

        return presentDate;

    }

    public static String getFutureDate() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Add 7 days to the current date (to get a future date)
        calendar.add(Calendar.DAY_OF_YEAR, 7);

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String futureDate = sdf.format(calendar.getTime());

        return futureDate;
    }

    public static String getPastDate() {

        // Get the current date
        Calendar calendar = Calendar.getInstance();

        //  63 days back to the current date (to get a past date)
        calendar.add(Calendar.DAY_OF_YEAR, -63);

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String pastDate = sdf.format(calendar.getTime());

        return pastDate;
    }

    public Timestamp getCurrentDateTime(){

        // Get current date and time
        Date currentDate = new Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
        return currentTimestamp;
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Throwable t = result.getThrowable();
            StringWriter error = new StringWriter();
            t.printStackTrace(new PrintWriter(error));
            logger.error(error.toString());
        }

        try{
            logger.info("\n Request : \n" + Request.request);
            logger.info("\n Response : \n" + Request.response.asPrettyString());
            logger.info("-------------Test Execution ended---------------\n\n");
        }
        catch (NullPointerException n){

        }

    }


    @AfterSuite
    public void tearDown() throws Exception {
        ExtentReport.endTest();

        /*Commented the code which exports log data from db to excel*/
        //ExportToExcel.generate_excel_report();



    }
}
