const express = require('express')
const router = express.Router()

const Exomoons = require('../models/Exomoons')

router.get('/', async (req, res) => {
	const { rows } = await Exomoons.getAll()
	res.render('exomoons/index.hbs', { exomoonsList: rows })
})

module.exports = router
