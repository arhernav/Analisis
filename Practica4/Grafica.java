import java.util.LinkedList;
import java.lang.Comparable;
import java.util.ListIterator;
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

	public Arista(String elemento1, int peso, String elemento2){
	    this.elemento1 = elemento1;
	    this.elemento2 = elemento2;
	    this.peso = peso;
	}

	protected String getElemento1(){
	    return this.elemento1;
	}

	protected String getElemento2(){
	    return this.elemento2;
	}

	protected int getPeso(){
	    return this.peso;
	}

	@Override
	public int compareTo(Arista a){
	    return this.peso - a.getPeso();
	}

	protected boolean equals(Arista a){
	    boolean v = true;
	    if(this.elemento1 != a.elemento1) v = false;
	    if(this.peso != a.peso) v = false;
	    if(this.elemento2 != a.elemento2) v = false;
	    return v;
	}

	@Override
	public String toString(){
	    return "( " + this.elemento1 + ", " + this.peso + ", " + this.elemento2 + ")";
	}
		
    }


    LinkedList<String> elementos = new LinkedList<>();

    LinkedList<Arista> aristas = new LinkedList<>();

    protected void addElemento(String s){
	if(this.elementos.contains(s))return;
	elementos.add(s);
    }

    protected void addArista(String elemento1, int peso, String elemento2){
	if(!this.elementos.contains(elemento1) || !this.elementos.contains(elemento2)){
	    System.out.print("Faltan elementos");
	    return;
	}
	Arista nueva = new Arista(elemento1, peso, elemento2);
	this.aristas.add(nueva);
    }

    protected void deleteElemento(String s){
	this.elementos.remove(s);
	ListIterator<Arista> ite = this.aristas.listIterator(0);
	while(ite.hasNext()){
	    Arista aristaIte = ite.next();
	    if(aristaIte.elemento1.equals(s) || aristaIte.elemento2.equals(s)) ite.remove();
	}
    }

    protected void deleteArista(String elemento1, int peso, String elemento2){
	Arista supp = new Arista(elemento1, peso, elemento2);
	ListIterator<Arista> ite = this.aristas.listIterator(0);
	while(ite.hasNext()){
	    Arista aristaIte = ite.next();
	    if(aristaIte.equals(supp)) ite.remove();
	}
    }


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
    





    
}
