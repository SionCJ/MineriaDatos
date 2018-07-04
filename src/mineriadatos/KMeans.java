package mineriadatos;

import java.io.FileInputStream;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class KMeans {

    public static void main(String[] args) {
        if (args.length != 2) {
            mensajeError();
            System.exit(0);
        }

        try {
            // Crear y configurar algoritmo de clustering
            System.out.println("Crear algoritmo de clustering ...");
            SimpleKMeans clusterer = new SimpleKMeans();
            clusterer.setNumClusters(Integer.parseInt(args[0]));
            clusterer.setMaxIterations(100);

            // Cargar dataset desde ARFF
            System.out.println("Cargar dataset ...");            
            ArffLoader cargadorARRF = new ArffLoader();
            cargadorARRF.setSource(new FileInputStream(args[1]));
            Instances dataset = cargadorARRF.getDataSet();

            // Entrenar algoritmo de clustering
            System.out.println("Entrenar algoritmo de clustering ...");
            clusterer.buildClusterer(dataset);
            System.out.println();

            // Identificar el cluster de cada instancia
            Instance instancia;
            int cluster;
            System.out.println("Asignación de instancias a clusters ...");
            for (int i=0; i < dataset.numInstances(); i++)  {
                instancia = dataset.instance(i);
                cluster = clusterer.clusterInstance(instancia);
                System.out.println("[Cluster "+cluster+"] Instancia: "+instancia.toString());
            }
            System.out.println();
            
            // Imprimir centroides (sÃ³lo con SimpleKMeans [o similares])            
            int[] tamanoClusters = clusterer.getClusterSizes();
            Instances centroides = clusterer.getClusterCentroids();
            Instance centroide;
            System.out.println("Centroides k-means ...");
            for(cluster=0; cluster < clusterer.numberOfClusters(); cluster++){
                centroide = centroides.instance(cluster);
                System.out.print("Cluster "+cluster+" ("+tamanoClusters[cluster]+" instancias): ");
                System.out.println("Centroide["+centroide.toString()+"]");                
            }
            System.out.println();
                        
        } catch (Exception e) {
            System.out.println("Error en clustering: " + e.getMessage());
        }
    }

    private static void mensajeError() {
        System.out.println("ERROR: parametros incorrectos");
        System.out.println("formato: java ClusteringSimple [num. clusters] [fichero ARFF]");
    }
    
}
