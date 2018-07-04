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
public class OpcionesCluster {
    public int numGrupos, numIter, semilla;
    
    OpcionesCluster(int grupos, int iteraciones, int semilla) {
        numGrupos = grupos;
        numIter = iteraciones;
        this.semilla = semilla;
    }
}
