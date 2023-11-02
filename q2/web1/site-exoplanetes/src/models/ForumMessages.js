const db = require('../db')

function getAll () {
	return db.query(`
        SELECT *
        FROM exoplanets.forum_messages
        ORDER BY id
    `)
}

function incLike (id) {
	return db.query(`
        UPDATE exoplanets.forum_messages fm
        SET likes=fm.likes + 1
        WHERE fm.id = $1
    `, [id])
}

function addMessage (obj) {
	return db.query(`
        INSERT INTO exoplanets.forum_messages (message, author)
        VALUES ($1, $2)
    `, [obj.message, obj.author])
}

module.exports = {
	getAll,
	incLike,
	addMessage
}
