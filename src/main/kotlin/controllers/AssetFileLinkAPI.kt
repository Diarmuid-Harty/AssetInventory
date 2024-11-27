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

    fun listFiles() {
        if (linkedFileList.isNotEmpty()) {
            println("""
                |  ,---------------------------------
                |  | FILES
            """.trimMargin()
            )
            linkedFileList.forEach { asset ->
                println(
                    """
                        |  |---------------------------------
                        |  |ID: ${asset.fileID} | Name: ${asset.fileName}
                    """.trimMargin()
                )
            }
            println("""
                |  `---------------------------------
                
            """.trimMargin())
        } else {
            println(
                """
             |  .-----------------------.
             |  |     No Files Found    |
             |  '-----------------------'
            """.trimMargin()
            )
        }
    }
}
