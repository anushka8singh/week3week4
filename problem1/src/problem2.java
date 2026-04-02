
import java.util.ArrayList;
import java.util.List;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class problem2 {

    public void bubbleSortAscending(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.print("Bubble (asc): [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i] + (i == clients.length - 1 ? "" : ", "));
        }
        System.out.println("] // Swaps: " + swaps);
    }

    public void insertionSortDescending(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && compareRiskBalance(clients[j], key) < 0) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }
        System.out.print("Insertion (desc): [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i] + (i == clients.length - 1 ? "" : ", "));
        }
        System.out.println("]");
    }

    private int compareRiskBalance(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return Integer.compare(a.riskScore, b.riskScore);
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    public void displayTopRisks(Client[] clients, int topN) {
        System.out.print("Top " + topN + " risks: ");
        int limit = Math.min(topN, clients.length);
        for (int i = 0; i < limit; i++) {
            System.out.print(clients[i].name + "(" + clients[i].riskScore + ")" + (i == limit - 1 ? "" : ", "));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] data = {
                new Client("clientC", 80, 5000.0),
                new Client("clientA", 20, 1500.0),
                new Client("clientB", 50, 3000.0)
        };

        problem2 manager = new problem2();

        Client[] bubbleData = data.clone();
        manager.bubbleSortAscending(bubbleData);

        Client[] insertionData = data.clone();
        manager.insertionSortDescending(insertionData);
        manager.displayTopRisks(insertionData, 3);
    }
}