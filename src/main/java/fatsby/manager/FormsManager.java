package fatsby.manager;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import fatsby.main.Application;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class FormsManager {
    private Application application;
    private static FormsManager instance;
    private JDesktopPane desktopPane;

    private FormsManager() {}
    public void initApplication(Application application) {
        this.application = application;
    }
    public static FormsManager getInstance() {
        if (instance == null) {
            instance = new FormsManager();
        }
        return instance;
    }
    public void showForm(JComponent form) {
        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            application.setContentPane(form);
            application.revalidate();
            application.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }

    public static FontIcon initIcon(Ikon ikonURL, int size, Color color){
        FontIcon icon = FontIcon.of(ikonURL, size, color);
        icon.setIconSize(size);
        icon.setIconColor(color);
        return icon;
    }

}
