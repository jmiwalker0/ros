import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RestaurantOrderingSystem {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // MENU DATA
        String[] items = {"Burger", "Fries", "Spaghetti", "Fried Chicken", "Soda"};
        double[] prices = {85.0, 45.0, 70.0, 120.0, 25.0};

        boolean moreCustomers = true;

        while (moreCustomers) {

            System.out.println("========================================");
            System.out.println("       WELCOME TO JW RESTAURANT");
            System.out.println("========================================");

            // Show menu
            System.out.println("\nMENU:");
            for (int i = 0; i < items.length; i++) {
                System.out.printf("%d. %-15s - ₱%.2f\n", (i + 1), items[i], prices[i]);
            }

            double total = 0;
            boolean ordering = true;
            StringBuilder receipt = new StringBuilder();
            receipt.append("======== RECEIPT ========\n");

            while (ordering) {
                System.out.print("\nEnter item number to order (0 to finish): ");
                int choice = sc.nextInt();

                if (choice == 0) {
                    ordering = false;
                    break;
                }

                if (choice < 1 || choice > items.length) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();

                double subTotal = prices[choice - 1] * qty;
                total += subTotal;

                receipt.append(String.format("%-15s x%d   ₱%.2f\n", 
                        items[choice - 1], qty, subTotal));
            }

            // Discount rule
            double discount = 0;
            if (total > 500) {
                discount = total * 0.10; // 10%
            }

            // VAT (bonus feature)
            double vat = total * 0.12;

            double finalTotal = total - discount + vat;

            // Add summary to receipt
            receipt.append("---------------------------\n");
            receipt.append(String.format("Subtotal:        ₱%.2f\n", total));
            receipt.append(String.format("Discount:        ₱%.2f\n", discount));
            receipt.append(String.format("VAT (12%%):       ₱%.2f\n", vat));
            receipt.append(String.format("GRAND TOTAL:     ₱%.2f\n", finalTotal));
            receipt.append("===========================\n");

            // Print receipt on console
            System.out.println("\n\n" + receipt);

            // Save to file
            FileWriter writer = new FileWriter("order_summary.txt", true);
            writer.write(receipt.toString());
            writer.write("\n\n");
            writer.close();

            // Ask for next customer
            System.out.print("Next customer? (y/n): ");
            char again = sc.next().charAt(0);

            moreCustomers = (again == 'y' || again == 'Y');
        }

        System.out.println("Thank you for using the system!");
        sc.close();
    }
}

