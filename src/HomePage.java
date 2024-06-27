import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends JFrame {

    private List<Order> orderHistory; // List to store order history

    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800); // Initial size
        getContentPane().setLayout(new BorderLayout());

        orderHistory = new ArrayList<>(); // Initialize order history list

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLUE);
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        // Burger button and dropdown menu
        JButton burgerButton = new JButton("☰");
        burgerButton.setFont(new Font("Arial", Font.BOLD, 24));
        burgerButton.setForeground(Color.WHITE);
        burgerButton.setBackground(Color.BLUE);
        burgerButton.setBorderPainted(false);
        burgerButton.setFocusPainted(false);
        burgerButton.setContentAreaFilled(false);

        JPopupMenu dropdownMenu = new JPopupMenu();
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem ordersItem = new JMenuItem("Orders"); // Added "Orders" menu item
        dropdownMenu.add(ordersItem); // Added "Orders" menu item
        dropdownMenu.add(logoutItem);

        burgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(burgerButton, burgerButton.getWidth() / 2, burgerButton.getHeight() / 2);
            }
        });

        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                Main.main(new String[0]); // Open Main
            }
        });

        ordersItem.addActionListener(new ActionListener() { // ActionListener for "Orders" menu item
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display order history
                showOrderHistory();
            }
        });

        headerPanel.add(burgerButton, BorderLayout.EAST);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Body
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.setLayout(new GridLayout(0, 1, 0, 10)); // GridLayout with 1 column
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        JScrollPane scrollPane = new JScrollPane(bodyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // List of books with prices
        String[] books = {
                "Graphic Novel: Dali by Nicole Lo & Myoji - Hardcover - Graphic Novel - ₱1,367.00",
                "Makina by Ian Sta. Maria & Katrina Olan - Trade Paperback - Philippine Fiction & Literature - ₱664.00",
                "Spy x Family: Family Portrait by Tatsuya Endo - Trade Paperback - Teens Fiction - Manga - ₱485.00",
                "The Ultimate Stay-At-Home Dad by Shannon Carpenter - Trade Paperback - Self-help Books - ₱293.00",
                "Bartending: The Basics of Mixology by Akhil Mathur - Trade Paperback - Culinary Books - ₱975.00",
                "From Russia With Love by Ian Fleming - Trade Paperback - Thriller, Mystery & Suspense - ₱877.00"
        };

        for (String book : books) {
            JButton bookButton = new JButton(book);
            bookButton.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
            bookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open payment page or dialog
                    openPaymentPage(book);
                }
            });
            bodyPanel.add(bookButton);
        }

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.BLUE);
        footerPanel.setPreferredSize(new Dimension(600, 50));
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

    private void openPaymentPage(String bookDetails) {
        // Simulate opening payment page or dialog
        JFrame paymentFrame = new JFrame("Payment");
        paymentFrame.setSize(400, 300);
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setLayout(new BorderLayout());

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        paymentFrame.add(paymentPanel, BorderLayout.CENTER);

        JLabel bookLabel = new JLabel("Selected Book: " + bookDetails);
        paymentPanel.add(bookLabel);

        JLabel pcsLabel = new JLabel("Select number of copies:");
        paymentPanel.add(pcsLabel);

        JComboBox<Integer> pcsDropdown = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        paymentPanel.add(pcsDropdown);

        JLabel totalMoneyLabel = new JLabel("Enter total money to pay:");
        paymentPanel.add(totalMoneyLabel);

        JTextField totalMoneyField = new JTextField();
        paymentPanel.add(totalMoneyField);

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double totalMoney = Double.parseDouble(totalMoneyField.getText().trim());
                    double bookPrice = extractPrice(bookDetails);
                    int pcs = (int) pcsDropdown.getSelectedItem();
                    double totalCost = bookPrice * pcs; // Total cost of books

                    // Calculate remaining balance
                    double remainingBalance = totalMoney - totalCost;

                    if (remainingBalance >= 0) {
                        // Success message
                        JOptionPane.showMessageDialog(null, "Successfully purchased:\n" + bookDetails + "\nPcs: " + pcs + "\nTotal amount deducted: ₱" + totalCost + "\nRemaining Balance: ₱" + remainingBalance);

                        // Add order to history
                        Order order = new Order(bookDetails, pcs, totalCost);
                        orderHistory.add(order);
                    } else {
                        // Insufficient balance message
                        JOptionPane.showMessageDialog(null, "Insufficient balance. Please input sufficient money.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for total money.");
                }
            }
        });
        paymentPanel.add(payButton);

        paymentFrame.setVisible(true);
    }

    private void showOrderHistory() {
        if (orderHistory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No orders yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Order order : orderHistory) {
                sb.append("Book: ").append(order.getBookDetails()).append(", Pcs: ").append(order.getPcs()).append(", Total Cost: ₱").append(order.getTotalCost()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Order History", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private double extractPrice(String bookDetails) {
        String[] parts = bookDetails.split(" - ");
        String pricePart = parts[parts.length - 1];
        String priceStr = pricePart.replaceAll("[^0-9.]", ""); // Extract numerical value
        return Double.parseDouble(priceStr);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });
    }

    // Inner class to represent an Order
    private class Order {
        private String bookDetails;
        private int pcs;
        private double totalCost;

        public Order(String bookDetails, int pcs, double totalCost) {
            this.bookDetails = bookDetails;
            this.pcs = pcs;
            this.totalCost = totalCost;
        }

        public String getBookDetails() {
            return bookDetails;
        }

        public int getPcs() {
            return pcs;
        }

        public double getTotalCost() {
            return totalCost;
        }
    }
}
