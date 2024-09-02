@import exercise.dto.posts.EditPostPage
@param EditPostPage page

@template.layout.page(
content = @`
    @if(page.getErrors() != null)
        <div class="mb-3">
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        </div>
    @endif

    <div class="mx-auto p-4 py-md-5">
        <form action="/posts/${page.getPost().getId()}" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Название
                    @if(page.getErrors() != null)
                        <input type="text" class="form-control" name="name" value="${page.getName()}" />
                    @else
                        <input type="text" class="form-control" name="name" value="${page.getPost().getName()}" />
                    @endif
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">Содержание</label>
                @if(page.getErrors() != null)
                    <textarea class="form-control" rows="20" cols="70" type="text" name="body">${page.getBody()}</textarea>
                @else
                    <textarea class="form-control" rows="20" cols="70" type="text" name="body">${page.getPost().getBody()}</textarea>
                @endif
            </div>
            <input type="submit" class="btn btn-primary" value="Редактировать" />
        </form>
    </div>
`
)