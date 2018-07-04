/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineriadatos;

/**
 *
 * @author Sion
 */
public class OpcionesClasificador {
    public boolean tipoArbol;
    public int minInstancias, semilla;
    public float factor;

    OpcionesClasificador(boolean tipo, float factor, int minInstancias, int semilla) {
        tipoArbol = tipo;
        this.factor = factor;
        this.minInstancias = minInstancias;
        this.semilla = semilla;
    }
}
