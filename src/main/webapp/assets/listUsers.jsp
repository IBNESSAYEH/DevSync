<%@ page import="com.youcode.DevSyncV1.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DevSync - User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<jsp:include page="navbar.jsp" />

<div class="container mx-auto px-4 py-8">
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold text-gray-900">User Management</h1>
        <button type="button"
                data-modal-target="createUserModal"
                data-modal-toggle="createUserModal"
                class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors">
            Add New User
        </button>
    </div>

    <!-- Users Table -->
    <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">ID</th>
                <th scope="col" class="px-6 py-3">Username</th>
                <th scope="col" class="px-6 py-3">First Name</th>
                <th scope="col" class="px-6 py-3">Last Name</th>
                <th scope="col" class="px-6 py-3">Email</th>
                <th scope="col" class="px-6 py-3">Role</th>
                <th scope="col" class="px-6 py-3">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) { %>
            <tr class="bg-white border-b hover:bg-gray-50">
                <td class="px-6 py-4"><%= user.getId() %></td>
                <td class="px-6 py-4"><%= user.getUsername() %></td>
                <td class="px-6 py-4"><%= user.getFirstName() %></td>
                <td class="px-6 py-4"><%= user.getLastName() %></td>
                <td class="px-6 py-4"><%= user.getEmail() %></td>
                <td class="px-6 py-4"><%= user.getRole() %></td>
                <td class="px-6 py-4">
                    <div class="flex space-x-2">
                        <button type="button"
                                class="text-blue-600 hover:underline"
                                data-modal-target="updateUserModal"
                                data-modal-toggle="updateUserModal"
                                data-user-id="<%= user.getId() %>"
                                data-user-username="<%= user.getUsername() %>"
                                data-user-firstname="<%= user.getFirstName() %>"
                                data-user-lastname="<%= user.getLastName() %>"
                                data-user-email="<%= user.getEmail() %>"
                                data-user-role="<%= user.getRole() %>">
                            Edit
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
                                    <form action="users/update" method="post" id="createUserForm" class="p-4 md:p-5">
                                        <input type="hidden" name="id" id="id" value="<%= user.getId() %>">
                                        <div class="grid gap-4 mb-4">
                                            <div class="col-span-2">
                                                <label for="username" class="block mb-2 text-sm font-medium text-gray-900">Username</label>
                                                <input type="text" name="username" value="<%= user.getUsername() %>" id="username" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5" required>
                                            </div>
                                            <div class="col-span-2">
                                                <label for="password" class="block mb-2 text-sm font-medium text-gray-900">Password</label>
                                                <input type="password" name="password" value="<%= user.getPassword() %>" id="password" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5" required>
                                            </div>
                                            <div class="col-span-2 sm:col-span-1">
                                                <label for="firstName" class="block mb-2 text-sm font-medium text-gray-900">First Name</label>
                                                <input type="text" name="firstName" value="<%= user.getFirstName() %>" id="firstName" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5" required>
                                            </div>
                                            <div class="col-span-2 sm:col-span-1">
                                                <label for="lastName" class="block mb-2 text-sm font-medium text-gray-900">Last Name</label>
                                                <input type="text" name="lastName" value="<%= user.getLastName() %>" id="lastName" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5" required>
                                            </div>
                                            <div class="col-span-2">
                                                <label for="email" class="block mb-2 text-sm font-medium text-gray-900">Email</label>
                                                <input type="email" name="email" value="<%= user.getEmail() %>" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5" required>
                                            </div>
                                            <div class="col-span-2">
                                                <label for="role" class="block mb-2 text-sm font-medium text-gray-900">Role</label>
                                                <select name="role" id="role" value="<%= user.getRole() %>" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5" required>
                                                    <option value="">Select role</option>
                                                    <option value="MANAGER">MANAGER</option>
                                                    <option value="USER">User</option>
                                                </select>
                                            </div>
                                        </div>
                                        <button type="submit" class="text-white inline-flex items-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center w-full">
                                            Update User
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <form action="users/delete" method="post" >
                            <input type="hidden" name="id" value="<%= user.getId() %>">
                            <button type="submit"
                                    class="text-red-600 hover:underline">
                                Delete
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


<jsp:include page="createUser.jsp" />


<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
<script src="../js/userManagement.js"></script>
</body>
</html>