import './App.css';
import Login from './components/todo/login/Login';
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import WelcomeComponent from './components/todo/welcome/Welcome';
import ErrorComponent from './components/todo/general/ErrorComponent';
import ListTodos from './components/todo/Todos';
import Logout from './components/todo/logout/Logout';
import Header from './components/todo/general/header/Header';
import Footer from './components/todo/general/footer/Footer';
import AuthProvider, { useAuth } from './components/todo/context/AuthContext';
import TodoComponent from './components/todo/todo-page/TodoComponent';

function App() {

  const AuthenticatedRoute= ({children})=>{
    const authContext= useAuth();
    return authContext.isLoggedIn? children: <Navigate to={'/'} />
  }

  return (
    <div className="App">

        <AuthProvider>
          <BrowserRouter>
            <Header/>
            <Routes>
              <Route path='/' element= {<Login/>}/>
              <Route path='/login' element= {<Login/>}/>
              <Route path='/welcome/:username' element= {
                <AuthenticatedRoute>
                  <WelcomeComponent/>
                </AuthenticatedRoute>
                }/>
              <Route path='/todos' element= {
                <AuthenticatedRoute>
                  <ListTodos/>
                </AuthenticatedRoute>
                } />
              <Route path='/todos/create' element= {
                <AuthenticatedRoute>
                  <TodoComponent/>
                </AuthenticatedRoute>
              } />'
              <Route path='/todos/:id' element= {
                <AuthenticatedRoute>
                  <TodoComponent/>
                </AuthenticatedRoute>
              } />
              <Route path='/logout' element= {
                <AuthenticatedRoute>
                  <Logout/>
                </AuthenticatedRoute>
                } />
              <Route path='*' element= {<ErrorComponent/>}/>
            </Routes>
            <Footer/>
          </BrowserRouter>
        </AuthProvider>

    </div>
  );
}

export default App;
