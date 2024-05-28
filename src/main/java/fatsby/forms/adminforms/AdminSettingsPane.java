package fatsby.forms.adminforms;

import fatsby.manager.FormsManager;
import fatsby.manager.Room;
import fatsby.manager.Serializer;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class AdminSettingsPane extends BlurChild {
    public AdminSettingsPane() {
        init();
    }
    private void init() {
        setLayout(new BorderLayout()); // Changed to BorderLayout for better layout management

        testLabel = new JLabel("Add New Room", SwingConstants.CENTER);
        addRoomButton = new JButton("Add");

        JDesktopPane1 = new JDesktopPane();
        JDesktopPane1.setOpaque(false); // Changed to true for better visibility during debugging
        JDesktopPane1.setVisible(true);
        JDesktopPane1.setPreferredSize(new Dimension(500, 400)); // Set preferred size for visibility

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(testLabel);
        topPanel.add(addRoomButton);

        add(topPanel, BorderLayout.NORTH);
        add(JDesktopPane1, BorderLayout.CENTER);


        //Add Room Panel
        addRoomButton.addActionListener(e -> {
            System.out.println("Add Room Button Pressed");

            JInternalFrame internalFrame = new JInternalFrame("Add Room", true, true, true, true);
            internalFrame.setSize(500, 500); // Adjust the size as needed
            internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            internalFrame.setLayout(new MigLayout());
            JLabel label = new JLabel("Enter Room Number");
            label.setIcon(FormsManager.initIcon(Dashicons.CLIPBOARD, 16, Color.WHITE));
            internalFrame.add(label, "wrap");
            JTextField txtRoomNumber = new JTextField();
            txtRoomNumber.setPreferredSize(new Dimension(internalFrame.getSize().width, 10));
            internalFrame.add(txtRoomNumber, "wrap");
            JLabel capacityLbl = new JLabel("Enter Room Capacity");
            capacityLbl.setIcon(FormsManager.initIcon(Dashicons.GROUPS, 16, Color.WHITE));
            internalFrame.add(capacityLbl, "wrap");
            JTextField txtCapacity = new JTextField();
            txtCapacity.setPreferredSize(new Dimension(internalFrame.getSize().width, 10));
            internalFrame.add(txtCapacity, "wrap");
            JLabel money = new JLabel("Enter Money per Hour");
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
            } catch (java.beans.PropertyVetoException ex) {
                ex.printStackTrace();
            }

            JDesktopPane1.revalidate();
            JDesktopPane1.repaint();

            //Add Button
            addBtn.addActionListener(c ->{
                String roomNumber = txtRoomNumber.getText();
                String descString = txtDescription.getText();
                Serializer.FileCopy(dir, roomNumber);
                String imageURL = "src/main/resources/fatsby/images/rooms/"+roomNumber+".jpg";
                int capacity, price;
                try{
                    capacity = Integer.parseInt(txtCapacity.getText());
                    price = Integer.parseInt(txtMoney.getText());
                    Room room = new Room(roomNumber, capacity, price, descString, imageURL);
                    Serializer.serializeObject(room, "src/main/java/fatsby/database/rooms", roomNumber+".dat");
                    JOptionPane.showMessageDialog(null, "Room Added Successfully!");
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
    private JButton addRoomButton;
    private JDesktopPane JDesktopPane1;
    private String dir;
}
