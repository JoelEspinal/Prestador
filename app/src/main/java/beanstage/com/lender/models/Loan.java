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

        double fee = 0.0;

       if(mIsAnual){
           i = (double) i/12;
       }

//      Calculate fees, french mode
//      R = P [(i (1 + i)^n) / ((1 + i)^n – 1)]
       fee = this.mAmount * ((i * Math.pow((1+ i), n))/(Math.pow((1 + i),  n) -  1));
       fee = (double) Math.round(fee * 100) / 100;
        for(int it = 0; it < n; it++){
            Fee feeObj = new Fee();
            feeObj.fee = fee;


            feeList.add(feeObj);
        }

        return feeList;
    }
}
