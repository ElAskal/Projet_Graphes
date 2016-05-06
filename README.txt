Pour utiliser notre projet :

1) Compiler avec ant (se trouver dans le même répertoire que build.xml)
2) Exécuter la commande java -jar Projet_Graphes.jar Graphes/NomDuFichier.txt
3) Les résultats sont consultables dans le dossier resultats (un fichier par algorithme)
4) Plusieurs exécutions à la suite avec des fichiers différents sont possibles, les fichiers de résultat comprennent tous les résultats pour un algorithme donné (append du txt à chaque fois). 

La commande ant clean permet de supprimer les répertoires bin et resultats ainsi que le .jar généré.

Projet compatible avec Java 7 et 8.

Le graphe dixMilleSommets.txt n'est pas inclus dans notre projet car il est trop volumineux (l'envoi par mail
d'un fichier d'un tel poids aurait été calamiteux).
Il est cependant possible de le récupérer localement (à ajouter au dossier Graphes) afin de le tester.
