package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testRootPage() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testListUsers() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).hasSizeGreaterThanOrEqualTo(3)
                .contains("Bobbi", "Wehner")
                .contains("Will", "Casper")
                .contains("Darryl", "Ward");
    }

    @Test
    void testListUsers1() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users?term=s").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).hasSizeGreaterThanOrEqualTo(3)
                .contains("Stacee", "Tremblay")
                .contains("Solomon", "Bayer")
                .contains("Serina", "Schaden")
                .doesNotContain("Bobbi", "Wehner");
    }

    @Test
    void testListUsers2() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users?term=WI").asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).hasSizeGreaterThanOrEqualTo(2)
                .contains("Will", "Casper")
                .contains("Willis", "Jast")
                .doesNotContain("Wendell", "Legros");
    }
}
