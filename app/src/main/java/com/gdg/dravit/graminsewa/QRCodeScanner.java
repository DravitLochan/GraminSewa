package com.gdg.dravit.graminsewa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRCodeScanner extends AppCompatActivity {

    TextView tv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);

        tv= (TextView) findViewById(R.id.message);
        b= (Button) findViewById(R.id.show_qr);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap myQRCode = BitmapFactory.decodeStream(
                            getAssets().open("static_qr_code_without_logo.jpg")
                    );
                    BarcodeDetector barcodeDetector =
                            new BarcodeDetector.Builder(getApplicationContext())
                                    .setBarcodeFormats(Barcode.QR_CODE)
                                    .build();
                    Frame myFrame = new Frame.Builder()
                            .setBitmap(myQRCode)
                            .build();
                    SparseArray<Barcode> barcodes = barcodeDetector.detect(myFrame);
                    if(barcodes.size() != 0) {

                        // Print the QR code's message
                        tv.setText(barcodes.valueAt(0).displayValue);
                    }

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"error occured while reading the qr code!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
