package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String postsPath(int pageNumber) {
        return "/posts?page=" + pageNumber;
    }

    // Это нужно, чтобы не преобразовывать типы снаружи
    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    private static String postPath(String id) {
        return "/posts/" + id;
    }
}
