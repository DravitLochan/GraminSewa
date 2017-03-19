package com.gdg.dravit.graminsewa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.net.Uri;

public class send_mail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
    }

    public void send(View view)                                     //onClickButtonEvent
    {
        TextView et1 = (TextView) findViewById(R.id.to);
        EditText et2 = (EditText) findViewById(R.id.sub);
        EditText et3 = (EditText) findViewById(R.id.body);
        String to= "contact@graminseva.com";
        String sub= et2.getText().toString();
        String body= et3.getText().toString();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_USER,to);
        email.putExtra(Intent.EXTRA_SUBJECT,sub);
        email.putExtra(Intent.EXTRA_TEXT,body);

        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email,"choose a client"));
    }

}
