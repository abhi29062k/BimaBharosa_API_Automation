package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Constants.XMLFileConstants;
import Operations.Register;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import Response.RegisterResponse;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Test_1_RegisterComplaint_PresentDate extends Main {

    //Author : Abhishek
    @Test(priority = 1)
    public void test_registerComplaint_PresentDate() throws IOException, TransformerException {

        test_name = "Positive_TC_001 : Register Complaint with Present Date";
        test = extent.createTest(test_name);

        //Generate random entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to specified xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());
        reg.setEntityCompRefNumber(entityRefNumber);

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isEmpty()){
            message = "Test Passed : Complaint Registered with present date";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint not Registered with present date";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }


        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
