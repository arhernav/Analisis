/**
  Clase para modelar conjuntos ajenos
 */

import java.util.HashMap;
public class DisjointSets{

    /**
      Clase interna para modelar los elementos en los  conjuntos ajenos
     */
    protected class Nodo{

	//Nombre del elemento
	String nombre;
	//Tamaño del subarbol del elemento
	int tamaño;
	//Padre del  elemento
	Nodo padre;

	/**
	 * Constructor de Nodo
	 * @param String - Nombre del  elemento
	 */
	public Nodo(String nombre){
	    this.nombre = nombre;
	    this.tamaño = 1;
	    this.padre = this;
	}

	/**
	 * Setter para el tamaño del Nodo
	 * @param int - Tamaño a asignar al nodo
	 */
	protected void setTamaño(int tamaño){
	    this.tamaño = tamaño;
	}

	
	/**
	 * Metodo para obtener el nombre del elemento
	 * @return String - Nombre del elemento
	 */
	protected String getNombre(){
	    return this.nombre;
	}

	/**
	 * Metodo que devuelve el padre del nodo
	 * @return Nodo - Padre del nodo
	 */
	protected Nodo getPadre(){
	    return this.padre;
	}


	/**
	 * Metodo que devuelve el nodo representante de la clase
	 * @return Nodo - Nodo representante de la clase
	 */
	protected Nodo getRepresentante(){
	    if(this == this.padre) return this;
	    return this.padre.getRepresentante();
	}

	/**
	 * Metodo toString()
	 * @return String - Representacion en  cadena del nodo con el formato:
	 *         Nombre, tamaño, padre, representante
	 */
	@Override
	public String toString(){
	    return this.nombre + ": tamaño = " + this.tamaño + ", padre = " + this.padre.nombre + ", representante: " + this.getRepresentante().nombre;
	}
	
    }

    
    /*Hash map para facilitar la creacion de conjuntos ajenos*/
    HashMap<String, Nodo> set = new HashMap<>();

    /**
     * Metodo para añadir un nuevo elemento al conjunto.
     * Este queda como elemento unico de su clase
     * @param String - Nombre del elemento
     */
    public void addSet(String s){
	Nodo nuevo = new Nodo(s);
	set.put(s, nuevo);
    }

    /**
     * Metodo para imprimir todos los elementos del conjunto
     */
     public void print(){
	set.forEach( (string, nodo) -> {System.out.println(nodo);});
    }
    
    /**
     * Metodo que devuelve el representante de la clase de un elemento
     * @param String - elemento del cual se devolvera el representante
     * En caso  de no estar el elemento, regresa el mensaje temporal
     */
    public String getRepresentante(String s){
	if(!set.containsKey(s)) return "Valor no en el conjunto";
	Nodo n = set.get(s);
	return n.getRepresentante().getNombre();
    }

    /**
     * Metodo para unir dos elementos a una clase
     * Se realiza union por tamaño
     * @param String s1 - primer elemento a unir
     * @param String s2 - Segundo elemento a unir
     */
    public void join(String s1, String s2){
	if(s1 == s2 || !set.containsKey(s1) || !set.containsKey(s2)){
	    System.out.println("Valores no en el conjunto");
	    return;
	}
	Nodo n1 = set.get(s1);
	Nodo n2 = set.get(s2);
	if(n1.getRepresentante() == n2.getRepresentante())return;
	if(n1.tamaño < n2.tamaño){
	    n1.padre = n2;
	    n2.tamaño += n1.tamaño;
	    return;
	}
	n2.padre = n1;
	n1.tamaño += n2.tamaño;
	
    }

    
}
