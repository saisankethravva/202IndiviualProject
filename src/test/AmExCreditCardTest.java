package test;

import static org.junit.Assert.*;
import main.Handler.*;
import org.junit.Test;
import java.util.Date;
import main.CreditCard.*;

public class AmExCreditCardTest {

    @Test
    public void testAmExCC() {
        System.out.println("\nTesting AmericanExpress Card");

        String cardNum1 = "341000000000000";
        String cardNum2 = "741000000123400";
        String cardNum3 = "371000001234500";
        String cardNum4 = "301000000000000";
        String cardNum5 = "34100000000000012";

        Date expiredDate = new Date();
        String cardHolderName = "Sai Sanketh";
        String cardType = "AmericanExpress";

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
        CreditCardDetails cc4 = masterCreditCardHandler.creditCardCheck(cardNum4, expiredDate, cardHolderName);
        CreditCardDetails cc5 = masterCreditCardHandler.creditCardCheck(cardNum5, expiredDate, cardHolderName);

        // Check whether it is a valid card
        assertTrue(cc1.getisValid());
        // Check whether it returns card type: "AmericanExpress Card"
        assertEquals(cardType, cc1.getcardVarient());
        // Check whether first digit of AmericanExpress card is 3
        assertFalse(cc2.getisValid());
        // Check whether second digit of AmericanExpress card is 3 or 7
        assertTrue(cc3.getisValid());
        assertFalse(cc4.getisValid());
        // Check whether length of AmericanExpress card is 15 digits
        assertFalse(cc5.getisValid());

    }

}
