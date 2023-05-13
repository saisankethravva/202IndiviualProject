package main.Handler;

import java.util.Date;
import main.CreditCard.*;

public class CardHandlerAmEx implements Handler {
    private Handler successor = null;

    @Override
    public CreditCardDetails creditCardCheck(String cardNum, Date expiredDate, String cardHolderName) {
        int size;
        int primaryDigit;
        int secondaryDigit;
        System.out.println("AmExCreditCardHandler received the request");
        try {
            size = cardNum.length();
            primaryDigit = Character.getNumericValue(cardNum.charAt(0));
            secondaryDigit = Character.getNumericValue(cardNum.charAt(1));
        } catch (StringIndexOutOfBoundsException e) {
            // TODO: handle exception
            size = 0;
            primaryDigit = 0;
            secondaryDigit = 0;
        }
        if (size == 15 && primaryDigit == 3 && (secondaryDigit == 4 || secondaryDigit == 7)) {
            System.out.println("It is an AmEx Credit Card");
            return new CreditCardAmex(cardNum, expiredDate, cardHolderName, true, "AmericanExpress");
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
