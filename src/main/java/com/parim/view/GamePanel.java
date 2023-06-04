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
            g.drawRect((int) block.getX() * TileObject.getSIZE(), (int) block.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
            g.fillRect((int) block.getX() * TileObject.getSIZE(), (int) block.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
        }
        g.setColor(Color.red);
        for (EnemyObject enemy : enemies) {
            g.drawRect((int) enemy.getX() * TileObject.getSIZE(), (int) enemy.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
            g.fillRect((int) enemy.getX() * TileObject.getSIZE(), (int) enemy.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
        }
        g.setColor(Color.green);
        for (PipeObject pipe : pipes) {
            g.drawRect((int) pipe.getX() * TileObject.getSIZE(), (int) pipe.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
            g.fillRect((int) pipe.getX() * TileObject.getSIZE(), (int) pipe.getY() * TileObject.getSIZE(), TileObject.getSIZE(), TileObject.getSIZE());
        }
    }
}
