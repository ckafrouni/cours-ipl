const express = require('express')
const router = express.Router()

const ForumMessages = require('../models/ForumMessages')

/**
 * @namespace req.body
 * @property {number} id
 * @property {string} message
 * @property {string} author
 */

router.get('/', async (req, res) => {
	const { rows } = await ForumMessages.getAll()
	res.render('forum/index.hbs', { messagesTable: rows })
})

router.post('/like', async (req, res) => {
	await ForumMessages.incLike(parseInt(req.body.id))
	res.redirect('back')
})

router.post('/add', async (req, res) => {
	await ForumMessages.addMessage({
		message: req.body.message,
		author: req.body.author
	})
	res.status(201).redirect('back')
})

module.exports = router
