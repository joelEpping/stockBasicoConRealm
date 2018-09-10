package joelepping.es.stockbasicoconrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by User on 9/9/2018.
 */

public class stockbasicoconrealmApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        inicializarRealm();

    }

    private void inicializarRealm(){
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("stock_basico.realm")
                .schemaVersion(1)
//                .deleteRealmIfMigrationNeeded()
//                //.initialData(new RealmInitialData())
//                //.migration()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
         Realm.getDefaultInstance();

    }
}
