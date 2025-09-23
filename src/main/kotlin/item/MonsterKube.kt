package org.example.item
import org.example.joueur
import org.example.monstre.IndividuMonstre
import kotlin.div
import kotlin.random.Random


class MonsterKube(id: Int,
                  nom: String,
                  description : String,
                  var chanceCapture: Double):
    Item (id,nom,description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez le Monster Kube! ")
        if (cible.entraineur != null) {
            println("Le monstre ne peut pas être capturé.")
            return false
        } else {
            var ratioVie = cible.pv / cible.pvMax
            var chanceEffective = chanceCapture * (1.5 - ratioVie)
            if (chanceEffective < 5) chanceEffective = 5.0
            var nbAleatoire = (0 until 100).random()
            if (nbAleatoire < chanceEffective) {
                println("le monstre est capturé !")
                cible.renommer()
                if (joueur.equipeMonstre.size >= 6) {
                    joueur.boiteMonstre.add(cible)
                } else {
                    joueur.equipeMonstre.add(cible)
                }
                cible.entraineur = joueur
                return true
            } else println("Presque! le kube n'a pas pu capturer le monstre")
        return false
        }

    }
}