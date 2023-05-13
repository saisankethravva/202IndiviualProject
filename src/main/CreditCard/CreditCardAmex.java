package main.CreditCard;
import java.util.Date;

public class CreditCardAmex extends CreditCardDetails{
    public CreditCardAmex(String cardNumber, Date expiryDate, String nameOnCard, boolean isValid, String  cardVarient) {
        super(cardNumber, expiryDate, nameOnCard, isValid,  cardVarient);
    }
}
