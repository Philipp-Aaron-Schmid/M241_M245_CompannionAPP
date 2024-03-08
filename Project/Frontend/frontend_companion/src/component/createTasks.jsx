import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle, faBell, faGear } from '@fortawesome/free-solid-svg-icons';
import { BsUpload } from 'react-icons/bs';

export const CreateTask = ({ closeModal, onSubmit }) => {

    //Navigation 
    const navigate = useNavigate();
    const handleLogoutClick = () => { navigate("/"); };
    const handleBackClick = () => { navigate(-1); };

    //Formular
    const [formState, setFormState] = useState({ taskID: "", taskName: "", taskDescription: "", module: "", collaboration: "", category: "", tools: "" }); const handleChange = (e) => { setFormState({ ...formState, [e.target.name]: e.target.value }); };
    function handleSubmit(e) {
        e.preventDefault();
        let { taskID, taskName, taskDescription, module, collaboration, category, tools } = formState;
        if (taskID) {
            while (taskID.length < 6) {
                taskID = taskID + '';
            }
        }
        axios.post('http://localhost:3030/tasks', { taskID, taskName, taskDescription, module, collaboration, category, tools })
            .then(res => {
                navigate("/tasks");
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
                    <button className="button">Benutzerkonto & Berechtigung</button>
                </div>
                <div>
                    <button className="button" onClick={handleBackClick}>Zurückgehen</button>
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
                <h1>Neue Aufgabe erstellen</h1>
                <form onSubmit={handleSubmit}>
                    <fieldset className="fieldset">
                        <legend>ID</legend>
                        <input name="taskID" type="text" maxLength="6" value={formState.taskID} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>Task-Name</legend>
                        <input name="taskName" type="text" maxLength="24" value={formState.taskName} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>Task-Beschreibung</legend>
                        <input name="taskDescription" type="text" value={formState.taskDescription} onChange={handleChange} />
                    </fieldset>
                    <fieldset>
                        <legend>Modul</legend>
                        <input name="module" type="text" maxLength="4" value={formState.module} onChange={handleChange} />
                    </fieldset>
                    <div>
                        <label>Kollaboration
                            <select name="collaboration" value={formState.collaboration} onChange={handleChange}>
                                <option value="blank">Bitte auswählen</option>
                                <option value="Einzelarbeit">Einzelarbeit</option>
                                <option value="Gruppenarbeit">Gruppenarbeit</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>Kategorie
                            <select name="category" value={formState.category} onChange={handleChange}>
                                <option value="blank">Bitte auswählen</option>
                                <option value="Software-Entwicklung">Software-Entwicklung</option>
                                <option value="Webdesign">Webdesign</option>
                                <option value="Datenbank">Datenbank</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>Tools
                            <select name="tools" value={formState.tools} onChange={handleChange}>
                                <option value="blank">Bitte auswählen</option>
                                <option value="GitHub">GitHub</option>
                                <option value="React">React</option>
                                <option value="Docker">Docker</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <button className="saveButton" type="submit" onClick={handleSubmit}>Speichern</button>
                    </div>
                    <div>
                        <Link to="/uploadTasks" className="uploadLink">
                            <BsUpload className="icon" />CSV-Datei hochladen
                        </Link>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default CreateTask;
