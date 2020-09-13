A service responsible for auto creating orders and then send it to another BE microservice to process it and save it to DB.
It automatically creates a new order every 2 seconds and pick a random product name from list of names and assign random quantity to that order.
