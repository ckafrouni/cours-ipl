const db = require('../db')

module.exports.getAll = () => db.query(`
    SELECT *
    FROM exoplanets.exoplanets
`)

module.exports.add = (unique_name, h_class, discovery_year) => db.query(`
    INSERT INTO exoplanets.exoplanets (unique_name, h_class, discovery_year)
    VALUES ($1, $2, $3);
`, [unique_name, h_class, discovery_year])

module.exports.findByUniqueName = (uniqueName) => db.query(`
    SELECT *
    FROM exoplanets.exoplanets
    WHERE unique_name ILIKE $1
`, [uniqueName])

module.exports.findById = (exoplanetIdParam) => db.query(`
    SELECT *
    FROM exoplanets.exoplanets
    WHERE id = $1
`, [exoplanetIdParam])

module.exports.filterByHClass = (hClass) => db.query(`
    SELECT *
    FROM exoplanets.exoplanets
    WHERE h_class ILIKE $1;
`, [hClass])

module.exports.filterByDiscoveryYear = (discoveryYear) => db.query(`
    SELECT *
    FROM exoplanets.exoplanets
    WHERE discovery_year = $1;
`, [discoveryYear])

module.exports.update = (id, unique_name, h_class, discovery_year, ist, p_class) => db.query(`
    UPDATE exoplanets.exoplanets
    SET unique_name=$2,
        h_class=$3,
        discovery_year=$4,
        ist=$5,
        p_class=$6
    WHERE id = $1;
`, [id, unique_name, h_class, discovery_year, ist, p_class])