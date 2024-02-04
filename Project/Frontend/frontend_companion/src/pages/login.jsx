// Importiert React und die benötigten Ressourcen
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from 'react-router-dom';
import AuthService from "../functions/authService";

// Importiere Designs (Logo, Gestaltung)
import Logo from '../assets/img/logo.png';
import Image from '../assets/img/login.png';
import '../css/App.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons';

// Komponente für das Login-Formular
export default function Login() {
    const [mail, setMail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        AuthService.login(mail, password) // AJAX
            .then(() => {
                navigate("/private");
            })
            .catch((error) => {
                console.log(error.response.data.message);
            });
    };

    const onChangeMail = (e) => { setMail(e.target.value); };
    const onChangePassword = (e) => { setPassword(e.target.value); };

    return (
        <div className="App">
            <section>

                <header className="App-header">
                    <img src={Logo} className="logo" alt="logo" />
                </header>

                <div className="navButton">
                    <form onSubmit={handleSubmit} method="post">
                        <fieldset>
                            <legend>Email-Adresse</legend>
                            <input
                                value={mail}
                                onChange={onChangeMail}
                                name="mail"
                                type="text"
                            />
                        </fieldset>
                        <br />
                        <fieldset>
                            <legend>Passwort</legend>
                            <input
                                value={password}
                                onChange={onChangePassword}
                                name="password"
                                type="password"
                            />
                        </fieldset>
                        <div class="forgotPassword">
                            <a href="/forgotPassword">Passwort vergessen?</a>
                        </div>
                        <button className="buttonText" type="submit">Login</button>
                    </form>
                </div>

                <div>
                    <ul>
                        <li>
                            <Link to="/datenschutz" className="link">Datenschutzerklärung</Link>
                        </li>
                        <li>
                            <Link to="/agb" className="link">Allgemeine Geschäftsbedingungen</Link>
                        </li>
                    </ul>
                </div>

                <div>
                    <img src={Image} alt="Login" className="imageLogin" />
                </div>

                <Link to="/hilfe" className="helpLink">
                    <FontAwesomeIcon className="icon" icon={faQuestionCircle}/> Hilfe & Support
                </Link>

            </section>
        </div>
    );
}
