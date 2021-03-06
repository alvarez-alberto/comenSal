package com.ceiba.descuento.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;

public class ServicioCrearDescuento {

    private final RepositorioDescuento repositorioDescuento;

    public ServicioCrearDescuento(RepositorioDescuento repositorioDescuento) {
        this.repositorioDescuento = repositorioDescuento;
    }

    public Long ejecutar(String nombreRestaurante, Descuento descuento){
        return this.repositorioDescuento.crear( nombreRestaurante, descuento);
    }
}
