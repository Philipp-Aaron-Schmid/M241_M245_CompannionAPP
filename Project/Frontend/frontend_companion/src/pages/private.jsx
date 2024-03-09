import React from 'react';
import { Link } from "react-router-dom";

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons';

const Private = () => {

  return (
    <div>

      <header className="App-header">
        <img src={Logo} className="logo" alt="logo" />
      </header>

      <nav>
        <ul>
          <li>
            <Link to="/profile">Profil</Link>
          </li>
          <li>
            <Link to="/tasks">Tasks</Link>
          </li>
        </ul>
      </nav>

      <Link to="/hilfe" className="helpLink">
        <FontAwesomeIcon className="icon" icon={faQuestionCircle} /> Hilfe & Support
      </Link>

    </div>
  );
}

export default Private;
