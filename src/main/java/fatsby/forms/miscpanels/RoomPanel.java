package fatsby.forms.miscpanels;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.forms.DashboardPane;
import fatsby.manager.Car;
import fatsby.manager.Store;
import fatsby.manager.User;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;

public class RoomPanel extends BlurChild {
    private User user = User.getInstance();
    private Store store = Store.getInstance();

    public RoomPanel(Car car, boolean isInventory){
        super(new Style()
                .setBlur(30)
                .setBorder(new StyleBorder(10)
                        .setOpacity(0.15f)
                        .setBorderWidth(1.2f)
                        .setBorderColor(new GradientColor(new Color(200,200,200), new Color(150,150,150), new Point2D.Float(0,0), new Point2D.Float(1f,0)))
                )
                .setOverlay(new StyleOverlay(new Color(0,0,0),0.2f))
        );
        init(car, isInventory);
    }
    public void init(Car car, boolean isInventory){
        // Create a new JPanel to hold room details

        setLayout(new MigLayout("align center", "[center]", "[center]"));
        setPreferredSize(new Dimension(150,150));

        try {
            String imageURL = car.getImageURL();
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image originalImage = originalIcon.getImage();
            // Resize the image to 150x150 pixels
            Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);
            add(imageLabel, "wrap"); // Add the image label to the panel with a line break
        } catch (Exception e) {
            System.err.println("Error loading car image: " + e.getMessage());
        }

        // Create labels for room details
        JLabel roomNumber = new JLabel(car.getCarName());
        JLabel roomCapacity = new JLabel("Capacity: " + car.getCapacity());
        JLabel roomPrice = new JLabel("Price: $" + car.getPrice());

        //Changing style for book btn
        JButton bookBtn = new JButton("View Info");
        bookBtn.putClientProperty(FlatClientProperties.STYLE, "background: #90EE90; foreground: #000000;");
        bookBtn.setFocusPainted(false);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.putClientProperty(FlatClientProperties.STYLE, "background: #FF474C; foreground: #000000;");
        deleteBtn.setFocusPainted(false);

        // Add labels to the panel
        add(roomNumber, "wrap");
        add(roomCapacity, "wrap");
        add(roomPrice, "wrap");
        if (!isInventory){
            if (user.isStaff()){
                add(deleteBtn, "wrap, align center");
            } else {
                add(bookBtn, "wrap, align center");
            }
        }

        bookBtn.addActionListener(e -> {
            CarInfoDialog dialog = new CarInfoDialog(car);
            dialog.setVisible(true);
        });

        deleteBtn.addActionListener(e ->{
            String filePath = "C:\\FatsbyCarRetailer\\database\\cars\\" + car.getCarName()+".dat";
            String imgPath = "C:\\FatsbyCarRetailer\\resources\\images\\cars\\" + car.getCarName()+".jpg";
            System.out.println(filePath);
            File imgToDelete = new File(imgPath);
            File fileToDelete = new File(filePath);
            if (fileToDelete.delete() && imgToDelete.delete()) {
//                DashboardPane.cars.remove(car); //OLD CODE
                store.removeCar(car);
                DashboardPane.refreshDashboardPane();
                System.out.println("Deleted " + car.getCarName());
            } else {
                System.err.println("Failed to delete the car file.");
            }
        });
    }
}
