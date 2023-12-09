package gui.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import sharedObject.RenderableHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class CreditMenuController implements Initializable {
    @FXML
    private ImageView darkWojak;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        darkWojak.setImage(RenderableHolder.darkWojak.getImage());
    }
}
