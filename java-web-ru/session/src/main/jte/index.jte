@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            @if(page.getName() == null)
                <div class="mb-3">
                    <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
                </div>
            @else
                <div class="mb-3">
                    <p>Привет, ${page.getName()}!</p>
                    <div class="mx-auto p-4 py-md-5">
                        <form action="${NamedRoutes.logoutPath()}" method="post">
                            <input type="submit" class="btn btn-primary" value="Выйти" />
                        </form>
                    </div>
                </div>
            @endif
        </main>
    </div>
`
)
