package controllers

import models.AssetFileLink
import utils.readNextInt

class AssetFileLinkAPI {

    private var linkedFileList = ArrayList<AssetFileLink>()
    private var linkedFileIDCounter = 0

    //TODO Add Link to gameAsset ID

    fun createAssetFileLink(assetLink: AssetFileLink): Boolean {
        assetLink.fileID = ++linkedFileIDCounter
        return linkedFileList.add(assetLink)
    }

    fun deleteFileFromArray(itemToDelete: Int) {

        // .find found here: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/find.html
        val fileToDelete = linkedFileList.find { it.fileID == itemToDelete }
        if (fileToDelete != null) {
            println("File selected for deletion:")
            printFileFull(fileToDelete)
            println("Proceed with deletion? \n 1) Yes\n 2) No")
            val confirmDelete = readNextInt("\n > ")
            when (confirmDelete) {
                1 -> {
                    linkedFileList.remove(fileToDelete)
                    println("Deletion Succesful")
                }

                2 -> println("Deletion Cancelled")
            }
        } else noFileFound()
    }

    fun fileIDRange(fileID: Int): Boolean {
        return linkedFileList.any { it.fileID == fileID }
    }

    fun linkedFileListSize(): Int {
        return linkedFileList.size
    }

    fun printFileFull(assetLink: AssetFileLink) {
        println(
            """
                |  ,---------------------------------
                |  | Asset Details              
                |  |---------------------------------
                |  |ID: ${assetLink.fileID}
                |  |Name: ${assetLink.fileName}
                |  |Description: ${assetLink.fileDescription}
                |  |Type: NOT YET IMPLEMENTED
                |  '---------------------------------
                """.trimMargin()
        )
    }

    fun listFiles() {
        if (linkedFileList.isNotEmpty()) {
            println(
                """
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
            println(
                """
                |  `---------------------------------
                
            """.trimMargin()
            )
        } else noFileFound()
    }

    fun noFileFound() {
        println(
            """
             |  .-----------------------.
             |  |     No Files Found    |
             |  '-----------------------'
            """.trimMargin()
        )
    }
}
