import Estructuras.APriorityQueue.PriorityQueue;
import Estructuras.DinamicQueue.Queue;
import Estructuras.ListasEnlaceDoble.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<String, LinkedList<Vertice>> vertices;

    public Grafo() {
        this.vertices = new HashMap<>();
        this.vertices.put("bucaramanga", new LinkedList<>());
        this.vertices.put("giron", new LinkedList<>());
        this.vertices.put("provenza", new LinkedList<>());
        this.vertices.put("cañaveral", new LinkedList<>());
        this.vertices.put("pidecuesta", new LinkedList<>());
        this.vertices.put("lebrija", new LinkedList<>());
    }

    public void agregarArista(String v1, String v2, int peso) {
        this.vertices.get(v1).add(new Vertice(v2, peso));
        this.vertices.get(v2).add(new Vertice(v1, peso));
    }

    public int calcularValorArista(String v1, String v2) {
        // Aquí iría el código para calcular el peso de la arista entre v1 y v2
        return 0;
    }

    public Map<String, Integer> dijkstra(String origen) {
        Map<String, Integer> distancias = new HashMap<>();
        PriorityQueue<Vertice> cola = new PriorityQueue<>(20);
        Queue coladinamica=new Queue();

        for (String vertice : this.vertices.keySet()) {
            if (vertice.equals(origen)) {
                distancias.put(origen, 0);
                coladinamica.insert(new Vertice(origen,0));
            } else {
                distancias.put(vertice, Integer.MAX_VALUE);
                coladinamica.insert(new Vertice(vertice, Integer.MAX_VALUE);
            }
        }

        while (!cola.isEmpty()) {
            Vertice actual = (Vertice) cola.extract();

            for (Vertice vecino : vertices.get(actual) {
                int distanciaNueva = distancias.get(actual.nombre) + vecino.peso;

                if (distanciaNueva < distancias.get(vecino.nombre)) {
                    coladinamica.extract(vecino);
                    distancias.put(vecino.nombre, distanciaNueva);
                    vecino.peso = distanciaNueva;
                    coladinamica.insert(vecino);
                }
            }
        }

        return distancias;
    }

    private class Vertice implements Comparable<Vertice> {
        public String nombre;
        public int peso;

        public Vertice(String nombre, int peso) {
            this.nombre = nombre;
            this.peso = peso;
        }

        public int compareTo(Vertice otro) {
            return Integer.compare(this.peso, otro.peso);
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }
    }
}
