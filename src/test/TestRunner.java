package test;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {

        Result res1 = JUnitCore.runClasses(MasterCreditCardTest.class);
        Result res2 = JUnitCore.runClasses(VisaCreditCardTest.class);
        Result res3 = JUnitCore.runClasses(AmExCreditCardTest.class);
        Result res4 = JUnitCore.runClasses(DiscoverCreditCardTest.class);

        System.out.println("\nMaster Card Test:");

        for (Failure fl : res1.getFailures()) {

            System.out.println(fl.toString());
        }

        System.out.println(res1.wasSuccessful());

        System.out.println("\nVisa Card Test:");

        for (Failure fl : res2.getFailures()) {

            System.out.println(fl.toString());
        }
        System.out.println(res2.wasSuccessful());

        System.out.println("\nAmericanExpress Card Test:");
        
        for (Failure fl : res3.getFailures()) {

            System.out.println(fl.toString());
        }
        System.out.println(res3.wasSuccessful());

        System.out.println("\nDiscover Card Test:");

        for (Failure fl : res4.getFailures()) {

            System.out.println(fl.toString());
        }

        System.out.println(res4.wasSuccessful());

    }
}