package main.CreditCard;
import java.util.Date;

abstract public class CreditCardDetails {

    private Date expiryDate;
    private String nameOnCard;
    private String cardVarient;
    private String cardNumber;
    private boolean isValid;

    public CreditCardDetails (String cardNumber, Date expiryDate, String nameOnCard, boolean isValid, String cardVarient){
        this.nameOnCard = nameOnCard;
        this.cardVarient = cardVarient;
        this.isValid = isValid;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getnameOnCard(){return this.nameOnCard;}
    public String getcardVarient(){return this.cardVarient;}
    public String getcardNumber(){return this.cardNumber;}
    public boolean getisValid(){return this.isValid;}
    public Date getexpiryDate(){return this.expiryDate;}

}
