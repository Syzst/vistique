/* eslint-disable max-len */
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const express = require("express");
const cors = require("cors");

admin.initializeApp();

const db = admin.firestore();
const app = express();

app.use(cors({origin: true}));

// ------------------------ USER DATA ------------------------
{
// Create new User
  app.post("/user", async (req, res) => {
    const {
      user_name,
      user_email,
      user_password,
      user_photo,
      user_language,
    } = req.body;

    try {
      const user = {
        user_name,
        user_email,
        user_password,
        user_photo,
        user_language,
      };
      const docRef = await db.collection("users").add(user);
      res.status(201).send({message: "User Added!", user_id: docRef.id});
    } catch (error) {
      console.error("Error adding user:", error);
      res.status(500).send("Error adding user");
    }
  });

  // Read all Users
  app.get("/users", async (req, res) => {
    const snapshot = await db.collection("users").get();

    const users = [];
    snapshot.forEach((doc) => {
      const id = doc.id;
      const data = doc.data();

      users.push({id, ...data});
    });
    res.status(200).send(JSON.stringify(users));
  });

  // Read User by ID
  app.get("/user/:id", async (req, res) => {
    const snapshot = await db.collection("users")
        .doc(req.params.id).get();

    const userId = snapshot.id;
    const userData = snapshot.data();

    res.status(200).send(JSON.stringify({id: userId, ...userData}));
  });

  // Update User by ID
  app.put("/user/:id", async (req, res) => {
    const body = req.body;

    await db.collection("users").doc(req.params.id).update(body);

    res.status(200).send("Successfully Updating user");
  });

  // Delete User by id
  app.delete("/user/:id", async (req, res) => {
    await admin.firestore().collection("users").doc(req.params.id).delete();

    res.status(200).send("Successfully Deleting user");
  });
}

// ------------------------ POPULAR BATIK DATA ------------------------
{
// Create new Popular Batik
  app.post("/pbatik", async (req, res) => {
    const {
      pbatik_name,
      pbatik_price,
      pbatik_description,
      pbatik_history,
      pbatik_photo,
    } = req.body;

    try {
      const pbatik = {
        pbatik_name,
        pbatik_price,
        pbatik_description,
        pbatik_history,
        pbatik_photo,
      };
      const docRef = await db.collection("pbatiks").add(pbatik);
      res.status(201).send({message: "Popular Batik Added!", pbatik_id: docRef.id});
    } catch (error) {
      console.error("Error adding popular batik:", error);
      res.status(500).send("Error adding popular batik");
    }
  });

  // Read all Popular Batik
  app.get("/pbatiks", async (req, res) => {
    const snapshot = await db.collection("pbatiks").get();

    const pbatiks = [];
    snapshot.forEach((doc) => {
      const id = doc.id;
      const data = doc.data();

      pbatiks.push({id, ...data});
    });
    res.status(200).send(JSON.stringify(pbatiks));
  });

  // Read Popular Batik by ID
  app.get("/pbatik/:id", async (req, res) => {
    const snapshot = await db.collection("pbatiks")
        .doc(req.params.id).get();

    const pbatikId = snapshot.id;
    const pbatikData = snapshot.data();

    res.status(200).send(JSON.stringify({id: pbatikId, ...pbatikData}));
  });

  // Update Popular Batik by ID
  app.put("/pbatik/:id", async (req, res) => {
    const body = req.body;

    await db.collection("pbatiks").doc(req.params.id).update(body);

    res.status(200).send("Successfully Updating Popular Batik");
  });

  // Delete Popular Batik by id
  app.delete("/pbatik/:id", async (req, res) => {
    await admin.firestore().collection("pbatiks").doc(req.params.id).delete();

    res.status(200).send("Successfully Deleting Popular Batik");
  });
}

