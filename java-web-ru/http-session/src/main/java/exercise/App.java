package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.bundledPlugins.enableDevLogging());
        app.get("/users", ctx -> {
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(null);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(null);
            if (page != null && per != null) {
                ctx.json(USERS.stream().skip((long) (page - 1) * per).limit(per).toList());
            } else {
                ctx.json(USERS.stream().limit(5).toList());
            }
        });
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
