package se.iths.javaprog.toni.drawingshapes.drawingio;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Network {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    public BooleanProperty connected = new SimpleBooleanProperty();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    private ObservableList<Shape> shapes;
    private static final String START_SVG_TAG = "<";


    public Network(ObservableList<Shape> shapes) {
        this.shapes = shapes;

    }

    public void connect() {
        if (connected.get())
            return;
        try {
            socket = new Socket("178.174.162.51", 8000);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            connected.set(true);
            System.out.println("Connected to server");

            executorService.submit(this::incomingHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(Shape shape) {
        if (connected.get()) {
            executorService2.submit(() -> {
                String svgShape = SvgIO.svgRowString(shape);
                writer.println(svgShape);
            });
        }
    }

    private void incomingHandler() {
        try {
            while (true) {
                getShapes();
            }
        } catch (IOException e) {
            System.out.println("I/O error. Disconnected.");
            Platform.runLater(() -> connected.set(false));
        }
    }

    private void getShapes() throws IOException {
        String line = reader.readLine();
        String incoming = getData(line);

        if (START_SVG_TAG.equals(incoming.substring(0, 1))) {
            Platform.runLater(() ->
                    shapes.add(SvgIO.createShapeFromString(incoming)));
        }
    }

    private String getData(String line) {
        String[] arr = line.split(" ", 2);
        return arr[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return Objects.equals(socket, network.socket) && Objects.equals(writer, network.writer) && Objects.equals(reader, network.reader) && Objects.equals(connected, network.connected) && Objects.equals(executorService, network.executorService) && Objects.equals(executorService2, network.executorService2) && Objects.equals(shapes, network.shapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, writer, reader, connected, executorService, executorService2, shapes);
    }

    @Override
    public String toString() {
        return "Network{" +
                "socket=" + socket +
                ", writer=" + writer +
                ", reader=" + reader +
                ", connected=" + connected +
                ", executorService=" + executorService +
                ", executorService2=" + executorService2 +
                ", shapes=" + shapes +
                '}';
    }

    public void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Could not disconnect.");
                e.printStackTrace();
            }
        }
    }
}
