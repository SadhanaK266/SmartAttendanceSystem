import {

    BrowserRouter,
    Routes,
    Route

} from "react-router-dom";

import LoginPage from "../pages/LoginPage";

import RegisterPage from "../pages/RegisterPage";

import TeacherDashboard from "../pages/TeacherDashboard";

import StudentDashboard from "../pages/StudentDashboard";

function AppRoutes() {

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/"
                    element={<LoginPage />}
                />

                <Route
                    path="/register"
                    element={<RegisterPage />}
                />

                <Route
                    path="/teacher"
                    element={<TeacherDashboard />}
                />

                <Route
                    path="/student"
                    element={<StudentDashboard />}
                />

            </Routes>

        </BrowserRouter>
    );
}

export default AppRoutes;