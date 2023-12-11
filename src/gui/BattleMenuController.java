package gui;

import battleEntity.battleUnit.BaseUnit;
import battleEntity.combatMove.AttackMove;
import battleEntity.combatMove.BaseMove;
import battleEntity.combatMove.SupportMove;
import battleEntity.monster.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import worldObject.Entity;
import worldObject.Player;


import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {

    @FXML
    private HBox battlePane;
   /* @FXML
    private ImageView monster1;
    @FXML
    private ImageView monster2;
    @FXML
    private ImageView monster3;*/
    @FXML
    private  ImageView warrior;
    @FXML
    private  ImageView assassin;
    @FXML
    private  ImageView blackmage;
    @FXML
    private  ImageView whitemage;
    @FXML
    private Text move1Text;
    @FXML
    private Text move2Text;
    @FXML
    private Text move3Text;
    @FXML
    private Text move4Text;
    @FXML
    private Text run;
    @FXML
    private Text enemyTarget1;
    @FXML
    private Text enemyTarget2;
    @FXML
    private Text enemyTarget3;
    @FXML
    private  Text enemy1;
    @FXML
    private Text enemy2;
    @FXML
    private Text enemy3;
    @FXML
    private Pane enemySelectPane;
    @FXML
    private Text allyTarget1;
    @FXML
    private Text allyTarget2;
    @FXML
    private Text allyTarget3;
    @FXML
    private Text allyTarget4;
    @FXML
    private  Text warriorStatus;
    @FXML
    private  Text assassinStatus;
    @FXML
    private  Text blackmageStatus;
    @FXML
    private Text whitemageStatus;
    @FXML
    private Pane parentPane;
    @FXML
    private Pane allySelectPane;
    @FXML
    private Pane movePane;
    @FXML
    private Pane actionDisplayPane;
    @FXML
    private Text actionDisplayText;
    @FXML
    private  Text enemyBack;
    @FXML
    private  Text allyBack;
    @FXML
    private  Text instructionPane;

    private BaseMove choseMove;

    private int chosenMove;
    private ArrayList<String> items;
    private ArrayList<Text> itemsButtonList;
    private  ArrayList<Text> skillButtonsList = new ArrayList<>();
    private ArrayList<Text> monsterList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpBattle();
        initializePaneAndLayout();
        initializeRunButton();
        initializeOnClicking();
        initializeDefault();
    }

    public void initializeRunButton() {
        run.setOnMouseClicked(event -> {
            GameLogic.exitBattle();
        });
    }



    public void initializeDefault() {
       /* removeResultPane();
        removeItemPane();
        removeSkillPane();
        removeBackPane();*/
        warrior.setImage(new WritableImage(RenderableHolder.unitImage.getPixelReader(), 0, 716, 96, 96));
        assassin.setImage(new WritableImage(RenderableHolder.unitImage.getPixelReader(), 0, 832, 96, 96));
        blackmage.setImage(new WritableImage(RenderableHolder.unitImage.getPixelReader(), 0, 1288, 96, 96));
        whitemage.setImage(new WritableImage(RenderableHolder.unitImage.getPixelReader(), 0, 1172, 96, 96));
      //  monster1.setImage();
        warriorStatus.setText("Warrior" + " " + GameLogic.warrior.getHp() + " / " + GameLogic.warrior.getMp());
        assassinStatus.setText("Assassin" + " " + GameLogic.assassin.getHp() + " / " + GameLogic.assassin.getMp());
        blackmageStatus.setText("Black Mage" + " " + GameLogic.blackMage.getHp() + " / " + GameLogic.blackMage.getMp());
        whitemageStatus.setText("White Mage" + " " + GameLogic.whiteMage.getHp() + " / " + GameLogic.whiteMage.getMp());
        enemy1.setText(getEnemyUnit(0).getName());
        enemy2.setText(getEnemyUnit(1).getName());
        enemy3.setText(getEnemyUnit(2).getName());
    }
    public static void setUpBattle(){
        BaseUnit[] enemyUnit = new BaseUnit[7];
        enemyUnit[0] = new StoneGolem("StoneGolem",100,100,10,100);
        enemyUnit[1] = new GreenNaga("GreenNaga",100,100,10,100);
        enemyUnit[2] = new AdeptNecromancer("AdeptNecromancer",100,100,10,100);
        enemyUnit[3] = new GelatinousCube("GelatinousCube",100,100,10,100);
        enemyUnit[4] = new IronGolem("IronGolem",100,100,10,100);
        enemyUnit[5] = new Chimera("Chimera",100,100,10,100);
        enemyUnit[6] = new GrandMasterWarlock("GrandMasterWarlock",100,100,10,100);
        Random random = new Random();
        int firstEnemy = random.nextInt(4);
        int secondEnemy = random.nextInt(7);
        int thirdEnemy = random.nextInt(4);
        GameLogic.getEnemiesUnits().clear();
        GameLogic.getEnemiesUnits().add(enemyUnit[firstEnemy]);
        GameLogic.getEnemiesUnits().add(enemyUnit[secondEnemy]);
        GameLogic.getEnemiesUnits().add(enemyUnit[thirdEnemy]);

    }
    public void removeBattlePane() {
        parentPane.getChildren().remove(battlePane);

    }

    public void addBattlePane() {
        if (!parentPane.getChildren().contains(battlePane)) {
            parentPane.getChildren().add(battlePane);
        }
    }

    public void removeActionDisplayPane() {
        parentPane.getChildren().remove(actionDisplayPane);

    }

    public void addActionDisplayPane() {
        parentPane.getChildren().add(actionDisplayPane);
    }

    public void updateData() {
        allyTarget1.setText(getAllyUnit(0).getName());
        allyTarget2.setText(getAllyUnit(1).getName());
        allyTarget3.setText(getAllyUnit(2).getName());
        allyTarget4.setText(getAllyUnit(3).getName());
        enemyTarget1.setText(getEnemyUnit(0).getName());
        enemyTarget2.setText(getEnemyUnit(1).getName());
        enemyTarget3.setText(getEnemyUnit(2).getName());

    }


    public void initializePaneAndLayout() {
        actionDisplayText.setText("");
        actionDisplayPane.setLayoutY(520);
        parentPane.getChildren().remove(actionDisplayPane);

        parentPane.getChildren().remove(allySelectPane);
        parentPane.getChildren().remove(enemySelectPane);
    }

    public void initializeOnClicking() {

        ArrayList<Text> buttons = new ArrayList<>();
        buttons.add(move1Text);
        buttons.add(move2Text);
        buttons.add(move3Text);
        buttons.add(move4Text);
        buttons.add(run);
        buttons.add(enemyBack);
        buttons.add(allyBack);

        for (Text eachButton : buttons) {
            eachButton.setOnMouseEntered(event -> {
                RenderableHolder.hoverSound.play();
                eachButton.setFill(Color.GOLD);
            });
            eachButton.setOnMouseExited(event -> {
                eachButton.setFill(Color.WHITE);
                RenderableHolder.declineSound.play();
            });
            eachButton.setOnMouseClicked(event -> {
                RenderableHolder.confirmSound.play();
            });
        }

    }


    public void updateUserMovePane(BaseUnit owner) {
        ArrayList<Text> buttons = new ArrayList<>();
        buttons.add(move1Text);
        buttons.add(move2Text);
        buttons.add(move3Text);
        buttons.add(move4Text);
        instructionPane.setText(owner.toString() + "'s Turn");

        for (Text eachMoveButton : buttons) {
            eachMoveButton.setOnMouseClicked(event -> {
                if (owner.getMoveSet()[buttons.indexOf(eachMoveButton)].isUsable()) {
                    choseMove = owner.getMoveSet()[buttons.indexOf(eachMoveButton)];
                    switchToSelectPane();
                }

            });
            eachMoveButton.setText(owner.getMoveSet()[buttons.indexOf(eachMoveButton)].getName());

        }

    }

    public void switchToSelectPane() {
        battlePane.getChildren().remove(battlePane.getChildren().size() - 1);
        if (choseMove instanceof AttackMove) {
            battlePane.getChildren().add(enemySelectPane);
        } else if (choseMove instanceof SupportMove) {
            battlePane.getChildren().add(allySelectPane);
        }
    }

    public void initializeTargetSelectPane() {
        allyTarget1.setText(GameLogic.getAlliessUnits().get(0).getName());
        allyTarget2.setText(GameLogic.getAlliessUnits().get(1).getName());
        allyTarget3.setText(GameLogic.getAlliessUnits().get(2).getName());
        allyTarget4.setText(GameLogic.getAlliessUnits().get(3).getName());
        enemyTarget1.setText(GameLogic.getEnemiesUnits().get(0).getName());
        enemyTarget2.setText(GameLogic.getEnemiesUnits().get(1).getName());
        enemyTarget3.setText(GameLogic.getEnemiesUnits().get(2).getName());
        allyTarget1.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getAlliessUnits().get(0), allyTarget1);
        });
        allyTarget2.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getAlliessUnits().get(1), allyTarget2);
        });
        allyTarget3.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getAlliessUnits().get(2), allyTarget3);
        });
        allyTarget4.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getAlliessUnits().get(3), allyTarget4);
        });
        enemyTarget1.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getEnemiesUnits().get(0), enemyTarget1);

        });
        enemyTarget2.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getEnemiesUnits().get(1), enemyTarget2);

        });
        enemyTarget2.setOnMouseClicked(event -> {
            setSelectButton(GameLogic.getEnemiesUnits().get(2), enemyTarget2);

        });

        enemyBack.setOnMouseClicked(event -> {
            switchToMovePane();

        });
        allyBack.setOnMouseClicked(event -> {
            switchToMovePane();
        });
    }
    public void setSelectButton(BaseUnit owner, Text button) {
        if (!owner.isDestroyed()) {
            choseMove.setTarget(owner);
            GameLogic.movesContainer.add(choseMove);
            switchToMovePane();
            GameLogic.battle();
        } else {
            fillDisableButton(button);
        }
    }

    public void fillDisableButton(Text button) {
        button.setFill(Color.RED);
        button.setOnMousePressed(event -> {
            button.setFill(Color.DARKRED);
        });
        button.setOnMouseReleased(event -> {
            button.setFill(Color.RED);
        });

    }
    public void switchToMovePane() {

        battlePane.getChildren().remove(battlePane.getChildren().size() - 1);
        battlePane.getChildren().add(movePane);

    }
    public void updateMovePane(BaseUnit owner) {
        ArrayList<Text> buttons = new ArrayList<>();
        buttons.add(move1Text);
        buttons.add(move2Text);
        buttons.add(move3Text);
        buttons.add(move4Text);
        for (int i = 0 ; i < 4 ; i++){
            buttons.get(i).setText(owner.getMoveSet()[i].getName());
        }
    }



    public void showTextInDisplayPane(String text) {

            actionDisplayText.setText("");
            int typeDelay = 60;
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(text.length() + 1); // +1 to leave the final text visible
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(typeDelay), event -> {

                int currentLength = actionDisplayText.getText().length();
                if (currentLength < text.length()) {
                    actionDisplayText.setText(text.substring(0, currentLength + 1));
                } else {
                    timeline.stop();
                }
            }));
            timeline.play();
    }
    public void resetActionDisplayText() {
        actionDisplayText.setText(null);
    }

    public BaseUnit getAllyUnit(int i) {
        return GameLogic.getAlliessUnits().get(i);
    }

    public static BaseUnit getEnemyUnit(int i) {
        return GameLogic.getEnemiesUnits().get(i);
    }

    /*public void setOnUnitButtonMouseHovering(BaseUnit unit, Text text) {
        text.setOnMouseEntered(event -> {
            unit.setMouseHovering(true);
            text.setFill(Color.GOLD);
            RenderableHolder.mouseHoverSound.play();

        });
        text.setOnMouseExited(event -> {
            text.setFill(Color.WHITE);
            unit.setMouseHovering(false);
        });

    }*/

    /*public void setUnitStatusOnMouseHover() {
        setOnUnitButtonMouseHovering(GameLogic.getUser1(), allyTarget1);
        setOnUnitButtonMouseHovering(GameLogic.getUser2(), allyTarget2);
        setOnUnitButtonMouseHovering(GameLogic.getMonster1(), enemyTarget1);
        setOnUnitButtonMouseHovering(GameLogic.getMonster2(), enemyTarget2);

    }*/





}
