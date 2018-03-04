package beanstage.com.lender;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import beanstage.com.lender.models.Loan;

/**
 * Created by User on 2/18/2018.
 */

public class LoanDialog extends DialogFragment {

    private EditText mAmountEditText;
    private EditText mRateEditText;
    private EditText mTermEditText;
    private ToggleButton mIsAnualCheck;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_field_loan, container);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_field_loan, null);

        mAmountEditText = (EditText) view.findViewById(R.id.amountEditText);
        mRateEditText = (EditText) view.findViewById(R.id.rateEditText);
        mTermEditText = (EditText) view.findViewById(R.id.termEditText);
        mIsAnualCheck = (ToggleButton) view.findViewById(R.id.isAnualToggleButton);
        Resources res = getResources();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle(res.getString(R.string.loan_info)).setPositiveButton(res.getText(R.string.calculate), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoanDialog.this.sendBackResult();
            }
        });

        builder.setNegativeButton(res.getText(R.string.cancel), null);
        return builder.create();
    }

    public interface InfoDialogListener {
        void onFinishEditInfo(Bundle loanBundleInfo);
    }

    public void sendBackResult() {
        InfoDialogListener infoDialogListener = (InfoDialogListener) getTargetFragment();

        Bundle bundle = new Bundle();

        String amountText = mAmountEditText.getText().toString();
        String rateText = mRateEditText.getText().toString();
        String termText = mTermEditText.getText().toString();
        boolean isRateAnual = mIsAnualCheck.isChecked();

        if(!(amountText.isEmpty() || rateText.isEmpty() || termText.isEmpty())){
            double amountValue = Double.valueOf(amountText);
            double anualRateValue = Double.valueOf(rateText);
            int n = Integer.valueOf(termText);

            bundle.putDouble(Loan.AMOUNT, amountValue);
            bundle.putDouble(Loan.RATE, anualRateValue);
            bundle.putInt(Loan.TERM, n);
            bundle.putBoolean(Loan.IS_ANUAL, isRateAnual);
        }

        infoDialogListener.onFinishEditInfo(bundle);
        dismiss();
    }
}