package fatsby.forms.miscpanels;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.manager.Car;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CarInfoDialog extends JDialog {
    public CarInfoDialog(Car car) {
        init(car);
    }
    private void init(Car car) {
        setTitle("Car Info");
        setSize(new Dimension(760, 660));
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        scrollPane = new JScrollPane(drawPanel(car));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);;
        add(scrollPane);
    }

    private JComponent drawPanel(Car car){
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
            // Resize the image to 150x150 pixels
            Image resizedImage = originalImage.getScaledInstance(700, 450, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            panel.add(imageLabel, "wrap"); // Add the image label to the panel with a line break
        } catch (Exception e) {
            System.err.println("Error loading car image: " + e.getMessage());
        }

        return panel;
    }
    private JScrollPane scrollPane;
}
