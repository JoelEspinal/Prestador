package beanstage.com.lender.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelespinal on 24/02/18.
 */

public class Loan {

    public static final String AMOUNT = "amount";
    public static final String MONTHLY_RATE = "monthly_rate";
    public static final String TERM = "term";

    public List<Fee> calculateFee(double amount, double rate, int n){
        List<Fee> feeList = new ArrayList<Fee>();

        return feeList;
    }
}
