package Base;

import Operations.Register;
import Operations.Update;
import Request.Request;
import io.restassured.response.Response;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Random;

public class RegisterHelper extends Main{

    /*Register Complaint Against Insurance Company*/
    public static Response registerAgainst_InsCompany() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_AgainstInsuranceCompany.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);

        Response response = Request.register("Register","register_AgainstInsuranceCompany.xml");
        return response;
    }
    public static Response registerAgainst_InsCompany_withPastDateReg() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_AgainstInsuranceCompany.xml");
        register.setComplaintDate(getPastDate());
        register.setComplaintReceiptDate(getPastDate());
        register.setEntityCompRefNumber(entityRefNo);

        Response response = Request.register("Register","register_AgainstInsuranceCompany.xml");
        return response;
    }


    /*Register Complaint Against Broker*/
    public static Response registerAgainst_Broker() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_AgainstBroker.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);
        register.setComplaintAgainstTypeId("2");
        register.setBrokerLicenseNumber("B317");

        Response response = Request.register("Register","register_AgainstBroker.xml");
        return response;
    }

    /*Register Complaint Against Surveyor*/
    public static Response registerAgainst_Surveyor() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_AgainstSurveyor.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);
        register.setComplaintAgainstTypeId("5");
        register.setIntermediaryName("M/S THE INDIAN SURVEYORS PVT LTD");
        register.setIntermediaryLicenseNumber("IRDA/CORP/SLA-0334");

        Response response = Request.register("Register","register_AgainstSurveyor.xml");
        return response;
    }

    /*Register Complaint Against TPA*/
    public static Response registerAgainst_TPA() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_AgainstTPA.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);
        register.setComplaintAgainstTypeId("6");
        register.setIntermediaryName("Park Mediclaim TPA Private Ltd.");
        register.setIntermediaryLicenseNumber("25");

        Response response = Request.register("Register","register_AgainstTPA.xml");
        return response;
    }

    /*Register Complaint Against Agent*/
    public static Response registerAgainst_Agent() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_AgainstAgent.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);
        register.setComplaintAgainstTypeId("7");
        register.setIntermediaryName("ASHOK KUMAR SINGH");
        register.setIntermediaryLicenseNumber("IRDA/IND/SLA-10181");

        Response response = Request.register("Register","register_AgainstAgent.xml");
        return response;
    }

    /*Register Complaint For life Insurance Company*/
    public static Response registerAgainst_Life_InsCompany() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_Against_Life_InsCompany.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);
        register.setInsuranceTypeId("1");
        register.setPolicyTypeId("1");
        register.setComplaintTypeId("1");
        register.setComplaintDescriptionId("1");

        Response response = Request.register("Register","register_Against_Life_InsCompany.xml");
        return response;
    }

    /*Register Complaint For Non-Life Insurance Company*/
    public static Response registerAgainst_NonLife_InsCompany() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Register register = new Register("register_Against_Non-Life_InsCompany.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);
        register.setInsuranceTypeId("2");
        register.setPolicyTypeId("5");
        register.setComplaintTypeId("7");
        register.setComplaintDescriptionId("89");

        Response response = Request.register("Register","register_Against_Non-Life_InsCompany.xml");
        return response;
    }
    public static Response registerAgainst_InsCompany_claim() throws TransformerException, IOException {
        String entityRefNo = generateEntityReferenceNo();
        Update register = new Update("register_claim.xml");
        register.setComplaintDate(getCurrentDate());
        register.setComplaintReceiptDate(getCurrentDate());
        register.setEntityCompRefNumber(entityRefNo);

        Response response = Request.register("Update","register_claim.xml");
        return response;
    }

}