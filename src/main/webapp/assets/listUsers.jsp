<%@ page import="com.youcode.DevSyncV1.entities.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 10/1/2024
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>DevSync</title>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-center">Liste des utilisateurs</h2>
        <div class="text-center">
            <form action="assets/createUser.jsp" method="get">
                <button type="submit" class="btn btn-primary">Créer un utilisateur</button>
            </form>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Nom d'utilisateur</th>
                <th>Prénom</th>
                <th>Nom de famille</th>
                <th>Email</th>
                <th>Rôle</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getLastName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
                <td>
                    <div class="d-flex justify-content-around align-items-center">
                        <!-- Edit User Form -->
                        <form action="users/edit" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <button type="submit" class="btn btn-primary">Modifier</button>
                        </form>
                        <!-- Delete User Form -->
                        <form action="users/delete" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <button type="submit" class="btn btn-danger">Supprimer</button>
                        </form>
                    </div>
                </td>


            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>

</div>

<!-- Inclusion de Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
