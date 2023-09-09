import java.util.*;

public class SortingComparison {
    private static int treeSortCounter = 0;
    public static void main(String[] args) {
        int[] randomArray = generateRandomArray(1000);
        System.out.println("Случайный массив " + Arrays.toString(randomArray));

        // Копируем массив для обеих сортировок
        int[] treeSortArray = Arrays.copyOf(randomArray, randomArray.length);
        int[] bucketSortArray = Arrays.copyOf(randomArray, randomArray.length);

        // Сортировка Tree Sort и расчет количества итераций
        int treeSortIterations = treeSort(treeSortArray);

        // Сортировка Bucket Sort и расчет количества итераций
        int bucketSortIterations = bucketSort(bucketSortArray);

        System.out.println("Количество итераций Tree Sort: " + treeSortIterations);
        System.out.println("Количество итераций Bucket Sort: " + bucketSortIterations);

        // Определяем, какая сортировка имеет меньшее количество итераций
        if (treeSortIterations < bucketSortIterations) {
            System.out.println("Меньшее количество итераций у Tree Sort.");
        } else if (bucketSortIterations < treeSortIterations) {
            System.out.println("Меньшее количество итераций у Bucket Sort.");
        } else {
            System.out.println("Обе сортировки имеют одинаковое количество итераций.");
        }
    }

    // Генерация случайного массива размерности n
    public static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000); // Генерируем случайное число от 0 до 999
        }
        return arr;
    }

    // Сортировка Tree Sort и расчет количества итераций
    public static int treeSort(int[] arr) {
        int iterations = 0;

        TreeNode root = null;

        for (int value : arr) {
            root = insert(root, value);
        }

        inOrderTraversal(root, arr);
        iterations = treeSortCounter;
        return iterations;
    }

    private static TreeNode insert(TreeNode root, int value) {
        treeSortCounter++;
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.data) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    private static void inOrderTraversal(TreeNode root, int[] arr) {
        if (root != null) {
            inOrderTraversal(root.left, arr);
            arr[index++] = root.data;
            inOrderTraversal(root.right, arr);
        }
    }

    private static int index = 0;

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    // Сортировка Bucket Sort и расчет количества итераций
    public static int bucketSort(int[] arr) {
        int iterations = 0;
        int maxVal = getMaxValue(arr);

        // Создаем массив корзин
        List<Integer>[] buckets = new ArrayList[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Распределение элементов по корзинам
        for (int num : arr) {
            buckets[num].add(num);
        }

        // Сортировка корзин и слияние
        int index = 0;
        for (List<Integer> bucket : buckets) {
            iterations++;
            if (bucket.size() > 0) {
                Collections.sort(bucket);
                for (int num : bucket) {
                    arr[index++] = num;
                }
            }
        }

        return iterations;
    }

    // Получение максимального значения в массиве
    private static int getMaxValue(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        for (int num : arr) {
            maxVal = Math.max(maxVal, num);
        }
        return maxVal;
    }

    // Вспомогательная функция для обмена элементов
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}