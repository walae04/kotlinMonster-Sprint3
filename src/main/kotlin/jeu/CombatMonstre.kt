package org.example.jeu

import org.example.item.Utilisable
import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(var monstreJoueur: IndividuMonstre,
                    var monstreSauvage: IndividuMonstre) {
    var round: Int=1
    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */

    fun gameOver(): Boolean{
        var defaite = false
        for (monstre in joueur.equipeMonstre){
            if (monstre.pv <= 0) defaite = true
        }
        return defaite
    }
    fun jouerGagne():Boolean {
            while (monstreSauvage.pv <= 0) {
                println("${joueur.nom} a gagné !")
                var gainExp = monstreSauvage.exp * 0.20
                monstreJoueur.exp += gainExp
                println("${monstreJoueur.nom} gagne ${gainExp} exp")
                return true
            }
            if (monstreSauvage.entraineur == joueur) {
                println("${monstreSauvage.nom} a été capturé !")
                return true
            } else return false

    }
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) monstreSauvage.attaquer(monstreJoueur)
    }
    fun actionJoueur(): Boolean {
        if (gameOver() == true) return false

        println("Menu d'actions :\n 1. Attaquer    2. SacItem    3. EquipeMonstre")
        var choix = readln().toInt()

        if (choix == 1) {
            monstreJoueur.attaquer(monstreSauvage)
        }
        else if (choix == 2) {
            println(joueur.sacAItems)
            var indexChoix = readln().toInt()
            var objetChoisi = joueur.sacAItems[indexChoix]
            if(objetChoisi is Utilisable){
                var captureReussie = objetChoisi.utiliser(monstreSauvage)
                if (captureReussie) return false else return true
            } else {
                println("Objet non utilisable")
                return true
            }
        }
        else if (choix == 3) {
            for (monstres in joueur.equipeMonstre) {
                if (monstres.pv > 0) {
                    println(monstres.afficherDetail())
                }
            }
            var indexChoix = readln().toInt()
            var choixMonstre = joueur.equipeMonstre[indexChoix]
            if (choixMonstre.pv <= 0) {
                println("${choixMonstre} remplace ${monstreJoueur}")
                monstreJoueur = choixMonstre
            } else {
                println("Impossible ! Ce montres est KO")
            }
        }
        return true
    }
    fun afficherCombat() {
        println("======== Début Round : ${round} ========\n" +
                "Niveau : ${monstreSauvage.pvMax}\n" +
                "Pv : ${monstreSauvage.pv / monstreSauvage.pvMax}\n" +
                monstreSauvage.espece.afficheArt(true) +
                monstreJoueur.espece.afficheArt(false) +
                "Niveau : ${monstreJoueur.niveau}\n" +
                "Pv : ${monstreJoueur.pv / monstreJoueur.pvMax}")
    }
    fun jouer() {

        afficherCombat()
        if (monstreJoueur.vitesse >= monstreSauvage.vitesse) {
            if (actionJoueur() == false) return
            actionAdversaire()
        }
        else{
            actionAdversaire()
            if (gameOver() == false) actionJoueur()
        }
        return
    }
    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lanceCombat() {
        while (!gameOver() && !jouerGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }

}