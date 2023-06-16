import os
import numpy as np
import uvicorn
import traceback
import tensorflow as tf

from pydantic import BaseModel
from urllib.request import Request
from fastapi import FastAPI, Response, UploadFile
from utils import load_image_into_numpy_array
from PIL import Image

model = tf.keras.models.load_model('./Batik-Datasets.h5')

app = FastAPI()

# This endpoint is for a test (or health check) to this server
@app.get("/")
def index():
    return "Hello world from ML endpoint!"

# This endpoint is for predict an image
@app.post("/predict_image")
def predict_image(uploaded_file: UploadFile, response: Response):
    try:
        # Checking if it's an image
        if uploaded_file.content_type not in ["image/jpeg", "image/png"]:
            response.status_code = 400
            return "File is Not an Image"
        
        image = load_image_into_numpy_array(uploaded_file.file.read())
        print("Image shape:", image.shape)
        
        # Step 1: image preprocessing
        pil_image = Image.fromarray(image)
        resized_image = pil_image.resize((224, 224))
        preprocessed_image = np.array(resized_image) / 255.0

        # Step 2: Prepare your data to your model
        input_data = np.expand_dims(preprocessed_image, axis=0)

        # Step 3: Predict the data
        prediction = model.predict(input_data)

        # Step 4: Change the result your determined API output
        output = prediction.tolist()
        
        most_score = max(output[0])

        batik_class = ""
        if (most_score == output[0][0]):
            batik_class = "Batik Ikat Celup"
        elif (most_score == output[0][1]):
            batik_class = "Batik Insang"
        elif (most_score == output[0][2]):
            batik_class = "Batik Kawung"
        elif (most_score == output[0][3]):
            batik_class = "Batik Megamendung"
        elif (most_score == output[0][4]):
            batik_class = "Batik Parang"
        
        return output, batik_class
    except Exception as e:
        traceback.print_exc()
        response.status_code = 500
        return "Internal Server Error"

port = os.environ.get("PORT", 8080)
print(f"Listening to http://0.0.0.0:{port}")
uvicorn.run(app, host='0.0.0.0',port=port)