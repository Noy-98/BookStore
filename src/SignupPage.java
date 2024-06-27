import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class SignupPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    // Store user credentials
    private static Map<String, String> userCredentials = new HashMap<>();

    public SignupPage() {
        setTitle("Signup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        getContentPane().setBackground(Color.BLUE); // Background color

        // Create main panel with BoxLayout for vertical alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Create Signup label
        JLabel signupLabel = new JLabel("Signup");
        signupLabel.setForeground(Color.WHITE);
        signupLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(signupLabel);

        // Add space between components
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create card panel similar to Main.java
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setPreferredSize(new Dimension(350, 300));
        cardPanel.setMaximumSize(new Dimension(350, 300));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field with placeholder
        usernameField = new JTextField("Username");
        usernameField.setPreferredSize(new Dimension(300, 40));
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
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
        usernameField.setForeground(Color.GRAY);
        cardPanel.add(usernameField);

        // Add space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Password field with placeholder
        passwordField = new JPasswordField("Password");
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        passwordField.setEchoChar((char) 0);
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
        passwordField.setForeground(Color.GRAY);
        cardPanel.add(passwordField);

        // Add space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Confirm Password field with placeholder
        confirmPasswordField = new JPasswordField("Confirm Password");
        confirmPasswordField.setPreferredSize(new Dimension(300, 40));
        confirmPasswordField.setMaximumSize(new Dimension(300, 40));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        confirmPasswordField.setEchoChar((char) 0);
        confirmPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(confirmPasswordField.getPassword()).equals("Confirm Password")) {
                    confirmPasswordField.setText("");
                    confirmPasswordField.setEchoChar('*');
                    confirmPasswordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(confirmPasswordField.getPassword()).isEmpty()) {
                    confirmPasswordField.setForeground(Color.GRAY);
                    confirmPasswordField.setText("Confirm Password");
                    confirmPasswordField.setEchoChar((char) 0);
                }
            }
        });
        confirmPasswordField.setForeground(Color.GRAY);
        cardPanel.add(confirmPasswordField);

        // Add space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(100, 40));
        signupButton.setMaximumSize(new Dimension(100, 40));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField.getPassword());

                    // Save user credentials
                    userCredentials.put(username, password);

                    JOptionPane.showMessageDialog(SignupPage.this, "Signup successful!");

                    // Navigate to Main.java
                    dispose();
                    Main.main(new String[0]);
                }
            }
        });
        cardPanel.add(signupButton);

        // Add space between components
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create 'Have an account?' label and 'Login' link
        JPanel haveAccountPanel = new JPanel();
        haveAccountPanel.setOpaque(false);
        haveAccountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel haveAccountLabel = new JLabel("Have an account?");
        haveAccountPanel.add(haveAccountLabel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the current frame
                Main.main(new String[0]); // Open Main.java
            }
        });
        haveAccountPanel.add(loginLabel);

        cardPanel.add(haveAccountPanel);

        mainPanel.add(cardPanel);

        getContentPane().add(mainPanel);
    }

    private boolean validateForm() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static Map<String, String> getUserCredentials() {
        return userCredentials;
    }

    public static void main(String[] args) {
        // Create and display the Signup page
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SignupPage signupPage = new SignupPage();
                signupPage.setVisible(true);
            }
        });
    }
}
