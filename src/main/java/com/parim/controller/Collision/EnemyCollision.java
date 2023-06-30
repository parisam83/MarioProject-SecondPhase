package com.parim.controller.Collision;

import com.parim.controller.GameController;
import com.parim.model.components.TileObject;
import com.parim.model.components.enemies.Enemy;

import java.util.ArrayList;

public class EnemyCollision {
    private GameController gc = GameController.getInstance();
    private ArrayList<Enemy> enemies = gc.getEnemies();
    private ArrayList<TileObject> tilesExceptMario = new ArrayList<TileObject>(){{
        addAll(gc.getBlocks());
        addAll(gc.getPipes());
        addAll(gc.getEnemies());
        addAll(gc.getItems());
    }};
    public EnemyCollision(){
        enemyTilesExceptMarioCollision();
    }

    public void enemyTilesExceptMarioCollision(){
        for (Enemy enemy : enemies) {
            if (enemy.getYVelocity() <= 0) enemy.activateGravity();
            for (TileObject tile : tilesExceptMario){
                if (enemy == tile) continue;
                if (gc.intersectRight(enemy, tile)) {
                    enemy.setX(tile.getX() - 1);
                    enemy.updateVelocityMoveLeft();
                }
                if (gc.intersectLeft(enemy, tile)) {
                    enemy.setX(tile.getX() + 1);
                    enemy.updateVelocityMoveRight();
                }
                if (gc.intersectDown(enemy, tile)) {
                    enemy.deactivateGravity();
                    enemy.setYVelocity(0);
                }
            }
        }
    }
}
