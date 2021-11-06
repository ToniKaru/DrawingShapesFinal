package se.iths.javaprog.toni.drawingshapes.svgIO;

import se.iths.javaprog.toni.drawingshapes.Model;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Circle;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Square;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SvgIO {

    private static final String HOMEPATH = System.getProperty("user.home");

    public static void saveToFile(List<Shape> shapes,Path path, double height, double width){

        File file = new File(String.valueOf(path));
        List<String> strings = new ArrayList<>();
        strings.add("!DOCTYPE html");
        strings.add("<svg height=\""+ height+"\" width=\""+ width+"\">\n");
        shapes.forEach(shape -> svgRow(shape, strings));
        strings.add("\n</svg> ");
        try{
            Files.write(path, strings);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void saveToFile(String text, Path path, double height, double width){

        List<String> strings = new ArrayList<>();
        strings.add("<svg height=\""+ height+"\" width=\""+ width+"\">\n");
        strings.add(text);
        strings.add("\n</svg> ");
        try {
            Files.write(path, strings);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    static void svgRow (Shape shape, List<String> strings) {
        if (shape instanceof Circle)
            circleSvgRow((Circle) shape, strings);
        else if (shape instanceof Square)
            squareSvgRow((Square) shape, strings);
        else
            throw new IllegalArgumentException();


    }

    private static void squareSvgRow(Square s, List<String> strings) {
        strings.add("<rect x=\"" + s.getX() + "\" y=\"" + s.getY() +
                "\" width=\"" + s.getScaledSide() + "\" height=\"" + s.getScaledSide() +
                "\" fill=\"#" + s.getColor().toString().substring(2) + "\" />\n");
    }

    private static void circleSvgRow(Circle c, List<String> strings) {
        strings.add("<circle cx=\"" + c.getX() + "\" cy=\"" + c.getY() +
                "\" r=\"" + c.getScaledRadius() + "\" fill=\"#" + c.getColor().toString().substring(2) + "\" />\n");
    }
}
