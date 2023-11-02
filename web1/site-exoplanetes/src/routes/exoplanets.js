const express = require('express')
const router = express.Router()

const Exoplanet = require('../models/Exoplanets')

/**
 * @namespace req.body
 * @property {number} id
 * @property {string} unique_name
 * @property {string} h_class
 * @property {string} p_class
 * @property {number} discovery_year
 * @property {float} ist
 *
 * @namespace req.query
 * @property {number} id
 * @property {string} unique_name
 * @property {string} h_class
 * @property {number} discovery_year
 */

router.get('/', async (req, res) => {
	const {rows} = await Exoplanet.getAll()
	res.render('exoplanets/index.hbs', {exoplanetsTable: rows})
})

router.post('/add', async (req, res) => {
	await Exoplanet.add(
		req.body.unique_name,
		req.body.h_class,
		parseInt(req.body.discovery_year)
	)
	res.status(201).redirect('back')
})

router.get('/search', async (req, res) => {
	const {rows} = await Exoplanet.getAll()
	const exoplanet = await Exoplanet.findByUniqueName(req.query.unique_name)

	res.render('exoplanets/index.hbs', {
		exoplanetsTable: rows, exoplanet: exoplanet.rows[0]
	})
})

router.get('/details', async (req, res) => {
	const {rows} = await Exoplanet.findById(parseInt(req.query.id))
	res.render('exoplanets/details.hbs', {exoplanet: rows[0]})
})

router.get('/filter', async (req, res) => {
	if (req.query.h_class) {
		const {rows} = await Exoplanet.filterByHClass(req.query.h_class)
		res.render('exoplanets/index.hbs', {exoplanetsTable: rows})
	} else if (req.query.discovery_year) {
		const {rows} = Exoplanet.filterByDiscoveryYear(parseInt(req.query.discovery_year))
		res.render('exoplanets/index.hbs', {exoplanetsTable: rows})
	}
})

router.get('/update', async (req, res) => {
	const data = await Exoplanet.findById(parseInt(req.query.id))
	const id_error = (data.rows.length.length === 0)
	const exoplanet = data.rows[0]
	res.render('exoplanets/edit.hbs', {id_error, exoplanet})
})

router.post('/update', async (req, res) => {
	await Exoplanet.update(parseInt(req.body.id),
		req.body.unique_name,
		req.body.h_class,
		parseInt(req.body.discovery_year),
		parseFloat(req.body.ist),
		req.body.p_class
	)
	res.status(202).redirect(`details?id=${req.body.id}`)
})

module.exports = router
