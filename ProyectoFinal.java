package proyectofinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProyectoFinal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        //Opcion descartada de titulo: 
        //"El Despliegue Concurrente de los 6 Estilos de Respiración contra el Cronómetro" 
        //(Referencia a la serie "Demon Slayer")
        System.out.println(" Comparacion de metodos de ordenamiento ");
        System.out.println("Instruccion: Se utilizaran ArrayLists para la generacion dinamica");
        System.out.println("y Arreglos primitivos para la ejecucion concurrente optima.\n");    
        System.out.println("Nombre: Santiago Valle =) ");
        System.out.println("Nombre: Erian Satoy :) \n");
        //Escenarios
        System.out.println("Seleccione el escenario de prueba:");
        System.out.println("1. 100 elementos aleatorios");
        System.out.println("2. 50,000 elementos aleatorios");
        System.out.println("3. 100,000 elementos aleatorios");
        System.out.println("4. 100,000 elementos (Restringido 1-5)"); 
        System.out.print("Opcion: ");
        int opcion = scanner.nextInt();

        int tamano;
        int rangoMax;

        switch (opcion) {
            case 2 -> { tamano = 50000; rangoMax = 1000000; }
            case 3 -> { tamano = 100000; rangoMax = 1000000; }
            case 4 -> { tamano = 100000; rangoMax = 5; }
            default -> { tamano = 100; rangoMax = 1000; }
        }

        System.out.print("Ingrese el tiempo total de ejecucion (segundos): ");
        int segundos = scanner.nextInt();
       
        System.out.println("\nGenerando " + tamano + " elementos con rango [1-" + rangoMax + "] con ArrayList =)-/-( ");
        ArrayList<Integer> listaDinamica = new ArrayList<>();
        
        for (int i = 0; i < tamano; i++) {
            int numero = random.nextInt(rangoMax) + 1;
            listaDinamica.add(numero);
        }

        int[] arregloBase = listaDinamica.stream().mapToInt(i -> i).toArray();

        //Preparación de Hilos, asignamos una respiracion (Algoritmo) a cada Cazador (Referenmcia a la serie "Demon slayer")
        List<HiloOrdenador> corredores = new ArrayList<>();
        corredores.add(new HiloOrdenador(new BubbleSort(), arregloBase));// Respiracion de la Burbuja (Lento)
        corredores.add(new HiloOrdenador(new InsertionSort(), arregloBase));  // Respiracion de la Insercion
        corredores.add(new HiloOrdenador(new SelectionSort(), arregloBase)); // Respiracion de la Seleccion
        corredores.add(new HiloOrdenador(new ShellSort(), arregloBase)); // Respiracion del Caparazon
        corredores.add(new HiloOrdenador(new MergeSort(), arregloBase));// Respiracion de la Mezcla (Pilar)
        corredores.add(new HiloOrdenador(new QuickSort(), arregloBase)); // Respiracion del Rayo (Velocidad Divina)


        System.out.println("Iniciando hilos concurrentes... (" + segundos + "s)");
        for (HiloOrdenador hilo : corredores) hilo.start();

        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Tiempo agotado. Deteniendo procesos...");
        for (HiloOrdenador hilo : corredores) hilo.detener();
        for (HiloOrdenador hilo : corredores) {
            try { hilo.join(); } catch (InterruptedException e) {}
        }

        List<Resultado> ranking = new ArrayList<>();
        for (HiloOrdenador hilo : corredores) {
            ranking.add(new Resultado(
                hilo.getNombreAlgoritmo(),
                hilo.getComplejidadTeorica(),
                hilo.getArreglosOrdenados(), 
                hilo.getPromedioMilisegundos() 
            ));
        }
        Collections.sort(ranking);

        System.out.println("\nResultados Finales =) ");
        System.out.printf("%-15s | %-10s | %-12s | %-15s%n", "Algoritmo", "Teorica", "Completados", "Tiempo Real (avg)");
        System.out.println("------------------------------------------------------------------");
        
        for (Resultado r : ranking) {
            String status = (r.cantidad > 0) ? String.format("%.4f ms", r.tiempoPromedio) : "No finalizo =( ";
            System.out.printf("%-15s | %-10s | %-12d | %-15s%n", 
                r.nombre, r.complejidad, r.cantidad, status);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Analisis: Compare la columna 'Teorica' con 'Tiempo Real'.");
        
        System.exit(0);
    }
    
}
