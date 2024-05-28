package fatsby.forms.miscpanels;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.manager.Room;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.net.URL;

public class RoomPanel extends BlurChild {
    public RoomPanel(Room room){
        super(new Style()
                .setBlur(30)
                .setBorder(new StyleBorder(10)
                        .setOpacity(0.15f)
                        .setBorderWidth(1.2f)
                        .setBorderColor(new GradientColor(new Color(200,200,200), new Color(150,150,150), new Point2D.Float(0,0), new Point2D.Float(1f,0)))
                )
                .setOverlay(new StyleOverlay(new Color(0,0,0),0.2f))
        );
        init(room);
    }
    public void init(Room room){
        // Create a new JPanel to hold room details

        setLayout(new MigLayout("align center", "[center]", "[center]"));
        setPreferredSize(new Dimension(150,150));

        try {
            String imageURL = room.getImageURL();
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image originalImage = originalIcon.getImage();
            // Resize the image to 150x150 pixels
            Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            add(imageLabel, "wrap"); // Add the image label to the panel with a line break
        } catch (Exception e) {
            System.err.println("Error loading room image: " + e.getMessage());
        }

        // Create labels for room details
        JLabel roomNumber = new JLabel("Room Number: " + room.getRoomNumber());
        JLabel roomCapacity = new JLabel("Room Capacity: " + room.getCapacity());
        JLabel roomPrice = new JLabel("Price: $" + room.getPrice());

        //Changing style for book btn
        JButton bookBtn = new JButton("Book");
        bookBtn.putClientProperty(FlatClientProperties.STYLE, "background: #90EE90; foreground: #000000;");

        // Add labels to the panel
        add(roomNumber, "wrap");
        add(roomCapacity, "wrap");
        add(roomPrice, "wrap");
        add(bookBtn, "wrap, align center");
    }
}
