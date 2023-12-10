package gui;

import items.BaseItem;
import items.HealthPotion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import worldObject.Player;

import javax.lang.model.element.Name;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InventoryMenuController implements Initializable {
    @FXML
    private Text healthPotion;
    @FXML
    private Text hPotionAmount;
    @FXML
    private Text manaPotion;
    @FXML
    private Text mPotionAmount;
    @FXML
    private Text strengthPotion;
    @FXML
    private Text sPotionAmount;
    @FXML
    private Text defensePotion;
    @FXML
    private Text dPotionAmount;
    @FXML
    private Text throwingKnife;
    @FXML
    private Text knifeAmount;
    @FXML
    private Text bomb;
    @FXML
    private Text bombAmount;
    @FXML
    private Text exitButton;
    private HashMap<Text, Text> itemLists = new HashMap<Text, Text>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeItemAmount();
        initializeItemList();
        //    initializeEvent();

        exitButton.setOnMouseClicked(event -> {
            RenderableHolder.declineSound.play();
            GameLogic.closeInventory();
        });

        exitButton.setOnMouseEntered(event -> {
            RenderableHolder.hoverSound.play();
            exitButton.setFill(Color.WHITE);
        });

        exitButton.setOnMouseExited(event -> {
            exitButton.setFill(Color.LIGHTGRAY);
        });

    }

    public void initializeItemAmount() {
        hPotionAmount.setText(Integer.toString(Player.getPlayerInventory().get("Health potion")));
        mPotionAmount.setText(Integer.toString(Player.getPlayerInventory().get("Mana potion")));
        sPotionAmount.setText(Integer.toString(Player.getPlayerInventory().get("Strength potion")));
        dPotionAmount.setText(Integer.toString(Player.getPlayerInventory().get("Defense potion")));
        knifeAmount.setText(Integer.toString(Player.getPlayerInventory().get("Throwing knife")));
        bombAmount.setText(Integer.toString(Player.getPlayerInventory().get("Bomb")));
    }

    public void initializeItemList() {
        itemLists.put(healthPotion, hPotionAmount);
        itemLists.put(manaPotion, mPotionAmount);
        itemLists.put(strengthPotion, sPotionAmount);
        itemLists.put(defensePotion, dPotionAmount);
        itemLists.put(throwingKnife, knifeAmount);
        itemLists.put(bomb, bombAmount);
        for (Map.Entry<Text, Text> entry : itemLists.entrySet()) {
            Text name = entry.getKey();
            Text amount = entry.getValue();
            if (amount.getText().equals("0")) {
                name.setFill(Color.LIGHTGRAY);
                amount.setFill(Color.LIGHTGRAY);
            } else {
                name.setFill(Color.WHITE);
                amount.setFill(Color.WHITE);
            }
        }
    }

}
