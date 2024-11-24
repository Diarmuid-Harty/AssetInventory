package controllers
import models.GameAsset

class GameAssetAPI() {

    private var assetList = ArrayList<GameAsset>()
    private var assetIDCounter = 0

    fun createAsset(asset: GameAsset): Boolean {
        asset.assetID = ++assetIDCounter
        return assetList.add(asset)
    }




}