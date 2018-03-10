package beanstage.com.lender;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beanstage.com.lender.adapters.FeeAdapter;
import beanstage.com.lender.models.Fee;
import beanstage.com.lender.models.Loan;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoanActivityFragment extends Fragment implements View.OnClickListener, LoanDialog.InfoDialogListener {

    RecyclerView mRecyclerView;
    FeeAdapter mFeeAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<Fee> mFeeList;
    Loan loan;

    double amount;
    double rate;
    int n;
    boolean isAnual;

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
        amount = loanBundleInfo.getDouble(Loan.AMOUNT, 0);
        rate =  loanBundleInfo.getDouble(Loan.RATE, 0);
        n =  loanBundleInfo.getInt(Loan.TERM, 0);
        isAnual =  loanBundleInfo.getBoolean(Loan.IS_ANUAL, false);

        refreshLoanList();
    }

    private void refreshLoanList(){
        mFeeList.clear();
        mRecyclerView.removeAllViews();
        // mFeeAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mFeeAdapter);

        loan = new Loan(amount, rate, n, isAnual);
        mFeeList = loan.calculateFee();

        mFeeAdapter.setmFeeList(mFeeList);
        mRecyclerView.setAdapter(mFeeAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(Loan.AMOUNT, amount);
        outState.putDouble(Loan.RATE, rate);
        outState.putInt(Loan.TERM, n);
        outState.putBoolean(Loan.IS_ANUAL, isAnual);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){

            amount = savedInstanceState.getDouble(Loan.AMOUNT, 0);
            rate =  savedInstanceState.getDouble(Loan.RATE, 0);
            n =  savedInstanceState.getInt(Loan.TERM, 0);

            refreshLoanList();
        }

    }
}
