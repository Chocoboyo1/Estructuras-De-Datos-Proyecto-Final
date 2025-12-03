package proyectofinal;

public class HiloOrdenador extends Thread{
    private Ordenador algoritmo;
    private int[] arregloOriginal;
    private volatile boolean running = true;
    
    // Metricas =)
    private long arreglosOrdenados = 0;
    private long tiempoTotalNano = 0;

    public HiloOrdenador(Ordenador algoritmo, int[] arregloBase) {
        this.algoritmo = algoritmo;
        this.arregloOriginal = arregloBase; 
    }

    public void detener() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            
            int[] copiaTrabajo = arregloOriginal.clone();

            long inicio = System.nanoTime();
            algoritmo.ordenar(copiaTrabajo);
            long fin = System.nanoTime();

            if (running) {
                tiempoTotalNano += (fin - inicio);
                arreglosOrdenados++;
            }
        }
    }

    public long getArreglosOrdenados() {
        return arreglosOrdenados;
    }

    public double getPromedioMilisegundos() {
        if (arreglosOrdenados == 0) return 0.0;
        double promedioNano = (double) tiempoTotalNano / arreglosOrdenados;
        return promedioNano / 1_000_000.0; 
    }
    
    public String getNombreAlgoritmo() {
        return algoritmo.getNombre();
    }
    public String getComplejidadTeorica() {
    return algoritmo.getComplejidad();
    }    
}
