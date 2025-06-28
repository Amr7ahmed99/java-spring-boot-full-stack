<%@ include file="general/header.jspf" %>
<%@ include file= "general/navbar.jspf" %>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Welcome</h1>
                <p>Your username is ${username}</p>
                <hr>
                <div>
                    <a href="list-todos"> Manage </a> Todos
                </div>
            </div>
        </div>
    </div>
<%@ include file="general/footer.jspf" %>
