package beanstage.com.lender;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class LoanActivity extends AppCompatActivity {

    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            mFragment = getSupportFragmentManager().getFragment(savedInstanceState, "LOAN_FRAGMENT");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        getSupportFragmentManager().putFragment(outState, "LOAN_FRAGMENT", mFragment);
    }
}
