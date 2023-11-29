package input;

import java.util.HashSet;

import display.GamePanel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class InputUtils {
    private static HashSet<KeyCode> pressedKeys;
    private static HashSet<MouseButton> pressedMouse;

    static {
        pressedKeys = new HashSet<>();
        pressedMouse = new HashSet<>();
    }

    public static void registerInput(GamePanel gamePanel) {
        gamePanel.setOnKeyPressed(event -> {
            pressedKeys.add((event.getCode()));
        });

        gamePanel.setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });

        gamePanel.setOnMousePressed(event -> {
            pressedMouse.add(event.getButton());
        });

        gamePanel.setOnMouseReleased(event -> {
            pressedMouse.remove(event.getButton());
        });

    }

    public static boolean isKeypressed(KeyCode key) {
        return pressedKeys.contains(key);
    }

    public static boolean isMousePressed(MouseButton button) {
        return pressedMouse.contains(button);
    }

    public static HashSet<KeyCode> getPressedKeys(GamePanel gameScreen) {
        gameScreen.setOnKeyPressed(event -> {
            pressedKeys.add((event.getCode()));
        });

        gameScreen.setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });
        return pressedKeys;
    }

}
