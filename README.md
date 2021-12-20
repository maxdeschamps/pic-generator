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
