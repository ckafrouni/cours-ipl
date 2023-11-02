const db = require('../db')

module.exports.create = (email, password) => db.query(`
    INSERT INTO exoplanets.users (email, password)
    VALUES ($1, $2)
`, [email, password])

module.exports.getByEmail = (email) => db.query(`
    SELECT *
    FROM exoplanets.users
    WHERE email = $1
`, [email])


module.exports.getById = (id) => db.query(`
    SELECT *
    FROM exoplanets.users
    WHERE id = $1
`, [id])


module.exports.update = (id, email, password) => db.query(`
    UPDATE exoplanets.users u
    SET email=$2,
        password=$3
    WHERE u.id = $1
`, [id, email, password])


module.exports.delete = (id) => db.query(`
    DELETE
    FROM exoplanets.users
    WHERE id = $1
`, [id])
