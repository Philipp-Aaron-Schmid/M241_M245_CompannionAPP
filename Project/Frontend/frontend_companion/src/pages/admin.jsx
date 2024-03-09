import React from 'react';
import { Link, useNavigate } from "react-router-dom";

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle, faBell, faGear } from '@fortawesome/free-solid-svg-icons';


const Private = () => {

    //Navigation
    const navigate = useNavigate();
    const handleLogoutClick = () => {
        navigate("/");
    };
    const handleTaskClick = () => {
        navigate("/tasks");
    };
    const handleUserClick = () => {
        navigate("/users");
    };

    return (
        <div>

            <header className="App-header">
                <img src={Logo} className="logo" alt="logo" />
            </header>

            <section className="navButton">
                <div>
                    <button className="button">Dashboard</button>
                </div>
                <div>
                    <button className="button" onClick={handleUserClick}>Benutzerkonto & Berechtigung</button>
                </div>
                <div>
                    <button className="button" onClick={handleTaskClick}>Modulen & Aufgaben</button>
                </div>
                <div>
                    <button className="button" onClick={handleLogoutClick}>Logout</button>
                </div>
            </section>

            <div className="navLinks">
                <Link to="/message" className="settingLink">
                    <FontAwesomeIcon className="iconLink" icon={faGear} />Einstellungen
                </Link>

                <Link to="/message" className="messageLink">
                    <FontAwesomeIcon className="iconLink" icon={faBell} />Benachrichtungen
                </Link>

                <Link to="/help" className="helpLink">
                    <FontAwesomeIcon className="iconLink" icon={faQuestionCircle} />Hilfe & Support
                </Link>
            </div>

        </div>
    );
}

export default Private;
