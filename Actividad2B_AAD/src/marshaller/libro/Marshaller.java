package marshaller.libro;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {
	private Document dom = null;
	private ArrayList<Libro> libros;
	
	public Marshaller(ArrayList<Libro> l){
		libros = l;
	}
	
	public void crearDocumento(){
		//Creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		

		try {
			//Creamos un document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//Creamos una instancia de DOM
			dom = db.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearArbolDom(){
		//Creamos el elemento raiz libros
		Element docEle = dom.createElement("libros");
		dom.appendChild(docEle);
		
		//Y lo recorremos con iterator
		Iterator it = libros.iterator();
		while(it.hasNext()){
			Libro lib = (Libro) it.next();
			//Para cada objeto libro creamos el elemento libro y lo añadimos
			Element libroEle = setLibro(lib);
			docEle.appendChild(libroEle);
		}
	}
	
	private Element setLibro(Libro lib){
		//Creamos el elemento libro
		Element libroEle = dom.createElement("libro");
		
		//Creamos el elemento titulo y lo añadimos al documento
		Element tituloEle = dom.createElement("titulo");
		Text titulo = dom.createTextNode(lib.getTitulo());
		tituloEle.appendChild(titulo);
		libroEle.appendChild(tituloEle);
		
		//Creamos el elemento autor y lo añadimos al documento
		Element autorEle = dom.createElement("autor");
		Text autor = dom.createTextNode(lib.getAutor());
		autorEle.appendChild(autor);
		libroEle.appendChild(autorEle);
		
		//Creamos el elemento año de publicacion y lo añadimos al documento
		Element anoPublicacionEle = dom.createElement("anoPublicacion");
		Text anoPublicacion = dom.createTextNode(Integer.toString(lib.getAñoPublicacion()));
		anoPublicacionEle.appendChild(anoPublicacion);
		libroEle.appendChild(anoPublicacionEle);
		
		//Creamos el elemento editorial y lo añadimos al documento
		Element editorialEle = dom.createElement("editorial");
		Text editor = dom.createTextNode(lib.getEditor());
		editorialEle.appendChild(editor);
		libroEle.appendChild(editorialEle);
		
		//Creamos el elemento numero de paginas y lo añadimos al documento
		Element numPagEle = dom.createElement("numPag");
		Text numPag = dom.createTextNode(Integer.toString(lib.getNumPaginas()));
		numPagEle.appendChild(numPag);
		libroEle.appendChild(numPagEle);
		
		//Devolvemos el elemento libro
		return libroEle;
		
	}
	
	public void escribirDocAXml(File fichero) throws TransformerException {
		//Creamos una instancia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		//Para establecer los saltos de linea en el XML hay que poner esta linea
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		
		//Especificamos donde escribimos y la fuente de datos
		StreamResult sr = new StreamResult(fichero);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, sr);
	}
}
