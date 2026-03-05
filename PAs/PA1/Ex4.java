package PA1;


import java.util.ArrayList;
import java.util.List;

public class Ex4 {
    public static class MyCreditCard extends CreditCard {
        private final double apr;
        private final List<String> transactions;

        public MyCreditCard(String cust, String bk, String acnt, int lim, double initialBal, double apr) {
            super(cust, bk, acnt, lim, initialBal);
            this.apr = apr;
            this.transactions = new ArrayList<>();
        }

        @Override
        public boolean charge(double price) {
            boolean isSuccess = super.charge(price);
            if (isSuccess) {
                transactions.add("Charge: $" + String.format("%.2f", price));
            }
            return isSuccess;
        }

        @Override
        public void makePayment(double amount) {
            super.makePayment(amount);
            transactions.add("Payment: $" + String.format("%.2f", amount));
        }

        public void addInterest() {
            if (balance > 0) {
                // Calculating the monthly rate by taking the 12th root of (1 + apr)
                double monthlyFactor = Math.pow(1 + apr, 1.0 / 12) - 1;
                double interest = balance * monthlyFactor;
                balance += interest;
                transactions.add("Interest: $" + String.format("%.2f", interest));
            }
        }

        public void printTransactions() {
            System.out.println("Acct " + getAccount() + " History:");
            System.out.print("  [");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.print(transactions.get(i));

                // 如果不是最后一个元素，打印分隔符
                if (i < transactions.size() - 1) {
                    System.out.print(" | ");
                }

                // 核心逻辑：每输出 4 个交易记录自动换行 (且确保最后一行不产生多余换行)
                if ((i + 1) % 4 == 0 && i != transactions.size() - 1) {
                    System.out.print("\n   ");
                }
            }
            System.out.println("]");
        }

        public static void main(String[] args) {
            // 1. Create 2 credit cards with annual interest of 10%
            MyCreditCard card1 = new MyCreditCard("Aiden", "Bank of Java", "1234", 5000, 0, 0.10);
            MyCreditCard card2 = new MyCreditCard("Alice", "C++ Federation", "5678", 2000, 0, 0.10);

            System.out.println("Author: Aiden Wang\n");
            System.out.println("--- 6-Month Simulation ---");
            // 2. Simulate 6 months
            for (int month = 1; month <= 6; month++) {
                // Card 1 operations
                card1.charge(300.0);
                card1.charge(50.0);
                card1.makePayment(100.0); // Partial payment
                card1.addInterest();

                // Card 2 operations
                card2.charge(150.0);
                card2.makePayment(150.0); // Full payment
                card2.addInterest();

                // Print both balances compactly on a single line
                System.out.printf("Month %d | Card 1 Bal: $%-7.2f | Card 2 Bal: $%.2f\n",
                        month, card1.getBalance(), card2.getBalance());
            }

            // 3. Print all transactions
            System.out.println("\n--- Transaction History ---");
            card1.printTransactions();
            card2.printTransactions();
        }
    }
}
