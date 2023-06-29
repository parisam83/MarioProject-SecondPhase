package com.parim.controller.Collision;

import com.parim.controller.GameController;
import com.parim.model.components.MarioObject;
import com.parim.model.components.blocks.Block;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.items.Item;
import com.parim.model.components.pipes.Pipe;

import java.util.ArrayList;

public class ItemCollision {
    private GameController gc = GameController.getInstance();
    private MarioObject mario = gc.getMarioObject();
    private ArrayList<Block> blocks = gc.getBlocks();
    private ArrayList<Enemy> enemies = gc.getEnemies();
    private ArrayList<Pipe> pipes = gc.getPipes();
    private ArrayList<Item> items = gc.getItems();

    public ItemCollision(){
        itemBlockCollision();
    }

    public void itemBlockCollision(){
        for (Item item : items){
            item.activateGravity();
            for (Block block : blocks)
                if (gc.intersectDown(item, block)) {
                    item.deactivateGravity();
                    item.setYVelocity(0);
                }
        }
    }
}
