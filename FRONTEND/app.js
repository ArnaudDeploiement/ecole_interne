// app.js
import { createLearner } from './Controllers/LearnerController.js';


//LearnerController
document.getElementById('formItem').addEventListener('submit', function(event) {
    event.preventDefault();

    const fname = document.getElementById('fname').value;
    const lname = document.getElementById('lname').value;

    createLearner(fname, lname)
        .then(data => {
            console.log('Réponse du serveur :', data);
            //mettre un retour sur le HTML !!
        })
        .catch(error => {
            console.error('Erreur dans la création de l\'apprenant :', error);
        });
});
