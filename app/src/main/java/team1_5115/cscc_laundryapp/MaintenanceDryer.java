package team1_5115.cscc_laundryapp;

import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Leet on 4/26/2016.
 */
public class MaintenanceDryer extends AppCompatActivity implements MaintenanceIssueFragment.OnFragmentInteractionListener, MaintenanceConfirmFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_dryer);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MaintenanceIssueFragment issueFragment = new MaintenanceIssueFragment();
        transaction.add(R.id.maintenance_dryer_container, issueFragment, "dryer_fragment");
        transaction.commit();
    }

    public void onIssueSubmitButtonClicked(View view) {
        MaintenanceIssueFragment oldFragment = (MaintenanceIssueFragment) getSupportFragmentManager().findFragmentById(R.id.maintenance_issue_fragment);
        MaintenanceConfirmFragment newFragment = new MaintenanceConfirmFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.maintenance_dryer_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onConfirmSubmitButtonClicked(View view) {
        Toast.makeText(this.getBaseContext(), "A maintenance request has sent", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onCancelButtonClicked(View view) {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
