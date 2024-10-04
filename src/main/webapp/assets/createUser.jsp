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
            <form action="http://localhost:8081/DevSyncV1_war_exploded/user?action=create" method="post">

                <input type="hidden" name="action" value="create">

                <div class="form-group mb-3">
                    <label for="username">Nom d'utilisateur</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Entrez le nom d'utilisateur" required>
                </div>

                <div class="form-group mb-3">
                    <label for="password">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
                </div>

                <div class="form-group mb-3">
                    <label for="firstName">Prénom</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Entrez le prénom" required>
                </div>

                <div class="form-group mb-3">
                    <label for="lastName">Nom de famille</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Entrez le nom de famille" required>
                </div>

                <div class="form-group mb-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Entrez l'email" required>
                    <small id="emailHelp" class="form-text text-muted">Votre email ne sera jamais partagé.</small>
                </div>

                <div class="form-group mb-3">
                    <label for="role">Rôle</label>
                    <select class="form-select" id="role" name="role" required>
                        <option value="MANAGER">Admin</option>
                        <option value="USER">User</option>
                    </select>
                </div>

                <div class="form-check mb-4">
                    <input type="checkbox" class="form-check-input" id="agreeTerms" required>
                    <label class="form-check-label" for="agreeTerms">J'accepte les conditions d'utilisation</label>
                </div>

                <button type="submit" class="btn btn-primary w-100">Créer utilisateur</button>
            </form>
    </div>
</div>

<!-- Inclusion de Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
