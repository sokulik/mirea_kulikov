package Practice5_10.Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String USER_FILE = "users.dat";

    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Ошибка при загрузке пользователей: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveUser(User user) {
        List<User> users = loadUsers();

        if (users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            System.out.println("⚠️ Пользователь с таким именем уже существует");
            return;
        }

        users.add(user);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
            System.out.println("✅ Пользователь " + user.getUsername() + " успешно зарегистрирован");
        } catch (IOException e) {
            System.out.println("❌ Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

    public static boolean userExists(String username) {
        return loadUsers().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public static boolean validateUser(String username, String password) {
        return loadUsers().stream()
                .anyMatch(user -> user.getUsername().equals(username) &&
                        user.getPassword().equals(password));
    }

    public static int getUserCount() {
        return loadUsers().size();
    }
}