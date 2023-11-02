const express = require('express')
const router = express.Router()


// redirect '/' to '/home'
router.get('/', (req, res) => {
	res.redirect('/home')
})

router.get('/home', (req, res) => {
	const today = new Date()
	const todayString = 'Nous sommes le ' + today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear() + '.'
	const hourtodayString = 'Il est ' + today.getHours() + ':' + today.getMinutes() + ' à Bruxelles'
	res.render('index.hbs', { today: todayString + ' ' + hourtodayString })
})

module.exports = router
