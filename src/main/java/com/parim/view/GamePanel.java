package com.parim.view;

import com.parim.access.GameAccess;
import com.parim.model.GameObject;
import com.parim.model.SectionObject;
import com.parim.model.components.*;
import com.parim.view.loaders.ImageLoader;

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
        TileObject.setSIZE(Toolkit.getDefaultToolkit().getScreenSize().width/(double) section.getLength());
        for (BlockObject block : blocks)
            g.drawImage(ImageLoader.getInstance().LoadObject("/blocks/" + block.getType()), (int) (block.getX() * TileObject.getSIZE()), (int) (block.getY() * TileObject.getSIZE()), null);
        for (EnemyObject enemy : enemies)
            g.drawImage(ImageLoader.getInstance().LoadObject("/enemies/" + enemy.getType()), (int) (enemy.getX() * TileObject.getSIZE()), (int) (enemy.getY() * TileObject.getSIZE()), null);
        for (PipeObject pipe : pipes)
            g.drawImage(ImageLoader.getInstance().LoadObject("/pipes/" + pipe.getType()), (int) (pipe.getX() * TileObject.getSIZE()), (int) (pipe.getY() * TileObject.getSIZE()), null);
    }
}
