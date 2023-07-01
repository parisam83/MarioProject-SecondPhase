package com.parim.model.user;

import com.parim.model.GameObject;

import java.util.ArrayList;

public class User {
    private int id, maxScore, coins;
    private String username, password;
    private Character currentCharacter = Character.Mario;
    private ArrayList<GameObject> savedGames = new ArrayList<>();
    private ArrayList<Character> characters = new ArrayList<Character>(){{add(currentCharacter);}};

    public User(){}
    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void addRemoveGame(int indexToDelete, GameObject gameToAdd){
        if (indexToDelete >= 0 && indexToDelete < savedGames.size()) savedGames.remove(indexToDelete);
        savedGames.add(gameToAdd);
    }
    public void addRemoveGame(GameObject gameToDelete, GameObject gameToAdd){
        if (gameToDelete != null) savedGames.remove(gameToDelete);
        if (gameToAdd !=  null) savedGames.add(gameToAdd);
    }
    public GameObject GetLastAddedGame(){
        if (savedGames.size() > 0) return savedGames.get(savedGames.size() - 1);
        return null;
    }
    public void addCharacter(Character character){
        characters.add(character);
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public ArrayList<GameObject> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(ArrayList<GameObject> savedGames) {
        this.savedGames = savedGames;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
