package fatsby.forms.miscpanels;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import fatsby.forms.InventoryPane;
import fatsby.manager.Car;
import fatsby.manager.FormsManager;
import fatsby.manager.Serializer;
import fatsby.manager.User;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.paymentfont.PaymentFont;

import javax.swing.*;
import java.awt.*;

public class CheckoutDialog extends javax.swing.JDialog {
    private User user = User.getInstance();

    public CheckoutDialog(Car car){
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY,Font.PLAIN,13));
        init(car);
    }
    private void init(Car car){
        setTitle("Checkout");
        setSize(new Dimension(660, 400));
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new MigLayout("insets 20 20 20 20, align center", "[grow]", "[grow]"));
        drawPanel(car);
    }
    private void drawPanel(Car car){

        JLabel billingDetailsLBL = new JLabel("Billing Details:");
        billingDetailsLBL.putClientProperty(FlatClientProperties.STYLE,"font:bold +10");
        this.add(billingDetailsLBL, "wrap");

        ButtonGroup bg = new ButtonGroup();
        JRadioButton visaBTN = new JRadioButton();
        JRadioButton applePayBTN = new JRadioButton();
        JRadioButton paypalBTN = new JRadioButton();
        JRadioButton mastercardBTN = new JRadioButton();
        bg.add(visaBTN);
        bg.add(applePayBTN);
        bg.add(paypalBTN);
        bg.add(mastercardBTN);

        JLabel visaLBL = new JLabel();
        visaLBL.setIcon(FormsManager.initIcon(PaymentFont.VISA, 25, Color.white));
        JLabel applePayLBL = new JLabel();
        applePayLBL.setIcon(FormsManager.initIcon(PaymentFont.APPLE_PAY, 25, Color.white));
        JLabel paypalLBL = new JLabel();
        paypalLBL.setIcon(FormsManager.initIcon(PaymentFont.PAYPAL, 25, Color.white));
        JLabel mastercardLBL = new JLabel();
        mastercardLBL.setIcon(FormsManager.initIcon(PaymentFont.MASTERCARD, 25, Color.white));

        JPanel checkoutOptionsPanel = new JPanel();
        checkoutOptionsPanel.setLayout(new MigLayout());
        checkoutOptionsPanel.add(visaBTN, "split 2");
        checkoutOptionsPanel.add(visaLBL, "gapright 30");
        checkoutOptionsPanel.add(applePayBTN, "split 2");
        checkoutOptionsPanel.add(applePayLBL, "gapright 30");
        checkoutOptionsPanel.add(paypalBTN, "split 2");
        checkoutOptionsPanel.add(paypalLBL, "gapright 30");
        checkoutOptionsPanel.add(mastercardBTN, "split 2");
        checkoutOptionsPanel.add(mastercardLBL, "gapright 30");
        this.add(checkoutOptionsPanel, "gapleft 100, wrap");

        JPanel panel = new JPanel(new MigLayout());
        JLabel cardNumberLBL = new JLabel("Card Number");
        JTextField cardNumTXT = new JTextField(15);
        JLabel cardHolderLBL = new JLabel("Card Holder");
        JTextField cardHolderTXT = new JTextField(15);

        panel.add(cardNumberLBL, "center, split 2");
        panel.add(cardNumTXT, "center");
        panel.add(cardHolderLBL, "center, split 2");
        panel.add(cardHolderTXT, "center, wrap");

        JLabel expiryDateLBL = new JLabel("Expiry Date");
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        String[] years = new String[20];
        int startYear = 2024;
        for (int i = 0; i < 20; i++) {
            years[i] = String.valueOf(startYear + i);
        }
        JComboBox<String> yearCB = new JComboBox<>(years);
        JComboBox<String> monthCB = new JComboBox<>(months);

        panel.add(expiryDateLBL, "center, split 3");
        panel.add(monthCB, "center");
        panel.add(yearCB, "center");

        JLabel cvcLBL = new JLabel("CVC");
        JTextField cvcTXT = new JTextField(10);
        panel.add(cvcLBL, "center, split 2");
        panel.add(cvcTXT, "center, wrap");
        this.add(panel, "gapleft 40, grow, wrap");

        JPanel sumPanel = new JPanel(new MigLayout("insets 10 40 10 40"));
        sumPanel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        JLabel carName = new JLabel(car.getCarName());
        JLabel price = new JLabel("$"+car.getPrice());
        sumPanel.add(carName, "gapright 300");
        sumPanel.add(price, "wrap");

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setBackground(Color.lightGray);
        separator.setForeground(Color.white);
        sumPanel.add(separator, "growx, span, wrap");
        JLabel total = new JLabel("TOTAL");
        total.putClientProperty(FlatClientProperties.STYLE,"font:bold +10");
        sumPanel.add(total, "");
        JLabel priceTotal = new JLabel("$"+car.getPrice());
        priceTotal.putClientProperty(FlatClientProperties.STYLE,"font:bold +10");
        sumPanel.add(priceTotal, "");
        this.add(sumPanel, "wrap, align center");
        JButton purchaseBTN = new JButton("Purchase");
        purchaseBTN.putClientProperty(FlatClientProperties.STYLE, "background: #90EE90; foreground: #000000;");
        purchaseBTN.setFocusPainted(false);
        purchaseBTN.setFont(new Font("Arial", Font.BOLD, 20));
        purchaseBTN.setMargin(new Insets(10, 20, 10, 20));
        this.add(purchaseBTN, "wrap, align center, gapy 20");

        purchaseBTN.addActionListener(e ->{
            user.addOwnedCar(car);
            InventoryPane.refreshInventoryPane();
            Serializer.serializeObject(user, "C:\\FatsbyCarRetailer\\database\\users", user.getUsername()+".dat");
            JOptionPane.showMessageDialog(CheckoutDialog.this, "Thank you for your purchase!");
        });
    }
}
