package fatsby.forms;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.forms.miscpanels.RoomPanel;
import fatsby.manager.Car;
import fatsby.manager.Store;
import fatsby.manager.User;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;

public class InventoryPane extends BlurChild {
    public InventoryPane() {
        init();
    }
    private  void init(){
        setLayout(new MigLayout("wrap 5, insets 20", "[center]", "[center]"));

        int totalAssetsINT = 0;
        for(Car car: user.getOwnedCars()){
            totalAssetsINT += car.getPrice();
        }
        JLabel totalAssets = new JLabel("Total Assets Value: "+totalAssetsINT);
        totalAssets.putClientProperty(FlatClientProperties.STYLE,"font:bold +10");
        add(totalAssets, "growx, wrap");

        //Scroll Panel
        container = new BlurChild();
        container.setLayout(new MigLayout("wrap 5, insets 0", "[]", "[]"));
        for (Car car : user.getOwnedCars()) {
            RoomPanel roomPanel = new RoomPanel(car, true);
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

    public static void refreshInventoryPane() {
        container.removeAll(); // Remove all components
        container.setLayout(new MigLayout("wrap 5, insets 0", "[]", "[]"));
        for (Car car : user.getOwnedCars()) {
            RoomPanel roomPanel = new RoomPanel(car, true);
            container.add(roomPanel);
        }
        container.revalidate();
        container.repaint();
    }

    public static BlurChild container;
    private static User user = User.getInstance();
}
