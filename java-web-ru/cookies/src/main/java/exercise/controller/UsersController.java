package exercise.controller;

import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = Security.encrypt(ctx.formParamAsClass("password", String.class).get());
        var token = Security.generateToken();

        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));

        var cookieToken = ctx.cookie("token");
        var userToken = user.getToken();

        if (cookieToken != null && cookieToken.equals(userToken)) {
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.render("users/build.jte");
        }


    }
    // END
}
