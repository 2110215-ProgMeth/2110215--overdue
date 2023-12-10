package gui;

import display.ScreenUtil;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import font.FontManager;
import sharedObject.RenderableHolder;

public class MainMenuButton extends StackPane {
    private Text text;
    private Rectangle background;

    public MainMenuButton(String text) {
       /* Stop[] stops = new Stop[] { new Stop(0, Color.DARKGRAY), new Stop(0.1, Color.BLACK),
                new Stop(0.9, Color.BLACK), new Stop(1, Color.DARKGRAY) };

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);*/

       /* background = new Rectangle(ScreenUtil.screenWidth / 5, ScreenUtil.screenHeight / 3
                                , ScreenUtil.screenWidth / 4, ScreenUtil.screenHeight / 12);*/ // 225 55

/*        background.setOpacity(0.4);*/

        this.text = new Text(text);
        this.text.setFill(Color.LIGHTGRAY);

        this.text.setFont(FontManager.getDialogue(8 * ScreenUtil.scale));
        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(/*background,*/ this.text);

        this.setOnMouseEntered(e -> {
         /*   background.setFill(gradient);*/
            this.text.setFill(Color.WHITE);
            RenderableHolder.hoverSound.play();
        });

        this.setOnMouseExited(e -> {
           /* background.setFill(Color.BLACK);*/
            this.text.setFill(Color.LIGHTGRAY);
        });

        this.setOnMousePressed(event -> {
   /*         background.setFill(Color.DARKVIOLET);*/
            RenderableHolder.confirmSound.play();
        });

        this.setOnMouseReleased(event -> {
           /* background.setFill(gradient);*/
        });

    }

}