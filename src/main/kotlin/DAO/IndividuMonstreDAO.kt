package org.example.DAO
import org.example.db
import org.example.dresseur.Entraineur
import org.example.jdbc.BDD
import org.example.monstre.EspeceMonster
import org.example.monstre.IndividuMonstre
import java.sql.PreparedStatement
import java.sql.Statement

class IndividuMonstreDAO(val bdd: BDD = db) {

    private val especeDAO = EspeceMonsterDAO(bdd)
    private val entraineurDAO = EntraineurDAO(bdd)

    /**
     * 🔹 Récupère tous les individus dans la base
     */
    fun findAll(): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        val sql = "SELECT * FROM db_monsters_wouahi.IndividusMonstre"
        val requete = bdd.connectionBDD!!.prepareStatement(sql)
        val resultat = bdd.executePreparedStatement(requete)

        if (resultat != null) {
            while (resultat.next()) {
                val id = resultat.getInt("id")
                val nom = resultat.getString("nom")
                val idEspece = resultat.getInt("idEspece")
                val idEntraineur = resultat.getInt("idEntraineur")
                val expInit = resultat.getDouble("expInit")

                val espece = especeDAO.findById(idEspece)
                val entraineur = entraineurDAO.findById(idEntraineur)

                if (espece != null) {
                    result.add(IndividuMonstre(id, nom, espece, entraineur, expInit))
                }
            }
        }

        requete.close()
        return result
    }

    /**
     * 🔹 Recherche un individu par son ID
     */
    fun findById(id: Int): IndividuMonstre? {
        val sql = "SELECT * FROM db_monsters_wouahi.IndividusMonstre WHERE id = ?"
        val requete = bdd.connectionBDD!!.prepareStatement(sql)
        requete.setInt(1, id)
        val resultat = bdd.executePreparedStatement(requete)
        var monstre: IndividuMonstre? = null

        if (resultat != null && resultat.next()) {
            val nom = resultat.getString("nom")
            val idEspece = resultat.getInt("idEspece")
            val idEntraineur = resultat.getInt("idEntraineur")
            val expInit = resultat.getDouble("expInit")

            val espece = especeDAO.findById(idEspece)
            val entraineur = entraineurDAO.findById(idEntraineur)

            if (espece != null) {
                monstre = IndividuMonstre(id, nom, espece, entraineur, expInit)
            }
        }

        requete.close()
        return monstre
    }

    /**
     * 🔹 Sauvegarde un individu (INSERT ou UPDATE)
     */
    fun save(monstre: IndividuMonstre): IndividuMonstre? {
        val requetePreparer: PreparedStatement

        if (monstre.id == 0) {
            // Insertion
            val sql = """
            INSERT INTO db_monsters_wouahi.IndividusMonstre 
            (nom, niveau, attaque, defense, vitesse, attaqueSpe, defenseSpe, pvMax,
             potentiel, exp, pv, espece_id, entraineur_equipe_id, entraineur_boite_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        } else {
            // Mise à jour
            val sql = """
            UPDATE db_monsters_wouahi.IndividusMonstre SET
            nom=?, niveau=?, attaque=?, defense=?, vitesse=?, attaqueSpe=?, defenseSpe=?, pvMax=?,
            potentiel=?, exp=?, pv=?, espece_id=?, entraineur_equipe_id=?, entraineur_boite_id=?
            WHERE id=?
        """.trimIndent()
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        }

        // Paramètres
        requetePreparer.setString(1, monstre.nom)
        requetePreparer.setInt(2, monstre.niveau)
        requetePreparer.setInt(3, monstre.attaque)
        requetePreparer.setInt(4, monstre.defense)
        requetePreparer.setInt(5, monstre.vitesse)
        requetePreparer.setInt(6, monstre.attaqueSpe)
        requetePreparer.setInt(7, monstre.defenseSpe)
        requetePreparer.setInt(8, monstre.pvMax)
        requetePreparer.setDouble(9, monstre.potentiel)
        requetePreparer.setDouble(10, monstre.exp)
        requetePreparer.setInt(11, monstre.pv)
        requetePreparer.setInt(12, monstre.espece.id)
        requetePreparer.setInt(13, monstre.entraineur?.id ?: 0) // équipe
        requetePreparer.setInt(14, monstre.entraineur?.id ?: 0) // boîte

        if (monstre.id != 0) requetePreparer.setInt(15, monstre.id)

        val nbLigneMaj = requetePreparer.executeUpdate()

        if (nbLigneMaj > 0 && monstre.id == 0) {
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                monstre.id = generatedKeys.getInt(1)
            }
        }

        requetePreparer.close()
        return if (nbLigneMaj > 0) monstre else null
    }


    /**
     * 🔹 Supprime un individu par son ID
     */
    fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM db_monsters_wouahi.IndividusMonstre WHERE id = ?"
        val requete = bdd.connectionBDD!!.prepareStatement(sql)
        requete.setInt(1, id)
        val nbLigne = requete.executeUpdate()
        requete.close()
        return nbLigne > 0
    }

    /**
     * 🔹 Sauvegarde une liste d’individus
     */
    fun saveAll(monstres: Collection<IndividuMonstre>): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        for (m in monstres) {
            val saved = save(m)
            if (saved != null) result.add(saved)
        }
        return result
    }
}
