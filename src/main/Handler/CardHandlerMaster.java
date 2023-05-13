package main.Handler;

import java.util.Date;
import main.CreditCard.*;

public class CardHandlerMaster implements Handler {
    private Handler successor = null;

    @Override
    public CreditCardDetails creditCardCheck(String cardNum, Date expiredDate, String cardHolderName) {
        int primaryDigit;
        int secondaryDigit;
        int size;
        System.out.println("MasterCreditCardHandler received the request");

        try {
            size = cardNum.length();
            primaryDigit = Character.getNumericValue(cardNum.charAt(0));
            secondaryDigit = Character.getNumericValue(cardNum.charAt(1));
        } catch (StringIndexOutOfBoundsException e) {
            size = 0;
            primaryDigit = 0;
            secondaryDigit = 0;
        }

        if (primaryDigit == 5 && secondaryDigit <= 5 && secondaryDigit >= 1 && size == 16) {
            System.out.println("This is a Master Credit Card");
            return new CreditCardMaster(cardNum, expiredDate, cardHolderName, true, "MasterCard");
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
