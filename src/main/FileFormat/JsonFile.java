package main.FileFormat;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.CreditCard.*;
import main.Handler.*;
import java.io.File;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.codehaus.jackson.map.ObjectMapper;

public class JsonFile extends FormatFile{
    @Override
    public ArrayList<CreditCardDetails> readFile(String fileInputPath) {

        CardHandlerMaster masterCreditCardHandler = new CardHandlerMaster();
        CardHandlerDiscover discoverCreditCardHandler = new CardHandlerDiscover();
        CardHandlerVisa visaCreditCardHandler = new CardHandlerVisa();
        CardHandlerAmEx amExCreditCardHandler = new CardHandlerAmEx();
        
        visaCreditCardHandler.setSuccessor(amExCreditCardHandler);
        amExCreditCardHandler.setSuccessor(discoverCreditCardHandler);
        masterCreditCardHandler.setSuccessor(visaCreditCardHandler);

        ArrayList<CreditCardDetails> listCreditCard = new ArrayList<>();
        System.out.println("Parsing JSON Document");

        File jsonFile = new File(fileInputPath);
        JSONParser jsonParser = new JSONParser();

        try {

            Object ob = jsonParser.parse(new FileReader(jsonFile));
            JSONObject cardItems = (JSONObject) ob;
            JSONArray cl = (JSONArray)cardItems.get("cards");

            for (int j=0; j<cl.size(); j++){
                JSONObject cc = (JSONObject) cl.get(j);
                String cn = String.valueOf(cc.get("cardNumber"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
                String cardHolderName = (String) cc.get("cardHolderName");
                Date expiryDate = simpleDateFormat.parse(String.valueOf(cc.get("expirationDate")));
                listCreditCard.add(masterCreditCardHandler.creditCardCheck(cn, expiryDate, cardHolderName));
            }

        } 
        
        catch (Exception msg) {
            msg.printStackTrace();
        }

        return listCreditCard;
    }

    @Override
    public boolean writeFile(ArrayList<CreditCardDetails> creditCardList, String fileOutputPath) {
        System.out.println("Writing to JSON Document");

        try {

            FileWriter fileWriter = new FileWriter(fileOutputPath + "output.json");
            //JSONObject cardList = new JSONObject();
            //ObjectMapper objectMapper = new ObjectMapper();

            fileWriter.write("{");
			fileWriter.write('\n');
            fileWriter.write("\"cardItems\":");
            fileWriter.write("[");
            fileWriter.write('\n');

  			for (int j = 0; j < creditCardList.size(); j++) {

  				CreditCardDetails creditcard = creditCardList.get(j);
  				fileWriter.write("{");
  				fileWriter.write('\n');

  				if (creditcard.getcardNumber() == "null") {
                    fileWriter.write("\"cardNumber\": " + creditcard.getcardNumber() + ",");
                } else {
                    fileWriter.write("\"cardNumber\": " + "\"" + creditcard.getcardNumber() + "\"" + ",");

                }
  				fileWriter.write('\n');

  				fileWriter.write("\"CardType\": " + "\"" + creditcard.getcardVarient() + "\"");
  				fileWriter.write('\n');

  				
  				if(j == creditCardList.size() - 1) {
                    fileWriter.write("}");
  				}
  				else {
                    fileWriter.write("},");
  				}
  				
  				fileWriter.write('\n');

  			}

  			fileWriter.write("]");
  			fileWriter.write("}");
  			fileWriter.close();

        } 
        
        catch (IOException msg) {
            msg.printStackTrace();
        }

        return true;
    }

}
