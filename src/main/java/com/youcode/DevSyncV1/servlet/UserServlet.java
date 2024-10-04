package com.youcode.DevSyncV1.servlet;

import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.entities.enums.Role;
import com.youcode.DevSyncV1.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users/*")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            listUsers(request, response);
        } else if (pathInfo.equals("/create")) {
            showCreateUserForm(request, response);
        } else if (pathInfo.equals("/edit")) {
            getUserById(request, response);
        } else if (pathInfo.equals("/delete")) {
            deleteUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showCreateUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/assets/createUser.jsp").forward(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/assets/listUsers.jsp").forward(request, response);
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(id);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/assets/updateUser.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(id);

        if (user != null) {
            userService.deleteUser(id);
            response.sendRedirect(request.getContextPath() + "/users");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (pathInfo.equals("/create")) {
            createUser(request, response);
        } else if (pathInfo.equals("/update")) {
            updateUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Role role = Role.valueOf(request.getParameter("role"));

        User newUser = new User(username, password, firstName, lastName, email, role);
        userService.createUser(newUser);

        response.sendRedirect(request.getContextPath() + "/users");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Role role = Role.valueOf(request.getParameter("role"));

        User user = userService.getUserById(id);
        if (user != null) {
            user.setUsername(username);
            if (password != null && !password.isEmpty()) {
                user.setPassword(password); // In a real app, you'd hash this password
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setRole(role);
            userService.updateUser(user);
            response.sendRedirect(request.getContextPath() + "/users");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
