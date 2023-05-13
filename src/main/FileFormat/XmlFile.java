package main.FileFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import main.CreditCard.*;
import main.Handler.*;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.NodeList;
import java.util.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlFile extends FormatFile {
    @Override
    public ArrayList<CreditCardDetails> readFile(String fileInputPath) {

        CardHandlerAmEx amExCreditCardHandler = new CardHandlerAmEx();
        CardHandlerDiscover discoverCreditCardHandler = new CardHandlerDiscover();
        CardHandlerMaster masterCreditCardHandler = new CardHandlerMaster();
        CardHandlerVisa visaCreditCardHandler = new CardHandlerVisa();

        masterCreditCardHandler.setSuccessor(visaCreditCardHandler);
        visaCreditCardHandler.setSuccessor(amExCreditCardHandler);
        amExCreditCardHandler.setSuccessor(discoverCreditCardHandler);

        ArrayList<CreditCardDetails> creditCardList = new ArrayList<>();
        System.out.println("Reading xml file");

        File fileXml = new File(fileInputPath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(fileXml);
            document.getDocumentElement();

            NodeList nl = document.getElementsByTagName("CARD");

            System.out.println(nl.getLength());

            for (int j = 0; j < nl.getLength(); j++) {
                Node n = nl.item(j);
                String cardNumber;

                if (n.getNodeType() == Node.ELEMENT_NODE) {

                    Element ele = (Element) n;

                    try {
                        cardNumber = String.format(
                                (ele.getElementsByTagName("CARD_NUMBER").item(0).getTextContent()));
                    }

                    catch (NumberFormatException e) {

                        cardNumber = ele.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
                    }

                    Date expiredDate = simpleDateFormat
                            .parse(ele.getElementsByTagName("EXPIRATION_DATE").item(0).getTextContent());
                    String cardHolderName = ele.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();
                    creditCardList.add(masterCreditCardHandler.creditCardCheck(cardNumber, expiredDate, cardHolderName));
                }
            }
        }

        catch (Exception msg) {
            System.out.println(msg);
        }

        return creditCardList;
    }

    @Override
    public boolean writeFile(ArrayList<CreditCardDetails> creditCardList, String fileOutputPath) {

        System.out.println("Writing XML Document");

        File fileXml = new File(fileOutputPath + "output.xml");

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element ele = document.createElementNS("", "CARDS");
            document.appendChild(ele);

            for (CreditCardDetails creditCard : creditCardList) {
                Element rowEle = document.createElement("CARD");
                Element nCardNumber = document.createElement("CARD_NUMBER");

                nCardNumber.appendChild(document.createTextNode(creditCard.getcardNumber()));
                rowEle.appendChild(nCardNumber);

                Element nType = document.createElement("CARD_TYPE");
                nType.appendChild(document.createTextNode(creditCard.getcardVarient()));

                rowEle.appendChild(nType);

                ele.appendChild(rowEle);
            }

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();

            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StreamResult outFile = new StreamResult(fileXml);
            DOMSource ds = new DOMSource(document);
            tf.transform(ds, outFile);

        }

        catch (Exception msg) {
            msg.printStackTrace();
        }

        return true;
    }
}