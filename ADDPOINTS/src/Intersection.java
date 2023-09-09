import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersection {
    public static List<Integer> findIntersectionWithMinMultiplicity(int[] x, int[] y) {
        // Создаем словари для подсчета кратностей элементов в массивах x и y
        Map<Integer, Integer> countX = new HashMap<>();
        Map<Integer, Integer> countY = new HashMap<>();

        // Заполняем словарь countX
        for (int element : x) {
            countX.put(element, countX.getOrDefault(element, 0) + 1);
        }

        // Заполняем словарь countY
        for (int element : y) {
            countY.put(element, countY.getOrDefault(element, 0) + 1);
        }

        // Создаем список для хранения пересечения
        List<Integer> intersection = new ArrayList<>();

        // Перебираем элементы массива x и проверяем их наличие в массиве y и кратность
        for (int element : x) {
            if (countY.containsKey(element) && countX.get(element) > 0 && countY.get(element) > 0) {
                intersection.add(element);
                countX.put(element, countX.get(element) - 1);
                countY.put(element, countY.get(element) - 1);
            }
        }

        return intersection;
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 2, 3, 4};
        int[] y = {2, 2, 3, 3, 4};

        List<Integer> intersection = findIntersectionWithMinMultiplicity(x, y);

        System.out.println("Пересечение с минимальной кратностью: " + intersection);
    }
}