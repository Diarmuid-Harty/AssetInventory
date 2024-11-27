package controllers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import controllers.GameAssetAPI
import models.GameAsset
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested

class GameAssetAPITest {

    // ide suggested late init
    lateinit var GameAssetAPI: GameAssetAPI

    @BeforeEach
    fun setup() {
        GameAssetAPI = GameAssetAPI()

        GameAssetAPI.createAsset(GameAsset(0, "Axe", "A chopping tool.", "Tool"))
        GameAssetAPI.createAsset(GameAsset(0, "BMW Coupe", "A powerful car with a high top speed.", "Vehicle"))
        GameAssetAPI.createAsset(GameAsset(0, "Bandage", "A sterile wrap for dressing wounds.", "Medical"))
        GameAssetAPI.createAsset(GameAsset(0, "Rifle", "A precision hunting rifle.", "RangedWeapon"))
        GameAssetAPI.createAsset(GameAsset(0, "Wooden Plank", "A plank of wood, used for construction.", "Material"))

    }

    @Nested
    inner class createAsset {
        @Test
        fun `adding an Asset increments id`() {
            val newAsset = GameAsset(0, "Steel Shield", "A sturdy shield made of steel for defense.", "Armor")
            val isAdded = GameAssetAPI.createAsset(newAsset)

            assertTrue(isAdded)
            assertEquals(6, newAsset.assetID)

        }
    }

}