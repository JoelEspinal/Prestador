package beanstage.com.lender.models;

/**
 * Created by joelespinal on 03/03/18.
 */

public class Fee {

    public int n;
    public double fee;
    public double amortization;
    public double interest;
    public double balance;



    //      Calculate fees, french mode
    //      R = P [(i (1 + i)^n) / ((1 + i)^n – 1)]
    public static double calculateFee(double amount, double i, int n){
        double fee = amount * ((i * Math.pow((1+ i), n))/(Math.pow((1 + i),  n) -  1));
        fee = (double) Math.round(fee * 100) / 100;
        return fee;
    }


}
