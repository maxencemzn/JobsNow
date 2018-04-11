<?php
    $email = $_POST['email'];
    $nom = $_POST['nom'];
    $prenom = $_POST['prenom'];
    $telephone = $_POST['telephone'];

    $sujet = $_POST['subject'];
    $emailDest = "maxence.mezin@hei.yncrea.fr";

    $messageMail = "L'étudiant suivant postule à l'annonce dont la référence est dans l'objet
                    
                    Prénom :    ".$prenom."
                    Nom :       ".$nom."
                    Téléphone : ".$telephone." ";

    mail($emailDest,$sujet,$messageMail);
    header("Page2.Annonces.html");
?>