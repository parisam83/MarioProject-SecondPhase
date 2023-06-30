package com.parim.controller.Collision;

import com.parim.controller.GameController;
import com.parim.model.components.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.model.components.blocks.Block;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.enemies.Spiny;
import com.parim.model.components.items.Item;
import com.parim.model.components.pipes.Pipe;
import com.parim.model.interfaces.Convertible;

import java.util.ArrayList;

public class MarioCollision {
    private GameController gc = GameController.getInstance();
    private MarioObject mario = gc.getMarioObject();
    private ArrayList<Block> blocks = gc.getBlocks();
    private ArrayList<Enemy> enemies = gc.getEnemies();
    private ArrayList<Pipe> pipes = gc.getPipes();
    private ArrayList<Item> items = gc.getItems();

    public MarioCollision(){
        mario.setMarioHasIntersectDown(false);
        blockMarioCollision();
        pipeMarioCollision();
        enemyMarioCollision();
        itemMarioCollision();
    }

    private void blockMarioCollision() {
        for (Block block : blocks){
            if (gc.intersectRight(mario, block)) mario.setX(block.getX() - 1);
            if (gc.intersectLeft(mario, block)) mario.setX(block.getX() + 1);
            if (gc.intersectDown(mario, block)) {
                mario.setY(block.getY() + 1);
                mario.marioHadIntersectDown();
            }
            if (gc.intersectUp(mario, block)) {
                mario.setY(block.getY() - 1);
                mario.updateVelocityMoveDown();
                block.addNumberOfHits();
                if (block instanceof Convertible && block.limitOfHitExceeded()){
                    ArrayList<TileObject> list = ((Convertible) block).convert();
                    gc.addTilesToAdd(list);
                    gc.addTileToRemove(block);
                }
            }
        }
    }
    private void pipeMarioCollision(){
        for (Pipe pipe : pipes){
            if (gc.intersectRight(mario, pipe)) mario.setX(pipe.getX() - 1);
            if (gc.intersectLeft(mario, pipe)) mario.setX(pipe.getX() + 1);
            if (gc.intersectUp(mario, pipe)) mario.setY(pipe.getY() - 1);
            if (gc.intersectDown(mario, pipe)) {
                mario.setY(pipe.getY() + 1);
                mario.marioHadIntersectDown();
            }
        }
    }
    private void enemyMarioCollision(){
        for (Enemy enemy : enemies){
            if (gc.intersectDown(mario, enemy)) {
                if (enemy instanceof Spiny){
                    ((Spiny) enemy).addNumberOfHits();
                    if (((Spiny) enemy).limitOfHitExceeded())
                        gc.marioKillsEnemy(enemy);
                }
                else gc.marioKillsEnemy(enemy);
            }
            if (gc.intersectRight(mario, enemy) || gc.intersectLeft(mario, enemy) || gc.intersectUp(mario, enemy)) {
                gc.marioDiedByEnemy();
            }
        }
    }
    private void itemMarioCollision(){
        for (Item item : items)
            if (gc.intersectUp(mario, item) || gc.intersectDown(mario, item) || gc.intersectRight(mario, item) || gc.intersectLeft(mario, item))
                gc.marioAteItem(item);
    }
}
