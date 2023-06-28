package com.parim.controller;

import com.parim.access.GameAccess;
import com.parim.model.GameObject;
import com.parim.model.SectionObject;
import com.parim.model.components.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.model.components.blocks.Block;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.pipes.Pipe;
import com.parim.model.interfaces.Convertible;
import com.parim.model.interfaces.Movable;
import com.parim.view.GamePanel;
import com.parim.view.MainFrame;
import com.parim.view.PausePanel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController {
    private static GameController instance;
    private boolean gameFinished = false;
    private MainFrame mainFrame;
    private GamePanel gamePanel;
    private PausePanel pausePanel = new PausePanel();
    private GameObject gameObject = new GameAccess().loadGame();
    private MarioObject marioObject = gameObject.getMario();
    private ArrayList<TileObject> allTiles = new ArrayList<>(), tilesToRemove = new ArrayList<>(), tilesToAdd = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private Set<Integer> pressedKeys = new HashSet<>();
    private double diff = 0.0001;
    private boolean down = false;

    public GameController(){}
    public GameController(MainFrame mainFrame){
        if (instance != null) return;
        instance = this;

        setAllTiles();
        this.mainFrame = mainFrame;
        this.mainFrame.setContentPane(gamePanel = new GamePanel());
        gamePanel.requestFocus();
    }

    public void run(){
        while (!gameFinished) {
            sleep();
            updateVelocity();
            move();
            checkCollision();
            updateTiles();
            gamePanel.requestFocus();
            mainFrame.repaint();
            mainFrame.revalidate();
        }
    }

    private void updateTiles() {
        allTiles.removeAll(tilesToRemove);
        allTiles.addAll(tilesToAdd);

        for (TileObject tile : tilesToAdd) {
            if (tile instanceof Block) blocks.add((Block) tile);
            else if (tile instanceof Pipe) pipes.add((Pipe) tile);
            else if (tile instanceof Enemy) enemies.add((Enemy) tile);
        }
        for (TileObject tile : tilesToRemove) {
            if (tile instanceof Block) blocks.remove((Block) tile);
            else if (tile instanceof Pipe) pipes.remove((Pipe) tile);
            else if (tile instanceof Enemy) enemies.remove((Enemy) tile);
        }
    }

    private void checkCollision() {
        down = false;
        blockMarioCollision();
        pipeMarioCollision();
    }

    private void blockMarioCollision() {
        for (Block block : blocks) {
            if (intersectRight(marioObject, block))
                marioObject.setX(block.getX() - 1);
            if (intersectLeft(marioObject, block))
                marioObject.setX(block.getX() + 1);
            if (intersectDown(marioObject, block)) {
                down = true;
                marioObject.setY(block.getY() + 1);
                marioObject.setYVelocity(0);
                marioObject.setInitialYBeforeJump(marioObject.getY());
            }

            if (!down && marioObject.getInitialYBeforeJump() == marioObject.getY() && marioObject.getY() > 0)
                marioObject.updateVelocityMoveDown();

            if (intersectUp(marioObject, block)){
                marioObject.setY(block.getY() - 1);
                marioObject.updateVelocityMoveDown();
                block.addNumberOfHits();
                if (block instanceof Convertible && block.limitOfHitExceeded()){
                    ArrayList<TileObject> list = ((Convertible) block).convert();
                    tilesToRemove.add(block);
                    tilesToAdd.addAll(list);
                }
            }
        }
    }
    private void pipeMarioCollision() {
        for (Pipe pipe : pipes){
            if (intersectRight(marioObject, pipe))
                marioObject.setX(pipe.getX() - 1);
            if (intersectLeft(marioObject, pipe))
                marioObject.setX(pipe.getX() + 1);

            if (intersectDown(marioObject, pipe)){
                down = true;
                marioObject.setY(pipe.getY() + 1);
                marioObject.setYVelocity(0);
                marioObject.setInitialYBeforeJump(marioObject.getY());
            }
            if (intersectUp(marioObject, pipe))
                marioObject.setY(pipe.getY() - 1);

            if (!down && marioObject.getInitialYBeforeJump() == marioObject.getY() && marioObject.getY() > 0)
                marioObject.updateVelocityMoveDown();
        }
    }
    private boolean intersectRight(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff) {
            if (Math.abs(dy) > .5 && Math.abs(dy) > Math.abs(dx)) {}
            else if (Math.abs(dx) > diff)
                if (dx < 0) return true;
        }
        return false;
    }
    private boolean intersectLeft(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff) {
            if (Math.abs(dy) > .5 && Math.abs(dy) > Math.abs(dx)) {}
            else if (Math.abs(dx) > diff)
                if (dx > 0) return true;
        }
        return false;
    }
    private boolean intersectUp(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff)
            if (Math.abs(dy) > 0.5 && Math.abs(dy) > Math.abs(dx))
                if (dy < 0) return true;
        return false;
    }
    private boolean intersectDown(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff)
            if (Math.abs(dy) > .5 && Math.abs(dy) > Math.abs(dx))
                if (dy > 0) return true;
        return false;
    }

    public void updateVelocity(){
        // update Mario velocity
        keyPressed();
        keyReleased();
        if (marioObject.getY() >= marioObject.getInitialYBeforeJump() + 5) {
            // System.out.println("Hi");
            marioObject.updateVelocityMoveDown();
        }

/*        // update other Movable velocities
        for (TileObject tileObject : allTiles)
            if (tileObject instanceof Movable){
                if (intersectRight())
                if (tileObject.getXVelocity() > 0) tileObject.setX(intersectRight(tileObject));
            }*/
    }

    private void keyPressed(){
        boolean left = false, right = false, up = false, down = false, space = false, escape = false;
        for (Integer e : pressedKeys){
            if (e == KeyEvent.VK_RIGHT) right = true;
            if (e == KeyEvent.VK_LEFT) left = true;
            if (e == KeyEvent.VK_UP) up = true;
            if (e == KeyEvent.VK_DOWN) down = true;
            if (e == KeyEvent.VK_SPACE) space = true;
            if (e == KeyEvent.VK_ESCAPE) escape = true;
        }
        if (right && left) marioObject.setXVelocity(0);
        else if (right) marioObject.updateVelocityMoveRight();
        else if (left) marioObject.updateVelocityMoveLeft();

        if (up) {
            if (marioObject.getYVelocity() == 0){
                marioObject.setInitialYBeforeJump(marioObject.getY());
                marioObject.updateVelocityMoveUp();
            }
        }
    }
    private void keyReleased(){
        boolean left = false, right = false, up = false, down = false, space = false, escape = false;
        for (Integer e : pressedKeys){
            if (e == KeyEvent.VK_RIGHT) right = true;
            if (e == KeyEvent.VK_LEFT) left = true;
            if (e == KeyEvent.VK_UP) up = true;
            if (e == KeyEvent.VK_DOWN) down = true;
            if (e == KeyEvent.VK_SPACE) space = true;
            if (e == KeyEvent.VK_ESCAPE) escape = true;
        }
        if (!right && !left) marioObject.setXVelocity(0);
        if (!right && left) marioObject.updateVelocityMoveLeft();
        if (!left && right) marioObject.updateVelocityMoveRight();
    }
    public void move(){
        for (TileObject tile : allTiles)
            if (tile instanceof Movable)
                ((Movable) tile).move();
    }
    public void sleep(){
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public GameObject loadGame(){
        return gameObject;
    }
    public static GameController getInstance(){
        if (instance == null)  instance = new GameController(new MainFrame());
        return instance;
    }

    public void addPressedKey(Integer e){
        pressedKeys.add(e);
    }
    public void removePressedKey(Integer e){
        pressedKeys.remove(e);
    }
    private void setAllTiles() {
        SectionObject sectionObject = gameObject.getLevels().get(0).getSections().get(0);
        allTiles.add(marioObject);
        blocks.addAll(sectionObject.getBlocks());
        enemies.addAll(sectionObject.getEnemies());
        pipes.addAll(sectionObject.getPipes());
        allTiles.addAll(blocks);
        allTiles.addAll(enemies);
        allTiles.addAll(pipes);

        /*for (LevelObject levelObject : gameObject.getLevels())
            for (SectionObject sectionObject : levelObject.getSections()){
                if (sectionObject.getBlocks() != null){
                    blocks.addAll(sectionObject.getBlocks());
                    allTiles.addAll(sectionObject.getBlocks());
                }
                if (sectionObject.getEnemies() != null) {
                    enemies.addAll(sectionObject.getEnemies());
                    allTiles.addAll(sectionObject.getEnemies());
                }
                if (sectionObject.getPipes() != null) {
                    pipes.addAll(sectionObject.getPipes());
                    allTiles.addAll(sectionObject.getPipes());
                }
            }*/
    }

    // Getters and Setters

    public ArrayList<TileObject> getAllTiles() {
        return allTiles;
    }

    public void setAllTiles(ArrayList<TileObject> allTiles) {
        this.allTiles = allTiles;
    }

    public ArrayList<TileObject> getTilesToRemove() {
        return tilesToRemove;
    }

    public void setTilesToRemove(ArrayList<TileObject> tilesToRemove) {
        this.tilesToRemove = tilesToRemove;
    }

    public ArrayList<TileObject> getTilesToAdd() {
        return tilesToAdd;
    }

    public void setTilesToAdd(ArrayList<TileObject> tilesToAdd) {
        this.tilesToAdd = tilesToAdd;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public void setPipes(ArrayList<Pipe> pipes) {
        this.pipes = pipes;
    }
}
