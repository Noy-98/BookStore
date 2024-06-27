import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        getContentPane().setLayout(new BorderLayout());

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

        headerPanel.add(burgerButton, BorderLayout.EAST);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.BLUE);
        footerPanel.setPreferredSize(new Dimension(600, 50));
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });
    }
}
