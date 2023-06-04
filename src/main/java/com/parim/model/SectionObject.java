package com.parim.model;

import com.parim.model.components.BlockObject;
import com.parim.model.components.EnemyObject;
import com.parim.model.components.PipeObject;

import java.util.ArrayList;

public class SectionObject {
    private static int HEIGHT = 20;
    private int length = 10, time = 0;
    private ArrayList<BlockObject> blocks;
    private ArrayList<EnemyObject> enemies;
    private ArrayList<PipeObject> pipes;
    private PipeObject spawnPipe;

    public SectionObject(int length, int time, ArrayList<BlockObject> blocks, ArrayList<EnemyObject> enemies,
                         ArrayList<PipeObject> pipes, PipeObject spawnPipe) {
        this.length = length;
        this.time = time;
        this.blocks = blocks;
        this.enemies = enemies;
        this.pipes = pipes;
        this.spawnPipe = spawnPipe;
    }

    // Getters and Setters
    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        SectionObject.HEIGHT = HEIGHT;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<BlockObject> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<BlockObject> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<EnemyObject> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<EnemyObject> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<PipeObject> getPipes() {
        return pipes;
    }

    public void setPipes(ArrayList<PipeObject> pipes) {
        this.pipes = pipes;
    }

    public PipeObject getSpawnPipe() {
        return spawnPipe;
    }

    public void setSpawnPipe(PipeObject spawnPipe) {
        this.spawnPipe = spawnPipe;
    }
}
