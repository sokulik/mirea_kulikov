package Practices.Practice4.Classes;
import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username+":"+password;
    }

    public static User fromString(String userString){
        String[] parts = userString.split(":");
        if (parts.length == 2) {
            return new User(parts[0], parts[1]);
        }
        return null;
    }


}
