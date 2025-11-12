import java.util.*;
import java.util.concurrent.*;

// Base Device class
class Device {
    String name;
    Device(String name) { this.name = name; }
    void turnOn() { System.out.println(name + " ON"); }
    void turnOff() { System.out.println(name + " OFF"); }
}

// Derived classes
class Light extends Device { Light(String name) { super(name); } }
class Fan extends Device { Fan(String name) { super(name); } }

// Generic Device Manager
class DeviceManager<T extends Device> {
    private List<T> devices = new ArrayList<>();
    public void addDevice(T device) { devices.add(device); }
    public void turnAllOn() { for (T d : devices) d.turnOn(); }
    public void turnAllOff() { for (T d : devices) d.turnOff(); }
    public List<T> getDevices() { return devices; }
}

// Multi-threaded device control
class DeviceThread extends Thread {
    private Device device;
    private boolean running = true;
    DeviceThread(Device device) { this.device = device; }
    public void run() {
        while (running) {
            System.out.println(device.name + " status check...");
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { System.out.println(device.name + " interrupted"); running=false; }
        }
    }
    public void stopThread() { running = false; }
}

// Main Smart Home
public class MODULE3 {
    public static void main(String[] args) throws InterruptedException {
        // 1. Generics example
        DeviceManager<Device> manager = new DeviceManager<>();
        Light livingRoom = new Light("Living Room Light");
        Fan ceilingFan = new Fan("Ceiling Fan");
        manager.addDevice(livingRoom);
        manager.addDevice(ceilingFan);

        System.out.println("Turning all devices ON via generics manager:");
        manager.turnAllOn();

        // 2. Multi-threading example
        DeviceThread t1 = new DeviceThread(livingRoom);
        DeviceThread t2 = new DeviceThread(ceilingFan);

        t1.start(); t2.start();

        Thread.sleep(3000); // Let threads run for 3 seconds
        System.out.println("Stopping all device threads...");
        t1.stopThread(); t2.stopThread();

        t1.join(); t2.join();

        // 3. ExecutorService for multiple devices
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> livingRoom.turnOn());
        executor.submit(() -> ceilingFan.turnOn());
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Smart Home simulation completed!");
    }
}
