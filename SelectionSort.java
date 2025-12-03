package proyectofinal;

public class SelectionSort implements Ordenador{
    @Override
    public String getNombre() { return "Selection Sort"; }
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx]) min_idx = j;
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    @Override
    public String getComplejidad() {
        return "O(n^2)";
    }
}
