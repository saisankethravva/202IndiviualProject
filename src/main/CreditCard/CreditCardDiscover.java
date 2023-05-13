package main.CreditCard;
import java.util.Date;

public class CreditCardDiscover extends CreditCardDetails{
    public CreditCardDiscover(String cardNumber, Date expiryDate, String nameOnCard, boolean isValid, String cardVarient) {
        super(cardNumber, expiryDate, nameOnCard, isValid, cardVarient);
    }
}
