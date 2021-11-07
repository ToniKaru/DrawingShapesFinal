package se.iths.javaprog.toni.drawingshapes.drawingio;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Network {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    public BooleanProperty connected = new SimpleBooleanProperty();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    ObservableList<Shape> shapes;

    public Network (ObservableList<Shape> shapes){
        this.shapes = shapes;

    }

    public void connect(){
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
            executorService2.submit(()->{
                String svgShape = SvgIO.svgRowString(shape);
                writer.println(svgShape);
            });
        }
    }

    private void incomingHandler() {
        try {
            while (true) {
                String line = reader.readLine();
                System.out.println(line);
                String[] start = line.split(" ",2);
                System.out.println("start[1]: " +start[1]);

                if ("<".equals(start[1].substring(0,1))){
                    Shape shape = SvgIO.createShapeFromString(start[1]);
                    Platform.runLater(()-> shapes.add(shape));
                }
            }
        } catch (IOException e) {
            System.out.println("I/O error. Disconnected.");
            Platform.runLater(() -> connected.set(false));
        }
    }
}
