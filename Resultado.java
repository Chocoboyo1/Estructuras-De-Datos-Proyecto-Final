package proyectofinal;

public class Resultado implements Comparable<Resultado> {
    String nombre;
    String complejidad;
    long cantidad;
    double tiempoPromedio;

    public Resultado(String n, String compl, long c, double t) { 
        this.nombre = n;
        this.complejidad = compl;
        this.cantidad = c;
        this.tiempoPromedio = t;
    }

    @Override
    public int compareTo(Resultado o) {
        return Long.compare(o.cantidad, this.cantidad);
    }
}
