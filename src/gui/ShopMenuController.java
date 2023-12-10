package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import worldObject.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopMenuController implements Initializable {
    @FXML
    private Text healthPotion;
    @FXML
    private Text manaPotion;
    @FXML
    private Text strengthPotion;
    @FXML
    private Text defensePotion;
    @FXML
    private Text throwingKnife;
    @FXML
    private Text bomb;
    @FXML
    private Text exitButton;
    @FXML
    private Text money;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InitializeItems();
    }

    public void InitializeItems() {
        ArrayList<Text> items = new ArrayList<Text>();
        items.add(healthPotion);
        items.add(manaPotion);
        items.add(strengthPotion);
        items.add(defensePotion);
        items.add(throwingKnife);
        items.add(bomb);

        money.setText(Integer.toString(Player.getMoney()));

        for (Text item : items) {
            item.setOnMouseEntered(mouseEvent -> {
                item.setFill(Color.WHITE);
                RenderableHolder.hoverSound.play();
            });

            item.setOnMouseExited(event -> {
                item.setFill(Color.LIGHTGRAY);
            });
        }

        exitButton.setOnMouseEntered(event -> {
            exitButton.setFill(Color.WHITE);
            RenderableHolder.hoverSound.play();
        });

        // Mouse Clicked
        healthPotion.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            if (Player.getMoney() >= 5) {
                Player.setMoney(Player.getMoney() - 5);
                money.setText(Integer.toString(Player.getMoney()));
            } else RenderableHolder.declineSound.play();
        });

        manaPotion.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            if (Player.getMoney() >= 8) {
                Player.setMoney(Player.getMoney() - 8);
                money.setText(Integer.toString(Player.getMoney()));
            } else RenderableHolder.declineSound.play();
        });

        strengthPotion.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            if (Player.getMoney() >= 4) {
                Player.setMoney(Player.getMoney() - 4);
                money.setText(Integer.toString(Player.getMoney()));
            } else RenderableHolder.declineSound.play();
        });

        defensePotion.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            if (Player.getMoney() >= 4) {
                Player.setMoney(Player.getMoney() - 4);
                money.setText(Integer.toString(Player.getMoney()));
            } else RenderableHolder.declineSound.play();
        });

        throwingKnife.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            if (Player.getMoney() >= 7) {
                Player.setMoney(Player.getMoney() - 7);
                money.setText(Integer.toString(Player.getMoney()));
            } else RenderableHolder.declineSound.play();
        });

        defensePotion.setOnMouseClicked(event -> {
            RenderableHolder.confirmSound.play();
            if (Player.getMoney() >= 10) {
                Player.setMoney(Player.getMoney() - 10);
                money.setText(Integer.toString(Player.getMoney()));
            } else RenderableHolder.declineSound.play();
        });

        exitButton.setOnMouseClicked(event -> {
            RenderableHolder.declineSound.play();
            GameLogic.closeShop();
        });

    }
}
