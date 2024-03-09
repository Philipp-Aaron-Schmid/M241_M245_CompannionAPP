import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle, faBell, faGear } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

export const UpdateTasks = () => {

    //Navigation 
    const navigate = useNavigate();
    const handleLogoutClick = () => { navigate("/"); };
    const handleBackClick = () => { navigate(-1); };

    //Formular
    const { id } = useParams();
    const [record, setRecord] = useState({});

    useEffect(() => { axios.get('http://localhost:3030/tasks/' + id).then(res => setRecord(res.data)).catch(error => console.log(error)); }, [id])
    function handleSubmit(e) { e.preventDefault(); axios.put('http://localhost:3030/tasks/' + id, record).then(res => { navigate("/tasks"); }); }

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
                <h1>Aufgabe aktualisieren</h1>
                <form onSubmit={handleSubmit}>
                    <fieldset>
                        <legend>ID</legend>
                        <input name="taskID" type="text" maxLength="6" value={record.taskID} onChange={e => setRecord({ ...record, taskID: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Task-Name</legend>
                        <input name="taskName" type="text" maxLength="24" value={record.taskName} onChange={e => setRecord({ ...record, taskName: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Task-Beschreibung</legend>
                        <input name="taskDescription=" type="text" value={record.taskDescription} onChange={e => setRecord({ ...record, taskDescription: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Modul</legend>
                        <input name="module" type="text" maxLength="4" value={record.module} onChange={e => setRecord({ ...record, module: e.target.value })} />
                    </fieldset>
                    <div>
                        <label>Kollaboration
                            <select name="collaboration" value={record.collaboration} onChange={e => setRecord({ ...record, collaboration: e.target.value })}>
                                <option value="blank">Bitte auswählen</option>
                                <option value="Einzelarbeit">Einzelarbeit</option>
                                <option value="Gruppenarbeit">Gruppenarbeit</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>Kategorie
                            <select name="category" value={record.category} onChange={e => setRecord({ ...record, category: e.target.value })}>
                                <option value="blank">Bitte auswählen</option>
                                <option value="Software-Entwicklung">Software-Entwicklung</option>
                                <option value="Webdesign">Webdesign</option>
                                <option value="Datenbank">Datenbank</option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>Tools
                            <select name="tools" value={record.tools} onChange={e => setRecord({ ...record, tools: e.target.value })}>
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
                        <button className="saveButton">Speichern</button>
                    </div>
                </form>
            </div>

        </div >
    );
}

export default UpdateTasks;
