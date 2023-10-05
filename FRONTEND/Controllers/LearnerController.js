// LearnerController.js

function createLearner(fname, lname) {
    const requestData = {
        fname: fname,
        lname: lname
    };

    return fetch('http://localhost:8080/learner', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // Spécifiez le type de contenu comme JSON
        },
        body: JSON.stringify(requestData)
    })
    .then(response => response.json())
    .catch(error => {
        console.error('Une erreur s\'est produite :', error);
        throw error; // Propage l'erreur pour une gestion ultérieure si nécessaire
    });
}

export { createLearner };
