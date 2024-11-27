package controllers

import models.GameAsset

class GameAssetAPI() {

    private var assetList = ArrayList<GameAsset>()
    private var assetIDCounter = 0

    fun createAsset(asset: GameAsset): Boolean {
        asset.assetID = ++assetIDCounter
        return assetList.add(asset)
    }

    fun listAssets() {
        if (assetList.isNotEmpty()) {
            println("""
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
            println("""
                |  `---------------------------------
                
            """.trimMargin())
        } else {
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


}