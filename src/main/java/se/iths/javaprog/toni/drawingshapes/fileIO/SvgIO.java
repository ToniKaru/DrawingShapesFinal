package se.iths.javaprog.toni.drawingshapes.fileIO;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Circle;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Square;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SvgIO {

//    public static Collection<Shape> getShapesFromFile(Path path){
//        if(!Files.exists(path)){
//            System.out.println("File not found");
//            return List.of();
//        }
//        Collection<Shape> shapes = new ArrayList<>();
//        try (Stream<String> lines = Files.lines(path)) {
//            shapes = lines
//                    .map(s -> s.createShape)
//                    .collect(Collectors.toList());
//        } catch (IOException e){
//            System.out.println("Could not load shapes from file.");
//        }
//        return shapes;
//    }

    public static void saveToFile(List<Shape> shapes,Path path, double height, double width){
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

    static void svgRow (Shape shape, List<String> strings) {
        if (shape instanceof Circle)
            circleSvgRow((Circle) shape, strings);
        else if (shape instanceof Square)
            squareSvgRow((Square) shape, strings);
        else
            return;
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
