package team1_5115.cscc_laundryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Login extends AppCompatActivity implements View.OnClickListener {

    Button logIn;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logIn = (Button) findViewById(R.id.button);
        logIn.setOnClickListener(this);

        signUp = (Button) findViewById(R.id.button2);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button ){
            Intent intent = new Intent(Login.this,MainStatus.class);
            intent.putExtra("id", v.getId());
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(Login.this,CreateAccount.class);
            intent.putExtra("id", v.getId());
            startActivity(intent);

        }

    }

}
