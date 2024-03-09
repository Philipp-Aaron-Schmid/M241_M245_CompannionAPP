import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle, faBell, faGear } from '@fortawesome/free-solid-svg-icons';
import { BsUpload } from 'react-icons/bs';

export const CreateUsers = ({ closeModal, onSubmit }) => {

    //Navigation 
    const navigate = useNavigate();
    const handleLogoutClick = () => { navigate("/"); };
    const handleBackClick = () => { navigate(-1); };
    const handleTaskClick = () => { navigate("/tasks"); };

    //Formular
    const [formState, setFormState] = useState({ userID: "", firstname: "", lastname: "", email: "", classID: "", roles: "" }); const handleChange = (e) => { setFormState({ ...formState, [e.target.name]: e.target.value }); };
    function handleSubmit(e) {
        e.preventDefault();
        let { userID, firstname, lastname, email, classID, roles } = formState;
        if (userID) {
            while (userID.length < 6) {
                userID = userID + '';
            }
        }
        axios.post('http://localhost:3030/users', { userID, firstname, lastname, email, classID, roles })
            .then(res => {
                navigate("/users");
            });
    }

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
                    <button className="button" onClick={handleBackClick}>Zurückgehen</button>
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

            <div className="table">
                <h1>Neue Benutzer anlegen</h1>
                <form onSubmit={handleSubmit}>
                    <fieldset className="fieldset">
                        <legend>ID</legend>
                        <input name="userID" type="text" maxLength="6" value={formState.userID} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>Vorname</legend>
                        <input name="firstname" type="text" maxLength="24" value={formState.firstname} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>Nachname</legend>
                        <input name="lastname" type="text" maxLength="24" value={formState.lastname} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>E-Mail</legend>
                        <input name="email" type="text" maxLength="24" value={formState.email} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>Klasse</legend>
                        <input name="classID" type="text" maxLength="15" value={formState.classID} onChange={handleChange} />
                    </fieldset>
                    <div>
                        <label>Rolle
                            <select name="roles" value={formState.roles} onChange={handleChange}>
                                <option value="blank">Bitte auswählen</option>
                                <option value="Student">Student</option>
                                <option value="Admin">Admin</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <button className="saveButton" type="submit" onClick={handleSubmit}>Speichern</button>
                    </div>
                    <div>
                        <Link to="/uploadUsers" className="uploadLink">
                            <BsUpload className="icon" />CSV-Datei hochladen
                        </Link>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default CreateUsers;
