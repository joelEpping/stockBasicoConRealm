package joelepping.es.stockbasicoconrealm.control;

import android.content.Context;

import java.util.List;

import joelepping.es.stockbasicoconrealm.data.ProductosDAO;
import joelepping.es.stockbasicoconrealm.model.Productos;

/**
 * Created by User on 9/9/2018.
 */

public class ProductosControl {
    Context ctx;
    public ProductosControl(Context context) {
        ctx = context;
    }

    public List<Productos> getProductos(){
        return new ProductosDAO().getProductos();
    }

    public Productos getProductos(String codigoDeBarra){
        return  new ProductosDAO().getProductos(codigoDeBarra);
    }

    public void insertarProductos(Productos productos){
            new ProductosDAO().insertarProductos(productos,ctx);
    }
}
