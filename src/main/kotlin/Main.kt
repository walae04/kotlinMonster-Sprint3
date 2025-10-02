package org.example
import org.example.dresseur.Entraineur
import org.example.item.Badge
import org.example.item.MonsterKube
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.Element
import org.example.monstre.EspeceMonster
import org.example.monstre.IndividuMonstre



var joueur = Entraineur(1,"Sacha",100)
var rival = Entraineur(2,"Regis",200)
var especeSpringLeaf = EspeceMonster(1,	"Springleaf",	"Graine",	9,	11	,11,	12	,14,	60,	6.5,9.0,8.0,	7.0,	10.0,	34.0,"Petit monstre espiègle rond comme une graine, adore le soleil.",	"Sa feuille sur la tête indique son humeur.",	"Curieux, amical, timide")
var especeFlamkip = EspeceMonster(4,"Flamkip","animal",12,8,13,16,7,50,10.0,5.5,9.5,9.5,6.5,22.0,"Petit animal entouré de flammes, déteste le froid.","Sa flamme change d’intensité selon son énergie.","Impulsif, joueur, loyal")
var especeAquamy = EspeceMonster(7,"Aquamy","Meteo",10,11,9,14,14,55,9.0,10.0,7.5,12.0,12.0,27.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.","Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")
var espece4 = EspeceMonster(8,"Laoumi","Animal",11,10,9,8,11,58,11.0,8.0,7.0,6.0,11.5,23.0,"Petit ourson au pelage soyeux, aime se tenir debout.","Son grognement est mignon mais il protège ses amis.","Affectueux, protecteur, gourmand")
var espece5 = EspeceMonster(10,"Bugsyface","Insecte",10,13,8,7,13,45,7.0,11.0,6.5,8.0,11.5,21.0,"Insecte à carapace luisante, se déplace par bonds et vibre des antennes.","Sa carapace devient plus dure après chaque mue.","Travailleur, sociable, infatigable")
var espece6 = EspeceMonster(13,"Galum","Minéral",12,15,6,8,12,55,9.0,13.0,4.0,6.5,10.5,13.0,"Golem ancien de pierre, yeux lumineux en garde.","Peut rester immobile des heures comme une statue.","Sérieux, stoïque, fiable")

var route1 = Zone(1,"Zone A",2500,mutableListOf(especeSpringLeaf,especeFlamkip,especeAquamy),null,null)
var route2 = Zone(2,"Zone B",2000,mutableListOf(espece4,espece5,espece6),null,null)

val monstre1 = IndividuMonstre(1, "springleaf", especeSpringLeaf,null,1500.0)
val monstre2 = IndividuMonstre(2, "flamkip", especeFlamkip,null,1500.0)
val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy,null,1500.0)

val monstreKube= MonsterKube(1,"spring","strong",10.2)
//val badgePierre= Badge(1,"Badge Roche","Badge gagné lorsque le joueur..."5)


var kube1 = MonsterKube(1, "le kube", "kube de capture", 50.0)

fun nouvellePartie(): Partie {
    println("Bonjour et bienvenu !! \n Quel est votre nom ?")
    var nomJoueur = readln()
    joueur.nom = nomJoueur

    var nouvelleGame: Partie = Partie(1, joueur, route1)
    return nouvelleGame
}
// 🔥🌱💧🐞🪨⚪ Variables globales
val feu = Element(1, "Feu 🔥")
val plante = Element(2, "Plante 🌱")
val eau = Element(3, "Eau 💧")
val insecte = Element(4, "Insecte 🐞")
val roche = Element(5, "Roche 🪨")
val normal = Element(6, "Normal ⚪")
fun main() {
    // 🔥 Feu
    feu.forces.addAll(listOf(plante, insecte, roche))
    feu.faiblesses.add(eau)
    // Pas d’immunité particulière pour Feu

    // 🌱 Plante
    plante.forces.addAll(listOf(eau, roche))
    plante.faiblesses.add(feu)
    // Pas d’immunité particulière pour Plante

    // 💧 Eau
    eau.forces.addAll(listOf(feu, roche))
    eau.faiblesses.add(plante)

    // 🐞 Insecte
    insecte.forces.addAll(listOf(plante))
    insecte.faiblesses.addAll(listOf(feu, roche))

    // 🪨 Roche
    roche.forces.addAll(listOf(insecte, feu))
    roche.faiblesses.add(eau)

    // ⚪ Normal
    normal.faiblesses.add(roche)

    println("${especeSpringLeaf.nom} (${especeSpringLeaf.elements[0].nom}) attaque ${especeAquamy.nom} : efficacité = ${especeSpringLeaf.elements[0].efficaciteContre(especeAquamy.elements[0])}")






    route1.zoneSuivante = route2
    route2.zonePrecedente = route1
    joueur.sacAItems.add(kube1)

    val partie = nouvellePartie()
    partie.choixStarter()
    partie.jouer()

//    println(monstre1)
//    monstre2.attaquer(monstre1)
//    println(monstre1)
//    //monstre2.pv-=2688
//    //println(monstre2)
//
//    route1.zoneSuivante = route2
//    route2.zonePrecedente = route1
//
//
//    println(especeSpringLeaf.afficheArt())
//    println(especeFlamkip.afficheArt())
//    println(especeAquamy.afficheArt())
//    println(espece4.afficheArt())
//    println(espece5.afficheArt())
//    println(espece6.afficheArt())
//    //t
//
//    //println(monstre2)
//    //monstre2.renommer()
//    //println(monstre2)
//
//    println("=== Détail du monstre 1 ===")
//    monstre1.afficherDetail()
//    println("=== Détail du monstre 2 ===")
//    monstre2.afficherDetail()
//    println("=== Détail du monstre 3 ===")
//    monstre3.afficherDetail()

//println(badgePierre)
}
/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */

fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron"-> "\u001B[38;5;94m"
        else -> "" // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}
