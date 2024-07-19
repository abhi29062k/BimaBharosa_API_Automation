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

public class Test_6_RegisterComplaint_AgainstSurveyor extends Main {

    //Author : Abhishek
    @Test(priority = 12)
    public void test_registerComplaint_Against_Surveyor() throws IOException, TransformerException {

        test_name = "Positive_TC_012 : Register Complaint Against Surveyor";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //save values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_SURVEYOR);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.SURVEYOR);
        reg.setIntermediaryName("M/S THE INDIAN SURVEYORS PVT LTD");
        reg.setIntermediaryLicenseNumber("IRDA/CORP/SLA-0334");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_AGAINST_SURVEYOR);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isEmpty()){
            message = "Test Passed : Complaint registered Against Surveyor";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Not registered Against Surveyor";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_SURVEYOR);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
