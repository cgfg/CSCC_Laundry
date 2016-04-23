package team1_5115.cscc_laundryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.button2 ){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
    }
}
