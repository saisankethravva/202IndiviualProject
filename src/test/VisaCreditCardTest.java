package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;
import main.Handler.*;
import main.CreditCard.*;

public class VisaCreditCardTest {

    @Test
    public void testVisaCC() {
        System.out.println("\nTesting Visa Card");

        String cardNum1 = "4120000000000";
        String cardNum2 = "8120000012340";
        String cardNum3 = "4120000000000123";
        String cardNum4 = "41200000000001234";
        String cardType = "Visa";
        Date expiredDate = new Date();
        String cardHolderName = "Sai Sanketh Ravva";

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

        // Check whether card is a valid card
        assertTrue(cc1.getisValid());
        // Check whether card returns card type: "Visa Card"
        assertEquals(cardType, cc1.getcardVarient());
        // Check whether first digit of visa card is 4
        assertFalse(cc2.getisValid());
        // Check whether length of visa card is 16 digits
        assertTrue(cc3.getisValid());
        // Check whether length of visa card is 13 or 16 digits
        assertFalse(cc4.getisValid());

    }

}
