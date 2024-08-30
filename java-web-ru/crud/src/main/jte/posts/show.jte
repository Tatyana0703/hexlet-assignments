@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <h1>${page.getPost().getId()}</h1>
    <p>${page.getPost().getName()}</p>
    <p>${page.getPost().getBody()}</p>
    <br><br>
    <a href="${NamedRoutes.postsPath(page.getPostsCurrentPageNumber())}">Назад</a>
`
)