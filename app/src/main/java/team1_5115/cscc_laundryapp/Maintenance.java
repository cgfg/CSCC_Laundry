package team1_5115.cscc_laundryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Maintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maintenance_radioButton1:
                if (checked) {
                    Toast.makeText(this, "You selected Problem 1", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.maintenance_radioButton2:
                if (checked) {
                    Toast.makeText(this.getBaseContext(), "You selected Problem 2", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.maintenance_radioButton3:
                if (checked) {
                    Toast.makeText(this.getBaseContext(),"You selected Problem 3", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.maintenance_radioButton4:
                if (checked) {
                    Toast.makeText(this.getBaseContext(),"You selected Other", Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }
}
