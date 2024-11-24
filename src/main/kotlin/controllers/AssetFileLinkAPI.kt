package controllers
import models.AssetFileLink

class AssetFileLinkAPI {

    private var linkedFileList = ArrayList<AssetFileLink>()
    private var linkedFileIDCounter = 0


    fun createAssetFileLink(assetLink: AssetFileLink): Boolean {
        assetLink.fileID = ++linkedFileIDCounter
        return linkedFileList.add(assetLink)
    }
}