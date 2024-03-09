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
import { CreateTask } from "./createTasks";

export const TableTask = (deleteRow) => {

    //Navigation 
    const navigate = useNavigate();
    const handleLogoutClick = () => { navigate("/"); };
    const handleBackClick = () => { navigate("/admin"); };
    const handleUserClick = () => { navigate("/users"); };

    //Tabelle & Popup
    const [records, setRecords] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [isActive, setIsActive] = useState(false);


    useEffect(() => {
        axios.get('http://localhost:3030/tasks')
            .then(res => {
                setRecords(res.data)
            })
    }, [])

    const handleDelete = (id) => {
        axios.delete(`http://localhost:3030/tasks/${id}`)
            .then(res => {
                setRecords(records.filter(record => record.id !== id));
            })
            .catch(error => console.error(error));
    };

    const handleDuplicate = (id) => {
        const recordToDuplicate = records.find(record => record.id === id);
        if (recordToDuplicate) {
            const duplicatedRecord = { ...recordToDuplicate, id: uuidv4() };
            axios.post('http://localhost:3030/tasks', duplicatedRecord)
                .then(res => {
                    setRecords([...records, res.data]);
                })
                .catch(error => console.error(error));
        }
    };

    const handleClick = () => {
        setIsActive(!isActive);
    };

    const [openAccordion, setOpenAccordion] = useState(null);
    const toggleAccordion = (index) => {
        if (openAccordion === index) {
            setOpenAccordion(null);
        } else {
            setOpenAccordion(index);
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
                    <button className="button" onClick={handleUserClick}>Benutzerkonto & Berechtigung</button>
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

            <table className="table" deleteRow={handleDelete}>
                <h1>Modulen & Aufgaben</h1>
                <thead>
                    <tr>
                        <div>
                            <input type="text" className="search" />
                            <BsSearch className="iconSearch" />
                        </div>
                        <div>
                            <Link to="/createTasks" className="createLink">
                                <FontAwesomeIcon className="iconPlus" icon={faCirclePlus} size="3x" onClick={() => setModalOpen} />
                            </Link>
                            {modalOpen && <CreateTask closeModal={() => {
                                setModalOpen(false);
                            }}
                                onSubmit={this.handleSubmit} />}
                        </div>
                    </tr>
                    <tr>
                        <th className="small">Details</th>
                        <th className="small">Task-ID</th>
                        <th className="large">Task-Name</th>
                        <th className="small">Modul</th>
                        <th className="medium">Kollaboration</th>
                        <th className="small">Status</th>
                        <th className="small">Aktion</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {records.map((record, index) => (
                        <React.Fragment key={index}>
                            <tr className="row">
                                <td>
                                    <BsChevronExpand className="icon" onClick={() => toggleAccordion(index)} />
                                </td>
                                <td>{record.taskID}</td>
                                <td>{record.taskName}</td>
                                <td>{record.module}</td>
                                <td>{record.collaboration}</td>
                                <td onClick={handleClick}>
                                    {isActive ? <BsEye /> : <BsEyeSlash />}
                                </td>
                                <td>
                                    <Link to={`/updateTasks/${record.id}`}><BsPencil className="icon" /></Link>
                                    <BsTrash className="icon" onClick={() => handleDelete(record.id)} />
                                    <BsCopy className="icon" onClick={() => handleDuplicate(record.id)} />
                                </td>
                            </tr>
                            {openAccordion === index && (
                                <tr className="row">
                                    <td>
                                        <div className="description">{record.taskDescription}</div>
                                    </td>
                                    <td>
                                        <div className="category-tag">{record.category}</div>
                                        <div className="tool-tag">{record.tools}</div>
                                    </td>
                                </tr>
                            )}
                        </React.Fragment>
                    ))}
                </tbody>
            </table>

        </div >
    );
}

export default TableTask;

