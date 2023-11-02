const rootRouter = require('./root')
const exomoonsRouter = require('./exomoons')
const exoplanetsRouter = require('./exoplanets')
const forumRouter = require('./forumMessages')
const usersRouter = require('./users')
// const xRouter = require('./x')

const routes = [
	{path: '/', router: rootRouter},
	{path: '/exomoons', router: exomoonsRouter},
	{path: '/exoplanets', router: exoplanetsRouter},
	{path: '/forum', router: forumRouter},
	{path: '/users', router: usersRouter},
	// Add paths & routers here
	// ['/x', xRouter]
]

module.exports.mountApp = (app) => {
	routes.forEach((route) => {
		app.use(route.path, route.router)
	})
	return app
}

module.exports.displayRoutes = (host, port) => {
	console.log('Quick links :')
	routes.forEach((route) => {
		console.log(`\thttp://${host}:${port}${route.path}`)
	})
}