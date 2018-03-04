package beanstage.com.lender;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import beanstage.com.lender.adapters.FeeAdapter;
import beanstage.com.lender.models.Fee;
import beanstage.com.lender.models.Loan;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoanActivityFragment extends Fragment implements View.OnClickListener, LoanDialog.InfoDialogListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mFeeAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private   List<Fee> mFeeList;

    public LoanActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_loan, container, false);



        view.findViewById(R.id.calculate).setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.feeRcicleView);



        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mFeeList = new ArrayList<Fee>();
        mFeeAdapter = new FeeAdapter(mFeeList);
        mRecyclerView.setAdapter(mFeeAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calculate:{
                showLoanDialog();
                break;
            }

            default:
                break;
        }
    }

    private void showLoanDialog(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        LoanDialog loanDialog = new LoanDialog();
        loanDialog.setTargetFragment(this, 300);
        loanDialog.show(fm, "Loan Details");
    }

    @Override
    public void onFinishEditInfo(Bundle loanBundleInfo) {
        double amount = loanBundleInfo.getDouble(Loan.AMOUNT, 0);
        double rate =  loanBundleInfo.getDouble(Loan.RATE, 0);
        int n =  loanBundleInfo.getInt(Loan.TERM, 0);
        boolean isAnual =  loanBundleInfo.getBoolean(Loan.IS_ANUAL, false);

        Loan loan = new Loan(amount, rate, n, isAnual);

        mFeeList.addAll(loan.calculateFee());

    }
}
