import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(51) + 50; i++) {
            List<Integer> input = generateRandomList(random.nextInt(9901) + 100);

            System.out.println("\nInput list " + (i + 1) + ": " + input.size() + " Элементы");

            long startTime = System.currentTimeMillis();
            StrandSortData sortData = strandSort(input);
            long endTime = System.currentTimeMillis();

            System.out.println("\nСортирвоанный лист " + (i + 1) + ":");
            printList(sortData.sortedList);

            double timeTakenInSeconds = (endTime - startTime) / 1000.0; // конвертация времени в секунды
            System.out.println("Затраченное время на сортировку: " + timeTakenInSeconds + " секунды");
            System.out.println("Номер итерации: " + sortData.iterations);
        }
    }

    public static StrandSortData strandSort(List<Integer> input) {
        List<Integer> sortedList = new ArrayList<>();
        int iterations = 0;

        while (!input.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            sublist.add(input.remove(0));

            for (int i = 0; i < input.size(); i++) {
                if (input.get(i) > sublist.get(sublist.size() - 1)) {
                    sublist.add(input.remove(i));
                    i--;
                }
                iterations++;
            }

            sortedList = merge(sortedList, sublist);
        }

        return new StrandSortData(sortedList, iterations);
    }

    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                mergedList.add(list1.get(i));
                i++;
            } else {
                mergedList.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            mergedList.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            mergedList.add(list2.get(j));
            j++;
        }

        return mergedList;
    }

    public static List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(9901) + 100); // Генерируем случайное число от 100 до 10000
        }

        return list;
    }

    public static void printList(List<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

class StrandSortData {
    List<Integer> sortedList;
    int iterations;

    public StrandSortData(List<Integer> sortedList, int iterations) {
        this.sortedList = sortedList;
        this.iterations = iterations;
    }
}
