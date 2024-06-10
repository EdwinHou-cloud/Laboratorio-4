import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Clase Nodo para representar cada nodo del árbol de Huffman
class Nodo implements Comparable<Nodo> {
    char caracter;
    int frecuencia;
    Nodo izquierda, derecha;

    // Constructor de la clase Nodo
    public Nodo(char caracter, int frecuencia, Nodo izquierda, Nodo derecha) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    // Método para comparar nodos en función de su frecuencia
    public int compareTo(Nodo otro) {
        return this.frecuencia - otro.frecuencia;
    }
}

public class CodificacionHuffman {
    // Método para construir el árbol de Huffman y devolver la raíz
    public static Nodo construirArbol(Map<Character, Integer> frecuencias) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();

        // Añadir todos los caracteres y sus frecuencias a la cola de prioridad
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            colaPrioridad.add(new Nodo(entrada.getKey(), entrada.getValue(), null, null));
        }

        // Construir el árbol de Huffman
        while (colaPrioridad.size() > 1) {
            Nodo izquierda = colaPrioridad.poll();
            Nodo derecha = colaPrioridad.poll();
            Nodo padre = new Nodo('\0', izquierda.frecuencia + derecha.frecuencia, izquierda, derecha);
            colaPrioridad.add(padre);
        }

        return colaPrioridad.poll();
    }

    // Método recursivo para generar los códigos de Huffman
    public static void generarCodigos(Nodo raiz, String codigo, Map<Character, String> codigosHuffman) {
        if (raiz == null) return;

        // Si es una hoja, agregar el código al mapa
        if (raiz.izquierda == null && raiz.derecha == null) {
            codigosHuffman.put(raiz.caracter, codigo);
        }

        // Recursivamente generar códigos para los nodos izquierdo y derecho
        generarCodigos(raiz.izquierda, codigo + '0', codigosHuffman);
        generarCodigos(raiz.derecha, codigo + '1', codigosHuffman);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese 10 caracteres y sus respectivos pesos:");
        Map<Character, Integer> frecuencias = new HashMap<>();

        // Leer los caracteres y sus frecuencias desde la entrada del usuario
        for (int i = 0; i < 10; i++) {
            System.out.print("Caracter: ");
            char caracter = scanner.next().charAt(0);
            System.out.print("Peso: ");
            int peso = scanner.nextInt();
            frecuencias.put(caracter, peso);
        }

        // Construir el árbol de Huffman y generar los códigos
        Nodo raiz = construirArbol(frecuencias);
        Map<Character, String> codigosHuffman = new HashMap<>();
        generarCodigos(raiz, "", codigosHuffman);

        // Mostrar los códigos de Huffman generados
        System.out.println("Códigos de Huffman:");
        for (Map.Entry<Character, String> entrada : codigosHuffman.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }

        // Llamar a la interfaz gráfica para mostrar el árbol y los códigos
        GraficoHuffman interfazGrafica = new GraficoHuffman(raiz, codigosHuffman);
        interfazGrafica.mostrar();
        scanner.close();
    }
}