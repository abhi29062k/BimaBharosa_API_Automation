package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Operations.Register;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.ErrorCodes;
import Utilities.XMLHelper;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import Response.RegisterResponse;

public class Test_30_PolicyProposalCertClaimNumber_Negative extends Main {

    @Test(priority = 78)
    public void test_Policy_Proposal_Cert_Claim_Number_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_078 : Register Complaint with Null Policy Proposal Cert Claim Number";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Policy_Proposal_Cert_ClaimNumber");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.POLICY_PROPOSAL_CERT_CLAIM_NUMBER_MANDATORY)) {
            message = "Test Passed : Policy proposal cert claim number is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Policy proposal cert claim number is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";

        }

        // ReSet the node with valid value..
        reg.setPolicyProposalCertClaimNumber("10529899");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

}
