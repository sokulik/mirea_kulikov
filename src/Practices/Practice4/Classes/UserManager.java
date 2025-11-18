package Practices.Practice4.Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String USER_FILE = "user.dat";
    public static void saveUser(User user){
        List<User> users = loadUsers();
        users.add(user);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))){
            oos.writeObject(users);

        }
        catch (IOException e){
            System.out.println("Ошибка при сохранении пользователя"+e.getMessage());
        }
    }
    public static List<User> loadUsers(){
        File file = new File(USER_FILE);
        if (!file.exists()){
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))){
            return (List<User>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Ошибка при загрузке пользователей: "+e.getMessage());
            return new ArrayList<>();
        }
    }


    public static boolean userExist(String username){
        List<User> users = loadUsers();
        for (User user : users){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean validateUser(String username, String password){
        List<User> users = loadUsers();
        for (User user : users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


}
