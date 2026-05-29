import { useState } from "react";

import "../styles/dashboard.css";

function TeacherDashboard() {

    const [attendanceStarted, setAttendanceStarted]
        = useState(false);

    const handleTriggerAttendance = () => {

        setAttendanceStarted(true);

        alert(
            "Attendance Session Started"
        );
    };

    return (

        <div className="dashboard-container">

            {/* SIDEBAR */}

            <div className="sidebar">

                <h2>
                    Smart Attendance
                </h2>

                <ul>

                    <li>
                        Dashboard
                    </li>

                    <li>
                        Attendance Sessions
                    </li>

                    <li>
                        Students
                    </li>

                    <li>
                        Reports
                    </li>

                    <li>
                        Logout
                    </li>

                </ul>

            </div>

            {/* MAIN CONTENT */}

            <div className="main-content">

                {/* TOPBAR */}

                <div className="topbar">

                    <h1>
                        Teacher Dashboard
                    </h1>

                    <div className="teacher-profile">

                        Teacher

                    </div>

                </div>

                {/* CARDS */}

                <div className="cards">

                    <div className="card">

                        <h3>
                            Today's Classes
                        </h3>

                        <p>
                            4
                        </p>

                    </div>

                    <div className="card">

                        <h3>
                            Attendance Sessions
                        </h3>

                        <p>
                            12
                        </p>

                    </div>

                    <div className="card">

                        <h3>
                            Total Students
                        </h3>

                        <p>
                            120
                        </p>

                    </div>

                </div>

                {/* ATTENDANCE PANEL */}

                <div className="attendance-panel">

                    <h2>
                        Live Attendance
                    </h2>

                    <p>
                        Course:
                        CS301 - Operating Systems
                    </p>

                    <p>
                        Room:
                        A-Block 204
                    </p>

                    <button
                        className="attendance-btn"
                        onClick={handleTriggerAttendance}
                    >

                        Trigger Attendance

                    </button>

                    {

                        attendanceStarted &&

                        <div className="live-box">

                            Attendance Window Active
                            for 60 seconds

                        </div>
                    }

                </div>

            </div>

        </div>
    );
}

export default TeacherDashboard;