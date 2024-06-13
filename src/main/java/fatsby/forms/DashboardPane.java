package fatsby.forms;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.forms.miscpanels.RoomPanel;
import fatsby.manager.Car;
import fatsby.manager.Serializer;
import fatsby.manager.Store;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DashboardPane extends BlurChild {
    public DashboardPane() {
        init();
    }
    private void init(){
        setLayout(new MigLayout("wrap 5, insets 20", "[center]", "[center]"));


        JLabel availCarLabel = new JLabel("Available Cars:");
        availCarLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +10");
        add(availCarLabel, "growx, wrap");

        //Scroll Panel
        container = new BlurChild();
        container.setLayout(new MigLayout("wrap 5, insets 0", "[]", "[]"));
        for (Car car : store.getCars()) {
            RoomPanel roomPanel = new RoomPanel(car);
            container.add(roomPanel);
        }

        JScrollPane availCarScrollPane = new JScrollPane(container);
        availCarScrollPane.setBorder(BorderFactory.createEmptyBorder());
        availCarScrollPane.setOpaque(false);
        availCarScrollPane.getViewport().setOpaque(false);
        availCarScrollPane.getVerticalScrollBar().setOpaque(false);
        availCarScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(availCarScrollPane, "growx, wrap");
    }

    public static void refreshDashboardPane() {
        container.removeAll(); // Remove all components
        container.setLayout(new MigLayout("wrap 5, insets 0", "[]", "[]"));
        for (Car car : store.getCars()) {
            RoomPanel roomPanel = new RoomPanel(car);
            container.add(roomPanel);
        }
        container.revalidate();
        container.repaint();
    }

    private JLabel testLabel;
    private static Store store = Store.getInstance();
    public static BlurChild container;
}
