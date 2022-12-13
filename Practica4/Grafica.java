import java.util.LinkedList;
import java.lang.Comparable;
import java.util.ListIterator;
import java.util.Collections;
/**
 * Clase para modelar las graficas
 */
public class Grafica{

    /**
     * Clase interna para modelar las aristas de las graficas
     */
    protected class Arista implements Comparable<Arista>{
	// Primer elemento de la arista
	String elemento1;

	// Segundo elemento de la arista
	String elemento2;

	// Peso de la arista
	int peso;

	/**
	 * Constructor de la clase arista
	 * @param String - elemento1 Primer elemento de la arista
	 * @param int - peso Peso de la arista
	 * @param String - elemento2 Segundo elemento de la arista
	 */
	public Arista(String elemento1, int peso, String elemento2){
	    this.elemento1 = elemento1;
	    this.elemento2 = elemento2;
	    this.peso = peso;
	}

	/**
	 * Devuelve el primer elemento de la arista
	 * @return String - primer elemento de la arista
	 */
	protected String getElemento1(){
	    return this.elemento1;
	}

	/**
	 * Devuelve el segundo elemento de la arista
	 * @return String - segundo elemento de la arista
	 */
	protected String getElemento2(){
	    return this.elemento2;
	}

	/**
	 * Devuelve el  peso de la arista
	 * @return int - peso de la arista
	 */
	protected int getPeso(){
	    return this.peso;
	}

	/**
	 * Metodo para comparar aristas en  base a su peso
	 * @return int -
	                 Numero negativo en caso de que esta arista tenga un peso menor a la arista dada
			 Numero positivo en caso de que esta arista sea mayor a la arista dada
			 0 si ambas aristas son iguales	 
	 */
	@Override
	public int compareTo(Arista a){
	    return this.peso - a.getPeso();
	}

	/**
	 * Metodo equals
	 * @return True en caso de que ambos elementos y el peso sean iguales. False en otro caso
	 */
	protected boolean equals(Arista a){
	    boolean v = true;
	    if(this.elemento1 != a.elemento1) v = false;
	    if(this.peso != a.peso) v = false;
	    if(this.elemento2 != a.elemento2) v = false;
	    return v;
	}

	/**
	 * metodo to string
	 * @return String -  Representacion en cadena de la arista
	 */
	@Override
	public String toString(){
	    return "( " + this.elemento1 + ", " + this.peso + ", " + this.elemento2 + ")";
	}
		
    }


    //Lista de elementos en la grafica
    LinkedList<String> elementos = new LinkedList<>();

    //Lista de aristas en la grafica
    LinkedList<Arista> aristas = new LinkedList<>();

    /**
     * Metodo para añadir un elemento a la grafica
     */
    protected void addElemento(String s){
	if(this.elementos.contains(s))return;
	elementos.add(s);
    }

    /**
     * Metodo para añadir una arista a la  grafica
     */
    protected void addArista(String elemento1, int peso, String elemento2){
	if(!this.elementos.contains(elemento1) || !this.elementos.contains(elemento2)){
	    System.out.print("Faltan elementos");
	    return;
	}
	Arista nueva = new Arista(elemento1, peso, elemento2);
	this.aristas.add(nueva);
    }

    /**
     * Metodo para eliminar un elemento de la grafica
     */
    protected void deleteElemento(String s){
	this.elementos.remove(s);
	ListIterator<Arista> ite = this.aristas.listIterator(0);
	while(ite.hasNext()){
	    Arista aristaIte = ite.next();
	    if(aristaIte.elemento1.equals(s) || aristaIte.elemento2.equals(s)) ite.remove();
	}
    }

    /**
     * Metod para eliminar una arista de la grafica
     */
    protected void deleteArista(String elemento1, int peso, String elemento2){
	Arista supp = new Arista(elemento1, peso, elemento2);
	ListIterator<Arista> ite = this.aristas.listIterator(0);
	while(ite.hasNext()){
	    Arista aristaIte = ite.next();
	    if(aristaIte.equals(supp)) ite.remove();
	}
    }


    /**
     * Metodo toString()
     * @return String - Representaciono en cadena de la grafica
     */
    @Override
    public String toString(){
	String aristasS = "Aristas: \n";
	String elementosS = "Elementos: ";
	for(String e: this.elementos){
	    elementosS = elementosS + e + ", ";
	}
	for(Arista a: this.aristas){
	    aristasS += "\t" + a.toString()+ "\n";
	}

	return elementosS + "\n\n" + aristasS;
    }



    /**
     * Metodo que ordena todas las aristas de la grafica de menor a mayor
     * Metodo auxiliar para implementar Kruskal
     */
    private void sortAristas(){
	Collections.sort(this.aristas);
    }

    /**
     * Metodo que crea un disjoint set con todos los elementos de la grafica
     * Metodo auxiliar para kruskal
     */
    private DisjointSets conjuntoElementos(){
	DisjointSets conjunto = new DisjointSets();
	for(String s : this.elementos)conjunto.addSet(s);
	return conjunto;
    }

    /**
     * Crea una grafica con los mismos elementos que la grafica  dada
     * Metodo auxiliar para Kruskal
     * @return Grafica - nueva grafica con los mismos elementos, pero sin aristas
     */
    private Grafica cloneGraficaElementos(){
	Grafica g = new Grafica();
	for(String s : this.elementos){
	    g.addElemento(s);
	}
	return g;
    }
    
    /**
     * Devuelve el arbol de peso minimo de la grafica obtenida por medio de Kruskal
     * Se implementa kruskal como un algoritmo no destructivo, de modo que se crea una grafica nueva y no se afecta la grafica de la cual
     * se obtiene el arbol
     * @return Grafica -  Arbol generador de peso minimo
     */
    public Grafica kruskal(){
	this.sortAristas();
	DisjointSets conjunto = this.conjuntoElementos();
	ListIterator<Arista> ite = this.aristas.listIterator(0);
	Grafica mst = this.cloneGraficaElementos();
	while(ite.hasNext()){
	    Arista aristaProcesando = ite.next();
	    String ele1 = aristaProcesando.elemento1;
	    String ele2 = aristaProcesando.elemento2;
	    if(!conjunto.sameSet(ele1, ele2)){
		conjunto.join(ele1, ele2);
		mst.addArista(ele1, aristaProcesando.peso, ele2);
	    }
	}

	return mst;
        
	
    }

    /**
     * Metodo que imprime el peso total del arbol y las aristas que lo componen
     * Metodo para cumplir con los requisitos de la practica
     * @return String - String conteniendo el peso total del arbol y 
     */
    public String getPesoYAristas(){
	int pesoTotal = 0;
	String s = "";
	for(Arista a: this.aristas){
	    s += "\t" + a.toString()+ "\n";
	    pesoTotal += a.peso;
	}
	return "Peso total del arbol: " + pesoTotal + "\n" + "Aristas que lo componen\n " + s;
    }





    
}
