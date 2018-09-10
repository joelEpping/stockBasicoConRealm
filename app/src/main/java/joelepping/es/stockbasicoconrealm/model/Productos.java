package joelepping.es.stockbasicoconrealm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User on 9/9/2018.
 */

public class Productos extends RealmObject {
    @PrimaryKey
    private int id;
    private String codigoDeBarra;
    private String nombre;
    private String modelo;
    private String imagen;
    private float precio;
    private int cantidad;

    public Productos() {
    }

    public Productos(int id, String codigoDeBarra, String nombre, String modelo, String imagen, float precio, int cantidad) {
        this.id = id;
        this.codigoDeBarra = codigoDeBarra;
        this.nombre = nombre;
        this.modelo = modelo;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id=" + id +
                ", codigoDeBarra='" + codigoDeBarra + '\'' +
                ", nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
