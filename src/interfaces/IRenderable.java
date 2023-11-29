package interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
    public int getZ(); // Ordering
    public void draw(GraphicsContext gc); // Draw
    public boolean isDestroyed(); // Check if exists

}
