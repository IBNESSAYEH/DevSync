<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-md rounded-lg p-8 max-w-4xl mx-auto flex items-center">

    <div class="w-full lg:w-1/2 p-8">


        <a href="http://localhost:8081/DevSyncV1_war_exploded/" class="flex items-center space-x-3 rtl:space-x-reverse ">
            <img src="../images/logo.webp" class="h-8" alt="Flowbite Logo p-0 m-0" style="margin-left: -9px" />
            <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">DevSync</span>
        </a>
        <h4 class="text-xl font-semibold text-gray-700 mb-4">Welcome Back!</h4>



        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="text-red-500 mb-4"><%= errorMessage %></div>
        <%
            }
        %>


        <form action="<%= request.getContextPath() %>/users/login" method="post" class="space-y-6">

            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email Address</label>
                <input type="email" id="email" name="email" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>


            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" required
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>


            <div>
                <button type="submit"
                        class="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-opacity-50">
                    Login
                </button>
            </div>
        </form>


        <p class="mt-4 text-sm text-gray-500">
            Don't have an account? <a href="/register" class="text-indigo-600 hover:underline">Sign up here</a>.
        </p>
    </div>
    <div class="hidden lg:block lg:w-1/2">
        <img src="../images/login.png" alt="Login Illustration" class="rounded-l-lg h-full object-cover">
    </div>

</div>
</body>
</html>
