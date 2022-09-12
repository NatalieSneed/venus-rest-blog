package sneed.venusrestblog.data;

import sneed.venusrestblog.data.Post;
import sneed.venusrestblog.data.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UsersController {
    private List<User> users = new ArrayList<>();
    private long nextId = 1;

    @GetMapping("/{id}")
    public User fetchUserById(@PathVariable long id) {
//        search through the list of posts
//        and return the post that matches the given id
        User user = findUserById(id);
        if (user == null) {
            throw new RuntimeException("what is going on?!");
        }
//        when found the post so just return it
        return user;
    }
    @GetMapping("/username/{username}")
    private User fetchByUsername(@PathVariable String username) {
        User user = findUserByUserName(username);
        if (user == null) {
            throw new RuntimeException("what is going on?!");
        }
        return user;
    }
    private User findUserByUserName(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    private User findUserById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
//        didn't find it so do something else
        return null;
    }
    @PostMapping("/create")
    public void createUser(@RequestBody User newUser) {
//        assign nextId to the new post
        newUser.setId(nextId);
        nextId++;
        users.add(newUser);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
//        search thru the list of posts
//        and delete the post the post that matches the given id
        User user = findUserById(id);
        if(user != null) {
            users.remove(user);
            return;
        }
//            what to do if we don't find it
        throw new RuntimeException("Could not find that post");
    }
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User updatedUser, @PathVariable long id) {
        User user = findUserById(id);
        if (user == null) {
            System.out.println("User not found");
        }else{
            if(updatedUser.getEmail() !=null) {
                user.setEmail(updatedUser.getEmail());
            }
            return;
        }
        throw new RuntimeException("Could not find that post");
    }
}
