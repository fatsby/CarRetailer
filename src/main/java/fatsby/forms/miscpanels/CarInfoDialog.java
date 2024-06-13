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

        JLabel description = new JLabel(car.getDescription());


        panel.add(carName, "grow, wrap");
        panel.add(description, "grow, wrap");

        return panel;
    }
    private JScrollPane scrollPane;
}
