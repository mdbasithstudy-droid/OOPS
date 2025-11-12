import java.util.*;
public class MODULE1 {


// 1. Abstract class (Abstraction)
abstract class SmartDevice {
    protected String deviceName;
    protected boolean status;

    public SmartDevice(String name) {
        this.deviceName = name;
        this.status = false;
    }

    // Abstract methods to be implemented by subclasses
    abstract void turnOn();
    abstract void turnOff();

    // Common method
    public void showStatus() {
        System.out.println(deviceName + " is " + (status ? "ON" : "OFF"));
    }
}

// 2. Light Control (Inheritance)
class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(String name) {
        super(name);
        this.brightness = 50;
    }

    @Override
    void turnOn() {
        status = true;
        System.out.println(deviceName + " turned ON with brightness " + brightness + "%");
    }

    @Override
    void turnOff() {
        status = false;
        System.out.println(deviceName + " turned OFF");
    }

    public void setBrightness(int level) {
        if (level >= 0 && level <= 100) {
            brightness = level;
            System.out.println(deviceName + " brightness set to " + brightness + "%");
        }
    }
}

// 3. Fan Control (Polymorphism)
class SmartFan extends SmartDevice {
    private int speed;

    public SmartFan(String name) {
        super(name);
        this.speed = 1;
    }

    @Override
    void turnOn() {
        status = true;
        System.out.println(deviceName + " turned ON at speed " + speed);
    }

    @Override
    void turnOff() {
        status = false;
        System.out.println(deviceName + " turned OFF");
    }

    public void setSpeed(int speed) {
        if (speed >= 1 && speed <= 5) {
            this.speed = speed;
            System.out.println(deviceName + " speed set to " + speed);
        }
    }
}

// 4. Security Module (Encapsulation)
class SmartDoor {
    private boolean locked;

    public SmartDoor() {
        locked = true;
    }

    public void lockDoor() {
        locked = true;
        System.out.println("Door is now LOCKED");
    }

    public void unlockDoor() {
        locked = false;
        System.out.println("Door is now UNLOCKED");
    }

    public boolean isLocked() {
        return locked;
    }
}

// 5. Home Controller (Composition + Methods & Messages)
class HomeController {
    private SmartLight light;
    private SmartFan fan;
    private SmartDoor door;

    public HomeController() {
        light = new SmartLight("Living Room Light");
        fan = new SmartFan("Ceiling Fan");
        door = new SmartDoor();
    }

    public void controlHome() {
        light.turnOn();
        light.setBrightness(80);

        fan.turnOn();
        fan.setSpeed(3);

        door.unlockDoor();

        System.out.println("\n--- Device Status ---");
        light.showStatus();
        fan.showStatus();
        System.out.println("Door Locked: " + door.isLocked());
    }
}

// 6. System Info (Static Members & Constructor)
class SystemInfo {
    static String systemVersion = "v1.0";
    String ownerName;

    public SystemInfo(String owner) {
        this.ownerName = owner;
    }

    public void showInfo() {
        System.out.println("Smart Home Automation System " + systemVersion);
        System.out.println("Owner: " + ownerName);
    }

    // finalize() demonstration
    protected void finalize() throws Throwable {
        System.out.println("System resources cleaned up!");
    }
}

public static void main(String[] args) {
    MODULE1 parentClass = new MODULE1();
    
    SystemInfo info = parentClass.new SystemInfo("Mohammed Basith");
    info.showInfo();

    HomeController controller = parentClass.new HomeController();
    controller.controlHome();

    // End of program
    info = null;
    System.gc(); // triggers finalize()
    }

}
