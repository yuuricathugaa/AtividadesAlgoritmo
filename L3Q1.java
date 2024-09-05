public class L3Q1 {

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copiando os dados para arrays temporários
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Misturando os arrays temporários de volta ao array original
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copiando os elementos restantes de leftArray
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copiando os elementos restantes de rightArray
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }

        // Imprimindo o array após o merge
        printArray(arr);
    }

    public static void mergesort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Ordena a primeira e segunda metade
            mergesort(arr, left, mid);
            mergesort(arr, mid + 1, right);

            // Combina as metades ordenadas
            merge(arr, left, mid, right);
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int size = arr.length;

        System.out.println("Vetor original:");
        printArray(arr);

        mergesort(arr, 0, size - 1);

        System.out.println("Vetor ordenado:");
        printArray(arr);
    }
}