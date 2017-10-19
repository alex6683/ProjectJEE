# JEE Project

Connection MYSQL à distance pour user m12017293 :

* Créer un tunnel crypter :
"ssh -L9999:dbs-perso.luminy.univmed.fr:3306 m12017293@sas.luminy.univ-amu.fr"

* Laisser ouvert et créer une boucle afin d'empecher une interruption pour inactivité.

* Ouvrir une autre tab et connectez vous à MySQL :
"mysql --host=127.0.0.1 --port=9999 -u m12017293 -p"

Maintenant la connection à distance à la base de donnée m12017293 est opé pour le projet
avec l'url du localhost de votre machine pour le port 9999.
