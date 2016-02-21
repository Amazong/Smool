/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smool.shared;

import java.io.Serializable;

/**
 *
 * @author Margarida
 */
public class User implements Serializable{
    protected String username;
    protected int id;
    protected String password;
    public int getID(){
        return this.id;
    }
    public String getPassword(){
        return this.password;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String s){
        this.username = s;
    }
    public void setID(int n){
        this.id = n;
    }
    public void setPassword(String s){
        this.password = s;
    }

    public User(){}


}
