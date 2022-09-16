package sneed.venusrestblog;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sneed.venusrestblog.data.Post;
import sneed.venusrestblog.data.Category;
import sneed.venusrestblog.data.PostsRepository;
import sneed.venusrestblog.data.User;
import sneed.venusrestblog.data.respository.CategoriesRepository;
import sneed.venusrestblog.data.respository.UsersRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostsController {
    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;
//    public PostsController(PostsRepository postsRepository) {
//        this.postsRepository = postsRepository;
//}
    @GetMapping("")
//       TODO: go get some posts
    public List<Post> fetchPosts(){
        return postsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> fetchPostById(@PathVariable long id) {
        return postsRepository.findById(id);
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost) {

//        use fake author for the post
        User author = usersRepository.findById(1L).get();
        newPost.setAuthor(author);

            Category cat1 = categoriesRepository.findById(1L).get();
            Category cat2 = categoriesRepository.findById(2L).get();
            newPost.setCategories(new ArrayList<>());
            newPost.getCategories().add(cat1);
            newPost.getCategories().add(cat2);

//        use a fake author for the posts
//        User fakeAuthor = new User();
//        fakeAuthor.setId(99);
//        fakeAuthor.setUserName("fake author");
//        fakeAuthor.setEmail("fakeauthor@stuff.com");
//        newPost.setAuthor(fakeAuthor);
////        make some fake categories and throw them in the new post
postsRepository.save(newPost);
    }
        @DeleteMapping("/{id}")
        public void deletePostById(@PathVariable long id) {
        postsRepository.deleteById(id);
//        TODO: search thru the list of posts
//        and delete the post the post that matches the given id
//            Post post = findPostById(id);
//            if(post != null) {
//              posts.remove(post);
//               return;
                }
//            what to do if we don't find it
//            throw new RuntimeException("Could not find that post");

        @PutMapping("/{id}")
        public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
        //find the post to update in the post list
            updatedPost.setId(id);

            Optional<Post> originalPost = postsRepository.findById(id);
            if(originalPost.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + "not founc");
            }
            BeanUtils.copyProperties((updatedPost, originalPost.get(), FieldHelper.getNullPropertyNames(updatedPost));

            postsRepository.save(originalPost.get());
//        Post post = findPostById(id);
//        if(post == null) {
//                    System.out.println("Post not found");
//        } else {
//            if(updatedPost.getTitle() != null) {
//                post.setTitle(updatedPost.getTitle());
//                    }
//            if(updatedPost.getContent() != null) {
//                post.setContent(updatedPost.getContent());
//                    }
//            return;
//                }
//        throw new RuntimeException("Post not found");
            }
        }





