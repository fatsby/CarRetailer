package fatsby.login;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.manager.FormsManager;
import fatsby.manager.Serializer;
import fatsby.manager.Staff;
import fatsby.manager.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Register extends JPanel {
    public Register(){
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        btnRegister = new JButton("Register");
        invCodeBox = new JTextField();
        genderBox = new JComboBox<String>(new String[]{"Male", "Female"});

        //Invite codes
        invCodes.add("admin2312");
        invCodes.add("admin1004");

        //Text fields placeholder texts
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Enter a badass Username here");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Enter your password here");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Re-enter password");
        invCodeBox.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Leave blank if not staff");
        txtPassword.putClientProperty(FlatClientProperties.STYLE,"" +
                "showRevealButton:true");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE,"" +
                "showRevealButton:true");

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");

        JLabel regLabel = new JLabel("Registering for Fatsby Car Retail: ");
        regLabel.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");
        JLabel description = new JLabel("Lead by our history, leading our future.");
        panel.add(regLabel);
        panel.add(description);
        panel.add(new JLabel("Username:"), "gapy 10");
        panel.add(txtUsername);
        panel.add(new JLabel("Password:"), "gapy 10");
        panel.add(txtPassword);
        panel.add(new JLabel("Confirm Password:"), "gapy 10");
        panel.add(txtConfirmPassword);
        panel.add(new JLabel("Invite Code:"), "gapy 10");
        panel.add(invCodeBox);
        panel.add(new JLabel("Gender:"), "gapy 10");
        panel.add(genderBox);
        panel.add(btnRegister, "gapy 10");
        panel.add(createLoginLabel(), "gapy 10");
        add(panel);

        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());
            String invCode = invCodeBox.getText();
            String gender = Objects.requireNonNull(genderBox.getSelectedItem()).toString();
            boolean isValidInvCode = false;


            if (invCode.isEmpty()){ //Customer registration
                if (!password.equals(confirmPassword)){
                    JOptionPane.showMessageDialog(this, "Passwords do not match");
                    return;
                } else{
                    User user = new User(username, password, gender);
                    Serializer.serializeObject(user, "C:\\FatsbyCarRetailer\\database\\users", username+".dat");
                    JOptionPane.showMessageDialog(this, "User has been registered successfully");
                }
            }

            if(!invCode.isEmpty()){ //Staff registration
                for (String code : invCodes) {
                    if (invCode.equals(code)) {
                        isValidInvCode = true;
                        break; // Valid invite code found, break out of the loop
                    }
                }

                if (!isValidInvCode) {
                    JOptionPane.showMessageDialog(null, "Invalid Invite Code");
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password and confirm password do not match");
                    return;
                } else{ //SUCCESSFUL REGISTER
                    Staff staff = new Staff(username, password, gender, invCode);
                    Serializer.serializeObject(staff, "C:\\FatsbyCarRetailer\\database\\staffs", username+".dat");
                    JOptionPane.showMessageDialog(null, "Staff has been registered successfully");
                }
            }

        });
    }

    private Component createLoginLabel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0 ,0));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "background:null");
        JButton loginButton = new JButton("<html><a href=\"#\">Login.</a></html>");
        loginButton.setContentAreaFilled(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Login());
        });
        JLabel AccountLabel = new JLabel("Already have an account?");

        panel.add(AccountLabel);
        panel.add(loginButton);
        return panel;
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnRegister;
    private JTextField invCodeBox;
    private JComboBox genderBox;
    private ArrayList<String> invCodes = new ArrayList<>();
}
