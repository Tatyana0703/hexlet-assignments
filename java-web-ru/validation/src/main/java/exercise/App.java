package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import exercise.repository.ArticleRepository;
import io.javalin.validation.ValidationError;
import io.javalin.validation.Validator;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage();
            ctx.render("articles/build.jte", model("page", page));
        });

        app.post("/articles", ctx -> {
            String enteredTitle = ctx.formParam("title");
            String enteredContent = ctx.formParam("content");

            Validator<String> titleValidator = ctx.formParamAsClass("title", String.class);
            Validator<String> contentValidator = ctx.formParamAsClass("content", String.class);

            titleValidator.check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .check(value -> !ArticleRepository.existsByTitle(value), "Статья с таким названием уже существует");
            Map<String, List<ValidationError<String>>> titleErrors = titleValidator.errors();

            contentValidator.check(value -> value.length() >= 10, "Статья должна быть не короче 10 символов");
            Map<String, List<ValidationError<String>>> contentErrors = contentValidator.errors();

            Map<String, List<ValidationError<String>>> allErrors =
                    Stream.concat(titleErrors.entrySet().stream(), contentErrors.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            if (allErrors.isEmpty()) {
                var article = new Article(enteredTitle, enteredContent);
                ArticleRepository.save(article);
                ctx.redirect("/articles");
            } else {
                ctx.status(422);
                var page = new BuildArticlePage(enteredTitle, enteredContent, allErrors);
                ctx.render("articles/build.jte", model("page", page));
            }

        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
