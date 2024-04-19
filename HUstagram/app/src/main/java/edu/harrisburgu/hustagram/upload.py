import json
from pymongo import MongoClient
from flask import Flask, request

app = Flask(__name__)

# Connect to MongoDB Atlas
client = MongoClient("mongodb+srv://mbrown3:<password>@cluster0.5xrjylh.mongodb.net/")
db = client["Hustagram"]
collection = db["image"]  # Collection for storing image data


@app.route('/upload_image', methods=['POST'])
def upload_image():
    request_data = request.get_json()
    image_name = request_data['image_name']
    comment = request_data['comment']
    date_time = request_data['date_time']

    # Insert image data into MongoDB
    image_data = {'image_name': image_name, 'comment': comment, 'date_time': date_time}
    collection.insert_one(image_data)

    return json.dumps({'message': 'Image uploaded successfully'})


@app.route('/all_images', methods=['GET'])
def all_images():
    # Retrieve all images from MongoDB
    images = list(collection.find({}, {'_id': 0}))  # Exclude _id field from response
    return json.dumps(images, default=str)


if __name__ == '__main__':
    app.run("0.0.0.0", port=5000)
