from fastapi import FastAPI, UploadFile, File
from deepface import DeepFace
import shutil
import uuid
import os

app = FastAPI()

UPLOAD_DIR = "uploads"

os.makedirs(UPLOAD_DIR, exist_ok=True)

@app.post("/verify-face")

async def verify_face(

        live_image: UploadFile = File(...),

        profile_image: UploadFile = File(...)

):

    try:

        live_path = f"{UPLOAD_DIR}/{uuid.uuid4()}.jpg"

        profile_path = f"{UPLOAD_DIR}/{uuid.uuid4()}.jpg"

        with open(live_path, "wb") as buffer:
            shutil.copyfileobj(
                live_image.file,
                buffer
            )

        with open(profile_path, "wb") as buffer:
            shutil.copyfileobj(
                profile_image.file,
                buffer
            )

        result = DeepFace.verify(

            img1_path=live_path,

            img2_path=profile_path,

            enforce_detection=False

        )

        os.remove(live_path)
        os.remove(profile_path)

        return {

            "verified": result["verified"],

            "distance": result["distance"]
        }

    except Exception as e:

        return {

            "verified": False,

            "error": str(e)
        }