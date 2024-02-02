import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../functions/authService";

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
        <form onSubmit={handleSubmit} method="post">
            <label>
                Mail:
                <input
                    value={mail}
                    onChange={onChangeMail}
                    name="mail"
                    type="text"
                />
            </label>
            <br />
            <label>
                Password:
                <input
                    value={password}
                    onChange={onChangePassword}
                    name="password"
                    type="password"
                />
            </label>
            <br />
            <button type="submit">Login</button>
        </form>
    )
}
