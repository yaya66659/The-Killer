# 🎲 THE KILLER – Jeu de dés Android

**THE KILLER** est un jeu de dés stratégique développé à l’origine pour jouer entre amis, mais conçu pour plaire aussi bien aux amateurs de jeux de dés qu'au grand public à la recherche d’un petit défi rapide et fun.

---

## 🧠 Origine du projet

Ce projet est l’adaptation mobile d’un jeu de dés auquel je jouais dans ma caravane, à une époque difficile de ma vie.  
C’est dans ce contexte que j’ai partagé de bons moments avec des personnes qui, comme moi, cherchaient à s’évader un instant grâce au jeu.

J’ai repris les règles simples et amusantes de ce jeu pour les transformer en une application Android, afin de partager cette expérience avec d’autres.

---

## 📜 Règles du jeu

- Les joueurs jouent à tour de rôle en lançant **5 dés**.
- À chaque lancer, ils **doivent garder au minimum 1 dé**, mais peuvent en garder plusieurs.
- Une fois les 5 dés gardés, le **score total** est calculé.

### 🎯 Résultat du score :
- **Exactement 11 ou 24** → le joueur gagne un lancer bonus d’**un dé de vie** (ajouté à sa vie).
- **Entre 11 et 24 (exclus)** → le joueur **perd** un nombre de points de vie égal à la différence avec 11 ou 24 (le plus proche).
- **Inférieur à 11 ou supérieur à 24** → le joueur débloque une **attaque** :
  - Attaque = `11 - score` si score < 11
  - Attaque = `score - 24` si score > 24

### ⚔️ Phase d’attaque :
- Le joueur choisit une **cible** parmi les joueurs encore en vie.
- Il relance 5 dés et **garde uniquement les dés de la valeur de l’attaque**.
- Il continue à relancer les dés restants tant qu’il peut obtenir cette valeur.
- La somme des dés gardés représente les **dégâts infligés** à la cible.

🔚 Le **dernier joueur humain en vie** gagne la partie.

---

## 📖 Exemples de gameplay

### Tour standard :
1. Le joueur lance 🎲 `5, 2, 6, 1, 4`
2. Il garde le `6` (obligatoire), puis relance les 4 autres 🎲 `3, 2, 5, 1`
3. Il garde `5` et `3`, relance les 2 restants 🎲 `2, 4`
4. Tous les dés ont été gardés.

**Score final** : `6 + 5 + 3 + 2 + 4 = 20`  
→ 20 est entre 11 et 24 → **le joueur perd 9 points de vie** (car 20 est plus proche de 11 que de 24).

### Tour avec attaque :
1. Le joueur obtient un score de `8` → attaque autorisée de valeur `3` (`11 - 8`)
2. Il cible un joueur, relance les 5 dés et garde ceux ayant la valeur `3`
3. Il répète l’opération jusqu’à ne plus obtenir de `3`
4. Il garde 🎲 `3, 3` → **6 points de dégâts infligés**

---

## 📱 Installation (APK)

Vous pouvez télécharger directement l’application Android ici :

👉 **[Télécharger THE KILLER (.apk)](release/The-Killer.apk)**

> ⚠️ Activez l’installation d’applications de sources inconnues dans les paramètres Android.

📱 Compatible Android **5.0 (Lollipop)** et supérieur.

---

## 👨‍💻 À propos du développement

**THE KILLER** est mon **premier jeu mobile Android**, développé seul de A à Z :

- Codé en **Java** avec **Android Studio**
- Conçu en 3 étapes :
  1. Prototype en **langage C** (mode console)
  2. Portage en **Java** (console orientée objet)
  3. Intégration complète dans Android Studio

Ce projet représente ma première grande étape dans l'apprentissage du développement mobile.

## Capture d'ecran (ScreenShots):

[Logo du jeu](screenshots/KillerLogo.png)

[Choix du pseudo](screenshots/KillerPseudo.png)

[Statistiques du joueur](screenshots/KillerStatJoueur.png)

[Lancer de dés](screenshots/KillerLancerDes.png)

[Garder certains dés](screenshots/KillerGraderDes.png)

[Tentez une attaque](screenshots/KillerTentezUneAttaque.png)

[Score de l'attaque](screenshots/KillerScoreAttaque.png)

[Cibler un joueur](screenshots/KillerciblerJoueur.png)

[Confirmation de la cible d’attaque](screenshots/KillerCibleAttaque.png)

[Attaque – étape 1](screenshots/KillerAttaque01.png)

[Attaque – étape 2](screenshots/KillerAttaque02.png)

[Attaque – étape 3](screenshots/KillerAttaque03.png)

[Infobulle 01](screenshots/KillerinfoBulle01.png)

[Infobulle 02](screenshots/KillerinfoBulle02.png)


---

## 📝 Licence

Ce projet est sous une licence **open source personnalisée** :

- ✅ Libre de modifier, partager et utiliser pour un usage personnel ou pédagogique
- ❌ **Interdiction formelle d'en faire un usage commercial** sans autorisation

Pour toute demande ou projet dérivé, vous pouvez me contacter à :  
📧 **yann55446@gmail.com**
---

## 🙏 Remerciements

Merci à ceux qui ont partagé ces moments de jeu à l’époque.  
Et à tous ceux qui soutiennent les développeurs indépendants.

---

