package exercise;

import exercise.dto.users.UsersPage;
import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.rendering.template.TemplateUtil;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/users", ctx -> {
            ctx.contentType("text/html;charset=utf-8");
            var page = new UsersPage(USERS);
            ctx.render("users/index.jte", TemplateUtil.model("page", page));
        });
        app.get("/users/{id}", ctx -> {
            ctx.contentType("text/html;charset=utf-8");
            var id = ctx.pathParam("id");
            var resultUser = USERS.stream().filter(v -> v.getId() == Long.parseLong(id)).findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var page = new UserPage(resultUser);
            ctx.render("users/show.jte", TemplateUtil.model("page", page));
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
