package com.n.sqliteactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MeaningActivity extends AppCompatActivity {
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        tvOutput = findViewById(R.id.tvOutput);
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            String meaning = bundle.getString("meaning");
            tvOutput.setText(meaning);
        }else{
            Toast.makeText(this,"No message",Toast.LENGTH_LONG).show();
        }
    }
}
