package joelepping.es.stockbasicoconrealm.ui.addProduct;

import android.content.Intent;
import android.graphics.BitmapFactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import joelepping.es.stockbasicoconrealm.R;
import joelepping.es.stockbasicoconrealm.control.ProductosControl;
import joelepping.es.stockbasicoconrealm.model.Productos;
import joelepping.es.stockbasicoconrealm.util.*;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class NewProductActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler {
    private static final int PICK_IMAGE_REQUEST = 1;
    private  final String TAG = NewProductActivity.this.getClass().getSimpleName();
    private ZXingScannerView zXingScannerView;
    private File direccionDelaImagen;
    ProductosControl productosControl = new ProductosControl(this);
    private Productos productos;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_product);

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
        setContentView(R.layout.activity_new_product);
        ButterKnife.bind(this);
        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
        codigo = result.getText();
        Log.i(TAG, codigo);
        codigoBarra = (TextView) findViewById(R.id.codigoBarra);
        imageView = (ImageView) findViewById(R.id.ivProductImage);
        codigoBarra.setText(codigo);
        zXingScannerView.resumeCameraPreview(this);
    }

    public void pickerImage(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Failed to open picture!", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                direccionDelaImagen = FileUtil.from(this, data.getData());
                Log.i("RICA COMIDIDTA",""+ direccionDelaImagen);
                imageView.setImageBitmap(BitmapFactory.decodeFile(direccionDelaImagen.getAbsolutePath()));
               // actualSizeTextView.setText(String.format("Size : %s", getReadableFileSize(direccionDelaImagen.length())));
               // clearImage();
            } catch (IOException e) {
                Toast.makeText(this, "Failed to read picture data!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }


    public boolean validar(){
        final String nombre= this.nombre.getText().toString();
        final String modelo= this.modelo.getText().toString();
        final String  precio =this.precio.getText().toString();
        final String cantidad = this.cantidad.getText().toString();
        if(nombre.length()==0){
            this.nombre.requestFocus(); this.nombre.setError("Nombre no puede ser nulo");
            return false;
        }
        else if(modelo.length()==0){
            this.modelo.requestFocus(); this.modelo.setError("El modelo no puede ser nulo");
            return false;
        }else if(precio.length()==0){
            this.precio.requestFocus(); this.precio.setError("El precio no puede ser nulo");
            return false;
        }else if(cantidad.length()==0){
            this.cantidad.requestFocus(); this.cantidad.setError("La canitidad no puede ser nulo");
            return false;
        } else if(direccionDelaImagen == null){
            Toast.makeText(this, "Selecciones una image para el proucto", Toast.LENGTH_SHORT).show();
            return false;
        }

        productos = new Productos(0,codigoBarra.getText().toString(),nombre,modelo,String.valueOf(direccionDelaImagen), Float.valueOf(precio),Integer.valueOf(cantidad));
        return true;

    }

    public void guardarProducto(View view){
        if(validar()){
            productosControl.insertarProductos(productos);

        }
    }




}
