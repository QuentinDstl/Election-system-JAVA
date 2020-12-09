# POO JAVA Project S5
### Lancement du programme :

Au lancement du programme, votre config vous est demandée, il faut demander à la changer puis en faire une nouvelle avec `new`. Vous n'avez qu'à la rentrer qu'une seule fois, elle sera sauvegardée pour les prochaine fois plus la peine de changer de config même apres avoir relancé le programme.

__Si vous souhaitez changer de config :__
> Ouvrez le fichier `project.config` et supprimez ce qu'il y a derrière le `save.config=` et `save.xlsx=`

Exemple : 
> save.config=exemple
> save.xlsx=src\loader_package\xlsx\big_pre_load.xlsx

Et après la supression il vous reste : 
> save.config=
> save.xlsx=

```/!\ La config default est sauvegardée, vous n'aurez donc qu'à la rentrer qu'une seule fois!``` 
```Vous pouvez par la suite répondre 'no' à la question voulez-vous changer de config.```

### Config perso :
Pour vous faire votre propre config changer le 'exemple' par :
> exemple.login=your_login_for_mysql 
> exemple.password=your_password_for_mysql 
> exemple.url=jdbc:mysql://your_url_use_for_the_database
