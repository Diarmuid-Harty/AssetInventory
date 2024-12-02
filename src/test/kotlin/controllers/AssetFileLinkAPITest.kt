package controllers

import models.AssetFileLink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AssetFileLinkAPITest {

        // ide suggested late init
        lateinit var AssetFileLinkAPI: AssetFileLinkAPI

        @BeforeEach
        fun setup() {
            AssetFileLinkAPI = AssetFileLinkAPI()

            AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Axe 3D Model", "High res axe model"))
            AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "BMW Coupe Engine Sound", "BMW engine sound file"))
            AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Bandage Texture", "Texture file for Bandage"))
            AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Rifle Physics Properties", "XML Containing handling and bullet physics fo hungitng rifle"))
            AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Wooden Plank Thumbnail", "Low res wood plank thumbnail"))

        }

        @Nested
        inner class createAssetFileLink {
            @Test
            fun `adding an Asset increments id`() {
                val newAsset = AssetFileLink(0, "Steel Shield Block Sound", "Steel Sheild Block Sound File")
                val isAdded = AssetFileLinkAPI.createAssetFileLink(newAsset)

                assertTrue(isAdded)
                assertEquals(6, newAsset.fileID)

            }
        }

   /*
   Doesn't work due to needing to input confirm delete
   @Test
    fun `test delete file from array`() {
        assertEquals(5, AssetFileLinkAPI.linkedFileListSize())
        AssetFileLinkAPI.deleteFileFromArray(1)
        assertEquals(4, AssetFileLinkAPI.linkedFileListSize())
    } */

    @Test
    fun `delete file outside range should not delete anything`() {
        assertEquals(5, AssetFileLinkAPI.linkedFileListSize())
        AssetFileLinkAPI.deleteFileFromArray(10)
        assertEquals(5, AssetFileLinkAPI.linkedFileListSize())
    }

}