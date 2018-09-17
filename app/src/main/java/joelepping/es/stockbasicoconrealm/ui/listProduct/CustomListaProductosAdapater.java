package joelepping.es.stockbasicoconrealm.ui.listProduct;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import joelepping.es.stockbasicoconrealm.R;
import joelepping.es.stockbasicoconrealm.model.Productos;

/**
 * Created by Joel Sánchez} on 15/9/2018.
 */

public class CustomListaProductosAdapater extends RecyclerView.Adapter<CustomListaProductosAdapater.CustomViewHolder> {
    private Context context;
    private List<Productos> productos;

    public CustomListaProductosAdapater(Context context, List<Productos> productos) {
        this.context=context;
        this.productos=productos;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_productos,parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.nombre.setText(productos.get(position).getNombre());
        holder.modelo.setText(productos.get(position).getModelo());
        holder.cantidad.setText( String.valueOf(productos.get(position).getCantidad()));
        holder.precio.setText((String.valueOf(productos.get(position).getPrecio())));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class CustomViewHolder extends  RecyclerView.ViewHolder{


        //Componentes que dan los detalles de la denuncia
        @BindView(R.id.txt_nombre)TextView nombre;
        @BindView(R.id.txt_modelo) TextView modelo;
        @BindView(R.id.txt_cantidad)TextView cantidad;
        @BindView(R.id.txt_precio)TextView precio;

        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
