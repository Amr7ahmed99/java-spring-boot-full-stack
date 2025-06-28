<%@ include file="general/header.jspf" %>
<%@ include file= "general/navbar.jspf" %>

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Welcome ${username}</h1>
                <hr>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>user</th>
                            <th>Description</th>
                            <th>target-date</th>
                            <th>is Done?</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${todos}" var="todo">
                            <tr>
                                <td>${todo.id}</td>
                                <td>${todo.user}</td>
                                <td>${todo.description}</td>
                                <td>${todo.targetDate}</td>
                                <td>${todo.done}</td>
                                <td>
                                    <a href="delete-todo?id=${todo.id}" class="btn btn-warning text-white">DELETE</a>
                                    <a href="update-todo?id=${todo.id}" class="btn btn-success text-white">UPDATE</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr>
                <a href="add-todo" class="btn btn-success">Add New Todo</a>
            </div>
        </div>
    </div>
    
<%@ include file="general/footer.jspf" %>