package com.gdg.dravit.graminsewa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class f_dictionary extends AppCompatActivity {

    SharedPreferences pref;
    final int max=10000;
    String[] word_list={"1","2","3","1","2","3","1","2","3","1","2","3","1","2","3","1","2","3","1","2","3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_dictionary);
       // String[] word_list= new String[max];
        pref =getSharedPreferences("Dictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =pref.edit();
        int k=pref.getInt("k",0);

            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, word_list);

            ListView listView = (ListView) findViewById(R.id.word_list);
            listView.setAdapter(adapter);
        }
    }

