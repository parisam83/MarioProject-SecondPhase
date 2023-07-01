package com.parim.controller;

import com.parim.access.GameAccess;
import com.parim.controller.Collision.EnemyCollision;
import com.parim.controller.Collision.ItemCollision;
import com.parim.controller.Collision.MarioCollision;
import com.parim.model.GameObject;
import com.parim.model.LevelObject;
import com.parim.model.SectionObject;
import com.parim.model.components.mario.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.model.components.blocks.Block;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.items.Item;
import com.parim.model.components.pipes.Pipe;
import com.parim.model.interfaces.HasTimeBeforeMove;
import com.parim.model.interfaces.Movable;
import com.parim.view.GamePanel;
import com.parim.view.MainFrame;
import com.parim.view.PausePanel;
import com.parim.view.WinPanel;

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
    private ArrayList<Item> items = new ArrayList<>();
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
            updateTimeOfTiles();
            updateObjectsIfSectionEnded();
            gamePanel.requestFocus();
            mainFrame.repaint();
            mainFrame.revalidate();
        }
    }

    private void updateObjectsIfSectionEnded() {
        if (marioObject.getX() > gameObject.getCurrentSection().getLength()-0.5) {
            saveSectionToGameObject();
            loadNextSectionAndUpdateGameObjectCurrents();
        }
    }

    private void loadNextSectionAndUpdateGameObjectCurrents() {
        SectionObject nextSection = gameObject.getNextSection();
        LevelObject nextLevel = gameObject.getNextLevel();
        if (nextSection == null){
            if (nextLevel == null) marioWon();
            else {
                gameObject.setCurrentLevel(nextLevel);
                gameObject.setCurrentSection(nextLevel.getSections().get(0));
            }
        }
        else
            gameObject.setCurrentSection(nextSection);

        marioObject = gameObject.resetMario();
        setAllTiles();
    }

    private void marioWon() {
        // TODO
        mainFrame.setContentPane(new WinPanel());
    }

    public void saveSectionToGameObject(){
        // gameObject.changeSectionTo(gameObject.getCurrentSection(), );
    }
    private void saveGame(){
        /* TODO:
                new GameAccess().saveGame(gameObject.getCurrentLevel(), 2);
        */
    }

    private void updateTimeOfTiles() {
        for (TileObject tile : allTiles)
            if (tile instanceof HasTimeBeforeMove)
                ((HasTimeBeforeMove) tile).updateTicksPassed();
    }

    private void updateTiles() {
        allTiles.removeAll(tilesToRemove);
        allTiles.addAll(tilesToAdd);

        for (TileObject tile : tilesToAdd) {
            if (tile instanceof Block) blocks.add((Block) tile);
            else if (tile instanceof Pipe) pipes.add((Pipe) tile);
            else if (tile instanceof Enemy) enemies.add((Enemy) tile);
            else if (tile instanceof Item) items.add((Item) tile);
        }
        for (TileObject tile : tilesToRemove) {
            if (tile instanceof Block) blocks.remove((Block) tile);
            else if (tile instanceof Pipe) pipes.remove((Pipe) tile);
            else if (tile instanceof Enemy) enemies.remove((Enemy) tile);
            else if (tile instanceof Item) items.remove((Item) tile);
        }

        tilesToRemove.clear();
        tilesToAdd.clear();
    }

    private void checkCollision() {
        down = false;
        new MarioCollision();
        new ItemCollision();
        new EnemyCollision();
    }

    public boolean intersectRight(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff) {
            if (Math.abs(dy) > .5 && Math.abs(dy) > Math.abs(dx)) {}
            else if (Math.abs(dx) > diff)
                if (dx < 0) return true;
        }
        return false;
    }
    public boolean intersectLeft(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff) {
            if (Math.abs(dy) > .5 && Math.abs(dy) > Math.abs(dx)) {}
            else if (Math.abs(dx) > diff)
                if (dx > 0) return true;
        }
        return false;
    }
    public boolean intersectUp(TileObject tile1, TileObject tile2){
        double dx = tile1.getX() - tile2.getX();
        double dy = tile1.getY() - tile2.getY();

        if (Math.abs(dy) < 1 - diff && Math.abs(dx) < 1 - diff)
            if (Math.abs(dy) > 0.5 && Math.abs(dy) > Math.abs(dx))
                if (dy < 0) return true;
        return false;
    }
    public boolean intersectDown(TileObject tile1, TileObject tile2){
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
            if (marioObject.GetYVelocity() == 0){
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
        for (TileObject tile : allTiles) {
            if (tile instanceof HasTimeBeforeMove)
                ((HasTimeBeforeMove) tile).enableMoveIfPossible();
            if (tile instanceof Movable)
                ((Movable) tile).move();
        }
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
        SectionObject sectionObject = gameObject.getCurrentSection();
        // Clear all objects
        allTiles.clear();
        blocks.clear();
        enemies.clear();
        pipes.clear();
        items.clear();

        // Add objects to their specific ArrayLists
        if (sectionObject.getBlocks() != null && !sectionObject.getBlocks().isEmpty()) blocks.addAll(sectionObject.getBlocks());
        if (sectionObject.getEnemies() != null && !sectionObject.getEnemies().isEmpty()) enemies.addAll(sectionObject.getEnemies());
        if (sectionObject.getPipes() != null && !sectionObject.getPipes().isEmpty()) pipes.addAll(sectionObject.getPipes());

        // Add objects to allTiles ArrayList
        allTiles.add(marioObject);
        if (!blocks.isEmpty()) allTiles.addAll(blocks);
        if (!enemies.isEmpty()) allTiles.addAll(enemies);
        if (!pipes.isEmpty()) allTiles.addAll(pipes);
    }


    public void marioKillsEnemy(Enemy enemy){
        addMarioPoints(enemy);
        tilesToRemove.add(enemy);
    }
    public void marioDiedByEnemy(){
        tilesToRemove.add(marioObject);
        if (gameObject.getHearts() == 0){
            gameOver();
            return;
        }
        marioObject = gameObject.resetMario();
        tilesToAdd.add(marioObject);
    }

    public void itemDiedByEnemy(Item item) {
        tilesToRemove.add(item);
    }

    private void gameOver() {
        // TODO
    }

    public void marioAteItem(Item item){
        addMarioPoints(item);
        tilesToRemove.add(item);
    }

    private void addMarioPoints(TileObject tile) {
        gameObject.addCoins(tile.COIN);
        gameObject.addScore(tile.SCORE);
    }

    public void addTileToRemove(TileObject tileObject){
        tilesToRemove.add(tileObject);
    }
    public void addTilesToAdd(ArrayList<TileObject> tiles){
        tilesToAdd.addAll(tiles);
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public MarioObject getMarioObject() {
        return marioObject;
    }
}
