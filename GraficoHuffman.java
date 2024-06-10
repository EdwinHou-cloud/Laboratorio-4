import javax.swing.*;
import java.awt.*;
import java.util.Map;

// Clase para la interfaz gráfica que muestra el árbol de Huffman
class GraficoHuffman extends JFrame {
    private Nodo raiz;
    private Map<Character, String> codigoHuffman;

    // Constructor de la clase GraficoHuffman
    public GraficoHuffman(Nodo raiz, Map<Character, String> codigoHuffman) {
        this.raiz = raiz;
        this.codigoHuffman = codigoHuffman;
        setTitle("Árbol de Huffman");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Método para mostrar la ventana gráfica
    public void mostrar() {
        setVisible(true);
    }

    // Sobrescribir el método paint para dibujar el árbol
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (raiz != null) {
            dibujarArbol(g, raiz, getWidth() / 2, 100, getWidth() / 4);
        }
    }

    // Método recursivo para dibujar el árbol de Huffman
    private void dibujarArbol(Graphics g, Nodo nodo, int x, int y, int separacion) {
        // Dibujar la línea y el nodo izquierdo si existe
        if (nodo.izquierda != null) {
            g.drawLine(x, y, x - separacion, y + 50);
            dibujarArbol(g, nodo.izquierda, x - separacion, y + 50, separacion / 2);
        }

        // Dibujar la línea y el nodo derecho si existe
        if (nodo.derecha != null) {
            g.drawLine(x, y, x + separacion, y + 50);
            dibujarArbol(g, nodo.derecha, x + separacion, y + 50, separacion / 2);
        }

        // Dibujar el nodo actual
        g.setColor(Color.WHITE);
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.BLACK);
        g.drawOval(x - 20, y - 20, 40, 40);
        g.drawString(nodo.caracter == '\0' ? "*" : Character.toString(nodo.caracter), x - 5, y + 5);
        g.drawString(Integer.toString(nodo.frecuencia), x - 5, y + 25);
    }
}