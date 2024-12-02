package controllers

import models.GameAsset
import utils.readNextInt

class GameAssetAPI() {

    private var assetList = ArrayList<GameAsset>()
    private var assetIDCounter = 0

    fun createAsset(asset: GameAsset): Boolean {
        asset.assetID = ++assetIDCounter
        return assetList.add(asset)
    }

    fun deleteAssetFromArray(itemToDelete: Int) {

        // .find found here: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/find.html
        val assetToDelete = assetList.find { it.assetID == itemToDelete }
        if (assetToDelete != null) {
            println("Asset selected for deletion:")
            printAssetFull(assetToDelete)
            println("Proceed with deletion? \n 1) Yes\n 2) No")
            val confirmDelete = readNextInt("\n > ")
            when (confirmDelete) {
                1 -> {
                    assetList.remove(assetToDelete)
                    println("Deletion Succesful")
                }

                2 -> println("Deletion Cancelled")
            }
        } else noAssetFound()
    }

    fun assetIDRange(assetID: Int): Boolean {
        return assetList.any { it.assetID == assetID }
    }

    fun assetListSize(): Int {
        return assetList.size
    }

    fun printAssetFull(asset: GameAsset) {
        println(
            """
                |  ,---------------------------------
                |  | Asset Details              
                |  |---------------------------------
                |  |ID: ${asset.assetID}
                |  |Name: ${asset.assetName}
                |  |Description: ${asset.assetDescription}
                |  |Type: ${asset.assetType}
                |  '---------------------------------
                """.trimMargin()
        )
    }

    fun listAssets() {
        if (assetList.isNotEmpty()) {
            println(
                """
                |  ,---------------------------------
                |  | ASSETS
            """.trimMargin()
            )
            assetList.forEach { asset ->
                println(
                    """
                 |  |---------------------------------
                 |  |ID: ${asset.assetID} | Name: ${asset.assetName}
                """.trimMargin()
                )
            }
            println(
                """
                |  `---------------------------------
                
            """.trimMargin()
            )
        } else {
            noAssetFound()
        }
    }

    fun noAssetFound() {
        println("")

        println(
            """
             |  .------------------------.
             |  |     No Assets Found    |
             |  '------------------------'
            """.trimMargin()
        )
    }

}