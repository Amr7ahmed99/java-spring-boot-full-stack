import { Link, useParams } from "react-router-dom";
export default function WelcomeComponent(){
    const params= useParams();

    return (
        <div>
            <h1>
                Welcome {params?.username}
            </h1>
            <h4>
                Your todos - <Link to={'/todos'}>Go here</Link>
            </h4>
        </div>
    );
}