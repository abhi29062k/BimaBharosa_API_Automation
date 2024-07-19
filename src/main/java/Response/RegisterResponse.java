package Response;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class RegisterResponse {

    XmlPath xmlPath;

    public RegisterResponse(Response response){
        xmlPath = response.xmlPath();
    }

   public String getIRDATokenNumber(){
        String tokenNumber = xmlPath.getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
        return tokenNumber;
   }

    public String getEntityCompRefNumber(){
        String entityCompRefNumber = xmlPath.getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
        return entityCompRefNumber;
    }

    public String getErrorCodes(){
        String errorCode = xmlPath.getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ERROR_CODES");
        return errorCode;
    }
}
