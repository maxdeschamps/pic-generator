# Projet Spring - Groupe 5

## 1 - Membres
- Antoine Bonin
- Jean-Philippe Bourdais
- Maxence Deschamps

## 2 - Prérequis
### A - Installation de _**ImageMagick**_ sur la machine
- Télécharger la dernière version d'_**ImageMagick**_:
  - [Linux](https://imagemagick.org/script/download.php#linux)
  - [Windows](https://imagemagick.org/script/download.php#windows)
  - [IOS](https://imagemagick.org/script/download.php#iOS)
- Installer le logiciel:
  - Lors du choix du chemin d'installation, renommer le dossier par `ImageMagick`. Normalement sous Windows le chemin sera `C:\Program Files\ImageMagick`
- Sous Windows, dans le dossier d'installation :
  - Copier `magick.exe`
  - Coller le fichier dans le même dossier en le renommant `convert.exe`

### B - Préparation du projet
- Dans le fichier `application.properties` (`src/main/resources`)
- Changer la variable `imageMagick.path` avec le chemin d'installation d'_**ImageMagick**_ (Ne pas oublier de doubler les `\`)

## 3 - Base de données
- Préparer votre environement de base de donnée suivant les informations dans le fichier `application.properties` (`src/main/resources`)
- Lancer une première fois le projet
- Exécuter dans la base de données ces requêtes afin d'avoir un jeu de test complet
```
INSERT INTO `user` (`username`, `email`, `firstname`, `lastname`, `password`, `role`) VALUES
# mot de passe : test
('test', 'test@test.com', 'Test', 'TEST', '$2a$11$U.7B0y975cQQj9NnzTN07.upV2NfQ3I0JC6X.cgf4RBRl8S3c0Tza', 'ADMIN');

INSERT INTO `image` (`id`, `name`, `src`, `author`) VALUES
(1, 'Développement 1', 'developpeur_1.jpg', 'test'),
(2, 'Développement 2', 'developpeur_2.jpg', 'test'),
(3, 'Noel 1', 'noel_1.jpg', 'test'),
(4, 'Noel 2', 'noel_2.jpg', 'test'),
(5, 'Paysage 1', 'paysage_1.jpg', 'test'),
(6, 'Paysage 2', 'paysage_2.jpg', 'test'),
(7, 'Ville 1', 'ville_1.jpg', 'test'),
(8, 'Ville 2', 'ville_2.jpg', 'test'),
(9, 'Nicolas Cage 1', 'nicolas_cage_2.jpg', 'test'),
(10, 'Nicolas Cage 2', 'nicolas_cage_2.jpg', 'test');

INSERT INTO `tag` (`id`, `name`) VALUES
(1, 'noel'),
(2, 'developpement'),
(3, 'ville'),
(4, 'paysage');

INSERT INTO `images_tags` (`image_id`, `tag_id`) VALUES
(1, 2),
(2, 2),
(3, 1),
(4, 1),
(5, 4),
(6, 4),
(7, 3),
(8, 3);
```

## 4 - Documentations
Lorsque le projet est lancé, se diriger vers le lien suivant [http://localhost:8080/sample](http://localhost:8080/sample). Celui ci recense tous les paramètres pouvant être utilisés.
