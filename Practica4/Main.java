/**
 Clase main 
 */

public class Main{

    public static void main(String args[]){

	Grafica graf = new Grafica();

	graf.addElemento("a");
	graf.addElemento("b");
	graf.addElemento("c");
	graf.addElemento("d");
	graf.addElemento("e");
	graf.addElemento("f");
	graf.addElemento("f");
	graf.addElemento("a");

	graf.addArista("a", 1, "b");
	graf.addArista("z", 3, "x");
	graf.addArista("b", 2, "c");
	graf.addArista("c", 3, "d");
	graf.addArista("a", 3, "b");
	

	System.out.println(graf);


	graf.deleteArista("a", 3, "b");
	System.out.println(graf);

	
    }
    
}
