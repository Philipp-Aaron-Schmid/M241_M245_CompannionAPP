import React from 'react';
import { Link, useNavigate } from "react-router-dom";

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons';
import { faBell } from '@fortawesome/free-solid-svg-icons';

const Private = () => {

    const navigate = useNavigate();

    const handleLogoutClick = () => {
        navigate("/");
    };

    return (
        <div>

            <header className="App-header">
                <img src={Logo} className="logo" alt="logo" />
            </header>

            <section className="navButton">
                <div>
                    <button className="button" onClick={handleLogoutClick}>Dashboard</button>
                </div>
                <div>
                    <button className="button" onClick={handleLogoutClick}>Benutzerkonto & Berechtigung</button>
                </div>
                <div>
                    <button className="button" onClick={handleLogoutClick}>Modulen & Aufgaben</button>
                </div>
                <div>
                    <button className="button" onClick={handleLogoutClick}>Logout</button>
                </div>
            </section>

            <div className="navLinks">
                <Link to="/benachrichtung" className="messageLink">
                    <FontAwesomeIcon className="icon" icon={faBell} /> Benachrichtungen
                </Link>

                <Link to="/hilfe" className="helpLink">
                    <FontAwesomeIcon className="icon" icon={faQuestionCircle} /> Hilfe & Support
                </Link>
            </div>

        </div>
    );
}

export default Private;
