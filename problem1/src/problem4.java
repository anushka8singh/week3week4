import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Asset {
    String ticker;
    double returnRate;
    double volatility;

    Asset(String ticker, double returnRate, double volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return ticker + ":" + (int)returnRate + "%";
    }
}

public class problem4 {

    public void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    private void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; ++i) L[i] = assets[left + i];
        for (int j = 0; j < n2; ++j) R[j] = assets[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k] = L[i++];
            } else {
                assets[k] = R[j++];
            }
            k++;
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    public void quickSort(Asset[] assets, int low, int high) {
        if (low < high) {
            if (high - low < 10) {
                insertionSort(assets, low, high);
            } else {
                int pivotIndex = partition(assets, low, high);
                quickSort(assets, low, pivotIndex - 1);
                quickSort(assets, pivotIndex + 1, high);
            }
        }
    }

    private int partition(Asset[] assets, int low, int high) {
        int m = low + (high - low) / 2;
        int pivotIdx = medianOfThree(assets, low, m, high);
        swap(assets, pivotIdx, high);

        Asset pivot = assets[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compareQuick(assets[j], pivot) > 0) {
                i++;
                swap(assets, i, j);
            }
        }
        swap(assets, i + 1, high);
        return i + 1;
    }

    private int medianOfThree(Asset[] assets, int a, int b, int c) {
        double va = assets[a].returnRate, vb = assets[b].returnRate, vc = assets[c].returnRate;
        if ((va - vb) * (vc - va) >= 0) return a;
        if ((vb - va) * (vc - vb) >= 0) return b;
        return c;
    }

    private int compareQuick(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(a.returnRate, b.returnRate);
        }
        return Double.compare(b.volatility, a.volatility);
    }

    private void insertionSort(Asset[] assets, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = assets[i];
            int j = i - 1;
            while (j >= low && compareQuick(assets[j], key) < 0) {
                assets[j + 1] = assets[j];
                j--;
            }
            assets[j + 1] = key;
        }
    }

    private void swap(Asset[] assets, int i, int j) {
        Asset temp = assets[i];
        assets[i] = assets[j];
        assets[j] = temp;
    }

    private static void printArray(String label, Asset[] assets) {
        System.out.print(label + ": [");
        for (int i = 0; i < assets.length; i++) {
            System.out.print(assets[i] + (i == assets.length - 1 ? "" : ", "));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Asset[] data = {
                new Asset("AAPL", 12.0, 0.2),
                new Asset("TSLA", 8.0, 0.5),
                new Asset("GOOG", 15.0, 0.1)
        };

        problem4 sorter = new problem4();

        Asset[] mData = data.clone();
        sorter.mergeSort(mData, 0, mData.length - 1);
        printArray("Merge", mData);

        Asset[] qData = data.clone();
        sorter.quickSort(qData, 0, qData.length - 1);
        printArray("Quick (desc)", qData);
    }
}
