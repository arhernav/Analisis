import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.*;
import java.io.InputStreamReader;
import java.lang.Integer;

/**
 Clase main 
 */
public class Main{

    
    /**
     * Metodo para constuir la grafica del documento dado
     * Suponemos que el documento cumple con las especificaciónes dadas
     * @return Grafica - Grafica construida con base al documento
     */
    public static Grafica constuirGrafica(Path documento){
	Grafica grafica = new Grafica();
	String elementosLinea[];
	try{
	    BufferedReader reader = Files.newBufferedReader(documento);
	    String linea;
	    elementosLinea = reader.readLine().split(",");
	    for(String s : elementosLinea)grafica.addElemento(s);
	    while((linea = reader.readLine()) != null){
		elementosLinea = linea.split(",");
		grafica.addArista(elementosLinea[0],Integer.parseInt(elementosLinea[2]), elementosLinea[1]);
	    }    
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
	return grafica;
    }


    
    public static void main(String args[]){
	if(args.length <= 0){
	    System.out.println("Dar documento para lectura");
	    return;
	}
	//Lectura del documento y construcción de la grafica
	Path documento = Paths.get(args[0]).toAbsolutePath();
	Grafica graficaDocumento = constuirGrafica(documento);

	//Aplicamos Krusal a la grafica,  imprimimos ambas graficas y damos los  datos pedidos

	Grafica mst = graficaDocumento.kruskal();

	System.out.println("Grafica dada:");
	System.out.println(graficaDocumento);

	System.out.println("\n");
	System.out.println("Grafica despues de aplicar Kruskal:");
	System.out.println(mst);

	System.out.println("\n");
	System.out.println("Datos solicitados de la practica:");
	System.out.println(mst.getPesoYAristas());
	
	
	
    }
    
}
