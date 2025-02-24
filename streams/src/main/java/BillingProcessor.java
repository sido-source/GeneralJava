import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
John,Doe,123,001,299.99,SKY001,USD
Jane,Smith,124,002,159.49,SKY002,USD
...

 */


public class BillingProcessor {
    public static void main(String[] args) {
        String filePath = "path/to/billingfile.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Stream the lines of the file and process them in parallel
            List<Order> orders = reader.lines()
                    .parallel()
                    .map(BillingProcessor::parseLineToOrder)
                    .collect(Collectors.toList());

            // Further processing of orders
            orders.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to parse a line into an Order object
    private static Order parseLineToOrder(String line) {
        String[] parts = line.split(",");
        return new Order(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3],
                Double.parseDouble(parts[4]), parts[5], parts[6]);
    }

    /*
    Step 3: Processing Orders Concurrently

If you need to perform further concurrent operations (e.g., filtering, grouping, or processing orders), you can continue to use parallel streams.

For example, you might want to calculate the total amount spent by each customer concurrently:
     */
    public static void main2(String[] args) {
        String filePath = "path/to/billingfile.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Map<String, Double> totalSpentByCustomer = reader.lines()
                    .parallel()
                    .map(BillingProcessor::parseLineToOrder)
                    .collect(Collectors.groupingByConcurrent(
                            order -> order.customerName + " " + order.surname,
                            Collectors.summingDouble(order -> order.amount)
                    ));

            // Print out the total spent by each customer
            totalSpentByCustomer.forEach((customer, total) ->
                    System.out.println(customer + " spent a total of " + total + " USD")
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// A simple class representing an order
class Order {
    String customerName;
    String surname;
    int customerId;
    String orderId;
    double amount;
    String sku;
    String currency;

    public Order(String customerName, String surname, int customerId, String orderId, double amount, String sku, String currency) {
        this.customerName = customerName;
        this.surname = surname;
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
        this.sku = sku;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return customerName + " " + surname + ": " + orderId + " - " + amount + " " + currency;
    }
}

