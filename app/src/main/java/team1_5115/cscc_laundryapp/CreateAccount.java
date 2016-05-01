package team1_5115.cscc_laundryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    Button creat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        creat = (Button) findViewById(R.id.button3);
        creat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CreateAccount.this,MainStatus.class);
        intent.putExtra("id", v.getId());
        startActivity(intent);
    }
}
