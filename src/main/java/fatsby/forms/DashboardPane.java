package fatsby.forms;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class DashboardPane extends BlurChild {
    public DashboardPane() {
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        testLabel = new JLabel("Dashboard");
        add(testLabel);
    }
    private JLabel testLabel;
}
