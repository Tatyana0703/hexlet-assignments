package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment show(@PathVariable long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment data) {
        var comment = new Comment(data.getBody());
        if (data.getPostId() != null) {
            comment.setPostId(data.getPostId());
        }
        return commentRepository.save(comment);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable long id, @RequestBody Comment data) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        if (data.getPostId() != null) {
            comment.setPostId(data.getPostId());
        }
        comment.setBody(data.getBody());
        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        commentRepository.findById(id).ifPresentOrElse(comment -> commentRepository.deleteById(comment.getId()),
                () -> {
                    throw new ResourceNotFoundException("Comment with id " + id + " not found");
                });
    }
}
// END
