package exercise.dto;

import java.util.List;

import exercise.model.Comment;
import exercise.model.Post;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Setter
@Getter
public class PostDTO {
    private long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;

    public static PostDTO getInstance(Post post, List<Comment> comments) {
        var postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());
        List<CommentDTO> commentsDto = comments.stream().map(v -> new CommentDTO(v.getId(), v.getBody())).toList();
        postDto.setComments(commentsDto);
        return postDto;
    }
}
// END
