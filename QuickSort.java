package proyectofinal;

public class QuickSort implements Ordenador{
    @Override
    public String getNombre() {
        return "Quick Sort";
    }

    @Override
    public String getComplejidad() {
        return "O(n log n)";
    }

    @Override
    public void ordenar(int[] arr) {
        quickSort3Way(arr, 0, arr.length - 1);
    }

    private void quickSort3Way(int[] arr, int low, int high) {
        if (high <= low) return;

        int lt = low;        
        int gt = high;       
        int i = low + 1;     
        int pivot = arr[low];

        while (i <= gt) {
            if (arr[i] < pivot) {
                intercambiar(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                intercambiar(arr, i, gt--);
            } else {
                i++;
            }
        }
        
        quickSort3Way(arr, low, lt - 1);
        quickSort3Way(arr, gt + 1, high);
    }

    private void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
