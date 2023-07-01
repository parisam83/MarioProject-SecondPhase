package com.parim.view;

import com.parim.controller.GameController;
import com.parim.view.menus.*;
import com.parim.view.menus.login.LoginPage;
import com.parim.view.menus.login.RegisterPage;
import com.parim.view.menus.saveGame.PreviousGamePage;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private FirstPage firstPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private MenuPage menuPage;
    private ProfilePage profilePage;
    private LeaderBoardPage leaderBoardPage;
    private NewGamePage newGamePage;
    private PreviousGamePage previousGamePage;
    private ShopPage shopPage;

    public MainFrame(){
        if (instance != null) instance.dispose();
        instance = this;

        this.setTitle("Super Mario Game!");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocation(0, 0);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }

    public void setFirstPage(){
        firstPage = new FirstPage();
        setPage(firstPage);
    }

    public void setLoginPage() {
        loginPage = new LoginPage();
        setPage(loginPage);
    }
    public void setRegisterPage() {
        registerPage = new RegisterPage();
        setPage(registerPage);
    }
    public void setMenuPage(){
        menuPage = new MenuPage();
        setPage(menuPage);
    }
    public void setProfilePage(){
        profilePage = new ProfilePage(GameController.getUser());
        setPage(profilePage);
    }

    public void setShopPage(){
        shopPage = new ShopPage(GameController.getUser());
        setPage(shopPage);
    }

    public void setLeaderBoardPage(){
        leaderBoardPage = new LeaderBoardPage();
        leaderBoardPage.addData();
        setPage(leaderBoardPage);
    }

    public void setNewGamePage() {
        newGamePage = new NewGamePage(GameController.getUser());
        setPage(newGamePage);
    }
    public void setPreviousGamePage() {
        previousGamePage = new PreviousGamePage(GameController.getUser());
        setPage(previousGamePage);
    }

    private void setPage(JPanel panel){
        this.setContentPane(panel);
        this.setVisible(true);
        panel.requestFocus();
        panel.setVisible(true);
    }

    // Errors for login
    public void addOptionPane(String message){
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    public void emptyValueInputError(){
        JOptionPane.showMessageDialog(this, "You cannot set your username or password empty!", "EmptyValueInputError", JOptionPane.ERROR_MESSAGE);
    }
    public void defaultValueInputError(){
        JOptionPane.showMessageDialog(this, "You cannot set your username and password as their default value!", "DefaultValueInputError", JOptionPane.ERROR_MESSAGE);
    }
    public void unsuccessfulLoginError(){
        JOptionPane.showMessageDialog(this, "Username or password is not correct!", "UnsuccessfulLoginError", JOptionPane.ERROR_MESSAGE);
    }

    public void unsuccessfulRegisterError(){
        JOptionPane.showMessageDialog(this, "This username is taken. Please try another username.", "UnsuccessfulRegisterError", JOptionPane.ERROR_MESSAGE);
    }
}
