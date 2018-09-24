package joelepping.es.stockbasicoconrealm.ui.listProduct;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import joelepping.es.stockbasicoconrealm.R;
import joelepping.es.stockbasicoconrealm.control.ProductosControl;
import joelepping.es.stockbasicoconrealm.model.Productos;

public class ListActivity extends AppCompatActivity {
    private LinearLayoutManager gridLayoutManager;
    private CustomListaProductosAdapater adapater;
    private ProductosControl productosControl = new ProductosControl(this);
    private List<Productos> data_list;
    private RecyclerView recyclerView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final Drawable drawable = ContextCompat.getDrawable(this, R.drawable.border).mutate();
       /* recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        gridLayoutManager = new GridLayoutManager(this, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        data_list = productosControl.getProductos();
        adapater = new CustomListaProductosAdapater(this,data_list);
        adapater.notifyDataSetChanged();
        recyclerView.setAdapter(adapater);*/
        imageView = findViewById(R.id.image);
        data_list = productosControl.getProductos();
        TableLayout tl = (TableLayout) findViewById(R.id.tableLayout1);

        //imageView.setImageURI(Uri.parse(data_list.get(0).getImagen()));
        for (Productos p : data_list) {
           // TableRow row = new TableRow(this);
            TextView contador = (TextView) findViewById(R.id.txt_id);
            //contador.setBackground(drawable);
            TextView codigoBarra = (TextView) findViewById(R.id.txt_codigoBarra);
            //codigoBarra.setBackground(drawable);
            TextView nombre = (TextView) findViewById(R.id.txt_nombre);
            //nombre.setBackground(drawable);
            TextView modelo = (TextView) findViewById(R.id.txt_modelo);
           // modelo.setBackground(drawable);
            TextView precio = (TextView) findViewById(R.id.txt_precio);
            //precio.setBackground(drawable);
            TextView cantidad = (TextView) findViewById(R.id.txt_cantidad);
            //cantidad.setBackground(drawable);
            ImageView imagen = (ImageView)findViewById(R.id.img_producto);
            //imagen.setBackground(drawable);
            codigoBarra.setText(p.getCodigoDeBarra());
            contador.setText(String.valueOf(p.getId()));
            nombre.setText(p.getNombre());
            modelo.setText(p.getModelo());
            precio.setText(String.valueOf(p.getPrecio()));
            cantidad.setText(String.valueOf(p.getCantidad()));
            File direccionDeLaimagen = new File(p.getImagen());
            imagen.setImageBitmap(BitmapFactory.decodeFile(direccionDeLaimagen.getAbsolutePath()));
            /*row.addView(contador);
            row.addView(codigoBarra);
            row.addView(nombre);
            row.addView(precio);
            row.addView(cantidad);
            row.addView(modelo);
            row.addView(imagen);
            tl.addView(row);*/
        }
        if (ContextCompat.checkSelfPermission(ListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(ListActivity.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    54);
        }else{
            Toast.makeText(ListActivity.this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            // File direccionDeLaimagen = new File(data_list.get(1).getImagen());
            //imageView.setImageBitmap(BitmapFactory.decodeFile(direccionDeLaimagen.getAbsolutePath()));
           // imageView.setImageURI(Uri.parse(data_list.get(0).getImagen()));
           // Picasso.with(this).load(Uri.parse(data_list.get(0).getImagen())).into(imageView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 54:  {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ListActivity.this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                    //imageView.setImageURI(Uri.parse(data_list.get(0).getImagen()));
                    Picasso.with(this).load(new File(data_list.get(1).getImagen())).into(imageView);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ListActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    }

