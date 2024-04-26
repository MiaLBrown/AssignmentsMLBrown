from flask import Flask, request, jsonify
from flask_pymongo import PyMongo

app = Flask(__name__)
app.config["MONGO_URI"] = "mongodb://localhost:27017/expensedb"  # MongoDB connection URI
mongo = PyMongo(app)

@app.route("/expenses", methods=["POST"])
def add_expense():
    data = request.json
    expense = {
        "name": data["name"],
        "amount": data["amount"]
    }
    mongo.db.expenses.insert_one(expense)
    return jsonify({"message": "Expense added successfully"}), 201

@app.route("/expenses", methods=["GET"])
def get_expenses():
    expenses = list(mongo.db.expenses.find({}, {"_id": 0}))
    return jsonify(expenses), 200

if __name__ == "__main__":
    app.run(debug=True)
