package Practice5_10.Classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User implements Serializable {
    private String username;
    private String password;
    private LocalDateTime registrationDate;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
    }

    // Геттеры
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "👤 " + username + " (зарегистрирован: " +
                registrationDate.format(formatter) + ")";
    }

    public static User fromString(String userString) {
        String[] parts = userString.split(":");
        if (parts.length == 2) {
            return new User(parts[0], parts[1]);
        }
        return null;
    }
}