@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" required name="term" value="${page.getTerm()}">
        <input type="submit" value="Искать">
    </form>
    @for(var user : page.getUsers())
        <h1>${user.getId()}</h1>
        <p>${user.getLastName()} ${user.getFirstName()}</p>
        <p>${user.getEmail()}</p>
    @endfor
`
)