import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';
import { v4 as uuidv4 } from 'uuid';

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle, faBell, faCirclePlus, faGear } from '@fortawesome/free-solid-svg-icons';
import { BsEye, BsPencil, BsTrash, BsCopy, BsChevronExpand, BsEyeSlash, BsSearch } from 'react-icons/bs';

// Importiere Navigation (Seiten, Komponenten)
import { CreateUsers } from "./createUsers";

export const TableTask = (deleteRow) => {

    //Navigation 
    const navigate = useNavigate();
    const handleLogoutClick = () => { navigate("/"); };
    const handleBackClick = () => { navigate("/admin"); };
    const handleTaskClick = () => { navigate("/tasks"); };

    //Tabelle & Popup
    const [records, setRecords] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
  
    useEffect(() => {
        axios.get('http://localhost:3030/users')
            .then(res => {
                setRecords(res.data)
            })
    }, [])

    const handleDelete = (id) => {
        axios.delete(`http://localhost:3030/users/${id}`)
            .then(res => {
                setRecords(records.filter(record => record.id !== id));
            })
            .catch(error => console.error(error));
    };

    const handleDuplicate = (id) => {
        const recordToDuplicate = records.find(record => record.id === id);
        if (recordToDuplicate) {
            const duplicatedRecord = { ...recordToDuplicate, id: uuidv4() };
            axios.post('http://localhost:3030/users', duplicatedRecord)
                .then(res => {
                    setRecords([...records, res.data]);
                })
                .catch(error => console.error(error));
        }
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

            <table className="table" deleteRow={handleDelete}>
                <h1>Benutzerkonto</h1>
                <thead>
                    <tr>
                        <div>

                            <input type="text" className="search" />
                            <BsSearch className="iconSearch" />
                        </div>
                        <div>
                            <Link to="/createUsers" className="createLink">
                                <FontAwesomeIcon className="iconPlus" icon={faCirclePlus} size="3x" onClick={() => setModalOpen} />
                            </Link>
                            {modalOpen && <CreateUsers closeModal={() => {
                                setModalOpen(false);
                            }}
                                onSubmit={this.handleSubmit} />}
                        </div>
                    </tr>
                    <tr>
                        <th className="small">User-ID</th>
                        <th className="large">Vorname</th>
                        <th className="large">Nachname</th>
                        <th className="medium">Klasse</th>
                        <th className="large">E-Mail</th>
                        <th className="small">Aktion</th>
                    </tr>
                </thead>
                <tbody>
                    {records.map((record, index) => (
                        <tr className="row">
                            <td>{record.userID}</td>
                            <td>{record.firstname}</td>
                            <td>{record.lastname}</td>
                            <td>{record.classID}</td>
                            <td>{record.email}</td>
                            <td>
                                <Link to={`/updateUsers/${record.id}`}><BsPencil className="icon" /></Link>
                                <BsTrash className="icon" onClick={() => handleDelete(record.id)} />
                                <BsCopy className="icon" onClick={() => handleDuplicate(record.id)} />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </div >
    );
}

export default TableTask;

