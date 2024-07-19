package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Constants.XMLFileConstants;
import Operations.Register;
import Request.Request;
import Response.RegisterResponse;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Test_2_RegisterComplaint_PastDate extends Main {

    //Author : Abhishek
    @Test(priority = 2)
    public void test_registerComplaint_PastDate() throws IOException, TransformerException {

        test_name = "Positive_TC_002 : Register Complaint with Past Date";
        test = extent.createTest(test_name);

        //Generate random entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to specified xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);
        reg.setComplaintDate("10-04-2024");
        reg.setComplaintReceiptDate("10-04-2024");
        reg.setEntityCompRefNumber(entityRefNumber);

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isEmpty()){
            message = "Test Passed : Complaint registered with past date";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint not registered with past date";
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
