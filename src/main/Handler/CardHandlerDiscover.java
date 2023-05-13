package main.Handler;

import java.util.Date;
import main.CreditCard.*;

public class CardHandlerDiscover implements Handler {
    private Handler successor = null;

    @Override
    public CreditCardDetails creditCardCheck(String cardNum, Date expiredDate, String cardHolderName) {
        int size = cardNum.length();
        System.out.println("DiscoverCreditCardHandler received the request");

        if (cardNum.startsWith("6011") && size == 16 && cardNum.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            System.out.println("This is a Discover Credit Card");
            return new CreditCardDiscover(cardNum, expiredDate, cardHolderName, true, "Discover");
        } else {
            if (successor != null)
                return successor.creditCardCheck(cardNum, expiredDate, cardHolderName);
        }

        String res = "";
        if (cardNum.isEmpty() || cardNum == "null") {
            res = "Invalid: empty/null card number";
        } else if (!cardNum.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            res = "Invalid: non numeric characters";
        } else if (size > 16) {
            res = "Invalid: more than 19 digits";
        } else {
            res = "Invalid: not a possible card number";
        }

        System.out.println("ERROR: It is an Invalid Card!");
        return new CreditCardDiscover(cardNum, expiredDate, cardHolderName, false, res);
    }

    @Override
    public void setSuccessor(Handler next) {
        this.successor = next;
    }
}
