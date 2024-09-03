@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
content = @`
    @if(page.getError() != null)
        <div class="mb-3">
            <label class="error-label">* ${page.getError()}</label>
        </div>
    @endif

    <div class="mx-auto p-4 py-md-5">
        <form action="/sessions" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Имя
                    <input type="text" class="form-control" name="name" value="${page.getName()}" />
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">
                    Пароль
                    <input type="text" class="form-control" name="password" value="${page.getPassword()}" />
                </label>
            </div>
            <input type="submit" class="btn btn-primary" value="Войти" />
        </form>
    </div>
`
)