// ------------------------ BATIK DATA ------------------------
{
// Create new Batik
  app.post("/batik", async (req, res) => {
    const {
      batik_name,
      batik_price,
      batik_description,
      batik_history,
      batik_photo,
    } = req.body;

    try {
      const batik = {
        batik_name,
        batik_price,
        batik_description,
        batik_history,
        batik_photo,
      };
      const docRef = await db.collection("batiks").add(batik);
      res.status(201).send({message: "Batik Added!", batik_id: docRef.id});
    } catch (error) {
      console.error("Error adding batik:", error);
      res.status(500).send("Error adding batik");
    }
  });

  // Read all Batik
  app.get("/batiks", async (req, res) => {
    const snapshot = await db.collection("batiks").get();

    const batiks = [];
    snapshot.forEach((doc) => {
      const id = doc.id;
      const data = doc.data();

      batiks.push({id, ...data});
    });
    res.status(200).send(JSON.stringify(batiks));
  });

  // Read Batik by ID
  app.get("/batik/:id", async (req, res) => {
    const snapshot = await db.collection("batiks")
        .doc(req.params.id).get();

    const batikId = snapshot.id;
    const batikData = snapshot.data();

    res.status(200).send(JSON.stringify({id: batikId, ...batikData}));
  });

  // Update Batik by ID
  app.put("/batik/:id", async (req, res) => {
    const body = req.body;

    await db.collection("batiks").doc(req.params.id).update(body);

    res.status(200).send("Successfully Updating Batik");
  });

  // Delete Batik by id
  app.delete("/batik/:id", async (req, res) => {
    await admin.firestore().collection("batiks").doc(req.params.id).delete();

    res.status(200).send("Successfully Deleting Batik");
  });
}

// ------------------------ ARTICLES DATA ------------------------
{
// Create new Article
  app.post("/article", async (req, res) => {
    const {
      article_title,
      article_description,
      article_photo,
    } = req.body;

    try {
      const article = {
        article_title,
        article_description,
        article_photo,
      };
      const docRef = await db.collection("articles").add(article);
      res.status(201).send({message: "Article Added!", article_id: docRef.id});
    } catch (error) {
      console.error("Error adding article:", error);
      res.status(500).send("Error adding article");
    }
  });

  // Read all Article
  app.get("/articles", async (req, res) => {
    const snapshot = await db.collection("articles").get();

    const articles = [];
    snapshot.forEach((doc) => {
      const id = doc.id;
      const data = doc.data();

      articles.push({id, ...data});
    });
    res.status(200).send(JSON.stringify(articles));
  });

  // Read Article by ID
  app.get("/article/:id", async (req, res) => {
    const snapshot = await db.collection("articles")
        .doc(req.params.id).get();

    const articleId = snapshot.id;
    const articleData = snapshot.data();

    res.status(200).send(JSON.stringify({id: articleId, ...articleData}));
  });

  // Update Article by ID
  app.put("/article/:id", async (req, res) => {
    const body = req.body;

    await db.collection("articles").doc(req.params.id).update(body);

    res.status(200).send("Successfully Updating Article");
  });

  // Delete Article by id
  app.delete("/article/:id", async (req, res) => {
    await admin.firestore().collection("articles").doc(req.params.id).delete();

    res.status(200).send("Successfully Deleting Article");
  });
}

// ------------------------ PREDICTS DATA ------------------------
{
  // Create new Predict
  app.post("/predict", async (req, res) => {
    const {
      predict_photo,
      predict_result,
    } = req.body;

    try {
      const predict = {
        predict_photo,
        predict_result,
      };
      const docRef = await db.collection("predicts").add(predict);
      res.status(201).send({message: "Predict Added!", predict_id: docRef.id});
    } catch (error) {
      console.error("Error adding predict:", error);
      res.status(500).send("Error adding predict");
    }
  });

  // Read all Predict
  app.get("/predicts", async (req, res) => {
    const snapshot = await db.collection("predicts").get();

    const predicts = [];
    snapshot.forEach((doc) => {
      const id = doc.id;
      const data = doc.data();

      predicts.push({id, ...data});
    });
    res.status(200).send(JSON.stringify(predicts));
  });

  // Read Predict by ID
  app.get("/predict/:id", async (req, res) => {
    const snapshot = await db.collection("predicts")
        .doc(req.params.id).get();

    const predictId = snapshot.id;
    const predictData = snapshot.data();

    res.status(200).send(JSON.stringify({id: predictId, ...predictData}));
  });

  // Update Predict by ID
  app.put("/predict/:id", async (req, res) => {
    const body = req.body;

    await db.collection("predicts").doc(req.params.id).update(body);

    res.status(200).send("Successfully Updating Predict");
  });

  // Delete Predict by id
  app.delete("/predict/:id", async (req, res) => {
    await admin.firestore().collection("predicts").doc(req.params.id).delete();

    res.status(200).send("Successfully Deleting Predict");
  });
}

exports.vistique = functions
    .region("asia-southeast2")
    .https.onRequest(app);
