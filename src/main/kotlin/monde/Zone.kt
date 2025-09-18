package org.example.monde

import org.example.monstre.EspeceMonster

class Zone(var id: Int,
           var nom: String,
           var expZone : Int,
           var especesMonstres : MutableList <EspeceMonster> = mutableListOf<EspeceMonster>(),
           var zoneSuivante : Zone? ,
           var zonePrecedente : Zone?
//TODO : -faire la méthode genereMonstre()
    //TODO : -faire la méthode rencontreMonstre()
    ) {
}