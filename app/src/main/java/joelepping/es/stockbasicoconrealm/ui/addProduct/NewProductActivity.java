package joelepping.es.stockbasicoconrealm.ui.addProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import joelepping.es.stockbasicoconrealm.R;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class NewProductActivity extends AppCompatActivity  implements ZBarScannerView.ResultHandler {
    private static final String TAG = "NewProductActivity";
    private ZBarScannerView mScannerView;
    private TextView codigoBarra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_product);
        codigoBarra = findViewById(R.id.codigoBarra);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(mScannerView!=null) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mScannerView!=null)
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void handleResult(Result result) {
        // Do something with the result here
        setContentView(R.layout.activity_new_product);
        Log.i(TAG, result.getContents()); // Prints scan results
        Log.i(TAG, result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
        Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
        // If you would like to resume scanning, call this method below:
       codigoBarra.setText(result.getContents());
        mScannerView.resumeCameraPreview(this);
    }
}
