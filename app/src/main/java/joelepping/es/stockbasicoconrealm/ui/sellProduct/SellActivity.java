package joelepping.es.stockbasicoconrealm.ui.sellProduct;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import joelepping.es.stockbasicoconrealm.R;
import joelepping.es.stockbasicoconrealm.control.ProductosControl;
import joelepping.es.stockbasicoconrealm.model.Productos;
import joelepping.es.stockbasicoconrealm.ui.addProduct.NewProductActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SellActivity extends AppCompatActivity   implements ZXingScannerView.ResultHandler{
    private  final String TAG = SellActivity.this.getClass().getSimpleName();
    private ZXingScannerView zXingScannerView;
    String codigo;

    @BindView(R.id.etProductName)
    EditText nombre;
    @BindView(R.id.etProductModel)
    EditText modelo;
    @BindView(R.id.etProductPrice)
    EditText precio;
    @BindView(R.id.etProductQuantity)
    EditText cantidad;
    @BindView(R.id.codigoBarra)
    TextView codigoBarra;
    @BindView(R.id.ivProductImage)
    ImageView imageView;
    @BindView(R.id.btn_guardar)
    Button guardarPrducto;
    @BindView(R.id.btnProductImage)
    Button pickerImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_sell);

        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (zXingScannerView != null)
            zXingScannerView.stopCamera();
    }
    @Override
    public void handleResult(Result result) {

        codigo = result.getText();
        Log.i(TAG, codigo);
        Realm realm = Realm.getDefaultInstance();
        Productos productos1 = realm.where(Productos.class).equalTo("codigoDeBarra", codigo).findFirst();

        if (productos1 != null) {
            setContentView(R.layout.activity_new_product);
            ButterKnife.bind(this);
            pickerImage.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
            nombre.setText(productos1.getNombre());
            precio.setText(String.valueOf(productos1.getPrecio()));
            cantidad.setText(String.valueOf(productos1.getCantidad()));
            modelo.setText(productos1.getModelo());
            codigoBarra.setText(codigo);
            File direccionDeLaimagen = new File(productos1.getImagen());
            imageView.setBackground(Drawable.createFromPath(direccionDeLaimagen.getAbsolutePath()));
            guardarPrducto.setText("Vender");
            guardarPrducto.setEnabled(false);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(SellActivity.this).create();
            alertDialog.setTitle("Atención!");
            alertDialog.setMessage("Este Producto no esta registrar deseas Registrarlo?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           Intent intent = new Intent(SellActivity.this,NewProductActivity.class);
                           intent.putExtra("codigoDeBarra",codigo);
                           startActivity(intent);
                        }
                    });
            alertDialog.show();

        }

    }
}
