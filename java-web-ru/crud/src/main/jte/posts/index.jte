@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage;
@param PostsPage page

@template.layout.page(
content = @`
    @for(var post : page.getPosts())
        <div>
            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
        </div>
    @endfor
    <br><br>
    @if(page.getCurrentPageNumber() > 1)
        <a href="${NamedRoutes.postsPath(page.getCurrentPageNumber() - 1)}">Назад</a>
    @endif
    @if(page.getCurrentPageNumber() < page.getMaxPageNumber())
        <a href="${NamedRoutes.postsPath(page.getCurrentPageNumber() + 1)}">Вперед</a>
    @endif
`
)