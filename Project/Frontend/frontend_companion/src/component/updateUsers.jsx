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
    const handleTaskClick = () => { navigate("/tasks"); };

    //Formular
    const { id } = useParams();
    const [record, setRecord] = useState({});

    useEffect(() => { axios.get('http://localhost:3030/users/' + id).then(res => setRecord(res.data)).catch(error => console.log(error)); }, [id])
    function handleSubmit(e) { e.preventDefault(); axios.put('http://localhost:3030/users/' + id, record).then(res => { navigate("/users"); }); }

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
                <h1>Benutzer aktualisieren</h1>
                <form onSubmit={handleSubmit}>
                    <fieldset>
                        <legend>ID</legend>
                        <input name="userID" type="text" maxLength="6" value={record.userID} onChange={e => setRecord({ ...record, userID: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Vorname</legend>
                        <input name="fristname" type="text" maxLength="24" value={record.firstname} onChange={e => setRecord({ ...record, firstname: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Nachname</legend>
                        <input name="lastname" type="text" maxLength="24" value={record.lastname} onChange={e => setRecord({ ...record, lastname: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Klasse</legend>
                        <input name="classID" type="text" maxLength="15" value={record.classID} onChange={e => setRecord({ ...record, classID: e.target.value })} />
                    </fieldset>
                    <fieldset>
                        <legend>Email</legend>
                        <input name="email" type="text" maxLength="24" value={record.email} onChange={e => setRecord({ ...record, email: e.target.value })} />
                    </fieldset>
                    <div>
                        <label>Rolle
                            <select name="roles" value={record.roles} onChange={e => setRecord({ ...record, roles: e.target.value })}>
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
                        <button className="saveButton">Speichern</button>
                    </div>
                </form>
            </div>

        </div >
    );
}

export default UpdateTasks;
