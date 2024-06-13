package fatsby.forms.miscpanels;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.manager.Car;
import fatsby.manager.FormsManager;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import org.kordamp.ikonli.fluentui.FluentUiFilledMZ;

import javax.swing.*;
import java.awt.*;

public class CarInfoDialog extends JDialog {
    public CarInfoDialog(Car car) {
        init(car);
    }
    private void init(Car car) {
        setTitle("Car Info");
        setSize(new Dimension(1060, 660));
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel container = new JPanel(new MigLayout("insets 25", "[fill]", "[grow]"));
        scrollPane = new JScrollPane(container);

        container.add(carDescPanel(car),"grow" );

        JPanel rightSidePanel = new JPanel(new MigLayout("insets 0, align center", "[]", "push[]push"));
        rightSidePanel.add(purchasePanel(car), "cell 0 0");
        container.add(rightSidePanel,"dock east");

        setupScrollPane();
        add(scrollPane);
    }

    private JComponent carDescPanel(Car car){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap, insets 20", "", ""));
        JLabel carName = new JLabel(car.getCarName());
        carName.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");

        String wrappedDescription = "<html><body style='width: %1spx'>" + car.getDescription() + "</body></html>";
        JLabel description = new JLabel(String.format(wrappedDescription, 550));

        panel.add(carName, "grow, wrap");
        panel.add(description, "grow, wrap");

        try {
            String imageURL = car.getImageURL();
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(700, 450, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            panel.add(imageLabel, "wrap");
        } catch (Exception e) {
            System.err.println("Error loading car image: " + e.getMessage());
        }

        return panel;
    }

    private JComponent purchasePanel(Car car){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap, insets 10 40 10 40"));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        JLabel carName = new JLabel(car.getCarName());
        carName.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +7");
        JLabel capacityLbl = new JLabel("Capacity: "+car.getCapacity());
        capacityLbl.setIcon(FormsManager.initIcon(Dashicons.ADMIN_USERS, 16, Color.white));
        JLabel priceLbl = new JLabel("Price: $"+car.getPrice());
        priceLbl.setIcon(FormsManager.initIcon(FluentUiFilledMZ.MONEY_24, 16, Color.white));
        JButton purchaseBTN = new JButton("Purchase");
        purchaseBTN.putClientProperty(FlatClientProperties.STYLE, "background: #90EE90; foreground: #000000;");
        purchaseBTN.setFocusPainted(false);
        purchaseBTN.setFont(new Font("Arial", Font.BOLD, 20));
        purchaseBTN.setMargin(new Insets(10, 20, 10, 20));


        panel.add(carName, "wrap, align center");
        panel.add(capacityLbl, "wrap, align center");
        panel.add(priceLbl, "wrap, align center");
        panel.add(purchaseBTN, "align center");
        purchaseBTN.addActionListener(e -> {
            CheckoutDialog checkoutDialog = new CheckoutDialog(car);
            checkoutDialog.setVisible(true);
        });
        return panel;
    }

    private void setupScrollPane() {
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JScrollPane scrollPane;
}
