package interfaces;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;


public interface IRenderable {
    public int getZ(); // Ordering
    public void draw(GraphicsContext gc); // Draw

    public boolean isDestroyed();// Check if exists
    public String getName();
    public void setName(String name);

    boolean isCollision();

}
