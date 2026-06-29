package pbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblError;

    private final Color PRIMARY = new Color(25, 118, 210);
    private final Color PRIMARY_LIGHT = new Color(227, 242, 253);
    private final Color PRIMARY_DARK = new Color(13, 71, 161);

    public LoginFrame() {

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PRIMARY_LIGHT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(PRIMARY_DARK);

        // Label
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        lblUsername.setForeground(Color.BLACK);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPassword.setForeground(Color.BLACK);

        // TextField
        txtUsername = new JTextField(16);
        txtUsername.setBackground(Color.WHITE);
        txtUsername.setForeground(Color.BLACK);

        txtPassword = new JPasswordField(16);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setForeground(Color.BLACK);

        // Tombol Login
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogin.setBackground(PRIMARY);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);

        // Label Error
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);

        // ===== TITLE =====
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        // ===== USERNAME =====
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUsername, gbc);

        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        // ===== PASSWORD =====
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassword, gbc);

        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // ===== BUTTON LOGIN =====
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        // ===== ERROR =====
        gbc.gridy = 4;
        panel.add(lblError, gbc);

        add(panel);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String username = txtUsername.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();

        if (username.equals("admin") && password.equals("123")) {

            dispose();
            new MahasiswaFrame();

        } else {

            lblError.setText("Username atau Password salah!");

            txtPassword.setText("");
            txtPassword.requestFocus();

        }

    }

}
