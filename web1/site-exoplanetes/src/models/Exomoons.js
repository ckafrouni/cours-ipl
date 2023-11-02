const db = require('../db')

module.exports.getAll = () => db.query(`
    SELECT *
    FROM exoplanets.exomoons
`)