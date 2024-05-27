package fatsby.forms.adminforms;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminSettingsPane extends BlurChild {
    public AdminSettingsPane() {
        init();
    }
    private void init(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        testLabel = new JLabel("Admin Settings");
        addRoomButton = new JButton("Add");
        JDesktopPane1 = new JDesktopPane();

        add(testLabel);
        add(addRoomButton);
        add(JDesktopPane1);

        addRoomButton.addActionListener(e -> {
            JInternalFrame internalFrame = new JInternalFrame("Add Room", true, true, true, true);
            internalFrame.setBounds(50, 50, 300, 200);
            JDesktopPane1.add(internalFrame);
        });
    }
    private JLabel testLabel;
    private JButton addRoomButton;
    private JDesktopPane JDesktopPane1;
}
