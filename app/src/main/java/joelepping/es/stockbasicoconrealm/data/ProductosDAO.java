package joelepping.es.stockbasicoconrealm.data;


import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import joelepping.es.stockbasicoconrealm.model.Productos;

import static joelepping.es.stockbasicoconrealm.util.AutoIncrementProduct.incrementarId;

public class ProductosDAO {
    private boolean estadoInsercion;
    private final String TAG = (ProductosDAO.this.getClass().getSimpleName());
    private Realm realm = Realm.getDefaultInstance();

    public final List<Productos> getProductos() {
        RealmResults<Productos> productos = realm.where(Productos.class).findAll();
        for (Productos productos1 : productos) {
            Log.i("Listado Productos", productos1.toString());
        }
        return productos;
    }

    public final Productos getProductos(int id) {
        Productos productos = realm.where(Productos.class).equalTo("id", id).findFirst();
        if (productos != null) {
            Log.i("Listar Productos by id", productos.toString());
        }
        return productos;
    }

    public boolean insertarProductos(final Productos productos) {

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

              estadoInsercion = true;

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,error.getMessage());
                Log.e(TAG,"pasa colado aqui");
                estadoInsercion = false;
            }
        });
        Log.e(TAG, String.valueOf(estadoInsercion));
        return estadoInsercion;
    }

}

