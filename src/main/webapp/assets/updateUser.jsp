<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>DevSync</title>
</head>
<body class="bg-light">

<div class="container mt-5 d-flex  flex-column align-items-center ">
    <h2 class="text-center mb-4">Modifier l'utilisateur</h2>
    <div class="card p-4 shadow col-11 col-md-8 " >
        <form action="http://localhost:8081/DevSyncV1_war_exploded/users?action=update" method="get">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= user.getId() %>">

            <div class="mb-3">
                <label for="username" class="form-label">Nom d'utilisateur:</label>
                <input type="text" name="username" id="username" class="form-control" value="${user.getUserN}" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Mot de passe:</label>
                <input type="password" name="password" id="password" class="form-control">
                <div id="passwordHelp" class="form-text">Laissez vide pour garder le mot de passe actuel.</div>
            </div>

            <div class="mb-3">
                <label for="firstName" class="form-label">Prénom:</label>
                <input type="text" name="firstName" id="firstName" class="form-control" value="${user.firstName}" required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Nom de famille:</label>
                <input type="text" name="lastName" id="lastName" class="form-control" value="${user.lastName}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" name="email" id="email" class="form-control" value="${user.email}" required>
            </div>

            <div class="mb-3">
                <label for="role" class="form-label">Rôle:</label>
                <select name="role" id="role" class="form-select" required>
                    <option value="MANAGER"  ${user.role == 'MANAGER' ? 'selected' : ''}>MANAGER</option>
                    <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>User</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Mettre à jour</button>
        </form>
    </div>
</div>

<!-- Inclusion de Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>