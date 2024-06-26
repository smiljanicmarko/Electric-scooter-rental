import React from 'react';
import { createRoot } from 'react-dom/client';
import { Route, Link, Navigate, BrowserRouter as Router, Routes } from 'react-router-dom';
import Home from './components/Home';
import NotFound from './components/NotFound';
import { Container, Nav, Navbar, Button } from 'react-bootstrap';
import Login from './components/auth/Login';
import { logout } from './services/auth';
import Zadaci from './Entitet/Zadaci';
import 'bootstrap/dist/css/bootstrap.min.css';
import Dodavanje from './Entitet/Dodavanje';
import Dodatno from './Entitet/Dodatno';
import Vracanje from './Entitet/Vracanje';


const App = () => {

    if(window.localStorage["jwt"]){
        return (
            <>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            JWD
                        </Navbar.Brand>
                        <Nav>
                        <Nav.Link as={Link} to="/zadaci">
                            Trotineti
                        </Nav.Link>  
                                         
                        <Button  onClick={logout}>Logout</Button>
                       
                        </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Navigate replace to = "/" />} />
                        <Route path="/zadaci" element={<Zadaci />} />
                        <Route path='/dodavanje' element={<Dodavanje/>}/>  
                        <Route path='/rezervacija/:id' element={<Dodatno/>}/>  
                        <Route path='/vracanje/:id' element = {<Vracanje/>}/>                   
                        <Route path="*" element={<NotFound />} />
                    </Routes>
                </Container>
                </Router>
            </>
        );
        }else{
            return(
           <>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            JWD
                        </Navbar.Brand>
                        <Nav>                       
                        <Nav.Link as={Link} to="/login">
                            Login
                        </Nav.Link>
                        </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />                        
                        <Route path="*" element={<Navigate replace to = "/login" />} />
                    </Routes>
                </Container>
                </Router>
            </>
            );
        }
    
    };


const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App/>,
);