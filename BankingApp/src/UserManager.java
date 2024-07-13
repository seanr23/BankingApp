import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public void register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        String hashedPassword = hashPassword(password);
        users.put(username, new User(username, hashedPassword));
        System.out.println("User registered successfully!");
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user == null || !user.getHashedPassword().equals(hashPassword(password))) {
            System.out.println("Invalid username or password.");
            return false;
        }
        System.out.println("Login successful!");
        return true;
    }

    private String hashPassword(String password) {
        // Implement a secure hash function (e.g., using SHA-256)
        return Integer.toString(password.hashCode()); // Simplified example
    }
}
