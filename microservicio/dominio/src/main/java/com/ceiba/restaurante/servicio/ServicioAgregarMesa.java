package com.ceiba.restaurante.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mesa.modelo.entidad.Mesa;
import com.ceiba.mesa.puerto.repositorio.RepositorioMesa;
import com.ceiba.mesa.servicio.ServicioCrearMesa;
import com.ceiba.restaurante.puerto.repositorio.RepositorioRestaurante;

public class ServicioAgregarMesa {

    private static final String RESTAURANTE_NO_EXISTE = "El restaurante no existe";
    private static final String EXISTE_ACTUALMENTE = "La mesa ya existe en este restaurante";

    private final RepositorioRestaurante repositorioRestaurante;
    private final RepositorioMesa repositorioMesa;
    private final ServicioCrearMesa servicioCrearMesa;

    public ServicioAgregarMesa(RepositorioRestaurante repositorioRestaurante, RepositorioMesa repositorioMesa, ServicioCrearMesa servicioCrearMesa) {
        this.repositorioRestaurante = repositorioRestaurante;
        this.repositorioMesa = repositorioMesa;
        this.servicioCrearMesa = servicioCrearMesa;
    }

    public void ejecutar(String nombreRestaurante, Mesa mesa){
        validarExistenciaRestaurante(nombreRestaurante);
        validarExistenciaMesa(nombreRestaurante, mesa.getIdentificador());
        this.servicioCrearMesa.ejecutar(nombreRestaurante, mesa);
    }

    private void validarExistenciaRestaurante(String nombreRestaurante){
        boolean existe = this.repositorioRestaurante.existe(nombreRestaurante);
        if(!existe){
            throw new ExcepcionSinDatos(RESTAURANTE_NO_EXISTE);
        }
    }

    private void validarExistenciaMesa(String nombreRestaurante, String identificador){
        boolean existe = this.repositorioMesa.existePorRestauranteYidentificador(nombreRestaurante, identificador);
        if(existe){
            throw new ExcepcionDuplicidad(EXISTE_ACTUALMENTE);
        }
    }

}


