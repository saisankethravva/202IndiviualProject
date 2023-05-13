package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;
import main.Handler.*;
import main.CreditCard.*;

public class DiscoverCreditCardTest {

    @Test
    public void testDiscoverCC() {
        System.out.println("\nTesting Discover Card");

        String cardNum1 = "6010000000000000";
        String cardNum2 = "6011000000123456";
        String cardNum3 = "601100000000000012";
        Date expiredDate = new Date();
        String cardHolderName = "Sanketh";
        String cardType = "Discover";

        CardHandlerVisa visaCreditCardHandler = new CardHandlerVisa();
        CardHandlerAmEx amExCreditCardHandler = new CardHandlerAmEx();
        CardHandlerMaster masterCreditCardHandler = new CardHandlerMaster();
        CardHandlerDiscover discoverCreditCardHandler = new CardHandlerDiscover();

        masterCreditCardHandler.setSuccessor(visaCreditCardHandler);
        visaCreditCardHandler.setSuccessor(amExCreditCardHandler);
        amExCreditCardHandler.setSuccessor(discoverCreditCardHandler);

        CreditCardDetails cc1 = masterCreditCardHandler.creditCardCheck(cardNum1, expiredDate, cardHolderName);
        CreditCardDetails cc2 = masterCreditCardHandler.creditCardCheck(cardNum2, expiredDate, cardHolderName);
        CreditCardDetails cc3 = masterCreditCardHandler.creditCardCheck(cardNum3, expiredDate, cardHolderName);

        // Check whether first four digits are 6011
        assertFalse(cc1.getisValid());
        // Check whether it is a valid card
        assertTrue(cc2.getisValid());
        // Check whether card returns card type: "Discover Card"
        assertEquals(cardType, cc2.getcardVarient());
        // Check whether length of Discover carrd is 16 digits
        assertFalse(cc3.getisValid());

    }

}
