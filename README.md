# Projet Application Android : Cinem APP
## Auteur

- Célio PAULA (3A)

## Présentation

Cinem APP est une application Android permettant d'obtenir des informations sur divers films.

Cette application fait appel à l'API Rest *OMDb API (The Open Movie Database)* grâce à **Retrofit** et expose les données reçues au travers d'une **RecyclerView**.
On récupère divers informations telles que :
- Le titre
- L'année
- La production 
- Les acteurs
- Le Box Office 
- etc...

Cette Application a été codée en Java tout en respectant le **pattern MVC**.

## Prérequis


- Installation d'Android Studio
- Récupérer la branche *release*<br/>


````
https://github.com/CelioPaula/AndroidApp.git
````

## Consignes respectées 

- Architecture MVC
- Appels REST (utilisation de Retrofit)
- Ecrans : 5 activités
- Affichage d'une liste dans un RecyclerView
- Affichage du détail d'un item de la liste
- Gitflow 
- Fonctions supplémentaires :
	- Barre de recherche
	- Mise en cache (SharedPreferences) des dernières recherches éffectuées
  	- Animations à l'écran de démarage
  	- Animation entre la première et seconde activité
  	- Mise en place d'une Navigation Bar sur la seconde activité
  	- Bouton pour vider les films stockés dans le cache
  	- Lien de redirection vers le site officiel du film concerné
  	- Notifications Push (Firebase)
  
## Fonctionnalités

### Premier écran (*launcher*)

- Animation affichant le logo de l'appli réalisé sous adobe Illustrator ainsi qu'un message clignotant (*Click to continue*)
- Transition *zoom* entre cet écran et le suivant
<img src="img_readme/launcher.png" alt="launcher" width="300" height="520"> 

### Second écran (*MainMenu*)

- RecyclerView affichant les derniers films recherchés présents dans la mémoire cache du smartphone
- NavigationBar à gauche de l'écran permettant de se rendre dans d'autres écrans ou bien de nettoyer le cache
<img src="img_readme/mainMenu.png" alt="launcher" width="300" height="520"> <img src="img_readme/navigationBar.png" alt="launcher" width="300" height="520"> 

