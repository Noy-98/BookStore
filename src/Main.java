import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Book Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(new GridBagLayout());

        // Create a panel to hold all components and center them
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Create the main label
        JLabel mainLabel = new JLabel("Book Store");
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(mainLabel);

        // Add some space between components
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create the card view panel
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(350, 300));
        cardPanel.setMaximumSize(new Dimension(350, 300));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(loginLabel);

        // Add some space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Username input field
        JTextField usernameField = new JTextField("Username");
        usernameField.setPreferredSize(new Dimension(300, 40));
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        usernameField.setForeground(Color.GRAY);
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.GRAY);
                    usernameField.setText("Username");
                }
            }
        });
        cardPanel.add(usernameField);

        // Add some space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Password input field
        JPasswordField passwordField = new JPasswordField("Password");
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        passwordField.setEchoChar((char) 0);
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        cardPanel.add(passwordField);

        // Add some space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create the login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 40));
        loginButton.setMaximumSize(new Dimension(100, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Map<String, String> userCredentials = SignupPage.getUserCredentials();
                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    frame.dispose(); // Close the current frame
                    HomePage.main(new String[0]); // Open HomePage
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cardPanel.add(loginButton);

        // Add some space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add 'Don't have an account?' label and 'Signup' link
        JPanel noAccountPanel = new JPanel();
        noAccountPanel.setOpaque(false);
        noAccountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel noAccountLabel = new JLabel("Don't have an account?");
        noAccountPanel.add(noAccountLabel);

        JLabel signupLabel = new JLabel("Signup");
        signupLabel.setForeground(Color.BLUE);
        signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose(); // Close the current frame
                SignupPage.main(new String[0]); // Open SignupPage
            }
        });
        noAccountPanel.add(signupLabel);

        cardPanel.add(noAccountPanel);

        mainPanel.add(cardPanel);

        frame.add(mainPanel);

        // Display the main frame
        frame.setVisible(true);
    }
}
