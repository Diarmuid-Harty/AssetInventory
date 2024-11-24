package Controllers

import Models.GameAsset
import Utils.readNextLine

class GameAssetAPI() {

    private var assetList = ArrayList<GameAsset>()
    private var assetIDCounter = 0

    fun createAsset(asset: GameAsset): Boolean {
        return assetList.add(asset)
    }

}