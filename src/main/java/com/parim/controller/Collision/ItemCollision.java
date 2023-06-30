package com.parim.controller.Collision;

import com.parim.controller.GameController;
import com.parim.model.components.TileObject;
import com.parim.model.components.blocks.Block;
import com.parim.model.components.blocks.Floor;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.items.Item;
import com.parim.model.components.items.Star;

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
        starFloorCollision();
        itemTilesExceptMarioCollision();
    }

    public void itemTilesExceptMarioCollision(){
        for (Item item : items){
            if (!(item instanceof Star) && item.getYVelocity() <= 0) item.activateGravity();
            for (TileObject tile : tilesExceptMario){
                if (item == tile) continue;
                if (tile instanceof Enemy && (gc.intersectRight(item, tile) || gc.intersectLeft(item, tile) || gc.intersectUp(item, tile) || gc.intersectDown(item, tile)))
                    gc.itemDiedByEnemy(item);
                if (gc.intersectRight(item, tile)) {
                    item.setX(tile.getX() - 1);
                    item.updateVelocityMoveLeft();
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

    public void starFloorCollision(){
        for (Item star : items)
            if (star instanceof Star)
                for (TileObject tile : tilesExceptMario) {
                    ((Star) star).setStarHasIntersectDownWithBlock(false);
                    // ((Star) star).setStarHasIntersectDownWithFloor(false);
                    if (gc.intersectDown(star, tile)) {
                        if (tile instanceof Floor) {
                            ((Star) star).setStarHasIntersectDownWithFloor(true);
                        }
                        else if (tile instanceof Block)
                            ((Star) star).setStarHasIntersectDownWithBlock(true);
                    }
                }
                    /*if (gc.intersectDown(star, tile)) {
                        ((Star) star).starHadIntersectDown();
                        if (tile instanceof Floor) {
                            if (!((Star) star).isLastTickWasOnFloor())
                                ((Star) star).updateLastTimeFirstComeToFloor();

                            if (((Star) star).isLastTickWasOnFloor() && ((Star) star).isJumpPossible()) {
                                System.out.println("star should jump!    ");
                                star.updateVelocityMoveUp();
                                ((Star) star).updateLastTimeFirstComeToFloor();
                            }
                            ((Star) star).setLastTickWasOnFloor(true);
                        }
                        else ((Star) star).setLastTickWasOnFloor(false);
                    }*/
    }
}
