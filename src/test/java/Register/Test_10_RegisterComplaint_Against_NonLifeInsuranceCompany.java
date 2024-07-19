package Register;

import Base.Main;
import Constants.*;
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

public class Test_10_RegisterComplaint_Against_NonLifeInsuranceCompany extends Main {

    //Author : Abhishek
    @Test(priority = 16)
    public void test_registerComplaint_Against_NonLife_InsuranceCompany() throws IOException, TransformerException {

        test_name = "Positive_TC_016 : Register Complaint Against Non-Life Insurance Company";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_NON_LIFE_INSURANCE_COMPANY);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());
        reg.setInsuranceTypeId(BimaBharosaConstants.NON_LIFE);
        reg.setPolicyTypeId(PolicyTypeConstants.HEALTH_INSURANCE);
        reg.setComplaintTypeId(ComplaintTypeConstants.PROPOSAL_RELATED);
        reg.setComplaintDescriptionId(ComplaintDescConstants.INSURER_COLLECTED_PREMIUM_ISSUED_POLICY_WITHOUT_ANY_PROPOSAL_OR_CONFIRMATION_IN_WRITING_FROM_INSURED);

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_NON_LIFE_INSURANCE_COMPANY);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        if(res.getEntityCompRefNumber().equals(entityRefNumber) && res.getErrorCodes().isBlank()){
            message = "Test Passed : Complaint Registered Against Non-Life Insurance Company";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Not Registered Against Non-Life Insurance Company";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_NON_LIFE_INSURANCE_COMPANY);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
