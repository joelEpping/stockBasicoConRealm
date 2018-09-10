package joelepping.es.stockbasicoconrealm.control;

import java.util.List;

import joelepping.es.stockbasicoconrealm.data.ProductosDAO;
import joelepping.es.stockbasicoconrealm.model.Productos;

/**
 * Created by User on 9/9/2018.
 */

public class ProductosControl {

    public List<Productos> getProductos(){
        return new ProductosDAO().getProductos();
    }

    public Productos getProductos(int id){
        return  new ProductosDAO().getProductos(id);
    }

    public void insertarProductos(Productos productos){
         new ProductosDAO().insertarProductos(productos);
    }
}
