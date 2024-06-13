package fatsby.forms.adminforms;

import fatsby.forms.DashboardPane;
import fatsby.manager.Car;
import fatsby.manager.FormsManager;
import fatsby.manager.Serializer;
import fatsby.manager.Store;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.File;

public class AdminSettingsPane extends BlurChild {
    private static Store store = Store.getInstance();

    public AdminSettingsPane() {
        init();
    }
    private void init() {
        setLayout(new BorderLayout()); // Changed to BorderLayout for better layout management

        testLabel = new JLabel("Add New Car", SwingConstants.CENTER);
        addCarBTN = new JButton("Add");

        JDesktopPane1 = new JDesktopPane();
        JDesktopPane1.setOpaque(false); // Changed to true for better visibility during debugging
        JDesktopPane1.setVisible(true);
        JDesktopPane1.setPreferredSize(new Dimension(500, 400)); // Set preferred size for visibility

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(testLabel);
        topPanel.add(addCarBTN);

        add(topPanel, BorderLayout.NORTH);
        add(JDesktopPane1, BorderLayout.CENTER);


        //Add Car Panel
        addCarBTN.addActionListener(e -> {
            System.out.println("Add Car Button Pressed");

            JInternalFrame internalFrame = new JInternalFrame("Add Car", true, true, true, true);
            internalFrame.setSize(500, 500); // Adjust the size as needed
            internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            internalFrame.setLayout(new MigLayout());
            JLabel label = new JLabel("Enter Car Name");
            label.setIcon(FormsManager.initIcon(Dashicons.CLIPBOARD, 16, Color.WHITE));
            internalFrame.add(label, "wrap");
            JTextField txtCarName = new JTextField();
            txtCarName.setPreferredSize(new Dimension(internalFrame.getSize().width, 10));
            internalFrame.add(txtCarName, "wrap");
            JLabel capacityLbl = new JLabel("Enter Car Capacity");
            capacityLbl.setIcon(FormsManager.initIcon(Dashicons.GROUPS, 16, Color.WHITE));
            internalFrame.add(capacityLbl, "wrap");
            JTextField txtCapacity = new JTextField();
            txtCapacity.setPreferredSize(new Dimension(internalFrame.getSize().width, 10));
            internalFrame.add(txtCapacity, "wrap");
            JLabel money = new JLabel("Enter Price");
            money.setIcon(FormsManager.initIcon(Dashicons.MONEY_ALT, 16, Color.WHITE));
            internalFrame.add(money, "wrap");
            JTextField txtMoney = new JTextField();
            txtMoney.setPreferredSize(new Dimension(internalFrame.getSize().width, 10));
            internalFrame.add(txtMoney, "wrap");
            JLabel description = new JLabel("Enter Description");
            description.setIcon(FormsManager.initIcon(Dashicons.INFO, 16, Color.WHITE));
            internalFrame.add(description, "wrap");
            JTextField txtDescription = new JTextField();
            txtDescription.setPreferredSize(new Dimension(internalFrame.getSize().width, 50));
            internalFrame.add(txtDescription, "wrap");
            JLabel addImg = new JLabel("Add Image");
            addImg.setIcon(FormsManager.initIcon(Dashicons.FORMAT_IMAGE, 16, Color.WHITE));
            internalFrame.add(addImg, "wrap");
            internalFrame.add(createFileChooser(), "wrap");
            JButton addBtn = new JButton("Add");
            internalFrame.add(addBtn, "center, gapy 10");

            JDesktopPane1.add(internalFrame);
            internalFrame.setLocation((JDesktopPane1.getWidth() - internalFrame.getWidth()) / 2,
                    (JDesktopPane1.getHeight() - internalFrame.getHeight()) / 2);

            internalFrame.setVisible(true);

            try {
                internalFrame.setSelected(true);
            } catch (PropertyVetoException ex) {
                ex.printStackTrace();
            }

            JDesktopPane1.revalidate();
            JDesktopPane1.repaint();

            //Add Button
            addBtn.addActionListener(c ->{
                String carName = txtCarName.getText();
                String descString = txtDescription.getText();
                Serializer.FileCopy(dir, carName);
                String imageURL = "C:\\FatsbyCarRetailer\\resources\\images\\cars\\"+ carName +".jpg";
                int capacity, price;
                try{
                    capacity = Integer.parseInt(txtCapacity.getText());
                    price = Integer.parseInt(txtMoney.getText());
                    Car car = new Car(carName, capacity, price, descString, imageURL);
                    Serializer.serializeObject(car, "C:\\FatsbyCarRetailer\\database\\cars", carName +".dat");
//                    DashboardPane.cars.add(car); // OLD CODE
                    store.addCar(car);
                    DashboardPane.refreshDashboardPane();
                    JOptionPane.showMessageDialog(null, "Car Added Successfully!");
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Must enter a number for capacity and price", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });

        this.revalidate();
        this.repaint();
    }

    private Component createFileChooser(){
        JPanel panel = new JPanel(new MigLayout());
        JLabel fileSelected = new JLabel("No File Selected");
        JButton button = new JButton("Choose Image");
        button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setDialogTitle("Choose Image");
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .jpg/.jpeg files", "jpg", "jpeg");
            chooser.addChoosableFileFilter(restrict);
           int response = chooser.showOpenDialog(null);
           if (response == JFileChooser.APPROVE_OPTION) {
               File file = new File(chooser.getSelectedFile().getAbsolutePath());
               dir = chooser.getSelectedFile().getAbsolutePath();
               fileSelected.setText(chooser.getSelectedFile().getAbsolutePath());
           }
        });
        panel.add(fileSelected, "wrap");
        panel.add(button);
        return panel;
    }

    private JLabel testLabel;
    private JButton addCarBTN;
    private JDesktopPane JDesktopPane1;
    private String dir;
}
