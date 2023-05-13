package main.FileFormat;
import java.util.ArrayList;
import main.CreditCard.*;

abstract public class FormatFile {
    public abstract ArrayList<CreditCardDetails> readFile(String inputFilePath);
    public abstract boolean writeFile(ArrayList<CreditCardDetails> creditCardList, String outputFilePath);

}
