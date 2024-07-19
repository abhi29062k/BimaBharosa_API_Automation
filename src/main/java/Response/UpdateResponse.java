package Response;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class UpdateResponse {
    XmlPath xmlPath;
    public UpdateResponse(Response response){
        xmlPath = response.xmlPath();

    }
    public String getIRDATokenNumber(){
        String tokenNumber = xmlPath.getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
        return tokenNumber;
    }
    public String getEntityCompRefNumber(){
        String entityCompRefNumber = xmlPath.getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
        return entityCompRefNumber;
    }
    public String getErrorCode(){
        String entityErrorCode = xmlPath.getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.ERROR_CODES");
        return entityErrorCode;
    }

}
