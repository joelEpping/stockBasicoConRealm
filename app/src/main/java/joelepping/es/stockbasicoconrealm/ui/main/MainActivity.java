package joelepping.es.stockbasicoconrealm.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;
import joelepping.es.stockbasicoconrealm.R;
import joelepping.es.stockbasicoconrealm.ui.addProduct.NewProductActivity;
import joelepping.es.stockbasicoconrealm.ui.listProduct.ListActivity;

public class MainActivity extends AppCompatActivity {


    GridLayout mainGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Clicked at: "+ finalI, Toast.LENGTH_SHORT).show();
                    resultado(finalI);


                }
            });
        }
    }

    private void resultado(int finalI) {
        switch (finalI){
            case 0:
                startActivity(new Intent(this, NewProductActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ListActivity.class));
        }


    }
}
