package com.ahmedibrahim.waher.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.ahmedibrahim.waher.R;
import com.facebook.FacebookSdk;

/**
 * Created by cca on 16/01/2019.
 */

public class WinActivity extends AppCompatActivity {

    TextView tvaddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wins);
        tvaddr = (TextView)findViewById(R.id.tv_address);
        if(getIntent()!=null) {
            String receivingAddress = getIntent().getStringExtra("receiving_address");
            tvaddr.setText(receivingAddress);
        }
    }

    public void btn_goto_home(View view){
        Intent intent = new Intent(WinActivity.this,HomeActivity.class);
        startActivity(intent);
    }

}
