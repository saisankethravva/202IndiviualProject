package main.CreditCard;
import java.util.Date;

public class CreditCardMaster extends CreditCardDetails{
    public CreditCardMaster(String cardNumber, Date expiryDate, String nameOnCard, boolean isValid, String cardVarient) {
        super(cardNumber, expiryDate, nameOnCard, isValid, cardVarient);
    }
}
