/* eslint-disable no-unused-vars */
/* eslint-disable require-jsdoc */
/* eslint-disable max-len */

"use strict";
const {Storage} = require("@google-cloud/storage");
const {format} = require("date-fns");
const path = require("path");

const pathKey = path.resolve("./capstone-project-vistique-9124bd1b7cb6.json");

// TODO: Sesuaikan konfigurasi Storage
const gcs = new Storage({
  projectId: "capstone-project-vistique",
  keyFilename: pathKey,
});

// TODO: Tambahkan nama bucket yang digunakan
const bucketName = "capstone-project-vistique";
const bucket = gcs.bucket(bucketName);

function getPublicUrl(filename) {
  return "https://storage.googleapis.com/" + bucketName + "/" + filename;
}

const imageUpload = (foldername) => (req, res, next) => {
  if (!req.file) return next();

  const currentDate = new Date();
  const gcsname = format(currentDate, "yyyy-MM-dd_HH-mm-ss") + "_" + req.file.originalname;
  const file = bucket.file(foldername + "/" + gcsname);

  const stream = file.createWriteStream({
    metadata: {
      contentType: req.file.mimetype,
    },
  });

  stream.on("error", (err) => {
    req.file.cloudStorageError = err;
    next(err);
  });

  stream.on("finish", async () => {
    req.file.cloudStorageObject = gcsname;
    req.file.cloudStoragePublicUrl = getPublicUrl(foldername + "/" + gcsname);
    next();
  });

  stream.end(req.file.buffer);
};

module.exports = imageUpload;
