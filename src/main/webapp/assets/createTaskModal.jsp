<%@ page import="com.youcode.DevSyncV1.entities.Tag" %>
<%@ page import="java.util.List" %>
<%@ page import="com.youcode.DevSyncV1.entities.User" %>
<div id="popup-modal" tabindex="-1" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
    <div class="relative p-4 w-full max-w-md max-h-full">
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <button type="button" class="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-hide="popup-modal">
                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                </svg>
                <span class="sr-only">Close modal</span>
            </button>
            <div class="p-4 md:p-5">
                <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">Create a new Task</h3>
         >
                <form id="taskForm" action="<%= request.getContextPath() %>/tasks/create" method="post">

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
</div>
