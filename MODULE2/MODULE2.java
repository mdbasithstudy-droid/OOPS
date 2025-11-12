// File: SmartHomeUnitII.java
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

// Demonstrates UNIT II concepts applied to Smart Home
public class MODULE2 {

    // 1. Arrays & Strings
    public static void arrayAndStringDemo() {
        String[] devices = {"Living Room Light", "Kitchen Light", "Ceiling Fan"};
        System.out.println("Devices in your home:");
        for (String device : devices) {
            System.out.println(device);
        }

        String status = "ON";
        System.out.println("Device status: " + status + " (length: " + status.length() + ")");
    }

    // 2. Inheritance & Polymorphism
    static class Device {
        String name;

        Device(String name) { this.name = name; }

        void turnOn() { System.out.println(name + " turned ON"); }
        void turnOff() { System.out.println(name + " turned OFF"); }
    }

    static class Light extends Device {
        int brightness;
        Light(String name) { super(name); brightness = 50; }

        @Override
        void turnOn() {
            System.out.println(name + " ON at brightness " + brightness + "%");
        }

        void setBrightness(int level) {
            brightness = level;
            System.out.println(name + " brightness set to " + brightness + "%");
        }
    }

    static class Fan extends Device {
        int speed;
        Fan(String name) { super(name); speed = 1; }

        @Override
        void turnOn() {
            System.out.println(name + " ON at speed " + speed);
        }

        void setSpeed(int speed) {
            this.speed = speed;
            System.out.println(name + " speed set to " + speed);
        }
    }

    // 3. Abstract & Final
    abstract static class SecurityDevice {
        abstract void activate();
    }

    static class SmartDoor extends SecurityDevice {
        private boolean locked;

        @Override
        void activate() {
            locked = true;
            System.out.println("Door locked automatically");
        }

        void unlock() {
            locked = false;
            System.out.println("Door unlocked");
        }

        boolean isLocked() { return locked; }
    }

    final static class Constants {
        static final int MAX_BRIGHTNESS = 100;
        static final int MAX_FAN_SPEED = 5;
    }

    // 4. Exception Handling
    public static void exceptionDemo(Light light) {
        try {
            light.setBrightness(150); // Invalid brightness
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            throw new IOException("Simulated sensor failure");
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Exception handling completed");
        }
    }

    // 5. Object class & cloning
    static class User implements Cloneable {
        String name;
        User(String name) { this.name = name; }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() { return "User: " + name; }
    }

    // 6. Reflection
    public static void reflectionDemo() throws Exception {
        Class<?> cls = SmartDoor.class;
        Method[] methods = cls.getDeclaredMethods();
        System.out.println("Methods in SmartDoor class:");
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }

    // 7. Inner classes
    class HomeController {
        class Scheduler {
            void scheduleLight(Light light, int brightness) {
                System.out.println("Scheduled " + light.name + " to brightness " + brightness);
                light.setBrightness(brightness);
            }
        }
    }

    // 8. I/O Streams
    public static void ioStreamDemo() {
        String log = "Smart Home Log: All devices activated successfully.\n";
        try (FileOutputStream fos = new FileOutputStream("smarthome.log");
             FileInputStream fis = new FileInputStream("smarthome.log")) {

            fos.write(log.getBytes());

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            System.out.println("Log read from file: " + new String(buffer));
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

    // Main method demonstrating all concepts
    public static void main(String[] args) throws Exception {
        System.out.println("1. Arrays & Strings Demo:");
        arrayAndStringDemo();

        System.out.println("\n2. Inheritance & Polymorphism Demo:");
        Device d1 = new Light("Living Room Light");
        Device d2 = new Fan("Ceiling Fan");
        d1.turnOn();
        d2.turnOn();

        System.out.println("\n3. Abstract & Final Demo:");
        SmartDoor door = new SmartDoor();
        door.activate();
        door.unlock();
        System.out.println("Door locked? " + door.isLocked());
        System.out.println("Constants: Max Brightness = " + Constants.MAX_BRIGHTNESS);

        System.out.println("\n4. Exception Handling Demo:");
        Light light = new Light("Bedroom Light");
        exceptionDemo(light);

        System.out.println("\n5. Object Cloning Demo:");
        User u1 = new User("Mohamed Basith");
        User u2 = (User) u1.clone();
        System.out.println(u1 + " , Cloned: " + u2);

        System.out.println("\n6. Reflection Demo:");
        reflectionDemo();

        System.out.println("\n7. Inner Classes Demo:");
        MODULE2 outer = new MODULE2();
        HomeController.Scheduler scheduler = outer.new HomeController().new Scheduler();
        scheduler.scheduleLight(light, 75);

        System.out.println("\n8. I/O Streams Demo:");
        ioStreamDemo();
    }
}
