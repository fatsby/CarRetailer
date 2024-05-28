package fatsby.forms;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.forms.miscpanels.RoomPanel;
import fatsby.manager.Room;
import fatsby.manager.Serializer;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardPane extends BlurChild {
    public DashboardPane() {
        //Add function to loop through every room files and add it to a list
        rooms = new ArrayList<Room>();
        rooms = Serializer.deserializeRooms("src/main/java/fatsby/database/rooms");
        System.out.println(rooms);
        init();
    }
    private void init(){
        setLayout(new MigLayout("wrap 3, insets 20", "[center]", "[center]"));
        //Scroll pane settings
//        setBorder(BorderFactory.createEmptyBorder());
//        setOpaque(false);
//        getViewport().setOpaque(false);
//        getVerticalScrollBar().setOpaque(false);
//        getVerticalScrollBar().setUnitIncrement(10);
//        getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,"" +
//                "trackArc:999;" +
//                "width:5;"+
//                "thumbInsets:0,0,0,0");


        JLabel availRoomLabel = new JLabel("Available rooms:");
        availRoomLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +10");
        add(availRoomLabel, "growx, wrap");
        for (Room room : rooms) {
            RoomPanel roomPanel = new RoomPanel(room);
            add(roomPanel);
        }
    }
    private JLabel testLabel;
    private List<Room> rooms;
}
