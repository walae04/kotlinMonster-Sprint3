package org.example.DAO
import org.example.db
import org.example.jdbc.BDD
import org.example.monstre.EspeceMonster
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

/**
 * DAO (Data Access Object) pour la table `EspecesMonstres`.
 *
 * Cette classe gère les opérations CRUD :
 * - 🔍 Lecture (findAll, findById, findByNom)
 * - 💾 Sauvegarde (save, saveAll)
 * - ❌ Suppression (deleteById)
 *
 * @param bdd L'objet de connexion à la base de données.
 */
class EspeceMonsterDAO(val bdd: BDD = db) {

    /**
     * Récupère toutes les espèces de monstres présentes dans la base.
     */
    fun findAll(): MutableList<EspeceMonster> {
        val result = mutableListOf<EspeceMonster>()
        val sql = "SELECT * FROM EspeceMonstre"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                result.add(
                    EspeceMonster(
                        resultatRequete.getInt("id"),
                        resultatRequete.getString("nom"),
                        resultatRequete.getString("type"),
                        resultatRequete.getInt("baseAttaque"),
                        resultatRequete.getInt("baseDefense"),
                        resultatRequete.getInt("baseVitesse"),
                        resultatRequete.getInt("baseAttaqueSpe"),
                        resultatRequete.getInt("baseDefenseSpe"),
                        resultatRequete.getInt("basePv"),
                        resultatRequete.getDouble("modAttaque"),
                        resultatRequete.getDouble("modDefense"),
                        resultatRequete.getDouble("modVitesse"),
                        resultatRequete.getDouble("modAttaqueSpe"),
                        resultatRequete.getDouble("modDefenseSpe"),
                        resultatRequete.getDouble("modPv"),
                        resultatRequete.getString("description") ?: "",
                        resultatRequete.getString("particularites") ?: "",
                        resultatRequete.getString("caracteres") ?: ""
                    )
                )
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche une espèce par son identifiant.
     */
    fun findById(id: Int): EspeceMonster? {
        var result: EspeceMonster? = null
        val sql = "SELECT * FROM EspeceMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null && resultatRequete.next()) {
            result = EspeceMonster(
                resultatRequete.getInt("id"),
                resultatRequete.getString("nom"),
                resultatRequete.getString("type"),
                resultatRequete.getInt("baseAttaque"),
                resultatRequete.getInt("baseDefense"),
                resultatRequete.getInt("baseVitesse"),
                resultatRequete.getInt("baseAttaqueSpe"),
                resultatRequete.getInt("baseDefenseSpe"),
                resultatRequete.getInt("basePv"),
                resultatRequete.getDouble("modAttaque"),
                resultatRequete.getDouble("modDefense"),
                resultatRequete.getDouble("modVitesse"),
                resultatRequete.getDouble("modAttaqueSpe"),
                resultatRequete.getDouble("modDefenseSpe"),
                resultatRequete.getDouble("modPv"),
                resultatRequete.getString("description") ?: "",
                resultatRequete.getString("particularites") ?: "",
                resultatRequete.getString("caracteres") ?: ""
            )
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche des espèces par leur nom.
     */
    fun findByNom(nomRechercher: String): MutableList<EspeceMonster> {
        val result = mutableListOf<EspeceMonster>()
        val sql = "SELECT * FROM EspeceMonstre WHERE nom = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setString(1, nomRechercher)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                result.add(
                    EspeceMonster(
                        resultatRequete.getInt("id"),
                        resultatRequete.getString("nom"),
                        resultatRequete.getString("type"),
                        resultatRequete.getInt("baseAttaque"),
                        resultatRequete.getInt("baseDefense"),
                        resultatRequete.getInt("baseVitesse"),
                        resultatRequete.getInt("baseAttaqueSpe"),
                        resultatRequete.getInt("baseDefenseSpe"),
                        resultatRequete.getInt("basePv"),
                        resultatRequete.getDouble("modAttaque"),
                        resultatRequete.getDouble("modDefense"),
                        resultatRequete.getDouble("modVitesse"),
                        resultatRequete.getDouble("modAttaqueSpe"),
                        resultatRequete.getDouble("modDefenseSpe"),
                        resultatRequete.getDouble("modPv"),
                        resultatRequete.getString("description") ?: "",
                        resultatRequete.getString("particularites") ?: "",
                        resultatRequete.getString("caracteres") ?: ""
                    )
                )
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Insère ou met à jour une espèce dans la base.
     */
    fun save(espece: EspeceMonster): EspeceMonster? {
        val requetePreparer: PreparedStatement

        if (espece.id == 0) {
            // Insertion
            val sql = """
                INSERT INTO EspeceMonstre 
                (nom, type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv, 
                modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv, 
                description, particularites, caracteres)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """.trimIndent()

            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        } else {
            // Mise à jour
            val sql = """
                UPDATE EspeceMonstre SET 
                nom=?, type=?, baseAttaque=?, baseDefense=?, baseVitesse=?, baseAttaqueSpe=?, baseDefenseSpe=?, basePv=?, 
                modAttaque=?, modDefense=?, modVitesse=?, modAttaqueSpe=?, modDefenseSpe=?, modPv=?, 
                description=?, particularites=?, caracteres=?
                WHERE id=?
            """.trimIndent()

            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        }

        // Assignation des paramètres communs
        requetePreparer.setString(1, espece.nom)
        requetePreparer.setString(2, espece.type)
        requetePreparer.setInt(3, espece.baseAttaque)
        requetePreparer.setInt(4, espece.baseDefense)
        requetePreparer.setInt(5, espece.baseVitesse)
        requetePreparer.setInt(6, espece.baseAttaqueSpe)
        requetePreparer.setInt(7, espece.baseDefenseSpe)
        requetePreparer.setInt(8, espece.basePv)
        requetePreparer.setDouble(9, espece.modAttaque)
        requetePreparer.setDouble(10, espece.modDefense)
        requetePreparer.setDouble(11, espece.modVitesse)
        requetePreparer.setDouble(12, espece.modAttaqueSpe)
        requetePreparer.setDouble(13, espece.modDefenseSpe)
        requetePreparer.setDouble(14, espece.modPv)
        requetePreparer.setString(15, espece.description)
        requetePreparer.setString(16, espece.particularites)
        requetePreparer.setString(17, espece.caractères)

        if (espece.id != 0) requetePreparer.setInt(18, espece.id)

        val nbLigneMaj = requetePreparer.executeUpdate()

        if (nbLigneMaj > 0 && espece.id == 0) { // 🔹 seulement pour l'insertion
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                espece.id = generatedKeys.getInt(1)
            }
        }

        requetePreparer.close()
        return if (nbLigneMaj > 0) espece else null
    }

        /**
     * Supprime une espèce par son identifiant.
     */
    fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM EspeceMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id)

        return try {
            val nbLigneMaj = requetePreparer.executeUpdate()
            requetePreparer.close()
            nbLigneMaj > 0
        } catch (erreur: SQLException) {
            println("Erreur lors de la suppression de l'espèce : ${erreur.message}")
            false
        }
    }

    /**
     * Sauvegarde plusieurs espèces de monstres à la fois.
     */
    fun saveAll(especes: Collection<EspeceMonster>): MutableList<EspeceMonster> {
        val result = mutableListOf<EspeceMonster>()
        for (e in especes) {
            val sauvegarde = save(e)
            if (sauvegarde != null) result.add(sauvegarde)
        }
        return result
    }
}
