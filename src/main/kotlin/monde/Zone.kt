package org.example.monde

import org.example.dresseur.Entraineur
import org.example.item.MonsterKube
import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstre.EspeceMonster
import org.example.monstre.IndividuMonstre
import org.example.monstreKube
import kotlin.random.Random

class Zone(var id: Int,
           var nom: String,
           var expZone : Int,
           var especesMonstres : MutableList <EspeceMonster> = mutableListOf<EspeceMonster>(),
           var zoneSuivante : Zone? ,
           var zonePrecedente : Zone?
    //TODO rencontreMonstre()

) {

    fun genereMonstre(): IndividuMonstre {


        var especeAlea = this.especesMonstres.random()

        var nouveauMonstre = IndividuMonstre(Random.nextInt(4,151), especeAlea.nom, especeAlea, null, expZone * Random.nextDouble(0.8,1.2))

        return nouveauMonstre

    }



    fun rencontreMonstre() {

        var monstreSauvage = genereMonstre()
        var premierMonstre : IndividuMonstre? = null
        for (monstre in joueur.equipeMonstre) {

            if (monstre.pv > 0) {premierMonstre = monstre}
        }
        var combat = CombatMonstre(premierMonstre!!, monstreSauvage)
        combat.lanceCombat()
    }

}
