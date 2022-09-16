package sneed.venusrestblog.data;
import org.springframework.beans.BeanUtils;
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
import javax.swing.text.html.Option;
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
        Optional<User> optionalUser = usersRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
        }
        return optionalUser;
    }

    @GetMapping("/me")
    private Optional<User> fetchMe() {
        return usersRepository.findById(1L);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User newUser) {
//        TODO: validate new user fields
        // don't need the below line at this point but just for kicks
        newUser.setCreatedAt(LocalDate.now());
        usersRepository.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + "not found ");
        }
        usersRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User updatedUser, @PathVariable long id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
        }
//        get the user from the optional so we no longer have to deal with the optional
        User originalUser = optionalUser.get();

        // merge the changed data in updatedUser with originalUser
        BeanUtils.copyProperties(updatedUser, originalUser, FieldHelper.getNullPropertyNames(updatedUser));

//        originalUser now has the merged data (changes + original data)
        originalUser.setId(id);

        usersRepository.save(updatedUser);
    }

    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @RequestParam String newPassword) {
        Optional<User> optionalUser = usersRepository.findById(id);
//        User user = usersRepository.findById(id).get();
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " not found");
        }
        User user = optionalUser.get();

//        compare old password with saved pw
        if(!user.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "amscray");
        }

//        validate new password
        if(newPassword.length() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "new pw length must be at least 3 characters");
        }
        user.setPassword(newPassword);
        usersRepository.save(user);
    }
}

