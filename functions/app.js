/* eslint-disable spaced-comment */
const express = require("express");
const cors = require("cors");
const functions = require("firebase-functions");
const routes = require("./routes.js");

const app = express();

app.use(cors({origin: true}));
app.use(express.json());
app.use(routes);

app.listen(5000, () => {
  console.log("Running on Port 5000!");
});

exports.vistique = functions
    .region("asia-southeast2")
    .https.onRequest(app);
