package beanstage.com.lender.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelespinal on 24/02/18.
 */

public class Loan {

    public static final String AMOUNT = "AMOUNT";
    public static final String RATE = "RATE";
    public static final String TERM = "N";   // TERM is equal to TERM
    public static final String IS_ANUAL = "IS_ANUAL";

    public double mAmount;
    public double i;
    public int n;
    public boolean mIsAnual;

    public Loan(double amount, double rate, int term, boolean isAnual){
        mAmount = amount;
        i = rate;
        n = term;
        mIsAnual = isAnual;
    }

    public List<Fee> calculateFee(){
        List<Fee> feeList = new ArrayList<Fee>();

       if(mIsAnual){
           i = (double) i/12;
       }

       i = i / 100;

        double balance =  Math.round(this.mAmount * 100) / 100;

        Fee feeObj = new Fee();
        feeObj.n = 0;
        feeObj.fee = 0;
        feeObj.amortization = 0;
        feeObj.balance = Math.round(balance * 100) / 100;

        feeList.add(feeObj);

        for(int it = 0; it < n; it++){
            feeObj = new Fee();
            feeObj.fee = Fee.calculateFee(mAmount, i, n);

            feeObj.interest = (double) Math.round(Fee.calculateFeeInterest(balance, i) * 100)/ 100;
            feeObj.amortization = (double) Math.round(Fee.calculateAmortization(feeObj.fee, feeObj.interest) * 100)/ 100;
            feeObj.balance = (double) Math.round(Fee.calculateBalance(balance, feeObj.amortization) * 100)/ 100;
            feeObj.n = (it + 1);

            feeList.add(feeObj);

            // update previus balance to current balance
            balance = feeObj.balance;
        }

        return feeList;
    }
}
