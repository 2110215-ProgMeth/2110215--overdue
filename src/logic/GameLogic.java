package logic;
import java.util.ArrayList;
import java.util.List;

import interfaces.IRenderable;
import sharedObject.RenderableHolder;

public class GameLogic {
    private List<IRenderable> gameObjectContainer;

    public GameLogic(){
        this.gameObjectContainer = new ArrayList<IRenderable>();
    }

    protected void addNewObject(IRenderable entity){
        gameObjectContainer.add(entity);
        RenderableHolder.getInstance().add(entity);
    }
    public void logicUpdate(){
        //System.out.println("GameLogic update called");
    }
}
