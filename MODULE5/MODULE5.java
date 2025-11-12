import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Smart Home MVC Application
public class MODULE5 {

    // ---------------- Model ----------------
    static class Device {
        String name;
        boolean status;
        int level; // brightness or speed
        Device(String name, int level) { this.name=name; this.level=level; this.status=false; }
        void turnOn() { status=true; }
        void turnOff() { status=false; }
        void setLevel(int l) { level=l; }
    }

    static class HomeModel {
        Device light = new Device("Living Room Light", 50);
        Device fan = new Device("Ceiling Fan", 1);
        Device door = new Device("Main Door", 0); // 0=locked, 1=unlocked
    }

    // ---------------- View ----------------
    static class HomeView extends JFrame {
        JButton lightBtn, fanBtn, doorBtn;
        JSlider lightSlider, fanSlider;
        JLabel lightStatus, fanStatus, doorStatus;

        HomeView() {
            setTitle("Smart Home Automation");
            setSize(500,300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(3,1));

            // Light Panel
            JPanel lightPanel = new JPanel();
            lightPanel.setBorder(BorderFactory.createTitledBorder("Light Control"));
            lightBtn = new JButton("Turn ON/OFF");
            lightSlider = new JSlider(0,100,50);
            lightStatus = new JLabel("OFF");
            lightPanel.add(lightBtn);
            lightPanel.add(new JLabel("Brightness:"));
            lightPanel.add(lightSlider);
            lightPanel.add(lightStatus);

            // Fan Panel
            JPanel fanPanel = new JPanel();
            fanPanel.setBorder(BorderFactory.createTitledBorder("Fan Control"));
            fanBtn = new JButton("Turn ON/OFF");
            fanSlider = new JSlider(1,5,1);
            fanStatus = new JLabel("OFF");
            fanPanel.add(fanBtn);
            fanPanel.add(new JLabel("Speed:"));
            fanPanel.add(fanSlider);
            fanPanel.add(fanStatus);

            // Door Panel
            JPanel doorPanel = new JPanel();
            doorPanel.setBorder(BorderFactory.createTitledBorder("Door Control"));
            doorBtn = new JButton("Lock/Unlock");
            doorStatus = new JLabel("Locked");
            doorPanel.add(doorBtn);
            doorPanel.add(doorStatus);

            add(lightPanel);
            add(fanPanel);
            add(doorPanel);
        }
    }

    // ---------------- Controller ----------------
    static class HomeController {
        HomeModel model;
        HomeView view;

        HomeController(HomeModel model, HomeView view) {
            this.model = model;
            this.view = view;

            // Light button
            view.lightBtn.addActionListener(e -> {
                if(model.light.status) { model.light.turnOff(); } 
                else { model.light.turnOn(); }
                model.light.setLevel(view.lightSlider.getValue());
                view.lightStatus.setText(model.light.status ? "ON, Brightness: "+model.light.level : "OFF");
            });

            // Fan button
            view.fanBtn.addActionListener(e -> {
                if(model.fan.status) { model.fan.turnOff(); } 
                else { model.fan.turnOn(); }
                model.fan.setLevel(view.fanSlider.getValue());
                view.fanStatus.setText(model.fan.status ? "ON, Speed: "+model.fan.level : "OFF");
            });

            // Door button
            view.doorBtn.addActionListener(e -> {
                if(model.door.level==0) { model.door.level=1; view.doorStatus.setText("Unlocked"); } 
                else { model.door.level=0; view.doorStatus.setText("Locked"); }
            });
        }
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeModel model = new HomeModel();
            HomeView view = new HomeView();
            new HomeController(model, view);
            view.setVisible(true);
        });
    }
}
    

