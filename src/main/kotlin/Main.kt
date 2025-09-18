package org.example
import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.EspeceMonster

var joueur = Entraineur(1,"Sacha",100)
var rival = Entraineur(2,"Regis",200)
var espece1 = EspeceMonster(1,	"Springleaf",	"Graine",	9,	11	,11,	12	,14,	60,	6.5,9.0,8.0,	7.0,	10.0,	34.0,"Petit monstre espiègle rond comme une graine, adore le soleil.",	"Sa feuille sur la tête indique son humeur.",	"Curieux, amical, timide")
var espece2 = EspeceMonster(4,"Flamkip","animal",12,8,13,16,7,50,10.0,5.5,9.5,9.5,6.5,22.0,"Petit animal entouré de flammes, déteste le froid.","Sa flamme change d’intensité selon son énergie.","Impulsif, joueur, loyal")
var espece3 = EspeceMonster(7,"Aquamy","Meteo",10,11,9,14,14,55,9.0,10.0,7.5,12.0,12.0,27.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.","Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")
var espece4 = EspeceMonster(8,"Laoumi","Animal",11,10,9,8,11,58,11.0,8.0,7.0,6.0,11.5,23.0,"Petit ourson au pelage soyeux, aime se tenir debout.","Son grognement est mignon mais il protège ses amis.","Affectueux, protecteur, gourmand")
var espece5 = EspeceMonster(10,"Bugsyface","Insecte",10,13,8,7,13,45,7.0,11.0,6.5,8.0,11.5,21.0,"Insecte à carapace luisante, se déplace par bonds et vibre des antennes.","Sa carapace devient plus dure après chaque mue.","Travailleur, sociable, infatigable")
var espece6 = EspeceMonster(13,"Galum","Minéral",12,15,6,8,12,55,9.0,13.0,4.0,6.5,10.5,13.0,"Golem ancien de pierre, yeux lumineux en garde.","Peut rester immobile des heures comme une statue.","Sérieux, stoïque, fiable")

var route1 = Zone(1,"Zone A",2500,mutableListOf(espece1,espece2,espece3),null,null)
var route2 = Zone(2,"Zone B",2000,mutableListOf(espece4,espece5,espece6),null,null)

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1


    println(espece1.afficheArt())
    println(espece2.afficheArt())
    println(espece3.afficheArt())
    println(espece4.afficheArt())
    println(espece5.afficheArt())
    println(espece6.afficheArt())
    //t



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
