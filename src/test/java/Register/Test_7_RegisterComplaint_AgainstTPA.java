package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Constants.ComplaintAgainstTypeConstants;
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

public class Test_7_RegisterComplaint_AgainstTPA extends Main {

    //Author : Abhishek
    @Test(priority = 13)
    public void test_registerComplaint_Against_TPA() throws IOException, TransformerException {

        test_name = "Positive_TC_013 : Register Complaint Against TPA";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_TPA);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.TPA);
        reg.setIntermediaryName("Park Mediclaim TPA Private Ltd.");
        reg.setIntermediaryLicenseNumber("25");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_TPA);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isBlank()){
            message = "Test Passed : Complaint Registered Against TPA";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Not Registered Against TPA";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_TPA);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
