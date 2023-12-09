package font;

import javafx.scene.text.Font;

public class FontManager {
    private static Font dialogue;
    private static Font title;
    private String path;

    public static Font getDialogue(double size) {
        String path = ClassLoader.getSystemResource("fonts/dialogue.ttf").toString();
        return Font.loadFont(path, size);
    }
}
