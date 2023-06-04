package com.parim.view;

import com.parim.access.GameAccess;
import com.parim.model.GameObject;
import com.parim.model.SectionObject;
import com.parim.model.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private GameObject game = new GameAccess().loadGame();
    private MarioObject mario = game.getMario();
    private SectionObject section = game.getCurrentSection();
    private ArrayList<BlockObject> blocks = section.getBlocks();
    private ArrayList<EnemyObject> enemies = section.getEnemies();
    private ArrayList<PipeObject> pipes = section.getPipes();

    public GamePanel(){
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BlockObject block : blocks) {
            g.drawRect(block.getX() * TileObject.getSIZE(), block.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
            g.fillRect(block.getX() * TileObject.getSIZE(), block.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
        }
        g.setColor(Color.red);
        for (EnemyObject enemy : enemies) {
            g.drawRect(enemy.getX() * TileObject.getSIZE(), enemy.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
            g.fillRect(enemy.getX() * TileObject.getSIZE(), enemy.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
        }
        g.setColor(Color.green);
        for (PipeObject pipe : pipes) {
            g.drawRect(pipe.getX() * TileObject.getSIZE(), pipe.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
            g.fillRect(pipe.getX() * TileObject.getSIZE(), pipe.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
        }
    }
}
