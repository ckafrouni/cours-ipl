require('dotenv').config()
const express = require('express')
const path = require('path')
const logger = require('morgan')
const hbs = require('hbs')
const session = require('express-session')

const host = 'localhost'
const port = 3000
const nodeEnv = process.env.NODE_ENV || 'development'

/***********
  HBS SETUP
 ***********/
const fs = require('fs')
const partialsDirs = fs.readdirSync(path.join(__dirname, 'views'))
	.filter(dir => fs.statSync(path.join(__dirname, 'views', dir)).isDirectory())
	.filter(dir => fs.existsSync(path.join(__dirname, 'views', dir, 'partials')))
	.map(dir => path.join(__dirname, 'views', dir, 'partials'))
partialsDirs.push(path.join(__dirname, 'views', 'partials'))
partialsDirs.forEach(dir => {
	hbs.registerPartials(dir)
})

hbs.registerHelper('eq', (a, b) => a === b)
hbs.registerHelper('exists', function (variable, options) {
	/**
	 * @namespace options
	 * @property {function} fn
	 * @property {function} inverse
	 */
	if (typeof variable !== 'undefined') {
		return options.fn(this)
	}
	return options.inverse(this)
})

/***************
  EXPRESS SETUP
 ***************/
const app = express()

app.disable('x-powered-by')
app.set('views', path.join(__dirname, 'views'))
app.set('view engine', 'hbs')

app.use(express.json())
app.use(express.urlencoded({extended: false}))
app.use(express.static(path.join(__dirname, 'public')))
app.use(nodeEnv === 'development' ? logger('dev') : logger('combined'))

app.use(session({
	secret: process.env.SESSION_SECRET,
	resave: true,
	saveUninitialized: false,
	cookie: {
		maxAge: 1000 * 60 * 60 // 1 hour
	}
}))
app.use((req, res, next) => {
	/** @namespace req.session */
	res.locals.session = req.session
	next()
})

const {mountApp, displayRoutes} = require('./routes')
mountApp(app)
	.listen(port, host, () => displayRoutes(host, port))