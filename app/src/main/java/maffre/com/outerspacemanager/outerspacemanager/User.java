package maffre.com.outerspacemanager.outerspacemanager;

/**
 * Created by maffreh on 06/03/2017.
 */

public class User {

    public String username;
    public String password;
    public String token;
    public long expires;
    public int points;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public String getAccessToken() {
        return token;
    }

    public long getExpiration() {
        return expires;
    }
}
