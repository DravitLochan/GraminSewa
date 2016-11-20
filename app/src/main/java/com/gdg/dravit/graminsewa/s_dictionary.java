package com.gdg.dravit.graminsewa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Locale;

import static android.R.attr.visible;


public class s_dictionary extends AppCompatActivity implements TextToSpeech.OnInitListener{

    SharedPreferences pref;
     FloatingActionButton fab;
    ImageButton speak;
    EditText word ;
    TextView meaning;
    private TextToSpeech tts;
    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_dictionary);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        speak= (ImageButton) findViewById(R.id.ib_speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOut();
            }
                        /*
                        *   using some method, add the meaning to the tv_meaning.
                        *   scrapping wont be an easy stuff though
                        */
        });
        word = (EditText) findViewById(R.id.et_word);
        setSupportActionBar(toolbar);
        fab.setVisibility(View.GONE);
        //speak.setVisibility(View.GONE);
        Button search = (Button)findViewById(R.id.b_search);
        meaning = (TextView)findViewById(R.id.tv_meaning);
        tts= new TextToSpeech(this,this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv=(TextView)findViewById(R.id.tv_meaning);
                EditText et_word = (EditText) findViewById(R.id.et_word);
                String word = et_word.getText().toString();
                String url="http://www.collinsdictionary.com/dictionary/english/"+word;
                String mean= "the orignal meaning will be scrapped and witten here";
                try {
                    Document doc= Jsoup.parse(url);
                    /*
                    *       code to extract menaing will be entered here!
                     */


                } catch (Exception e) {
                    e.printStackTrace();
                    tv.setText("error occured!");
                    tv.setVisibility(View.GONE);
                }
                if(!(tv.getText().toString().equals("error occured!")))
                {
                    fab.setVisibility(View.VISIBLE);
                    //speak.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(mean);
                }
                else
                {
                    Snackbar.make(view, "sorry! Internal error occured!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added to favorites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addToFav();
            }
        });
    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    private void addToFav() {
        pref =getSharedPreferences("Dictionary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =pref.edit();
        try
        {
            k=pref.getInt("k",0);
        }
        catch(Exception e)
        {
            k=0;
            editor.putInt("k",0);
            editor.commit();
        }
        editor.putString(k+"",word.getText().toString()+"->"+meaning.getText().toString());
        editor.putInt("k",++k);
        editor.commit();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    public void speakOut()
    {
        word=(EditText) findViewById(R.id.et_word);
               String str= word.getText().toString();
        //tts.setLanguage(Language.);
        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
    }
}
