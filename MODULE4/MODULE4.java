import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.concurrent.*;

// Single class demonstrating Networking & JDBC for Smart Home
public class MODULE4 {

    // ---------------- TCP Socket Device ----------------
    static class Device implements Serializable {
        String name;
        boolean status;
        Device(String name) { this.name = name; this.status = false; }
        void turnOn() { status = true; System.out.println(name + " ON"); }
        void turnOff() { status = false; System.out.println(name + " OFF"); }
        @Override public String toString() { return name + " status: " + (status?"ON":"OFF"); }
    }

    // ---------------- Multi-threaded TCP Server ----------------
    static class SmartHubServer {
        private int port;
        private ExecutorService executor = Executors.newFixedThreadPool(5);
        SmartHubServer(int port) { this.port = port; }

        void startServer() throws IOException {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Smart Hub Server running on port " + port);

            while(true) {
                Socket client = serverSocket.accept();
                executor.submit(() -> handleClient(client));
            }
        }

        private void handleClient(Socket client) {
            try (ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {

                Device d = (Device) in.readObject();
                d.turnOn(); // example operation
                out.writeObject(d);

            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // ---------------- TCP Client ----------------
    static class SmartDeviceClient {
        static void sendDevice(Device d, String host, int port) throws IOException, ClassNotFoundException {
            Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(d);
            Device response = (Device) in.readObject();
            System.out.println("Server response: " + response);
            socket.close();
        }
    }

    // ---------------- UDP Example ----------------
    static void udpExample() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String msg = "Temperature sensor reading: 27C";
        InetAddress address = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), address, 7000);
        socket.send(packet);
        System.out.println("UDP message sent: " + msg);
        socket.close();
    }

    // ---------------- InetAddress & URL ----------------
    static void networkInfo() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local host: " + addr.getHostName() + " IP: " + addr.getHostAddress());
        URL url = new URL("https://www.example.com");
        System.out.println("URL Host: " + url.getHost() + " Protocol: " + url.getProtocol());
    }

    // ---------------- RMI Example ----------------
    public interface RemoteDevice extends Remote {
        void turnOn() throws RemoteException;
        void turnOff() throws RemoteException;
    }

    public static class RemoteDeviceImpl extends UnicastRemoteObject implements RemoteDevice {
        String name;
        RemoteDeviceImpl(String name) throws RemoteException { super(); this.name=name; }
        public void turnOn() { System.out.println(name + " turned ON remotely"); }
        public void turnOff() { System.out.println(name + " turned OFF remotely"); }
    }

    static void startRmiServer() throws Exception {
        RemoteDeviceImpl device = new RemoteDeviceImpl("RMI Light");
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/RemoteDevice", device);
        System.out.println("RMI Server ready...");
    }

    static void startRmiClient() throws Exception {
        RemoteDevice device = (RemoteDevice) Naming.lookup("rmi://localhost/RemoteDevice");
        device.turnOn();
        device.turnOff();
    }

    // ---------------- JDBC Example ----------------
    static void jdbcDemo() {
        String url = "jdbc:mysql://localhost:3306/smarthome";
        String user = "root";
        String pass = "password";
        try (Connection conn = DriverManager.getConnection(url,user,pass)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DeviceLog (id INT AUTO_INCREMENT PRIMARY KEY, deviceName VARCHAR(50), status VARCHAR(10))");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO DeviceLog(deviceName,status) VALUES (?,?)");
            ps.setString(1, "Living Room Light");
            ps.setString(2, "ON");
            ps.executeUpdate();

            ResultSet rs = stmt.executeQuery("SELECT * FROM DeviceLog");
            while(rs.next()) System.out.println(rs.getInt("id")+" "+rs.getString("deviceName")+" "+rs.getString("status"));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) throws Exception {
        // Network info
        networkInfo();

        // TCP server in separate thread
        SmartHubServer server = new SmartHubServer(5000);
        new Thread(() -> {
            try { server.startServer(); } catch(IOException e) { e.printStackTrace(); }
        }).start();

        Thread.sleep(1000); // wait for server to start

        // TCP client
        Device d1 = new Device("Living Room Light");
        SmartDeviceClient.sendDevice(d1, "localhost", 5000);

        // UDP example
        udpExample();

        // RMI
        startRmiServer();
        startRmiClient();

        // JDBC
        jdbcDemo();
    }
}
