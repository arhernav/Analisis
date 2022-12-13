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
	graf.addElemento("g");
	graf.addElemento("h");

	graf.addArista("a", 1, "b");
	graf.addArista("a", 11, "b");
	graf.addArista("b", 2, "c");
	graf.addArista("b", 12, "c");
	graf.addArista("c", 3, "d");
	graf.addArista("c", 13, "d");
	graf.addArista("d", 4, "e");
	graf.addArista("d", 14, "e");
	graf.addArista("e", 5, "f");
	graf.addArista("e", 15, "f");
	graf.addArista("f", 6, "g");
	graf.addArista("f", 16, "g");
	graf.addArista("g", 7, "h");
	graf.addArista("g", 17, "h");
	graf.addArista("h", 8, "a");
	graf.addArista("h", 18, "a");
	
       

	System.out.println(graf);

	Grafica mst = graf.kruskal();

	System.out.println();
	System.out.println(mst);
	
	/*
        	con.print();
	System.out.println();
	con.join("a", "b");
	con.print();
	System.out.println();
	con.join("c", "d");
		con.print();
	System.out.println();
	con.join("d", "e");
		con.print();
	System.out.println();
	con.join("b", "d");
		con.print();
		System.out.println();*/
	




	
    }
    
}
