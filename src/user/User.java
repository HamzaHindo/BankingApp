package user;
import account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class User {
    public UUID id;
    public String username;
    public int age;
    public String hashedPass;

    public User(UUID id, String username, String hashedPass){
        this.id = id;
        this.username = username;
        this.hashedPass = hashedPass;
    }
    public User(UUID id, String username, String hashedPass,int age) {
        this(id, username, hashedPass);
        this.age = age;
    }
    public boolean is_admin() {
        return UUID.fromString("08d4b7d0-e39f-4d91-a59b-0aa94933cd28")
                .equals(this.id);
    }
}
