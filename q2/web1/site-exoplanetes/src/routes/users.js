const express = require('express')
const bcrypt = require('bcrypt')

const router = express.Router()

const Users = require('../models/Users')
const auth = require('../middlewares/auth')

/**
 * @namespace req.body
 * @property {string} email
 * @property {string} password
 * @property {string} confirm_password
 */

router.get('/register', async (req, res) => {
	res.render('users/register.hbs')
})

router.post('/register', async (req, res) => {
	const {rows} = await Users.getByEmail(req.body.email)
	if (rows.length >= 1) {
		return res.render('users/register.hbs', {msg: 'Email already in use'})
	}

	if (req.body.password !== req.body.confirm_password) {
		return res.render('users/register.hbs', {msg: 'Passwords do not match'})
	}

	const salt = await bcrypt.genSalt(10)
	const hashedPassword = await bcrypt.hash(req.body.password, salt)
	await Users.create(req.body.email, hashedPassword)

	res.redirect(req.session.redirectUrl || '/')
})

router.get('/login', async (req, res) => {
	res.render('users/login.hbs')
})

router.post('/login', async (req, res) => {
	const {rows} = await Users.getByEmail(req.body.email)

	if (rows.length <= 0) {
		return res.render('users/login.hbs', {msg: 'Email is incorrect'})
	}

	if (!await bcrypt.compare(req.body.password, rows[0].password)) {
		return res.render('users/login.hbs', {msg: 'Password is incorrect'})
	}

	req.session.userId = rows[0].id
	req.session.isAuth = true
	req.session.username = rows[0].name
	req.session.role = rows[0].role // user | admin | moderator | ...

	res.redirect(req.session.redirectUrl || '/')
})

router.get('/logout', auth.requiresAuthentication, (req, res) => {
	req.session.destroy()
	res.redirect('/')
})

module.exports = router