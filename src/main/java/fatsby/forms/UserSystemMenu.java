package fatsby.forms;

import fatsby.login.Login;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import org.kordamp.ikonli.swing.FontIcon;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.SimpleMenu;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.drawer.component.menu.data.Item;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

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
                new Item("Dashboard")
        };
        return new SimpleMenuOption().
                setMenus(items)
                .setMenuStyle(new SimpleMenuStyle() {
                    @Override
                    public void styleMenuPanel(JPanel panel, int[] index) {
                        panel.setOpaque(false);
                    }

                    @Override
                    public void styleMenuItem(JButton menu, int[] index) {
                        menu.setContentAreaFilled(false);
                    }
                });
    }

    private SimpleHeaderData getHeaderData(){
        FontIcon userIcon = FontIcon.of(Dashicons.ADMIN_USERS);
        userIcon.setIconSize(50);
        userIcon.setIconColor(Color.white);

        return new SimpleHeaderData()
                .setTitle(Login.currentUser)
                .setDescription("Welcome to the Grand Sanctuary Hotel");
    }

    private SimpleMenu simpleMenu;
}
