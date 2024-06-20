package fatsby.forms;

import fatsby.login.Login;
import fatsby.manager.IndexChangeListener;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import org.kordamp.ikonli.swing.FontIcon;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.*;
import raven.drawer.component.menu.data.Item;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class UserSystemMenu extends BlurChild {
    public UserSystemMenu() {
        super(new Style()
                .setBlur(30)
                .setBorder(new StyleBorder(10)
                        .setOpacity(0.15f)
                        .setBorderWidth(1.2f)
                        .setBorderColor(new GradientColor(new Color(200,200,200), new Color(150,150,150), new Point2D.Float(0,0), new Point2D.Float(1f,0)))
                )
                .setOverlay(new StyleOverlay(new Color(0,0,0),0.2f))
        );
        init();
    }
    private void init(){
        setLayout(new MigLayout("wrap,fill", "[fill]","[grow 0][fill]"));
        simpleMenu = new SimpleMenu(getMenuOption());
        simpleMenu.setOpaque(false);

        SimpleHeader header = new SimpleHeader(getHeaderData());
        header.setOpaque(false);
        add(header);
        add(simpleMenu);
    }

    private SimpleMenuOption getMenuOption(){
        raven.drawer.component.menu.data.MenuItem items[] = new raven.drawer.component.menu.data.MenuItem[]{
                new Item.Label("MAIN"),
                new Item("Dashboard", "dashicons-cart"),
                new Item("Settings", "dashicons-admin-generic"),
                new Item("Inventory", "dashicons-car")
        };
        SimpleMenuOption menuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FontIcon icon = new FontIcon();
                icon.setIconColor(new Color(208, 208, 208));
                icon.setIconSize(20);
                icon.setIkon(Dashicons.findByDescription(path));
                return icon;
            }
        };

        return menuOption
                .setMenus(items)
                .setMenuStyle(new SimpleMenuStyle() {
                    @Override
                    public void styleMenuPanel(JPanel panel, int[] index) {
                        panel.setOpaque(false);
                    }

                    @Override
                    public void styleMenuItem(JButton menu, int[] index) {
                        menu.setContentAreaFilled(false);
                    }
                })
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int[] ints) {
                        int oldIndex = index;
                        index = ints[0];
                        if (oldIndex != index) {
                            notifyIndexChange(index);
                        }
                    }
                });
    }

    private SimpleHeaderData getHeaderData(){
        FontIcon userIcon = FontIcon.of(Dashicons.ADMIN_USERS);
        userIcon.setIconSize(50);
        userIcon.setIconColor(Color.white);

        return new SimpleHeaderData()
                .setTitle(Login.currentUser)
                .setDescription("Welcome to Fatsby Car Retail");
    }

    public void addIndexChangeListener(IndexChangeListener listener) {
        listeners.add(listener);
    }

    public void removeIndexChangeListener(IndexChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyIndexChange(int newIndex) {
        for (IndexChangeListener listener : listeners) {
            listener.onIndexChanged(newIndex);
        }
    }

    private SimpleMenu simpleMenu;
    public static int index;
    private final List<IndexChangeListener> listeners = new ArrayList<>();


}
