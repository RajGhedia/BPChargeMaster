const replace = require('replace-in-file')

const options = {
    files: 'dist/www/index.html',

    //Replacement to make (string or regex)
    from: /<span class=cordovaInjection><\/span>/g,
    to: '<script src="cordova.js"></script>'
}

try {
    // eslint-disable-next-line no-unused-vars
    let changedFile = replace.sync(options)
    // eslint-disable-next-line no-console
    console.log('Modified file')
} catch (error) {
    // eslint-disable-next-line no-console
    console.error('Error occurred:', error)
}
