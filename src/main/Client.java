package main;

import java.util.ArrayList;
import java.util.Scanner;
import main.CreditCard.*;
import main.FileFormat.*;

public class Client {

    public static void main(String[] args) {
        FormatFile fileNew;
        ArrayList<CreditCardDetails> creditCardList;

        System.out.println("Please enter input file name: (csv/xml/json)");
        Scanner inputData = new Scanner(System.in);

        if (inputData.hasNextLine()) {
            String fileType = inputData.nextLine();
            fileNew = FileFactory.makeFile(fileType);

            if (fileNew != null) {
                creditCardList = fileNew.readFile(
                        "/Users/saisankethravva/Desktop/202Indiviual_project/202IndiviualProject/src/" + fileType);
                System.out.println("Successfully read!");
                if (fileNew.writeFile(creditCardList,
                        "/Users/saisankethravva/Desktop/202Indiviual_project/202IndiviualProject/src/")) {
                    System.out.println("Successfully written!");
                }
            } else {
                System.out.println("File Not Found!");
            }
        }

    }

}
