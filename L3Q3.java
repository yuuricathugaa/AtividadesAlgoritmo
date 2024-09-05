public class L3Q3 {

    static int swapCount = 0;
    static int heapifyCount = 0;

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapCount++;
    }

    public static void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, n, largest);
        }

        heapifyCount++;
        printArray(arr);
    }

    public static void buildMaxHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, n, i);
        }
    }

    public static void heapsort(int[] arr, int n) {
        buildMaxHeap(arr, n);
        System.out.println("Vetor após construir Max-Heap:");
        printArray(arr);

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            maxHeapify(arr, i, 0);
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};
        int size = arr.length;

        System.out.println("Vetor original:");
        printArray(arr);

        heapsort(arr, size);

        System.out.println("Vetor ordenado:");
        printArray(arr);
        System.out.println("Número total de trocas: " + swapCount);
        System.out.println("Número total de execuções do maxHeapify: " + heapifyCount);
    }
}