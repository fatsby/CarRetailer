package fatsby.forms;

import fatsby.manager.IndexChangeListener;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;

public class UserMenu extends BlurBackground implements IndexChangeListener {
    public UserMenu(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/fatsby/images/background.jpg"));
        setOverlay(new StyleOverlay(new Color(20,20,20), 0.9f));
        setImage(icon.getImage());
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill", "[fill]", "[fill]"));
        userSystemMenu = new UserSystemMenu();
        add(userSystemMenu, "dock west, gap 6 6 6 6, width 280!");

        cards = new JPanel(new CardLayout());
        dashboardPane = new DashboardPane();
        settingsPane = new SettingsPane();
        inventoryPane = new InventoryPane();

        cards.add(dashboardPane, DASHBOARD_PANE);
        cards.add(settingsPane, SETTINGS_PANE);
        cards.add(inventoryPane, INVENTORY_PANE);
        add(cards, "grow");
        switchPane(UserSystemMenu.index);
        userSystemMenu.addIndexChangeListener(this);
    }

    private void switchPane(int index){
        CardLayout cl = (CardLayout)cards.getLayout();
        if(index == 0){
            cl.show(cards, DASHBOARD_PANE);
        } else if (index == 1) {
            cl.show(cards, SETTINGS_PANE);
        } else if (index == 2) {
            cl.show(cards, INVENTORY_PANE);
        }
    }
    private fatsby.forms.UserSystemMenu userSystemMenu;
    private DashboardPane dashboardPane;
    private SettingsPane settingsPane;
    private InventoryPane inventoryPane;
    private JPanel cards;
    final static String DASHBOARD_PANE = "Dashboard Pane";
    final static String SETTINGS_PANE = "Settings Pane";
    final static String INVENTORY_PANE = "Inventory Pane";

    @Override
    public void onIndexChanged(int newIndex) {
        switchPane(newIndex);
    }
}
