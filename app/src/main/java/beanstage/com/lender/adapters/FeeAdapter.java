package beanstage.com.lender.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import beanstage.com.lender.R;
import beanstage.com.lender.models.Fee;

/**
 * Created by joelespinal on 03/03/18.
 */

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.ViewHolder> {

    private List<Fee> mFeeList;

    public FeeAdapter(List<Fee> feeList){
        mFeeList = feeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFeeTextView;
        public TextView mAortizationTextView;
        public TextView mInteresTextView;
        public TextView mBalanceTextView;

        public ViewHolder(View feeView) {
            super(feeView);
            mFeeTextView = (TextView) feeView.findViewById(R.id.feeTextView);
            mAortizationTextView = (TextView) feeView.findViewById(R.id.amortizationTextView);
            mInteresTextView = (TextView) feeView.findViewById(R.id.interesTextView);
            mBalanceTextView = (TextView) feeView.findViewById(R.id.balanceTextView);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View feeView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fee_row_layout, parent, false);

        ViewHolder vh = new ViewHolder(feeView);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Fee fee = mFeeList.get(position);

        holder.mFeeTextView.setText(String.valueOf(fee.fee));
        holder.mAortizationTextView.setText(String.valueOf(fee.amortization));
        holder.mInteresTextView.setText(String.valueOf(fee.interest));
        holder.mBalanceTextView.setText(String.valueOf(fee.balance));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFeeList.size();
    }
}
