package Update;

import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.UpdateResponse;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.XMLHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import static Base.Update_Complaint_Status_Helper.*;
import static Utilities.ErrorCodes.REMARKS_MANDATORY;
import static Utilities.ErrorCodes.TYPE_OF_DISPOSAL_MANDATORY;

public class Test_6_pen_to_Att_mand_field_validation extends Main {
    @Test(priority = 34)
    //Pending to att
    public void pen_to_att_without_mand_field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_34:Pending to Attended to without mand field";
        test=extent.createTest(test_name);
        if (error_code.equals(REMARKS_MANDATORY +","+TYPE_OF_DISPOSAL_MANDATORY)) {
            message = "Pending to Attended to status change not done.";
            test.log(Status.PASS,  message +" Error code <b>"+error_code+"</b>");
            logger.info(message + " Error code: "+error_code);
            status = "PASS";
        } else {
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
