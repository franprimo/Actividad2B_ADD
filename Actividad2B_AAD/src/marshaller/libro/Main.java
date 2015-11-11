package marshaller.libro;

import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Main {

	public static void main(String[] args) {
		ArrayList<Libro> libros;
		
		//Cargamos los datos
		libros = new ArrayList<Libro>();
		libros.add(new Libro("Correr o Morir", "Kilian Jornet", 2011, "Now Books", 200));
		libros.add(new Libro("Un hombre en fuga", "Manuela Ronchi", 2012, "Cultura ciclista", 288));
		
		//Creamos un objeto marshaller
		Marshaller marshaller = new Marshaller(libros);
		
		marshaller.crearDocumento();
		marshaller.crearArbolDom();
		
		File f = new File("biblio.xml");
		
		try {
			marshaller.escribirDocAXml(f);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
