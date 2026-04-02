import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    long timestamp;

    Transaction(String id, double fee, long timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + (timestamp > 0 ? "@" + timestamp : "");
    }
}

public class problem1 {

    public void sortTransactions(List<Transaction> transactions) {
        int n = transactions.size();
        if (n <= 100) {
            bubbleSort(transactions);
        } else if (n <= 1000) {
            insertionSort(transactions);
        }
        findOutliers(transactions);
    }

    public void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int swaps = 0;
        int passes = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break;
        }
        System.out.println("BubbleSort (fees): " + list + " // " + passes + " passes, " + swaps + " swaps");
    }

    public void insertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && compareFeeTimestamp(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + list);
    }

    private int compareFeeTimestamp(Transaction a, Transaction b) {
        if (a.fee != b.fee) {
            return Double.compare(a.fee, b.fee);
        }
        return Long.compare(a.timestamp, b.timestamp);
    }

    public void findOutliers(List<Transaction> list) {
        List<String> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t.id);
            }
        }
        System.out.print("High-fee outliers: ");
        if (outliers.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(String.join(", ", outliers));
        }
    }

    public static void main(String[] args) {
        List<Transaction> data = new ArrayList<>();
        data.add(new Transaction("id1", 10.5, 1000));
        data.add(new Transaction("id2", 25.0, 930));
        data.add(new Transaction("id3", 5.0, 1015));

        problem1 sorter = new problem1();
        sorter.sortTransactions(new ArrayList<>(data));
    }
}