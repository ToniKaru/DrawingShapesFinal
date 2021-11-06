package se.iths.javaprog.toni.drawingshapes.command.commands;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

public class Resize implements Command {

    private Shape shape;
    private double oldScale;
    private double newScale;


    public Resize(Shape shape, double newScale) {
        this.shape = shape;
        this.oldScale = shape.getScale();
        this.newScale = newScale;
        System.out.println("oldScale: " + oldScale);
        System.out.println("newScale: " + newScale);
    }

    @Override
    public void execute() {
        shape.setScale(newScale);


    }

    @Override
    public void unexecute() {
        shape.setScale(oldScale);

    }
}
