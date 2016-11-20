package com.gdg.dravit.graminsewa;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class main_dict extends AppCompatActivity {

    ImageButton search,fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dict);
        search = (ImageButton) findViewById(R.id.ib_main_search);
        fav = (ImageButton) findViewById(R.id.ib_main_fav);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main_dict.this,f_dictionary.class);
                startActivity(i);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main_dict.this,s_dictionary.class);
                startActivity(i);
            }
        });
    }
}
