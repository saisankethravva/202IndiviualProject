package main.Handler;
import java.util.Date;
import main.CreditCard.*;

public interface Handler {

    public CreditCardDetails creditCardCheck(String cardNum, Date expiredDate, String cardHolderName);
    public void setSuccessor(Handler next);

}
