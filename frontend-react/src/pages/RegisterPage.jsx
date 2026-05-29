import {

    useState

} from "react";

import {

    useNavigate

} from "react-router-dom";

import API from "../services/api";

import "../styles/register.css";

function RegisterPage() {

    const navigate =
        useNavigate();

    const [formData, setFormData]
        = useState({

        name: "",

        email: "",

        password: "",

        role: "ROLE_STUDENT",

        profileImage: null
    });

    const [message, setMessage]
        = useState("");

    const handleChange = (e) => {

        if (

            e.target.name ===
            "profileImage"

        ) {

            setFormData({

                ...formData,

                profileImage:
                    e.target.files[0]
            });

        } else {

            setFormData({

                ...formData,

                [e.target.name]:
                    e.target.value
            });
        }
    };

    const handleSubmit =
        async (e) => {

        e.preventDefault();

        try {

            const data =
                new FormData();

            data.append(
                "name",
                formData.name
            );

            data.append(
                "email",
                formData.email
            );

            data.append(
                "password",
                formData.password
            );

            data.append(
                "role",
                formData.role
            );

            data.append(
                "profileImage",
                formData.profileImage
            );

            await API.post(

                "/auth/register",

                data,

                {
                    headers: {

                        "Content-Type":

                            "multipart/form-data"
                    }
                }
            );

            setMessage(

                "Registration Successful"
            );

            setTimeout(() => {

                navigate("/");

            }, 1500);

        } catch (error) {

            console.log(error);

            setMessage(

                "Registration Failed"
            );
        }
    };

    return (

        <div className="register-container">

            <form
                className="register-form"
                onSubmit={handleSubmit}
            >

                <h2>

                    Create Account

                </h2>

                <input
                    type="text"
                    name="name"
                    placeholder="Enter Name"
                    onChange={handleChange}
                    required
                />

                <input
                    type="email"
                    name="email"
                    placeholder="Enter Email"
                    onChange={handleChange}
                    required
                />

                <input
                    type="password"
                    name="password"
                    placeholder="Enter Password"
                    onChange={handleChange}
                    required
                />

                <select
                    name="role"
                    onChange={handleChange}
                >

                    <option value="ROLE_STUDENT">

                        Student

                    </option>

                    <option value="ROLE_TEACHER">

                        Teacher

                    </option>

                </select>

                <input
                    type="file"
                    name="profileImage"
                    accept="image/*"
                    onChange={handleChange}
                    required
                />

                <button type="submit">

                    Register

                </button>

                {

                    message &&

                    <p className="message">

                        {message}

                    </p>
                }

            </form>

        </div>
    );
}

export default RegisterPage;