package com.parim.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.parim.model.GameObject;
import com.parim.model.LevelObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameAccess {
    private static final int NUMBER_OF_LEVELS = 1;
    private ObjectMapper mapper;
    private final String directory = "src/main/resources/game/L";
    private ArrayList<LevelObject> levelObjects = new ArrayList<>();

    public GameAccess(){
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public GameObject loadGame(){
        for (int i = 1; i <= NUMBER_OF_LEVELS; i++){
            try {
                File levelFile = new File(directory + String.valueOf(i) + ".json");
                LevelObject levelObject = mapper.readValue(levelFile, LevelObject.class);
                levelObject.addFloors();
                levelObjects.add(levelObject);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new GameObject(levelObjects);
    }}
