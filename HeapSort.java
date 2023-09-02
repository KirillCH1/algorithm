public class HeapSort {

    public static void sort(int[] array) {
        int n = array.length;

        // Построение максимальной пирамиды (max heap)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Извлечение элементов из пирамиды по одному и добавление их в конец сортируемого массива
        for (int i = n - 1; i > 0; i--) {
            // Переместить текущий корень в конец массива
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем heapify для уменьшенной пирамиды
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        // Если левый потомок больше корня
        if (leftChildIndex < heapSize && array[leftChildIndex] > array[largest]) {
            largest = leftChildIndex;
        }

        // Если правый потомок больше, чем наибольший элемент на данный момент
        if (rightChildIndex < heapSize && array[rightChildIndex] > array[largest]) {
            largest = rightChildIndex;
        }

        // Если наибольший элемент не корень
        if (largest != rootIndex) {
            int swap = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = swap;

            // Рекурсивно heapify поддерево
            heapify(array, heapSize, largest);
        }
    }
}
