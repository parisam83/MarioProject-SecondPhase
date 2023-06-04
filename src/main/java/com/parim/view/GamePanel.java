package com.parim.view;

import com.parim.access.GameAccess;
import com.parim.model.LevelObject;
import com.parim.model.SectionObject;
import com.parim.model.components.BlockObject;
import com.parim.model.components.TileObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private LevelObject levelObject = new GameAccess().loadGame().getCurrentLevel();
    public GamePanel(){
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (SectionObject section : levelObject.getSections())
            for (BlockObject block : section.getBlocks()){
                g.drawRect(block.getX(), block.getY(), TileObject.getSIZE(), TileObject.getSIZE());
                g.fillRect(block.getX(), block.getY(), TileObject.getSIZE(), TileObject.getSIZE());
            }
    }
}
