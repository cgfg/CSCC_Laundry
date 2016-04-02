package team1_5115.cscc_laundryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void clickEdit(View view){
        EditText editText = (EditText)findViewById(R.id.firstNameEdit);
        editText.setFocusableInTouchMode(true);
        editText = (EditText)findViewById(R.id.lastNameEdit);
        editText.setFocusableInTouchMode(true);
        editText = (EditText)findViewById(R.id.emailEdit);
        editText.setFocusableInTouchMode(true);
        editText = (EditText)findViewById(R.id.phoneNumEdit);
        editText.setFocusableInTouchMode(true);
        editText = (EditText)findViewById(R.id.buildingNumEdit);
        editText.setFocusableInTouchMode(true);
        editText = (EditText)findViewById(R.id.apartNumEdit);
        editText.setFocusableInTouchMode(true);
        editText = (EditText)findViewById(R.id.passwordEdit);
        editText.setFocusableInTouchMode(true);
    }

    public void clickSubmit(View view){
        EditText editText = (EditText)findViewById(R.id.firstNameEdit);
        editText.setFocusable(false);
        editText = (EditText)findViewById(R.id.lastNameEdit);
        editText.setFocusable(false);
        editText = (EditText)findViewById(R.id.emailEdit);
        editText.setFocusable(false);
        editText = (EditText)findViewById(R.id.phoneNumEdit);
        editText.setFocusable(false);
        editText = (EditText)findViewById(R.id.buildingNumEdit);
        editText.setFocusable(false);
        editText = (EditText)findViewById(R.id.apartNumEdit);
        editText.setFocusable(false);
        editText = (EditText)findViewById(R.id.passwordEdit);
        editText.setFocusable(false);
    }
}
