package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users")
public class PostsController {

    private final List<Post> posts = Data.getPosts();

    @GetMapping("/{id}/posts")
    public List<Post> showPostsByUserId(@PathVariable Integer id) {
        return posts.stream().filter(v -> v.getUserId() == id).toList();
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPostByUserId(@PathVariable Integer id, @RequestBody Post post) {
        var createPost = new Post();
        createPost.setUserId(id);
        createPost.setSlug(post.getSlug());
        createPost.setTitle(post.getTitle());
        createPost.setBody(post.getBody());

        posts.add(createPost);
        return createPost;
    }
}
// END
