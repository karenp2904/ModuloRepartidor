import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class grafo {
    private static int Bucaramanga1= 10000;
    private static int Giron1=8000;
    private static int Pobladogiron1= 7000;
    private static int Piedecuesta1= 9000;
    private static int Floridablanca1= 9000;
    private static int Cañaveral1= 8000;
    private static int SubTotal=0;
    private static int Total=0;

    public grafo(int bucaramanga1, int giron1, int pobladogiron1, int piedecuesta1, int floridablanca1,
                     int cañaveral1) {
        super();
        Bucaramanga1 = bucaramanga1;
        Giron1 = giron1;
        Pobladogiron1 = pobladogiron1;
        Piedecuesta1 = piedecuesta1;
        Floridablanca1 = floridablanca1;
        Cañaveral1 = cañaveral1;
    }

    // Crear un diccionario vacío para almacenar los valores de domicilio
    static HashMap<String, Integer> domicilios = new HashMap<>();

    // Crear un grafo de búsqueda en el que los nodos sean las direcciones y las aristas sean las relaciones entre ellas
    static HashMap<String, ArrayList<String>> grafo = new HashMap<>();

    public static void main(String[] args) {

        try {
            // Cargar el XML
            File inputFile = new File("factura.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Recorrer los elementos de cliente y agregar los valores de domicilio al diccionario
            NodeList nodeList = doc.getElementsByTagName("Factura");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String direccion = element.getAttribute("Direccion");
                    int valor_domicilio = obtener_valor_domicilio(direccion); // Función para obtener el valor del domicilio
                    domicilios.put(direccion, valor_domicilio);
                }
            }

            // Crear el grafo
            for (String direccion : domicilios.keySet()) {
                ArrayList<String> vecinos = new ArrayList<>();
                for (String otra_direccion : domicilios.keySet()) {
                    if (!direccion.equals(otra_direccion)) {
                        if (misma_calle(direccion, otra_direccion)) { // Función para verificar si las direcciones están en la misma calle
                            vecinos.add(otra_direccion);
                        } else if (mismo_barrio(direccion, otra_direccion)) { // Función para verificar si las direcciones están en el mismo barrio
                            vecinos.add(otra_direccion);
                        } else if (mismo_municipio(direccion, otra_direccion)) { // Función para verificar si las direcciones están en el mismo municipio
                            vecinos.add(otra_direccion);
                        }
                    }
                }
                grafo.put(direccion, vecinos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean mismo_barrio(String direccion, String otra_direccion) {
        // Separar la dirección por comas para obtener el barrio
        String[] direccion_parts = direccion.split(",");
        String[] otra_direccion_parts = otra_direccion.split(",");
        String barrio = direccion_parts[direccion_parts.length - 2].trim();
        String otra_barrio = otra_direccion_parts[otra_direccion_parts.length - 2].trim();

        // Comparar los barrios
        if (barrio.equalsIgnoreCase(otra_barrio)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean mismo_municipio(String direccion, String otra_direccion) {
        // Obtener el nombre del municipio de cada dirección
        String municipio1 = obtenerMunicipio(direccion);
        String municipio2 = obtenerMunicipio(otra_direccion);

        // Comparar si los nombres de los municipios son iguales
        return municipio1.equals(municipio2);
    }


    private static String obtenerMunicipio(String direccion) {
        // Se busca la posición de la coma en la dirección
        int posComa = direccion.indexOf(",");

        // Si no se encuentra la coma, se devuelve null
        if (posComa == -1) {
            return null;
        }

        // Se obtiene la subcadena desde la coma hasta el final de la dirección
        String subcadena = direccion.substring(posComa + 1);

        // Se divide la subcadena en palabras
        String[] palabras = subcadena.split(" ");

        // Se busca la palabra "municipio" o "ciudad" en las palabras
        for (String palabra : palabras) {
            if (palabra.equalsIgnoreCase("municipio") || palabra.equalsIgnoreCase("ciudad")) {
                // Si se encuentra la palabra, se devuelve la siguiente palabra
                int indice = Arrays.asList(palabras).indexOf(palabra);
                if (indice != -1 && indice < palabras.length - 1) {
                    return palabras[indice + 1];
                }
            }
        }

        // Si no se encuentra "municipio" o "ciudad", se devuelve null
        return null;
    }


    // Función para obtener el valor del domicilio
    static int obtener_valor_domicilio(String direccion) {
        String Bucaramanga = "bucaramanga";
        String Giron = "giron";
        String PobladoGiron = "poblado giron";
        String Piedecuesta = "piedecuesta";
        String Floridablanca = "florida blanca";
        String Cañaveral = "cañaveral";
        if(direccion == Bucaramanga ) {
            SubTotal=Bucaramanga1+SubTotal;
            Total=SubTotal+SubTotal;
        }
        else if(direccion == Giron) {
            SubTotal=Giron1+SubTotal;
            Total=SubTotal+Total;
        }else if(direccion == PobladoGiron) {
            SubTotal=Pobladogiron1+SubTotal;
            Total=SubTotal+Total;
        }else if(direccion == Floridablanca) {
            SubTotal=Floridablanca1+SubTotal;
            Total=SubTotal+Total;
        }else if(direccion == Piedecuesta) {
            SubTotal=Piedecuesta1+SubTotal;
            Total=SubTotal+Total;
        }else if(direccion == Cañaveral) {
            SubTotal=Cañaveral1+SubTotal;
            Total=SubTotal+Total;
        }
        return Total;
    }

    static boolean misma_calle(String direccion1, String direccion2) {
        // Obtener el nombre de la calle de cada dirección
        String calle1 = obtenerCalle(direccion1);
        String calle2 = obtenerCalle(direccion2);

        // Comparar si los nombres de las calles son iguales
        return calle1.equals(calle2);
    }

    private static String obtenerCalle(String direccion) {
        // Buscar la posición de la primera coma en la dirección
        int posComa = direccion.indexOf(",");

        // Si no se encuentra la coma, se devuelve null
        if (posComa == -1) {
            return null;
        }

        // Obtener la subcadena desde el inicio de la dirección hasta la coma
        String subcadena = direccion.substring(0, posComa);

        // Dividir la subcadena en palabras
        String[] palabras = subcadena.split(" ");

        // Recorrer las palabras buscando la palabra "calle"
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].equalsIgnoreCase("calle")) {
                // Si se encuentra la palabra "calle", se devuelve la siguiente palabra
                if (i < palabras.length - 1) {
                    return palabras[i + 1];
                } else {
                    // Si la palabra "calle" es la última en la subcadena, se devuelve null
                    return null;
                }
            }
        }

        // Si no se encuentra la palabra "calle", se devuelve null
        return null;
    }


}
