# MineriaDatos
Aplicación para entrenar algoritmos de clasificación utilizando la biblioteca de weka

Descripcion de la Funcionalidad

Al abrir la aplicacion cargar un nuevo archivo en formato arff, hay 2 ejemplos en la carpeta arff .
Automaticamente se procesaran los datos en las dos tablas superiores. la de la izquierda es la descripcion de los atributos del conjunto
de datos, la de la  derecha muestra las estadisticas sobre los atributos.

En el area de la grafica se puden elegir dos atributos para graficar su relacion(diferentes para que tenga sentido).

En el panel Clasificacion (inferior derecho) permite entrenar un Arbol de decision (C4.5) o un agrupador (K-means) a partir del conjunto
de datos cargado al principio. Permite establecer  el numero de iteraciones para validar  el algoritmo ya entrenado (Cross Validation)
Al hacer click en entrenar se mostrara el arbol entrenado o los datos del agrupador resultante.
