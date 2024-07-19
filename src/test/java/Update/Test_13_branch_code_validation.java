package Update;

import Base.Main;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Request.Request;
import Response.UpdateResponse;
import Utilities.BimabharosaDatabaseHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import Response.RegisterResponse;
import org.testng.annotations.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static Base.Main.getCurrentDate;
import static Base.RegisterHelper.registerAgainst_Broker;

public class Test_13_branch_code_validation extends Main {
    @Test(priority = 70)
    public void registerAgainst_Broker_new_to_ack_positive_TC() throws IOException, TransformerException {
        Response response = registerAgainst_Broker();
        RegisterResponse newState = new RegisterResponse(response);
        String tokenNo = newState.getIRDATokenNumber();
        String entityRefNo =newState.getEntityCompRefNumber();

        Update setAckStatus =new Update("update.xml");
        setAckStatus.setIrdaTokenNo(tokenNo);
        setAckStatus.setEntityCompRefNumber(entityRefNo);
        setAckStatus.setStatusCode("2");
        setAckStatus.setStatusChangeDate(getCurrentDate());

        Response ackResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(ackResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Positive_TC_70: Register Against Broker with New to Acknowledge status change. (Defect)";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(newState.getEntityCompRefNumber()) && error_code.isBlank()) {
            message = "Register Against Broker with New to Acknowledge status change must have error.";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
