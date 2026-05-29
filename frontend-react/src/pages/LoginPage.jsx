import { Link } from "react-router-dom";

import {

    useState,
    useContext

} from "react";

import {

    useNavigate

} from "react-router-dom";

import {

    AuthContext

} from "../context/AuthContext";

import {

    loginUser

} from "../services/authService";

import "../styles/login.css";

function LoginPage() {

    const [email, setEmail] =
        useState("");

    const [password, setPassword] =
        useState("");

    const [error, setError] =
        useState("");

    const navigate =
        useNavigate();

    const { login } =
        useContext(AuthContext);

    const handleLogin =
        async (e) => {

        e.preventDefault();

        try {

            const data =
                await loginUser(
                    email,
                    password
                );

            login(
                data.token,
                data.role
            );

            if(
                data.role ===
                "ROLE_TEACHER"
            ) {

                navigate(
                    "/teacher"
                );

            } else if(
                data.role ===
                "ROLE_STUDENT"
            ) {

                navigate(
                    "/student"
                );
            }

        } catch(err) {

            setError(
                "Invalid Credentials"
            );
        }
    };

    return (

        <div className="login-container">

            <form
                className="login-form"
                onSubmit={handleLogin}
            >

                <h2>
                    Smart Attendance
                </h2>

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e)=>

                        setEmail(
                            e.target.value
                        )
                    }
                    required
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e)=>

                        setPassword(
                            e.target.value
                        )
                    }
                    required
                />

                <button type="submit">

                    Login

                </button>
                <Link
                    to="/register"
                    className="register-link"
                >

                    Create Account

                </Link>

                {

                    error &&

                    <p className="error">

                        {error}

                    </p>
                }

            </form>

        </div>
    );
}

export default LoginPage;