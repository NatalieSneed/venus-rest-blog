package sneed.venusrestblog.data;
import sneed.venusrestblog.data.respository.UsersRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import sneed.venusrestblog.PostsController;
import sneed.venusrestblog.data.Post;
import sneed.venusrestblog.data.User;
import sneed.venusrestblog.data.PostsRepository;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UsersController {
    private UsersRepository usersRepository;

    //    private UsersRepository postsRepository;
//    @PostConstruct
//    public UsersController(PostsRepository postsRepository) {
//        this.postsRepository = postsRepository;
//    }
////    private List<User> users = new ArrayList<>(List.of(new User(1, "nat", "natnat@natnat.who", "55555", LocalDate.now(), UserRole.ADMIN, new ArrayList<>())));
////    private long nextId = 1;
    @GetMapping("")
    public List<User> fetchUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> fetchUserById(@PathVariable long id) {
        return usersRepository.findById(id);
    }

    @GetMapping("/me")
    private Optional<User> fetchMe() {
        return usersRepository.findById(1L);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User newUser) {
        // don't need the below line at this point but just for kicks
        newUser.setCreatedAt(LocalDate.now());
        usersRepository.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        usersRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User updatedUser, @PathVariable long id) {
        // find the post to update in the posts list
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @RequestParam String newPassword) {
        User user = usersRepository.findById(id).get();
        if(!user.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "amscray");
        }

        if(newPassword.length() < 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "new pw length must be at least 3 characters");
        }
        user.setPassword(newPassword);
        usersRepository.save(user);
    }
    }

