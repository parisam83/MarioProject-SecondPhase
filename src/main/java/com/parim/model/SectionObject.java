package com.parim.model;

import com.parim.model.components.blocks.Block;
import com.parim.model.components.blocks.Floor;
import com.parim.model.components.enemies.Enemy;
import com.parim.model.components.pipes.Pipe;

import java.util.ArrayList;

public class SectionObject {
    private static int HEIGHT = 20;
    private int length = 10, time = 0;
    private ArrayList<Block> blocks;
    private ArrayList<Enemy> enemies;
    private ArrayList<Pipe> pipes;
    private Pipe spawnPipe;

    public SectionObject(){}
    public SectionObject(int length, int time, ArrayList<Block> blocks, ArrayList<Enemy> enemies,
                         ArrayList<Pipe> pipes, Pipe spawnPipe) {
        this.length = length;
        this.time = time;
        this.blocks = blocks;
        this.enemies = enemies;
        this.pipes = pipes;
        this.spawnPipe = spawnPipe;
        addFloors();
    }

    public void addFloors(){
        for (int i = 0; i < HEIGHT*2; i++){
            blocks.add(new Floor(i, -1));
            blocks.add(new Floor(i, -2));
        }
    }

    public void updateTime(){
        time++;
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

    public Pipe getSpawnPipe() {
        return spawnPipe;
    }

    public void setSpawnPipe(Pipe spawnPipe) {
        this.spawnPipe = spawnPipe;
    }
}
