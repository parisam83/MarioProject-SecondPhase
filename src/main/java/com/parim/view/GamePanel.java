package com.parim.view;

import com.parim.controller.GameController;
import com.parim.model.GameObject;
import com.parim.model.SectionObject;
import com.parim.model.components.*;
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
    private ArrayList<BlockObject> blocks = section.getBlocks();
    private ArrayList<EnemyObject> enemies = section.getEnemies();
    private ArrayList<PipeObject> pipes = section.getPipes();

    public GamePanel(){
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(new KL());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TileObject.setSIZE(Toolkit.getDefaultToolkit().getScreenSize().width/(double) section.getLength());
        g.drawImage(ImageLoader.getInstance().LoadObject("/marios/" + mario.getType()), (int) (mario.getX() * TileObject.getSIZE()), (int) (mario.getY() * TileObject.getSIZE()), null);
        for (BlockObject block : blocks)
            g.drawImage(ImageLoader.getInstance().LoadObject("/blocks/" + block.getType()), (int) (block.getX() * TileObject.getSIZE()), (int) (block.getY() * TileObject.getSIZE()), null);
        for (EnemyObject enemy : enemies)
            g.drawImage(ImageLoader.getInstance().LoadObject("/enemies/" + enemy.getType()), (int) (enemy.getX() * TileObject.getSIZE()), (int) (enemy.getY() * TileObject.getSIZE()), null);
        for (PipeObject pipe : pipes)
            g.drawImage(ImageLoader.getInstance().LoadObject("/pipes/" + pipe.getType()), (int) (pipe.getX() * TileObject.getSIZE()), (int) (pipe.getY() * TileObject.getSIZE()), null);
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
