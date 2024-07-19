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
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import javax.xml.XMLConstants;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import Response.RegisterResponse;

public class Test_5_RegisterComplaint_AgainstBroker extends Main {

    //Author : Abhishek
    @Test(priority = 11)
    public void test_registerComplaint_Against_Broker() throws IOException, TransformerException {

        test_name = "Positive_TC_011 : Register Complaint Against Broker";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //save values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_BROKER);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.BROKER);
        reg.setBrokerLicenseNumber("B317");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_AGAINST_BROKER);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isBlank()){
            message = "Test Passed : Complaint registered Against Broker";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint not registered Against Broker";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_BROKER);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
