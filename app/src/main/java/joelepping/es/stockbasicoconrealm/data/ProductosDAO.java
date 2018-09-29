package joelepping.es.stockbasicoconrealm.data;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import joelepping.es.stockbasicoconrealm.model.Productos;
import joelepping.es.stockbasicoconrealm.ui.addProduct.NewProductActivity;
import joelepping.es.stockbasicoconrealm.ui.main.MainActivity;

import static joelepping.es.stockbasicoconrealm.util.AutoIncrementProduct.incrementarId;

public class ProductosDAO {
    private final String TAG = (ProductosDAO.this.getClass().getSimpleName());
    private Realm realm = Realm.getDefaultInstance();

    public final List<Productos> getProductos() {
        RealmResults<Productos> productos = realm.where(Productos.class).findAll();
        for (Productos productos1 : productos) {
            Log.i("Listado Productos", productos1.toString());
        }
        return productos;
    }

    public final Productos getProductos(String codigoDeBarra) {
        Productos productos = realm.where(Productos.class).equalTo("codigoDeBarra", codigoDeBarra).findFirst();
        if (productos != null) {
            Log.i("Listar Productos by id", productos.toString());
        }
        return productos;
    }

    public void insertarProductos(final Productos productos, final Context ctx) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int index = incrementarId();
                Productos realmProducto = realm.createObject(Productos.class, index);
                realmProducto.setCodigoDeBarra(productos.getCodigoDeBarra());
                realmProducto.setNombre(productos.getNombre());
                realmProducto.setModelo(productos.getModelo());
                realmProducto.setImagen(productos.getImagen());
                realmProducto.setCantidad(productos.getCantidad());
                realmProducto.setPrecio(productos.getPrecio());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG,"Insertado Correctamente");

                AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
                alertDialog.setTitle("Insertado Correctamente!");
                alertDialog.setMessage("¿Deseas agregar mas un producto?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ctx.startActivity(new Intent(ctx, NewProductActivity.class));
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ctx.startActivity(new Intent(ctx, MainActivity.class));
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,error.getMessage());
                Toast.makeText(ctx, "ha oucurrido un error, intente nuevamente"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}

