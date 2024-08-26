@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <h1>${page.getUser().getId()}</h1>
    <p>${page.getUser().getLastName()} ${page.getUser().getFirstName()}</p>
    <p>${page.getUser().getEmail()}</p>
`
)
