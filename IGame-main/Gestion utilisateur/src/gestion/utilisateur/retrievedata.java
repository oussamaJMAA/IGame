/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

/**
 *
 * @author oussa
 */
public final class retrievedata {
     private static retrievedata instance;

   
private String image ;
    private String username;
    private int id ;
   public retrievedata(){
    this.image="";
    this.username="";
    
} 

    public retrievedata(String username,String image,int id) {
        this.username = username;
        this.image=image;
        this.id=id;
        
        
               
    }

    public retrievedata(int id) {
        this.id=id;
 }
    public void setImage(String image) {
        this.image = image;
    }
   
    public static retrievedata getInstance(String username,String image,int id) {
        if(instance == null) {
            instance = new retrievedata(username,image,id);
        }
        return instance;
    }
    

    public String getImage() {
        return image;
    }

    public static retrievedata getInstance() {
        return instance;
    }

    public static void setInstance(retrievedata instance) {
        retrievedata.instance = instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

  

    public void cleanUserSession() {
        
 this.username = "";// or null 
this.image="";// or null

    }

    @Override
    public String toString() {
        return "retrievedata{" + "username=" + username + ", id=" + id + '}';
    }

  
}
   
    

