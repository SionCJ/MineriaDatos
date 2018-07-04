package mineriadatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.Instance;
//import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import weka.classifiers.trees.J48;
import weka.core.FastVector;

public class ArbolJ48 {
    // Constantes
    Instances entrenamiento, prueba;
    
    public ArbolJ48(String archivo) throws FileNotFoundException, IOException{
        BufferedReader buffreader = new BufferedReader( new FileReader(archivo) );
        entrenamiento = new Instances(buffreader);
        entrenamiento.setClassIndex(entrenamiento.numAttributes()-1);
    }
    
    public void entrenarAlgoritmo() throws Exception{
        System.out.println("Entrenando algoritmo...");
        J48 j48 = new J48();
        j48.buildClassifier(entrenamiento);
        System.out.println("Classifier:\n"+j48);
        System.out.println("Arbol construido");
    }
    
    public void DescripcionConjuntoEntrenamiento(){
        if( entrenamiento==null )
            System.out.println("No se ha cargado el conjunto de entrenamiento");
        else{
            System.out.println("Conjunto de Datos:\t"+entrenamiento.relationName());
            System.out.println(entrenamiento.numAttributes()+" atributos");
            System.out.println(entrenamiento.numInstances()+" instancias");
            System.out.println(entrenamiento.numClasses()+" clases");
        }
    }
    // Metodos estáticos [main()]
    public static void main(String[] args) {
        try {
            ArbolJ48 j48 = new ArbolJ48("iris.arff");
            j48.DescripcionConjuntoEntrenamiento();
            j48.entrenarAlgoritmo();
        } catch (IOException ex) {
            Logger.getLogger(ArbolJ48.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Archivo no encontrado");
        } catch (Exception ex) {
            Logger.getLogger(ArbolJ48.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void mensajeError() {
        System.out.println("ERROR: parametros incorrectos");
        System.out.println("formato: java ClasificadorSPAM [num. vecinos] [dir. entrenamiento] [dir. evaluacion]");
    }

}
