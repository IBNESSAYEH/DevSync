<%@ page import="com.youcode.DevSyncV1.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <title>DevSync</title>
</head>
<body class="bg-light">

<jsp:include page="assets/navbar.jsp" />

<section class="flex flex-col md:flex-row items-center justify-between p-10 m-5">
    <!-- Left Side (Text) -->
    <div class="w-full md:w-5/12 p-8">
        <h1 class="text-4xl font-bold mb-4">Welcome MR Developer to DevSync Task Manager!</h1>
        <p class="text-lg text-gray-600 mb-4">We are delighted to have you here. Explore our services and feel free to reach out for any inquiries.</p>
        <a href="users/login" class="inline-block bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700 transition duration-300">Login</a>
    </div>

    <!-- Right Side (Image) -->
    <div class="w-full md:w-4/12 p-4">
        <img src="images/welcome.png" alt="Welcome Image" class="rounded-lg " style="margin-left: 4vw; width: 80%; heigth : 75%">
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>
