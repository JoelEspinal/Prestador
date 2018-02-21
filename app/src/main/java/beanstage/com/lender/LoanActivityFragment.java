package beanstage.com.lender;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoanActivityFragment extends Fragment implements View.OnClickListener, LoanDialog.InfoDialogListener {

    public LoanActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_loan, container, false);

        view.findViewById(R.id.calculate).setOnClickListener(this);


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
    public void onFinishEditInfo(Bundle info) {
        Toast.makeText(getActivity(), "Amount: " + info.getInt("amount", 0), Toast.LENGTH_LONG).show();
    }
}
