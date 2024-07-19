package Operations;

import Constants.BimaBharosaConstants;
import Utilities.XMLHelper;

import Utilities.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;


public class Register {

    public String xmlFile;
    Document doc;

    public Register(String xmlFile) {
        try {
            this.xmlFile = xmlFile;
            // Load XML file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(System.getProperty("user.dir") + "/src/main/resources/XML/Register/" + this.xmlFile);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    //setters
    public void setFirstName(String firstName) throws TransformerException, IOException, ParserConfigurationException, SAXException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("First_Name").item(0);
            node.setTextContent(firstName);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"First_Name");

            //Set value to specified node
            Node node = doc.getElementsByTagName("First_Name").item(0);
            node.setTextContent(firstName);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }

    }

    public void setMobileNumber(String mob) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Mobile_Number").item(0);
            node.setTextContent(mob);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Mobile_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Mobile_Number").item(0);
            node.setTextContent(mob);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setUserType(String userType) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("User_Type").item(0);
            node.setTextContent(userType);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"User_Type");

            //Set value to specified node
            Node node = doc.getElementsByTagName("User_Type").item(0);
            node.setTextContent(userType);

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

    public void setComplaintDetails(String complaintDetails) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Details").item(0);
            node.setTextContent(complaintDetails);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Details");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Details").item(0);
            node.setTextContent(complaintDetails);

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

    public void setStateID(String stateID) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("State_Id").item(0);
            node.setTextContent(stateID);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"State_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("State_Id").item(0);
            node.setTextContent(stateID);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setDistrictID(String districtID) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("District_Id").item(0);
            node.setTextContent(districtID);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"District_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("District_Id").item(0);
            node.setTextContent(districtID);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setComplaintStatusId(String complaintStatusId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Status_Id").item(0);
            node.setTextContent(complaintStatusId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Status_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Status_Id").item(0);
            node.setTextContent(complaintStatusId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setComplaintAgainstTypeId(String complaintAgainstTypeId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Against_Type_Id").item(0);
            node.setTextContent(complaintAgainstTypeId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Against_Type_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Against_Type_Id").item(0);
            node.setTextContent(complaintAgainstTypeId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setInsuranceTypeId(String insuranceTypeId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Insurance_Type_Id").item(0);
            node.setTextContent(insuranceTypeId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Insurance_Type_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Insurance_Type_Id").item(0);
            node.setTextContent(insuranceTypeId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setPolicyTypeId(String policyTypeId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Policy_Type_Id").item(0);
            node.setTextContent(policyTypeId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Policy_Type_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Policy_Type_Id").item(0);
            node.setTextContent(policyTypeId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setComplaintTypeId(String complaintTypeId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Type_Id").item(0);
            node.setTextContent(complaintTypeId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Type_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Type_Id").item(0);
            node.setTextContent(complaintTypeId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setComplaintDescriptionId(String complaintDescriptionId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Complaint_Description_Id").item(0);
            node.setTextContent(complaintDescriptionId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Complaint_Description_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Complaint_Description_Id").item(0);
            node.setTextContent(complaintDescriptionId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setSourceOfComplaint(String sourceOfComplaint) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Source_Of_Complaint").item(0);
            node.setTextContent(sourceOfComplaint);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Source_Of_Complaint");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Source_Of_Complaint").item(0);
            node.setTextContent(sourceOfComplaint);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setPolicyNumber(String policyNumber) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Policy_Number").item(0);
            node.setTextContent(policyNumber);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Policy_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Policy_Number").item(0);
            node.setTextContent(policyNumber);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setOption(String option) throws TransformerException, IOException {
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

    public void setAddressedToInsurer(String addressedToInsurer) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Addressed_To_Insurer").item(0);
            node.setTextContent(addressedToInsurer);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Addressed_To_Insurer");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Addressed_To_Insurer").item(0);
            node.setTextContent(addressedToInsurer);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setTransLoginId(String transLoginId) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Trans_Login_Id").item(0);
            node.setTextContent(transLoginId);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Trans_Login_Id");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Trans_Login_Id").item(0);
            node.setTextContent(transLoginId);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setMode(String mode) throws TransformerException, IOException {
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

    public void setPolicyProposalCertClaimNumber(String policyProposalCertClaimNumber) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Policy_Proposal_Cert_ClaimNumber").item(0);
            node.setTextContent(policyProposalCertClaimNumber);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Policy_Proposal_Cert_ClaimNumber");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Policy_Proposal_Cert_ClaimNumber").item(0);
            node.setTextContent(policyProposalCertClaimNumber);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setIdentifierType(String identifierType) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Identifier_Type").item(0);
            node.setTextContent(identifierType);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Identifier_Type");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Identifier_Type").item(0);
            node.setTextContent(identifierType);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setBrokerLicenseNumber(String brokerLicenseNumber) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Broker_License_Number").item(0);
            node.setTextContent(brokerLicenseNumber);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Broker_License_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Broker_License_Number").item(0);
            node.setTextContent(brokerLicenseNumber);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setIntermediaryName(String intermediaryName) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Intermediary_Name").item(0);
            node.setTextContent(intermediaryName);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Intermediary_Name");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Intermediary_Name").item(0);
            node.setTextContent(intermediaryName);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }

    public void setIntermediaryLicenseNumber(String intermediaryLicenseNumber) throws TransformerException, IOException {
        try{
            //Set value to specified node if node exist
            Node node = doc.getElementsByTagName("Intermediary_License_Number").item(0);
            node.setTextContent(intermediaryLicenseNumber);
            updateXMLFile();
        }
        catch (Exception e){

            //Add node
            XMLHelper.addNode(doc,"Intermediary_License_Number");

            //Set value to specified node
            Node node = doc.getElementsByTagName("Intermediary_License_Number").item(0);
            node.setTextContent(intermediaryLicenseNumber);

            //Save xml file after adding node and setting values in file
            updateXMLFile();

        }
    }


    public void updateXMLFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.getProperty("user.dir") + "/src/main/resources/XML/Register/" + this.xmlFile);
        transformer.transform(source, result);

        //calling method to format and align the xml payload
        //XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, this.xmlFile);

    }


}





