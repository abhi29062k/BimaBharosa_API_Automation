package Register;

import Base.Main;
import Constants.*;
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

public class Test_9_RegisterComplaint_Against_LifeInsuranceCompany extends Main {

    //Author : Abhishek
    @Test(priority = 15)
    public void test_registerComplaint_Against_Life_InsuranceCompany() throws IOException, TransformerException {

        test_name = "Positive_TC_015 : Register Complaint Against Life Insurance Company";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_LIFE_INSURANCE_COMPANY);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());
        reg.setInsuranceTypeId(BimaBharosaConstants.LIFE);
        reg.setPolicyTypeId(PolicyTypeConstants.CONVENTIONAL_LIFE_INSURANCE_POLICY);
        reg.setComplaintTypeId(ComplaintTypeConstants.PROPOSAL_PROCESSING);
        reg.setComplaintDescriptionId(ComplaintDescConstants.PROPOSAL_PAPERS_SUBMITTED_BUT_MISPLACED_BY_INSURER);

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_LIFE_INSURANCE_COMPANY);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if (res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isBlank()) {
            message = "Test Passed : Complaint Registered Against Life Insurance Company";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Complaint Not Registered Against Life Insurance Company";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }


        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_LIFE_INSURANCE_COMPANY);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
