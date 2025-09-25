package org.example.monde

import org.example.dresseur.Entraineur
import org.example.monstre.EspeceMonster

class Zone(var id: Int,
           var nom: String,
           var expZone : Int,
           var especesMonstres : MutableList <EspeceMonster> = mutableListOf<EspeceMonster>(),
           var zoneSuivante : Zone? ,
           var zonePrecedente : Zone?
    //TODO rencontreMonstre()

) {

    fun genereMonstre() {
        var id: Int
        var espece: String
        var nom: String
        var entraineur: Entraineur?
        var expInit: Double


    }
}