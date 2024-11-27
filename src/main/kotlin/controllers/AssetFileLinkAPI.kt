package controllers
import models.AssetFileLink

class AssetFileLinkAPI {

    private var linkedFileList = ArrayList<AssetFileLink>()
    private var linkedFileIDCounter = 0

    //Add Link to gameAsset ID

    fun createAssetFileLink(assetLink: AssetFileLink): Boolean {
        assetLink.fileID = ++linkedFileIDCounter
        return linkedFileList.add(assetLink)
    }
}