package Operations;

import Utilities.XMLHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class Update {
    public String xmlFile;
    Document doc;
    public String firstName;
    public String entityCompRefNumber;

    public Update(String xmlFile) {
        try {
            this.xmlFile = xmlFile;
            // Load XML file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(System.getProperty("user.dir") + "/src/main/resources/XML/Update/" + this.xmlFile);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    public void setIrdaTokenNo(String irdaTokenNo) throws TransformerException, IOException {

        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("IRDA_Token_Number").item(0);
            node.setTextContent(irdaTokenNo);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"IRDA_Token_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("IRDA_Token_Number").item(0);
            node.setTextContent(irdaTokenNo);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }


    public void setEntityCompRefNumber(String entityCompRefNumber) throws TransformerException, IOException {

        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Entity_Complaint_Ref_Number").item(0);
            node.setTextContent(entityCompRefNumber);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Entity_Complaint_Ref_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Entity_Complaint_Ref_Number").item(0);
            node.setTextContent(entityCompRefNumber);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }
    public void setStatusCode(String statusCode) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Status_Id").item(0);
            node.setTextContent(statusCode);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Status_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Status_Id").item(0);
            node.setTextContent(statusCode);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }
    public void setStatusChangeDate(String date) throws TransformerException, IOException {

        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Status_Change_Date").item(0);
            node.setTextContent(date);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Status_Change_Date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Status_Id").item(0);
            node.setTextContent(date);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }
    public void setTypeOfDisposal(String typeOfDis) throws IOException, TransformerException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Type_of_disposal").item(0);
            node.setTextContent(typeOfDis);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Type_of_disposal");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Type_of_disposal").item(0);
            node.setTextContent(typeOfDis);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setRemarks(String typeOfDis) throws IOException, TransformerException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Remarks").item(0);
            node.setTextContent(typeOfDis);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Remarks");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Remarks").item(0);
            node.setTextContent(typeOfDis);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setEscalatedRemark(String escalatedRemark) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Escalated_Remark").item(0);
            node.setTextContent(escalatedRemark);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Escalated_Remark");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Escalated_Remark").item(0);
            node.setTextContent(escalatedRemark);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setOthersClsrAdditionalInfo(String othersClsrAdditionalInfo) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Others_Clsr_Additional_Info").item(0);
            node.setTextContent(othersClsrAdditionalInfo);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Others_Clsr_Additional_Info");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Others_Clsr_Additional_Info").item(0);
            node.setTextContent(othersClsrAdditionalInfo);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }

    public void setOthersClosureDescription(String othersClosureDescription) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Others_Closure_Description").item(0);
            node.setTextContent(othersClosureDescription);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Others_Closure_Description");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Others_Closure_Description").item(0);
            node.setTextContent(othersClosureDescription);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setRequestIRDAIForClosure(String requestIRDAIForClosure) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Request_IRDAI_For_Closure").item(0);
            node.setTextContent(requestIRDAIForClosure);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Request_IRDAI_For_Closure");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Request_IRDAI_For_Closure").item(0);
            node.setTextContent(requestIRDAIForClosure);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setEntityClosureReqDesc(String entityClosureReqDesc) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Entity_Closure_Req_Desc").item(0);
            node.setTextContent(entityClosureReqDesc);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Entity_Closure_Req_Desc");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Entity_Closure_Req_Desc").item(0);
            node.setTextContent(entityClosureReqDesc);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setClosureRequestLetterDate(String closureRequestLetterDate) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Closure_Request_Letter_Date").item(0);
            node.setTextContent(closureRequestLetterDate);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Closure_Request_Letter_Date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Closure_Request_Letter_Date").item(0);
            node.setTextContent(closureRequestLetterDate);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setDateOfHonoringService(String dateOfHonoringService) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Date_Of_Honoring_Service").item(0);
            node.setTextContent(dateOfHonoringService);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Date_Of_Honoring_Service");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Date_Of_Honoring_Service").item(0);
            node.setTextContent(dateOfHonoringService);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setInsurerResolutionLetterdate(String insurerResolutionLetterdate) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Insurer_Resolution_Letter_date").item(0);
            node.setTextContent(insurerResolutionLetterdate);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Insurer_Resolution_Letter_date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Insurer_Resolution_Letter_date").item(0);
            node.setTextContent(insurerResolutionLetterdate);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setClosureViolationRemarks(String closureViolationRemarks) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Closure_Violation_Remarks").item(0);
            node.setTextContent(closureViolationRemarks);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Closure_Violation_Remarks");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Closure_Violation_Remarks").item(0);
            node.setTextContent(closureViolationRemarks);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setRegulatoryImprovementSuggestion(String regulatoryImprovementSuggestion) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Regulatory_Improvement_Suggestion").item(0);
            node.setTextContent(regulatoryImprovementSuggestion);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Regulatory_Improvement_Suggestion");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Regulatory_Improvement_Suggestion").item(0);
            node.setTextContent(regulatoryImprovementSuggestion);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setIsComplainantInformed(String isComplainantInformed) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Is_Complainant_Informed").item(0);
            node.setTextContent(isComplainantInformed);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Is_Complainant_Informed");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Is_Complainant_Informed").item(0);
            node.setTextContent(isComplainantInformed);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setClosureActionSuggested(String closureActionSuggested) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Closure_Action_Suggested").item(0);
            node.setTextContent(closureActionSuggested);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Closure_Action_Suggested");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Closure_Action_Suggested").item(0);
            node.setTextContent(closureActionSuggested);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        };

    }
    public void setOriginal_IRDA_Token_Num(String Original_IRDA_Token_Num) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Original_IRDA_Token_Num").item(0);
            node.setTextContent(Original_IRDA_Token_Num);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Original_IRDA_Token_Num");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Original_IRDA_Token_Num").item(0);
            node.setTextContent(Original_IRDA_Token_Num);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setTransLoginId(String loginId) throws TransformerException, IOException {

        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Trans_Login_Id").item(0);
            node.setTextContent(loginId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Trans_Login_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Trans_Login_Id").item(0);
            node.setTextContent(loginId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }

    public void setOptionField(String option) throws TransformerException, IOException {

        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Option").item(0);
            node.setTextContent(option);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Option");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Option").item(0);
            node.setTextContent(option);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }

    public void setModeField(String mode) throws TransformerException, IOException {

        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Mode").item(0);
            node.setTextContent(mode);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Mode");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Mode").item(0);
            node.setTextContent(mode);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }
    public void setComplaintDate(String complaintDate) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Date").item(0);
            node.setTextContent(complaintDate);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Date").item(0);
            node.setTextContent(complaintDate);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setComplaintReceiptDate(String complaintReceiptDate) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Receipt_Date").item(0);
            node.setTextContent(complaintReceiptDate);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Receipt_Date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Receipt_Date").item(0);
            node.setTextContent(complaintReceiptDate);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setClaimReceivedAmount(String claimReceivedAmount) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Claim_Received_Amount").item(0);
            node.setTextContent(claimReceivedAmount);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Claim_Received_Amount");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Claim_Received_Amount").item(0);
            node.setTextContent(claimReceivedAmount);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setAdditionalClosureInformation(String additionalClosureInformation) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Claim_Received_Amount").item(0);
            node.setTextContent(additionalClosureInformation);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Claim_Received_Amount");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Claim_Received_Amount").item(0);
            node.setTextContent(additionalClosureInformation);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setClaimPaymentDate(String claimPaymentDate) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Claim_Payment_Date").item(0);
            node.setTextContent(claimPaymentDate);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Claim_Payment_Date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Claim_Payment_Date").item(0);
            node.setTextContent(claimPaymentDate);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setClaimPaymentChequeNumber(String claimPaymentChequeNumber) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Claim_Payment_Cheque_Number").item(0);
            node.setTextContent(claimPaymentChequeNumber);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Claim_Payment_Cheque_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Claim_Payment_Cheque_Number").item(0);
            node.setTextContent(claimPaymentChequeNumber);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setClaimPaymentChequeDate(String claimPaymentChequeDate) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Claim_Payment_Cheque_Date").item(0);
            node.setTextContent(claimPaymentChequeDate);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Claim_Payment_Cheque_Date");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Claim_Payment_Cheque_Date").item(0);
            node.setTextContent(claimPaymentChequeDate);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setClaimClosureAdditionalInfo(String claimClosureAdditionalInfo) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Claim_Clsr_Additional_Info").item(0);
            node.setTextContent(claimClosureAdditionalInfo);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Claim_Clsr_Additional_Info");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Claim_Clsr_Additional_Info").item(0);
            node.setTextContent(claimClosureAdditionalInfo);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void setIS_Does_Not_Pertain(String IS_Does_Not_Pertain) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("IS_Does_Not_Pertain").item(0);
            node.setTextContent(IS_Does_Not_Pertain);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"IS_Does_Not_Pertain");

            //Set value to specified node
            Node node = doc.getElementsByTagName("IS_Does_Not_Pertain").item(0);
            node.setTextContent(IS_Does_Not_Pertain);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setISDoesNotPertain_Remarks(String ISDoesNotPertain_Remarks) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("ISDoesNotPertain_Remarks").item(0);
            node.setTextContent(ISDoesNotPertain_Remarks);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"ISDoesNotPertain_Remarks");

            //Set value to specified node
            Node node = doc.getElementsByTagName("ISDoesNotPertain_Remarks").item(0);
            node.setTextContent(ISDoesNotPertain_Remarks);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }
    public void updateXMLFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.getProperty("user.dir") + "/src/main/resources/XML/Update/" + this.xmlFile);
        transformer.transform(source, result);

    }

}
