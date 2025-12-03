package proyectofinal;

public class ShellSort implements Ordenador{
    @Override
    public String getNombre() { return "Shell Sort"; }
    @Override
    public void ordenar(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }
    @Override
    public String getComplejidad() {
        return "O(n log n)*"; 
    }
}
