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
    public double mRrate;
    public int mTerm;
    public boolean mIsAnual;


    public Loan(double amount, double rate, int n, boolean isAnual){
        mAmount = amount;
        mRrate = rate;
        mTerm = n;
        mIsAnual = isAnual;
    }

    public List<Fee> calculateFee(){
        List<Fee> feeList = new ArrayList<Fee>();

       if(!mIsAnual){
           mRrate = (double) mRrate/12;
       }

        Fee fee = new Fee();
        fee.fee = 100.0;
        fee.amortization = 20.0;
        fee.interest = 1.5;
        fee.balance = 50;

        feeList.add(fee);

        return feeList;
    }
}
