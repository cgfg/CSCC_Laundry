package team1_5115.cscc_laundryapp;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CycleSelectDryer extends AppCompatActivity implements CycleSelectFragment.OnFragmentInteractionListener, CycleConfirmFragment.OnFragmentInteractionListener {
    int selectdMachineId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cycle_select_dryers);
        // TODO: get dynamic transparency to work for dryers
//        Bundle extras = getIntent().getExtras();
//        if (extras.containsKey("id")) {
//            selectdMachineId = extras.getInt("id");
//            Button icon = (Button)findViewById(selectdMachineId);
//            icon.setAlpha(1);
//        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CycleSelectFragment issueFragment = new CycleSelectFragment();
        transaction.add(R.id.cycle_select_dryer_container, issueFragment, "issue_fragment");
        transaction.commit();

    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.cycle_radio_group);
        RadioButton radio_1 = (RadioButton) findViewById(R.id.cycle_1_radio);
        RadioButton radio_2 = (RadioButton) findViewById(R.id.cycle_2_radio);
        RadioButton radio_3 = (RadioButton) findViewById(R.id.cycle_3_radio);
        RadioButton radio_4 = (RadioButton) findViewById(R.id.cycle_4_radio);
        radio_1.setTypeface(null, Typeface.NORMAL);
        radio_2.setTypeface(null, Typeface.NORMAL);
        radio_3.setTypeface(null, Typeface.NORMAL);
        radio_4.setTypeface(null, Typeface.NORMAL);
        RadioButton newRadio = (RadioButton) radioGroup.findViewById(view.getId());
        newRadio.setTypeface(null, Typeface.BOLD);
    }

    public void onIssueSubmitButtonClicked(View view) {
        CycleSelectFragment oldFragment = (CycleSelectFragment) getSupportFragmentManager().findFragmentById(R.id.cycle_options_fragment);
        CycleConfirmFragment newFragment = new CycleConfirmFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.cycle_select_dryer_container, newFragment);
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
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cycle_select_dryer);
//    }
