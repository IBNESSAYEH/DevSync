<%@ page import="com.youcode.DevSyncV1.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.youcode.DevSyncV1.entities.Task" %>
<%@ page import="com.youcode.DevSyncV1.entities.Tag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DevSync - User Management</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<jsp:include page="navbar.jsp" />






<div class="container mx-auto px-4 py-8">
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold text-gray-900">Task Management</h1>
        <button data-modal-target="popup-modal" data-modal-toggle="popup-modal" class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
            Add New Task
        </button>
    </div>

    <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">ID</th>
                <th scope="col" class="px-6 py-3">title</th>
                <th scope="col" class="px-6 py-3">Description</th>
                <th scope="col" class="px-6 py-3">due date</th>
                <th scope="col" class="px-6 py-3">completed</th>
                <th scope="col" class="px-6 py-3">assigned To</th>
                <th scope="col" class="px-6 py-3">created By</th>
                <th scope="col" class="px-6 py-3">Actions</th>            </tr>
            </thead>
            <tbody>
            <% List<Task> tasks = (List<Task>) request.getAttribute("tasks");

                if (tasks != null) {
                    for (Task task : tasks) { %>
            <tr class="bg-white border-b hover:bg-gray-50">
              <td class="px-6 py-4"><%= task.getId() %></td>
                <td class="px-6 py-4"><%= task.getTitle() %></td>
                <td class="px-6 py-4"><%= task.getDescription() %></td>
                <td class="px-6 py-4"><%= task.getDueDate() %></td>
                <td class="px-6 py-4"><%= task.isCompleted() ? "Completed" : "not yet" %></td>

                <td class="px-6 py-4"><%= task.getAssignedTo().getFirstName() + task.getAssignedTo().getLastName()%></td>
                <td class="px-6 py-4"><%= task.getCreatedBy().getFirstName() + task.getCreatedBy().getLastName()%></td>
                <td class="px-6 py-4">
                    <div class="flex space-x-2">
                        <button type="button"
                                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                                data-modal-target="updateUserModal"
                                data-modal-toggle="updateUserModal"
                                data-user-id="<%= task.getId() %>"
                                data-title="<%= task.getTitle() %>"
                                data-description="<%= task.getDescription() %>"
                                data-due-date="<%= task.getDueDate() %>"
                                data-assigned-to="<%= task.getAssignedTo() %>"
                                data-created-by="<%= task.getCreatedBy() %>">
                            <i class="fas fa-pencil-alt"></i>
                        </button>

                        <div id="updateUserModal" tabindex="-1" aria-hidden="true" class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-[calc(100%-1rem)] max-h-full">
                            <div class="relative w-full max-w-md max-h-full">
                                <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                                    <div class="flex items-center justify-between p-4 border-b rounded-t">
                                        <h3 class="text-xl font-semibold text-gray-900">
                                            Update User
                                        </h3>
                                        <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 inline-flex justify-center items-center" data-modal-hide="updateUserModal">
                                            <svg class="w-3 h-3" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                                            </svg>
                                            <span class="sr-only">Close modal</span>
                                        </button>
                                    </div>
                                    <form id="taskForm" action="<%= request.getContextPath() %>/tasks/update" method="post">
                                        <div class="mb-4">
                                            <input type="hidden" id="taskId" name="taskId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:text-white"  value="<%= task.getId() %>">
                                        </div>
                                        <!-- Title -->
                                        <div class="mb-4">
                                            <label for="title" class="block text-sm font-medium text-gray-900 dark:text-white">Title</label>
                                            <input type="text" id="title" name="title" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:text-white" placeholder="Task title" required>
                                        </div>


                                        <div class="mb-4">
                                            <label for="description" class="block text-sm font-medium text-gray-900 dark:text-white">Description</label>
                                            <textarea id="description" name="description" rows="3" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:text-white" placeholder="Task description" required></textarea>
                                        </div>


                                        <div class="mb-4">
                                            <label for="dueDate" class="block text-sm font-medium text-gray-900 dark:text-white">Due Date</label>
                                            <input type="datetime-local" id="dueDate" name="dueDate" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary focus:border-primary block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:text-white" required>
                                        </div>


                                        <div class="mb-4">
                                            <label for="tags" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Tags</label>
                                            <select id="tags" name="tags[]" multiple class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
                                                <% List<Tag> tags = (List<Tag>) request.getAttribute("tags");
                                                    if (tags != null && !tags.isEmpty()) {
                                                        for (Tag tag : tags) { %>
                                                <option value="<%= tag.getId() %>"><%= tag.getName() %></option>
                                                <% }} else { %>
                                                <option disabled>No tags available</option>
                                                <% } %>
                                            </select>
                                            <small class="text-gray-500 dark:text-gray-400">Please select at least two tags.</small>
                                        </div>




                                            <label for="users" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">assigned to</label>
                                            <select id="users" name="assignedTo" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                               <% List<User> users = (List<User>) request.getAttribute("users");
                                                    if (users != null && !users.isEmpty()) {
                                                        for (User user : users) { %>
                                                <option value="<%= user.getId() %>"><%= user.getFirstName() + " " + user.getLastName() %></option>
                                                <% }} else { %>
                                                <option disabled>No users available</option>
                                                <% } %>


                                            </select>


                                        <div class="flex justify-end">
                                            <button type="submit" class="text-white bg-blue-600 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Create Task</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        
                        <form action="tasks/delete" method="post">
                            <input type="hidden" name="taskId" value="<%= task.getId() %>">
                            <button type="submit"
                                    class="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900">
                                <i class="fas fa-trash"></i>
                            </button>
                        </form>

                        <form action="tasks/completed" method="post">
                            <input type="hidden" name="taskId" value="<%= task.getId() %>">
                            <button type="submit"
                                    class="focus:outline-none text-white bg-yellow-400 hover:bg-yellow-500 focus:ring-4 focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:focus:ring-yellow-900">
                                <i class="fas fa-check"></i>
                            </button>
                        </form>


                    </div>
                </td>
            </tr>
            <% }} %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="createTaskModal.jsp" />

<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
<script src="../js/userManagement.js"></script>
</body>
</html>