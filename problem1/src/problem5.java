import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Transaction {
    String accountId;
    String transactionId;

    Transaction(String accountId, String transactionId) {
        this.accountId = accountId;
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return accountId;
    }
}

public class problem5 {

    public void linearSearch(List<Transaction> logs, String target) {
        int first = -1;
        int last = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.size(); i++) {
            comparisons++;
            if (logs.get(i).accountId.equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear first " + target + ": index " + first + " (" + comparisons + " comparisons)");
        if (first != -1) {
            System.out.println("Linear last " + target + ": index " + last);
        }
    }

    public void binarySearch(List<Transaction> logs, String target) {
        int low = 0;
        int high = logs.size() - 1;
        int comparisons = 0;
        int index = -1;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int res = target.compareTo(logs.get(mid).accountId);

            if (res == 0) {
                index = mid;
                break;
            } else if (res > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        int count = 0;
        if (index != -1) {
            count = 1;
            int left = index - 1;
            while (left >= 0 && logs.get(left).accountId.equals(target)) {
                count++;
                left--;
            }
            int right = index + 1;
            while (right < logs.size() && logs.get(right).accountId.equals(target)) {
                count++;
                right++;
            }
        }

        System.out.println("Binary " + target + ": index " + index + " (" + comparisons + " comparisons), count=" + count);
    }

    public static void main(String[] args) {
        List<Transaction> logs = new ArrayList<>();
        logs.add(new Transaction("accB", "t1"));
        logs.add(new Transaction("accA", "t2"));
        logs.add(new Transaction("accB", "t3"));
        logs.add(new Transaction("accC", "t4"));

        problem5 searcher = new problem5();

        searcher.linearSearch(logs, "accB");

        Collections.sort(logs, Comparator.comparing(t -> t.accountId));

        searcher.binarySearch(logs, "accB");
    }
}
