package maffre.com.outerspacemanager.outerspacemanager.models;

/**
 * Created by maffreh on 06/03/2017.
 */

public class User {

    public String username;
    public String password;



    public String email;
    public String token;
    public long expires;
    public double minerals;
    public double gas;
    public float points;

    //pour l'inscription
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //pour la connection
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getEmail() { return email; }

    public String getUsername() {
        return username;
    }

    public double getGas() {
        return gas;
    }

    public double getMinerals() {
        return minerals;
    }

    public float getPoints() {
        return points;
    }

    public String getAccessToken() {
        return token;
    }

    public long getExpiration() {
        return expires;
    }
}
