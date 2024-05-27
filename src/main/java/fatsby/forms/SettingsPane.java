package fatsby.forms;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;

public class SettingsPane extends BlurChild {
    public SettingsPane() {
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        testLabel = new JLabel("Settings");
        add(testLabel);
    }
    private JLabel testLabel;
}
