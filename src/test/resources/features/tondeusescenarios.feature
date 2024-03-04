# Author Berrami Badr
# Date 01/03/2024
# Description
@smokeFeatures
Feature: feature pour simuler l'exécution des tondeuses : cas nominal et cas d'echec
  @smokeTestByBrowser
  Scenario: Cas nominal - Démarrer l'exécution des tondeuses
    Given utilisateur ouvre le navigateur
    And utilisateur charge le fichier de coordonnées fichierentree.txt
    When utilisateur clique sur upload
    Then resultat d'execution "1 3 N 5 1 E" est affiché
    Then utilisateur ferme navigateur

  @smokeTestByBrowser
  Scenario: Cas d'erreur - Démarrer l'exécution des tondeuses avec un fichier d'entree en erreur : "Coordonnées pelouse incorrectes"
    Given utilisateur ouvre le navigateur
    And utilisateur charge le fichier de coordonnées pelouseenerreur.txt
    When utilisateur clique sur upload
    Then message d'erreur "Coordonnées de pelouse incorrectes" est affiché
    Then utilisateur ferme navigateur

  @smokeTestByBrowser
  Scenario: Cas d'erreur - Démarrer l'exécution des tondeuses avec un fichier d'entree en erreur : "Coordonnees de tondeuse incorrectes"
    Given utilisateur ouvre le navigateur
    And utilisateur charge le fichier de coordonnées coordonneestondeuseenerreur.txt
    When utilisateur clique sur upload
    Then message d'erreur "Coordonnées de tondeuse incorrectes" est affiché
    Then utilisateur ferme navigateur

  @smokeTestByBrowser
  Scenario: Cas d'erreur - Démarrer l'exécution des tondeuses avec un fichier d'entree en erreur : "Commandes de tondeuse incorrectes"
    Given utilisateur ouvre le navigateur
    And utilisateur charge le fichier de coordonnées commandestondeuseenerreur.txt
    When utilisateur clique sur upload
    Then message d'erreur "Commandes de tondeuse incorrectes" est affiché
    Then utilisateur ferme navigateur

  @smokeTestByBrowser
  Scenario: Cas d'erreur - fichier en entrée vide
    Given utilisateur ouvre le navigateur
    And utilisateur charge le fichier de coordonnées fichiervide.txt
    When utilisateur clique sur upload
    Then message d'erreur "Le fichier des coordonnées utilisé en entrée est vide" est affiché
    Then utilisateur ferme navigateur