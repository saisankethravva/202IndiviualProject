package main.CreditCard;
import java.util.Date;

public class CreditCardVisa extends CreditCardDetails{
    public CreditCardVisa(String cardNumber, Date expiryDate, String nameOnCard, boolean isValid, String cardVarient) {
        super(cardNumber, expiryDate, nameOnCard, isValid, cardVarient);
    }
}
