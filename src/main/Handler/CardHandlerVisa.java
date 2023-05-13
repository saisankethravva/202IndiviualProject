package main.Handler;

import java.util.Date;
import main.CreditCard.*;

public class CardHandlerVisa implements Handler {
    private Handler successor = null;

    @Override
    public CreditCardDetails creditCardCheck(String cardNum, Date expiredDate, String cardHolderName) {
        int primaryDigit;
        int size;
        System.out.println("VisaCreditCardHandler received the request");

        try {
            size = cardNum.length();
            primaryDigit = Character.getNumericValue(cardNum.charAt(0));
        } catch (StringIndexOutOfBoundsException e) {
            size = 0;
            primaryDigit = 0;
        }
        if ((size == 16 || size == 13) && primaryDigit == 4) {
            System.out.println("This is a Visa Credit Card");
            return new CreditCardVisa(cardNum, expiredDate, cardHolderName, true, "Visa");
        } else {
            if (successor != null)
                return successor.creditCardCheck(cardNum, expiredDate, cardHolderName);
        }
        return null;
    }

    @Override
    public void setSuccessor(Handler next) {
        this.successor = next;
    }
}
