package com.example.restclientservweb;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class User {

    String id;
    String email;
    String username;
    String password;
    int dinero = 20;

    List<Products> productos;


    public User(String email, String username, String password) {
        this();
        this.email = email;
        this.username = username;
        this.password = password;
        this.productos = new LinkedList<>();
    }
    public User() {
    }

    private void setId(String id) {
        this.id=id;
    }

    public String getId(){
        return id;
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

    public List<Products> getProductos(){return productos; }
    public void addProducto(Products p){
        productos.add(p);
        dinero = dinero - p.getPrice();
    }

    public int getDinero(){
        return this.dinero;
    }
    public String getEmail(){
        return email;
    }
    public void setDinero(int dinero){
        this.dinero = dinero;
    }


    public void setProductos(List<Products> productos){this.productos = productos; }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public void setUser(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.dinero = user.getDinero();
        this.productos = user.getProductos();
    }
//
//    public User(String username, int dinero) {
//        this.username = username;
//        this.dinero = dinero;
//        this.productos = new LinkedList<>();
//    }
//


    private String login;
    private String avatarUrl;

    public User(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    @SerializedName("public_repos")
    private int publicRepos;

    private int followers;


    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}