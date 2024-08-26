@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<p>
    <a href="/">Ссылка на главную страницу</a>
    <a href="/users">Ссылка на страницу просмотра всех пользователей</a>
</p>
<br>
${content}
<br>
<p>Thanks for visiting, come again soon!</p>
</body>
</html>
