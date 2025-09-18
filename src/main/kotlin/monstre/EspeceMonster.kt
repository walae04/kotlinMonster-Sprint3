package org.example.monstre

import java.io.File
/**
* Représente une espèce de monstre dans le contexte du jeu.
*
* Un EspeceMonstre décrit les caractéristiques de base d’un type de monstre (comme un « modèle » ou une espèce). Contient ses statistiques de base, ses multiplicateurs de croissance, son nom, son type et éventuellement son art ASCII.
*
* @property id L'identifiant unique de l'espèce de monstre.
* @property nom Le nom de l'espèce de monstre.
* @property type Le type élémentaire ou catégorie du monstre (ex : feu, eau, etc.).
* @property baseAttaque La statistique de base d’attaque physique.
* @property baseDefense La statistique de base de défense physique.
* @property baseVitesse La statistique de base de vitesse.
* @property baseAttaqueSpe La statistique de base d’attaque spéciale.
* @property baseDefenseSpe La statistique de base de défense spéciale
* @property basePv La statistique de base de points de vie (PV).
* @property modAttaque Le multiplicateur de croissance pour l’attaque physique.
* @property modDefense  Le multiplicateur de croissance pour la défense physique.
* @property modVitesse Le multiplicateur de croissance pour la vitesse
* @property modAttaqueSpe Le multiplicateur de croissance pour l’attaque spéciale.
* @property modDefenseSpe Le multiplicateur de croissance pour la défense spéciale.
* @property modPv Le multiplicateur de croissance pour les points de vie (PV).
 * @property description Une description textuelle de l'espèce de monstre.
 * @property particularites Particularités ou traits spécifiques à l'espèce.
 * @property caractères Traits de caractère ou comportement du monstre.

 */
class EspeceMonster(
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,

    val description: String = "",
    val particularites: String = "",
    val caractères: String = ""
    ) {


    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}