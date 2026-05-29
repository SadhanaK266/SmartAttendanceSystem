import {

    useEffect,
    useRef,
    useState

} from "react";

import API from "../services/api";

import "../styles/student.css";

function StudentDashboard() {

    const videoRef = useRef(null);

    const canvasRef = useRef(null);

    const [cameraStarted,
        setCameraStarted]

        = useState(false);

    const [message,
        setMessage]

        = useState("");

    useEffect(() => {

        startCamera();

    }, []);

    const startCamera = async () => {

        try {

            const stream =
                await navigator
                .mediaDevices
                .getUserMedia({

                    video: true,
                    audio: true
                });

            videoRef.current.srcObject =
                stream;

            setCameraStarted(true);

        } catch (error) {

            console.log(error);

            setMessage(
                "Camera Permission Denied"
            );
        }
    };

    const captureImage = () => {

        const canvas =
            canvasRef.current;

        const video =
            videoRef.current;

        const context =
            canvas.getContext("2d");

        canvas.width =
            video.videoWidth;

        canvas.height =
            video.videoHeight;

        context.drawImage(

            video,
            0,
            0,
            canvas.width,
            canvas.height
        );

        setMessage(
            "Face Captured Successfully"
        );
    };

    const markAttendance =
        async () => {

        try {

            const canvas =
                canvasRef.current;

            const imageData =
                canvas.toDataURL(
                    "image/png"
                );

            const response =
                await API.post(

                "/student/mark-attendance",

                {
                    image:imageData
                }
            );

            setMessage(
                response.data
            );

        } catch(error) {

            console.log(error);

            setMessage(
                "Attendance Failed"
            );
        }
    };

    return (

        <div className="student-container">

            <div className="student-card">

                <h1>
                    Student Dashboard
                </h1>

                <p>
                    Smart Attendance System
                </p>

                <video
                    ref={videoRef}
                    autoPlay
                    playsInline
                    className="video-preview"
                />

                <canvas
                    ref={canvasRef}
                    style={{display:"none"}}
                />

                <div className="button-group">

                    <button
                        onClick={captureImage}
                    >

                        Capture Face

                    </button>

                    <button
                        onClick={markAttendance}
                    >

                        Mark Attendance

                    </button>

                </div>

                {

                    cameraStarted ?

                    <p className="success">

                        Camera Active

                    </p>

                    :

                    <p className="error">

                        Camera Not Active

                    </p>
                }

                <p className="message">

                    {message}

                </p>

            </div>

        </div>
    );
}

export default StudentDashboard;