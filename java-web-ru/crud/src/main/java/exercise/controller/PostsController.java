package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    private static final int POSTS_ON_PAGE = 5;
    private static final int CURRENT_PAGE_NUMBER = 1;

    private PostsController() {
        throw new IllegalStateException("Utility class");
    }

    public static void index(Context ctx) {
        var currentPageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(CURRENT_PAGE_NUMBER);
        var posts = PostRepository.findAll(currentPageNumber, POSTS_ON_PAGE);
        int maxPageNumber = PostRepository.getEntities().size() / POSTS_ON_PAGE + 1;
        var page = new PostsPage(posts, currentPageNumber, maxPageNumber);
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> {
                    ctx.status(404);
                    return new NotFoundResponse("Entity with id = " + id + " not found");
                });
        var postNumber = PostRepository.getEntities().indexOf(post) + 1;
        var postsCurrentPageNumber = (postNumber % POSTS_ON_PAGE == 0) ? postNumber / POSTS_ON_PAGE : postNumber / POSTS_ON_PAGE + 1;
        var page = new PostPage(post, postsCurrentPageNumber);
        ctx.render("posts/show.jte", model("page", page));
    }
}
