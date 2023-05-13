package main.FileFormat;

import java.text.ParseException;
import java.util.Date;
import main.CreditCard.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import main.Handler.*;
import java.io.*;

public class CsvFile extends FormatFile {

    @Override
    public ArrayList<CreditCardDetails> readFile(String fileInputPath) {
        CardHandlerVisa creditVisaCardHandler = new CardHandlerVisa();
        CardHandlerAmEx creditAmexCardHandler = new CardHandlerAmEx();
        CardHandlerDiscover creditDiscoverCardHandler = new CardHandlerDiscover();
        CardHandlerMaster creditMasterCardHandler = new CardHandlerMaster();

        creditMasterCardHandler.setSuccessor(creditVisaCardHandler);
        creditVisaCardHandler.setSuccessor(creditAmexCardHandler);
        creditAmexCardHandler.setSuccessor(creditDiscoverCardHandler);

        ArrayList<CreditCardDetails> creditCardList = new ArrayList<>();

        System.out.println("Parsing CSV document");

        File csvFile = new File(fileInputPath);
        String splitCsv = ",";
        String line;
        int x = 1;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = buffReader.readLine()) != null) {
                if (x > 1) {
                    String[] eachLine = line.split(splitCsv);
                    String cardNumber;
                    try {
                        try {
                            cardNumber = String.format(eachLine[0]);
                        } catch (NumberFormatException e) {

                            cardNumber = eachLine[0];

                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                        cardNumber = "null";
                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");

                    try {
                        Date expiryDate = simpleDateFormat.parse(eachLine[1]);
                        String nameOnCard = eachLine[2];
                        creditCardList.add(creditMasterCardHandler.creditCardCheck(cardNumber, expiryDate, nameOnCard));

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }

                }
                x++;
            }
        }

        catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return creditCardList;
    }

    @Override
    public boolean writeFile(ArrayList<CreditCardDetails> creditCardList, String fileOutputPath) {
        String limitter = ",";
        String splitter = "\n";

        System.out.println("Writing to CSV document");
        File csvFile = new File(fileOutputPath + "output.csv");

        try (PrintWriter pw = new PrintWriter(csvFile)) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("cardNumber");
            stringBuilder.append(limitter);
            stringBuilder.append("cardType");
            // stringBuilder.append(",");
            stringBuilder.append(splitter);

            for (CreditCardDetails creditCard : creditCardList) {
                stringBuilder.append(creditCard.getcardNumber());
                stringBuilder.append(limitter);
                stringBuilder.append(creditCard.getcardVarient());
                stringBuilder.append(splitter);
            }

            pw.write(stringBuilder.toString());
        }

        catch (FileNotFoundException msg) {
            System.out.println(msg.getMessage());
        }
        return true;
    }
}