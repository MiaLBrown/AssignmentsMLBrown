import json
from pymongo import MongoClient
from flask import Flask, request

app = Flask(__name__)

# Connect to MongoDB Atlas
client = MongoClient(
    "mongodb+srv://mbrown3:<password>@cluster0.grpwprm.mongodb.net/?retryWrites=true&w=majority"
    "&appName=Cluster0")
db = client["Customer"]


@app.route('/add_user', methods=['POST'])
def add_user():
    collection = db["CustCust"]
    request_data = request.get_json()
    name = request_data['name']
    address = request_data['address']
    phone = request_data['phone']
    print(f'ID: {id}, Name: {name}, Address: {address}, Phone: {phone}')
    filter = {'_id': id}
    return json.dumps({'id': str(_id.inserted_id)})


app.route('/update', methods=['POST'])


def update():
    collection = db["CustCust"]
    request_data = request.get_json()
    id = request_data['_id']
    name = request_data['name']
    address = request_data['address']
    phone = request_data['phone']
    print(f'ID: {id}, Name: {name}, Address: {address}, Phone: {phone}')
    filter = {'_id': id}
    return json.dumps({'id': str(_id.inserted_id)})


@app.route('/all', methods=['POST'])
def all_users():
    collection = db["CustCust"]
    customers = list(collection.find())
    return json.dumps(customers, default=str)


if __name__ == '__main__':
    app.run("0.0.0.0", port=5000)
