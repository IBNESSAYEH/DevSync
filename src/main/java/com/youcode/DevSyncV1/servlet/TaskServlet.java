package com.youcode.DevSyncV1.servlet;

import com.youcode.DevSyncV1.entities.Tag;
import com.youcode.DevSyncV1.entities.Task;
import com.youcode.DevSyncV1.entities.User;
import com.youcode.DevSyncV1.service.TagService;
import com.youcode.DevSyncV1.service.TaskService;
import com.youcode.DevSyncV1.service.UserService;
import com.youcode.DevSyncV1.service.implementation.TagServiceImpl;
import com.youcode.DevSyncV1.service.implementation.TaskServiceImpl;
import com.youcode.DevSyncV1.service.implementation.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "TaskServlet", value = "/tasks/*")
public class TaskServlet extends HttpServlet {

    private TaskService taskService;
    private TagService tagService;
    private UserService userService;


    @Override
    public void init() throws ServletException {

        taskService = new TaskServiceImpl();
        tagService = new TagServiceImpl();
        userService = new UserServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = taskService.findAll();
        List<User> users = userService.getAllUsers();
        List<Tag> tags = getAllTags(request, response);
        request.setAttribute("tags", tags);
        request.setAttribute("tasks", tasks);
        request.setAttribute("users", users);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/assets/tasks.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (pathInfo != null && pathInfo.equals("/create")) {
            createTask(request, response);
        } else if (pathInfo != null && pathInfo.equals("/delete")) {
            deleteTask(request, response);
        }else if (pathInfo != null && pathInfo.equals("/update")) {
            updateTask(request, response);
        }else if (pathInfo != null && pathInfo.equals("/completed")) {
            completeTask(request, response);
        }else if (pathInfo != null && pathInfo.equals("/acceptReplacement")) {
            AcceptReplacementOrder(request, response);
        }else if (pathInfo != null && pathInfo.equals("/replace")) {
            updateReplacementOrder(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }



    private void updateReplacementOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(taskId);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        userService.getUserById(user.getId());
        userService.updateUserJetonRemplacement(user.getId());


        if (task != null) {
            taskService.updateReplacementOrder(taskId);
            response.sendRedirect(request.getContextPath() + "/tasks");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }
    private void AcceptReplacementOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(taskId);

        if (task != null) {
            taskService.AcceptReplacementOrder(taskId);
            response.sendRedirect(request.getContextPath() + "/tasks");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDateTime dueDate = LocalDateTime.parse(request.getParameter("dueDate"));

        LocalDateTime now = LocalDateTime.now();
        if (dueDate.isBefore(now) || dueDate.isAfter(now.plusDays(3))) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The due date must be within the next 3 days and cannot be in the past.");
            return;
        }


        String[] tagIds = request.getParameterValues("tags[]");
        if (tagIds == null  || tagIds.length < 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please select at least 2 tags.");
            return;
        }
        if (tagIds == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tags parameter is missing.");
            return;
        }

        Set<Tag> tags = Stream.of(tagIds)
                .map(tagId -> tagService.getTagById(Long.parseLong(tagId)))
                .collect(Collectors.toSet());

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        long createdBy = 0;
        if (user != null) {
             createdBy = user.getId();
        }
        long assignedTo = Long.parseLong(request.getParameter("assignedTo"));

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setDueDate(dueDate);
        newTask.setTags(tags);
        newTask.setCreatedBy(userService.getUserById(createdBy));
        newTask.setAssignedTo(userService.getUserById(assignedTo));

        taskService.createTask(newTask);


        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task existingTask = taskService.getTaskById(taskId);

        if (existingTask != null) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            LocalDateTime dueDate = LocalDateTime.parse(request.getParameter("dueDate"));


            LocalDateTime now = LocalDateTime.now();
            if (dueDate.isBefore(now) || dueDate.isAfter(now.plusDays(3))) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The due date must be within the next 3 days and cannot be in the past.");
                return;
            }

            String[] tagIds = request.getParameterValues("tags[]");
            if (tagIds == null || tagIds.length < 2) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please select at least 2 tags.");
                return;
            }

            Set<Tag> tags = Stream.of(tagIds)
                    .map(tagId -> tagService.getTagById(Long.parseLong(tagId)))
                    .collect(Collectors.toSet());

            existingTask.setTitle(title);
            existingTask.setDescription(description);
            existingTask.setDueDate(dueDate);
            existingTask.setTags(tags);

            taskService.updateTask(existingTask, taskId);
            response.sendRedirect(request.getContextPath() + "/tasks");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }


    private void getTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(taskId);

        if (task != null) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("/assets/taskDetail.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }


    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(taskId);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        userService.getUserById(user.getId());
        userService.updateUserJetonParMois(user.getId());


        if (task != null) {

                taskService.deleteTask(taskId);
                response.sendRedirect(request.getContextPath() + "/tasks");

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }


    private void completeTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskService.getTaskById(taskId);



        if (task != null) {
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(task.getDueDate())) {
                task.setCompleted(true);
                taskService.updateTask(task, taskId);
                response.sendRedirect(request.getContextPath() + "/tasks");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Task cannot be marked as completed after the due date.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
        }
    }


    private List<Tag> getAllTags(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return tagService.findAll();
    }
}
