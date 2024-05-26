package fatsby.manager;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import fatsby.main.Application;

import javax.swing.*;
import java.awt.*;

public class FormsManager {
    private Application application;
    private static FormsManager instance;

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

}
