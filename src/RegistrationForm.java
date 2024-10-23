import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class RegistrationForm extends JDialog {
    private JTextField tfName;
    private JTextField tfRegNO;
    private JTextField tfEmail;
    private JTextField tfProgram;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JTextField tfGender;
    private JButton btnregister;
    private JButton btncancel;
    private JPanel registerPanel;


    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();

            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);

    }

    private void registerUser() {
        String name = tfName.getText();
        String regnumber = tfRegNO.getText();
        String email = tfEmail.getText();
        String program = tfProgram.getText();
        String gender = tfGender.getText();
        String password = pfPassword.getText();
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || regnumber.isEmpty() || email.isEmpty() || program.isEmpty() || gender.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name, regnumber, email, gender, program, password);
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to register new user",
                    " Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public User user;

    private User addUserToDatabase(String name, String regnumber, String email, String program, String gender, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/mysecureproject";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, regnumber, email, program, gender, password) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, regnumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, program);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.regnumber = regnumber;
                user.email = email;
                user.program = program;
                user.gender = gender;
                user.password = password;
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {
        JFrame parentFrame = new JFrame();
        RegistrationForm myForm = new RegistrationForm(parentFrame);
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful registration of: " + user.name);
        } else {
            System.out.println("Registration canceled");

        }
    }
}
