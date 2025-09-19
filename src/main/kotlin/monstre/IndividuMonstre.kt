package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.round

class IndividuMonstre(expInit: Double,
                      var id: Int,
                      var nom: String,
                      var espece: EspeceMonster,
                      var entraineur: Entraineur?
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
            if (field >= palierExp(niveau)) {
                levelUp()
                estNiveau1 = false

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
        return (100.0 * (niveau - 1)).pow(2)
    }
    /**
    * Augmenter de niveau un monstre, c'est-à-dire incrémenté le niveau, mais également calculer les nouvelles valeurs des caractéristiques (atq, def, vitesse, atqSpe, defSpe, et pvMax)
     * il faut aussi augmenter du nombre de pv par le nombre de pvMax gagné lors de l’augmentation du niveau.
     *
     **/
    fun levelUp(){
        attaque= round(attaque*potentiel).toInt()+ Random.nextInt(-2,2)
        defense= round(defense*potentiel).toInt()+ Random.nextInt(-2,2)
        vitesse= round(vitesse*potentiel).toInt()+ Random.nextInt(-2,2)
        attaqueSpe= round(attaqueSpe*potentiel).toInt()+ Random.nextInt(-2,2)
        defenseSpe= round(defenseSpe*potentiel).toInt()+ Random.nextInt(-2,2)
        pvMax= round(pvMax*potentiel).toInt()+ Random.nextInt(-5,5)

    }
}

