package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var name = ctx.sessionAttribute("currentUser");
        MainPage page;
        if (name == null) {
            page = new MainPage();
        } else {
            page = new MainPage(name.toString());
        }
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage();
        ctx.render("build.jte", model("page", page));
    }

    public static void login(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();

        var user = UsersRepository.findByName(name).orElse(null);
        if (user != null && user.getPassword().equals(Security.encrypt(password))) {
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            var page = new LoginPage(name, password, "Wrong username or password");
            ctx.render("build.jte", model("page", page)).status(422);
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
