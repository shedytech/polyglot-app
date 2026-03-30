const express = require('express');
const app = express();

app.get('/', (req, res) => {
  res.send('Hello, Engineer Vincent this app from Node.js Service 🚀');
});

app.listen(3000, () => {
  console.log('Node service running on port 3000');
});

