package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.especeSpringLeaf
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre

class Partie (
    val id: Int,
    var joueur: Entraineur,
    var zone: Zone
) {

    fun choixStarter() {
        var starter1 = IndividuMonstre(1, "springleaf", especeSpringLeaf, null, 1500.0)
        var starter2 = IndividuMonstre(2, "flamkip", especeSpringLeaf, null, 1500.0)
        var starter3 = IndividuMonstre(3, "aquamy", especeSpringLeaf, null, 1500.0)
        var starter: IndividuMonstre? = null

        starter1.afficherDetail()
        starter2.afficherDetail()
        starter3.afficherDetail()

        println("1. Springleaf    2. Flamkip    3.Aquamy")
        var choixStarter = readln().toInt()

        if (choixStarter !in 1..3) choixStarter()
        if (choixStarter == 1) {
            starter = starter1
        } else if (choixStarter == 2) {
            starter = starter2
        } else if (choixStarter == 3) {
            starter = starter3
        }

        starter!!.renommer()

        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur

    }

    fun modifierOrdreEquipe() {

        println("Entrez la position du monstre que vous voulez déplacer : ")
        var position1 = readln().toInt()

        println("Entrez la nouvelle position du monstre : ")
        var position2 = readln().toInt()

        var firstPokemon = joueur.equipeMonstre[position1]
        var secondPokemon = joueur.equipeMonstre[position2]

        joueur.equipeMonstre[position1] = secondPokemon
        joueur.equipeMonstre[position2] = firstPokemon

        println(joueur.equipeMonstre)

    }


    fun examineEquipe() {

        for (monstres in joueur.equipeMonstre) {
            print(joueur.equipeMonstre.indexOf(monstres))
            monstres.afficherDetail()
        }
        println()
        println(
            "Menu principal : q" +
                    "Modifier l'ordre des monstres : m" +
                    "Tapez le numéro du monstre pour voir les détails :"
        )

        var choix = readln()
        if (choix.lowercase() == "q") return
        else if (choix.lowercase() == "m") modifierOrdreEquipe()
        else if (choix.toInt() in 1..6) {
            var monstre = joueur.equipeMonstre[choix.toInt() - 1]
            monstre.afficherDetail()
        } else {
            println("Numero de montre inexistant")
            examineEquipe()
        }
    }


    fun jouer() {
        println(
            "Vous ete actuellement dans la zone : ${zone.nom}. Vous pouvez :\n" +
                    "1. Rencontrer un monstre sauvage\n" +
                    "2. Examiner l'equipe de monstres\n"
        )
        if (zone.zoneSuivante != null) println("3. Aller a la zone suivante")
        if (zone.zonePrecedente != null) println("3. Retourner a la zone precedente")

        var action = readln().toInt()

        when (action) {
            1 -> jouer()
            2 -> examineEquipe()
            3 -> zone = zone.zoneSuivante!!
            4 -> zone = zone.zonePrecedente!!

            else -> println("entré invalide")
        }
    }
}