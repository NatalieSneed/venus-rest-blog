package sneed.venusrestblog;

import org.springframework.web.bind.annotation.*;
import sneed.venusrestblog.data.Post;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostsController {
    private List<Post> posts = new ArrayList<>();
private long nextId = 1;
    @GetMapping("")
////        @RequestMapping(value = "/", method = RequestMethod.GET);
//       TODO: go get some posts
    public List<Post> fetchPosts() {
        return posts;
    }

    @GetMapping("/{id}")
    public Post fetchPostById(@PathVariable long id) {
//        TODO: search thru the list of posts
//        and return the post that matches the given id
        Post post = findPostById(id);
        if (post == null) {
            throw new RuntimeException("what is going on?!");
        }
        return post;
    }
    private Post findPostById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        //didn't find it so do something else
        return null;
    }
    @PostMapping("")
    public void createPost(@RequestBody Post newPost) {
//        assign nextId to the new post
        newPost.setId(nextId);
        nextId++;
        posts.add(newPost);
    }
        @DeleteMapping("/{id}")
        public void deletePostById(@PathVariable long id) {
//        TODO: search thru the list of posts
//        and delete the post the post that matches the given id
            Post post = findPostById(id);
            if(post != null) {
              posts.remove(post);
               return;
                }
//            what to do if we don't find it
            throw new RuntimeException("don't know what's going on");
        }
        @PutMapping("/{id}")
                public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
        //find the post to update in the post list
        Post post = findPostById(id);
                if(post == null) {
                    System.out.println("Post not found");
                } else {
                    if(updatedPost.getTitle() != null) {
                        post.setTitle(updatedPost.getTitle());
                    }
                    if(updatedPost.getContent() != null) {
                        post.setContent(updatedPost.getContent());
                    }
                    return;
                }
                throw new RuntimeException("Post not found");
            }
        }





