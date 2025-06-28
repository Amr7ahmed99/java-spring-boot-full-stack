<%@ include file="general/header.jspf" %>
<%@ include file= "general/navbar.jspf" %>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Update Todo</h1>
                <hr>
                <form:form action="update-todo" method="post" class="todo-form form-group" modelAttribute="todo">
                    <fieldset class="col-12 my-2">
                        <form:label path="targetDate">Target Date:</form:label>
                        <form:input path="targetDate" cssClass="form-control" id="target-date"/>
                        <form:errors path="targetDate" cssClass="text-warning"/>
                    </fieldset>

                    <fieldset class="col-12 my-2">
                        <form:label path="description" cssClass="label">Description:</form:label>
                        <form:input path="description" cssClass="form-control" id="description"/>
                        <form:errors path="description" cssClass="text-warning"/>
                    </fieldset>

                    <fieldset class="col-12 my-2">
                        <form:label path="done" cssClass="label">Is Done:</form:label>
                        <form:checkbox path="done" id="done"/>
                        
                        <form:hidden path="id"/>
                        <form:hidden path="user"/>
                    </fieldset>

                    <div class="col-12 my-2">
                        <button type="submit" class="btn btn-success">Update</button>
                        <a href="list-todos" class="btn btn-info">Back to List</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

<%@ include file="general/footer.jspf" %>
