

export default function ListTodos(){
    const today= new Date();
    const todos= [
        {
            id: 1,
            description: "Learn Java SpringBoot",
            targetDate: new Date(today.getFullYear(), today.getMonth()+1, today.getDay()),
            isDone: false
        },
        {
            id: 2,
            description: "Learn Java System Design",
            targetDate: new Date(today.getFullYear(), today.getMonth() + 2, today.getDay()),
            isDone: false
        }
    ]
    return (
        <div className="container">
            <h1> Things You Want To Do!</h1>
            <br/>
            <table className="table">
                <thead>
                    <tr>
                        <th> id</th>
                        <th> description</th>
                        <th> isDone?</th>
                        <th> targetDate</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map(todo => (
                            <tr key={todo.id}>
                                <td> {todo.id} </td>
                                <td> {todo.description} </td>
                                <td> {todo.isDone.toString()} </td>
                                <td> {todo.targetDate.toDateString()} </td>
                            </tr>
                        ))
                    }
                    
                </tbody>
            </table>
        </div>
    );
}