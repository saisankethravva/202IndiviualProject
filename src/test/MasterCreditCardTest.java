package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;
import main.Handler.*;
import main.CreditCard.*;

public class MasterCreditCardTest {
    @Test
    public void testMasterCC() {

        System.out.println("\nTesting Master Card");

        String cardNum1 = "5410000000000000";
        String cardNum2 = "6410000123400000";
        String cardNum3 = "5010001234000000";
        String cardNum4 = "54100123400000001";
        Date expiredDate = new Date();
        String cardHolderName = "Sanketh";
        String expectedCardType = "MasterCard";

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

        // Check whether it is a valid card
        assertTrue(cc1.getisValid());
        // Check whether card returns card type: "Master Card"
        assertEquals(expectedCardType, cc1.getcardVarient());
        // Check whether first digit of master card is 5
        assertFalse(cc2.getisValid());
        // Check whether second digit of master card is in range 1 to 5
        assertFalse(cc3.getisValid());
        // Check whether length of master card is 16 digits
        assertFalse(cc4.getisValid());

    }

}
