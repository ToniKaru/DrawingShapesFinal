package se.iths.javaprog.toni.drawingshapes.drawingio;

import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.Model;
import se.iths.javaprog.toni.drawingshapes.shapes.ChosenShape;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Circle;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Square;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SvgIO {

    private static final Pattern pattern = Pattern.compile("\"");


    public static void saveToFile(List<Shape> shapes,Path path, double height, double width){
        List<String> strings = new ArrayList<>();
        strings.add("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" height=\""+ height+"\" width=\""+ width+"\">\n");
        shapes.forEach(shape -> svgRow(shape, strings));
        strings.add("\n</svg> ");
        try{
            Files.write(path, strings);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String svgRowString(Shape shape){
        List<String> strings = new ArrayList<>();
        svgRow(shape, strings);
        return String.join("",strings);
    }

    public static void svgRow (Shape shape, List<String> strings) {
        if (shape instanceof Circle)
            circleSvgRow((Circle) shape, strings);
        else if (shape instanceof Square)
            squareSvgRow((Square) shape, strings);
    }

    private static void squareSvgRow(Square s, List<String> strings) {
        strings.add("<rect x=\"" + s.getX() +
                "\" y=\"" + s.getY() +
                "\" width=\"" + s.getScaledSide() +
                "\" height=\"" + s.getScaledSide() +
                "\" fill=\"#" + s.getColor().toString().substring(2) +
                "\" />");
    }

    private static void circleSvgRow(Circle c, List<String> strings) {
        strings.add("<circle cx=\"" + c.getX() +
                "\" cy=\"" + c.getY() +
                "\" r=\"" + c.getScaledRadius() +
                "\" fill=\"#" + c.getColor().toString().substring(2) +
                "\" />");
    }

    public static Shape createShapeFromString(String string) {
        String[] arr = pattern.split(string);
        String shapeType = arr[0].substring(1, arr[0].length()-4);
        double x = Double.parseDouble(arr[1]);
        double y = Double.parseDouble(arr[3]);
        double size;
        Color color;
        ChosenShape type;

        if ("circle".equals(shapeType)){
            type = ChosenShape.CIRCLE;
            size =Double.parseDouble(arr[5]) * 2;
            color = Color.web(arr[7]);
        }
        else if ("rec".equals(shapeType)) {
            type = ChosenShape.SQUARE;
            size =Double.parseDouble(arr[5]);
            color = Color.web(arr[9]);
        }
        else {
            System.out.println("Shape: " + shapeType + " did not read correctly");
            throw new IllegalArgumentException();
        }

        return Model.createShape(type, x, y, color, size);
    }
}
