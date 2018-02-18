package beanstage.com.lender;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by User on 2/18/2018.
 */

public class LoanDialog extends DialogFragment {

    private EditText mAmountEditText;
    private EditText mRateEditText;
    private EditText mTermEditText;

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

        mAmountEditText = (EditText) view.findViewById(R.id.edit_amount);
        mRateEditText = (EditText) view.findViewById(R.id.edit_rate);
        mTermEditText = (EditText) view.findViewById(R.id.edit_term);

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
        void onFinishEditInfo(Bundle info);
    }

    public void sendBackResult() {
        InfoDialogListener infoDialogListener = (InfoDialogListener) getTargetFragment();

        Bundle bundle = new Bundle();

        String amountText = mAmountEditText.getText().toString();
        String rateText = mRateEditText.getText().toString();
        String termText = mTermEditText.getText().toString();

        if(!(amountText.isEmpty() || rateText.isEmpty() || termText.isEmpty())){
            int amountValue = Integer.valueOf(amountText);
            double anualRateValue = Double.valueOf(rateText);
            int termValue = Integer.valueOf(mTermEditText.getText().toString());

            bundle.putInt("amount", amountValue);
            bundle.putDouble("anual_rate", anualRateValue);
            bundle.putInt("term", termValue);

        }

        infoDialogListener.onFinishEditInfo(bundle);
        dismiss();

    }
}