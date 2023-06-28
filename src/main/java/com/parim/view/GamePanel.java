package com.parim.view;

import com.parim.controller.GameController;
import com.parim.model.GameObject;
import com.parim.model.SectionObject;
import com.parim.model.components.*;
import com.parim.model.components.blocks.Block;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.pipes.Pipe;
import com.parim.view.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private GameObject game = GameController.getInstance().loadGame();
    private MarioObject mario = game.getMario();
    private SectionObject section = game.getCurrentSection();
    private ArrayList<Block> blocks = section.getBlocks();
    private ArrayList<Enemy> enemies = section.getEnemies();
    private ArrayList<Pipe> pipes = section.getPipes();

    public GamePanel(){
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(new KL());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateTiles();
        TileObject.setSIZE(Toolkit.getDefaultToolkit().getScreenSize().width/(double) section.getLength());
        g.drawImage(ImageLoader.getInstance().LoadObject("/marios/" + mario.getType(), mario.getWidth(), mario.getHeight()), (int) (mario.getX() * TileObject.getSIZE()), (int) ((9.5 - mario.getY()+2) * TileObject.getSIZE()), null);
        for (Block block : blocks)
            g.drawImage(ImageLoader.getInstance().LoadObject("/blocks/" + block.getType(), block.getWidth(), block.getHeight()), (int) (block.getX() * TileObject.getSIZE()), (int) ((9.5 - block.getY() + 2) * TileObject.getSIZE()), null);
        for (Enemy enemy : enemies)
            g.drawImage(ImageLoader.getInstance().LoadObject("/enemies/" + enemy.getType(), enemy.getWidth(), enemy.getHeight()), (int) (enemy.getX() * TileObject.getSIZE()), (int) ((9.5 - enemy.getY()+2) * TileObject.getSIZE()), null);
        for (Pipe pipe : pipes)
            g.drawImage(ImageLoader.getInstance().LoadObject("/pipes/" + pipe.getType(), pipe.getWidth(), pipe.getHeight()), (int) ((pipe.getX()-pipe.getWidth()+1) * TileObject.getSIZE()), (int) ((9.5 - pipe.getY() - pipe.getHeight()+3) * TileObject.getSIZE()), null);
    }

    private void updateTiles() {
        blocks = GameController.getInstance().getBlocks();
        pipes = GameController.getInstance().getPipes();
        enemies = GameController.getInstance().getEnemies();
    }

    public class KL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            GameController.getInstance().addPressedKey(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            GameController.getInstance().removePressedKey(e.getKeyCode());
        }
    }
}
