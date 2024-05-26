package fatsby.forms;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;

public class UserMenu extends BlurBackground {
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
    }
    private UserSystemMenu userSystemMenu;
}
