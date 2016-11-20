package com.gdg.dravit.graminsewa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  moveDictionary = (Button) findViewById(R.id.b_dictionary);
        moveDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,main_dict.class);
                startActivity(i);
            }
        });
        Button but=(Button)findViewById(R.id.send_mail);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,send_mail.class);
                startActivity(i);
            }
        });

        Button hn= (Button) findViewById(R.id.b_hn);
        hn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,helpline.class);
                startActivity(i);
            }
        });

    }
}
