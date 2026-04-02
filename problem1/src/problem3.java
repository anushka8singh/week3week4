
import java.util.ArrayList;
import java.util.List;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class problem3 {

    public void mergeSort(Trade[] trades, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(trades, left, mid);
            mergeSort(trades, mid + 1, right);
            merge(trades, left, mid, right);
        }
    }

    private void merge(Trade[] trades, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; ++i) L[i] = trades[left + i];
        for (int j = 0; j < n2; ++j) R[j] = trades[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                trades[k] = L[i];
                i++;
            } else {
                trades[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            trades[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            trades[k] = R[j];
            j++;
            k++;
        }
    }

    public void quickSortDesc(Trade[] trades, int low, int high) {
        if (low < high) {
            int pi = partition(trades, low, high);
            quickSortDesc(trades, low, pi - 1);
            quickSortDesc(trades, pi + 1, high);
        }
    }

    private int partition(Trade[] trades, int low, int high) {
        int pivot = trades[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot) {
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }
        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    public Trade[] mergeTwoLists(Trade[] morning, Trade[] afternoon) {
        Trade[] merged = new Trade[morning.length + afternoon.length];
        int i = 0, j = 0, k = 0;
        while (i < morning.length && j < afternoon.length) {
            if (morning[i].volume <= afternoon[j].volume) {
                merged[k++] = morning[i++];
            } else {
                merged[k++] = afternoon[j++];
            }
        }
        while (i < morning.length) merged[k++] = morning[i++];
        while (j < afternoon.length) merged[k++] = afternoon[j++];
        return merged;
    }

    public long computeTotalVolume(Trade[] trades) {
        long total = 0;
        for (Trade t : trades) {
            total += t.volume;
        }
        return total;
    }

    private static void printArray(String label, Trade[] trades) {
        System.out.print(label + ": [");
        for (int i = 0; i < trades.length; i++) {
            System.out.print(trades[i] + (i == trades.length - 1 ? "" : ", "));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Trade[] morning = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        problem3 analyzer = new problem3();

        Trade[] mergeData = morning.clone();
        analyzer.mergeSort(mergeData, 0, mergeData.length - 1);
        printArray("MergeSort", mergeData);

        Trade[] quickData = morning.clone();
        analyzer.quickSortDesc(quickData, 0, quickData.length - 1);
        printArray("QuickSort (desc)", quickData);

        long total = analyzer.computeTotalVolume(morning);
        System.out.println("Merged morning+afternoon total: " + total);
    }
}