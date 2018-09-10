package joelepping.es.stockbasicoconrealm.util;

import io.realm.Realm;
import joelepping.es.stockbasicoconrealm.model.Productos;

/**
 * Created by User on 9/9/2018.
 */

public class AutoIncrementProduct {

    public final static int incrementarId(){
        Realm realm = Realm.getDefaultInstance();
        Number currentIdNum = realm.where(Productos.class).max("id");
        int nextId;
        if(currentIdNum == null){
            nextId = 0;
        }else{
            nextId = currentIdNum.intValue()+1;
        }
        return nextId;
    }
}
