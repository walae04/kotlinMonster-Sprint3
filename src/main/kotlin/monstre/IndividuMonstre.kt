package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.round

class IndividuMonstre(
                      var id: Int,
                      var nom: String,
                      var espece: EspeceMonster,
                      var entraineur: Entraineur?,
                      expInit: Double
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 2)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 2)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 2)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 2)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 2)
    var pvMax: Int = espece.basePv + Random.nextInt(-2, 2)
    var potentiel: Double = Random.nextDouble(0.5, 2.0)
    var exp: Double = 0.0
        get() = field
        set(value) {
            // Assigner field = value
            field = value

            // Vérifier si niveau == 1
            var estNiveau1 = (niveau == 1)

            // Vérifier si palier atteint
            while (field >= palierExp(niveau)) {
                levelUp()


                // Affichage uniquement si pas niveau 1
                if (!estNiveau1) {
                    println("Le monstre $nom est maintenant niveau $niveau !")
                }
            }
        }
    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv.coerceIn(0, pvMax)

        }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100.0 * (niveau - 1).toDouble().pow(2)
    }
    /**
    * Augmenter de niveau un monstre, c'est-à-dire incrémenté le niveau, mais également calculer les nouvelles valeurs des caractéristiques (atq, def, vitesse, atqSpe, defSpe, et pvMax)
     * il faut aussi augmenter du nombre de pv par le nombre de pvMax gagné lors de l’augmentation du niveau.
     *
     **/
    fun levelUp(){
        niveau+=1
        attaque+= round((this.espece.modAttaque*potentiel)).toInt()+ Random.nextInt(-2,2)
        defense+= round((this.espece.modDefense*potentiel)).toInt()+ Random.nextInt(-2,2)
        vitesse+= round((this.espece.modVitesse*potentiel)).toInt()+ Random.nextInt(-2,2)
        attaqueSpe+= round((this.espece.modAttaqueSpe*potentiel)).toInt()+ Random.nextInt(-2,2)
        defenseSpe+= round((this.espece.modDefenseSpe*potentiel)).toInt()+ Random.nextInt(-2,2)
        pvMax+= round((this.espece.modPv*potentiel)).toInt()+ Random.nextInt(-5,5)
        pv = pvMax
    }
    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */

    fun attaquer(monstre: IndividuMonstre){
        var degatBrut=this.attaque
        var degatTotal=degatBrut-(this.defense/2)
        if (degatTotal<1) degatTotal=1
        var pvAvant=monstre.pv
        monstre.pv-=degatTotal
        var pvApres=monstre.pv
        println("$nom inflige ${pvAvant-pvApres}dégats à ${monstre.nom}")


    }
    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */
    fun renommer(){
        println("Renommer $nom")
       var nouveauNom= readln().toString()
        if (nouveauNom!="") this.nom=nouveauNom

    }
    fun afficherDetail() {
        val art = espece.afficheArt()
        val lignes = art.lines()
        val details = listOf(
            "Nom : ${nom}",
            "niveau : ${niveau}",
            "Pv : ${pv} / ${pvMax}",
            "Exp : ${exp}",
            "Atq : ${attaque}",
            "Def : ${defense}",
            "Vitesse : ${vitesse}",
            "AtqSpe : ${attaqueSpe}",
            "DefSpe : ${defenseSpe}"
        )
        val maxArtWidth = lignes.maxOfOrNull { it.length } ?: 0
        val maxLines = maxOf(lignes.size, details.size)
        for (i in 0 until maxLines) {
            val artLine = if (i < lignes.size) lignes[i] else ""
            val detailLine = if (i < details.size) details[i] else ""
            val paddedArt = artLine.padEnd(maxArtWidth + 4)

            println(paddedArt + detailLine)
        }
    }
}

//override fun toString(): String {
//    val textePresentation=
//       "-Nom : ${nom}\n - Espece : ${espece.nom}\n - Niveau : ${niveau}\n - Point de vie : ${pv}\n - Point de vie max : ${pvMax}\n"+
//      "- Attaque : ${attaque}\n - Defense : ${defense}\n - Vitesse : ${vitesse}\n"+
//       "-AttaqueSpe : ${attaqueSpe}\n - Defense : ${defenseSpe}\n- Potentiel : ${potentiel}\n"
//     return textePresentation