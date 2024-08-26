@import exercise.dto.users.UsersPage;
@param UsersPage page

@template.layout.page(
content = @`
    @for(var user : page.getUsers())
        <div>
            <a href="/users/${user.getId()}">${user.getLastName()}</a>
        </div>
    @endfor
`
)