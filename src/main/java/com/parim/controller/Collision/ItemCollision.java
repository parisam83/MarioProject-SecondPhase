package com.parim.controller.Collision;

import com.parim.controller.GameController;
import com.parim.model.components.TileObject;
import com.parim.model.components.items.Item;

import java.util.ArrayList;

public class ItemCollision {
    private GameController gc = GameController.getInstance();
    private ArrayList<Item> items = gc.getItems();
    private ArrayList<TileObject> tilesExceptMario = new ArrayList<TileObject>(){{
        addAll(gc.getBlocks());
        addAll(gc.getPipes());
        addAll(gc.getEnemies());
        addAll(gc.getItems());
    }};

    public ItemCollision(){
        itemTilesExceptMarioCollision();
    }

    public void itemTilesExceptMarioCollision(){
        for (Item item : items){
            item.activateGravity();
            for (TileObject tile : tilesExceptMario){
                if (item == tile) continue;
                if (gc.intersectRight(item, tile)) {
                    System.out.println("intersect Right" + item);
                    item.setX(tile.getX() - 1);
                    item.updateVelocityMoveLeft();
                    System.out.println(item.getXVelocity() + "\n");
                }
                if (gc.intersectLeft(item, tile)) {
                    item.setX(tile.getX() + 1);
                    item.updateVelocityMoveRight();
                }
                if (gc.intersectDown(item, tile)) {
                    item.deactivateGravity();
                    item.setYVelocity(0);
                }
            }
        }
    }
}
