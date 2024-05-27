package fatsby.forms.adminforms;

import fatsby.forms.DashboardPane;
import fatsby.manager.IndexChangeListener;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;

public class AdminMenu extends BlurBackground implements IndexChangeListener {
    public AdminMenu(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/fatsby/images/background.jpg"));
        setOverlay(new StyleOverlay(new Color(20,20,20), 0.9f));
        setImage(icon.getImage());
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill", "[fill]", "[fill]"));
        adminSystemMenu = new AdminSystemMenu();
        add(adminSystemMenu, "dock west, gap 6 6 6 6, width 280!");

        cards = new JPanel(new CardLayout());
        dashboardPane = new DashboardPane();
        settingsPane = new AdminSettingsPane();

        cards.add(dashboardPane, DASHBOARD_PANE);
        cards.add(settingsPane, SETTINGS_PANE);
        add(cards, "grow");
        switchPane(AdminSystemMenu.index);
        adminSystemMenu.addIndexChangeListener(this);
    }

    private void switchPane(int index){
        CardLayout cl = (CardLayout)cards.getLayout();
        if(index == 0){
            cl.show(cards, DASHBOARD_PANE);
        } else if (index == 1) {
            cl.show(cards, SETTINGS_PANE);
        }
    }
    private AdminSystemMenu adminSystemMenu;
    private DashboardPane dashboardPane;
    private AdminSettingsPane settingsPane;
    private JPanel cards;
    final static String DASHBOARD_PANE = "Dashboard Pane";
    final static String SETTINGS_PANE = "Settings Pane";

    @Override
    public void onIndexChanged(int newIndex) {
        switchPane(newIndex);
    }
}
