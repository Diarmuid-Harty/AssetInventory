import utils.*
import models.*
import controllers.GameAssetAPI
import controllers.AssetFileLinkAPI
import kotlin.system.exitProcess

val GameAssetAPI = GameAssetAPI()
val AssetFileLinkAPI = AssetFileLinkAPI()

fun main() {
    runMenu()
}

// Main menu used to navigate the program
fun mainMenu(): Int {
    print(
        """
    |  .--------------------------.
    |  |    Game Asset Manager    |
    |  | -------------------------|
    |  | Main Menu                |  
    |  |                          | 
    |  |  1) Create Asset         |
    |  |  2) Create file links    |
    |  |                          |
    |  |                          |
    |  |--------------------------|
    |  |  0) Exit                 |
    |  `--------------------------'
    """.trimMargin()
    )
    return readNextInt("\n> ")
}

// Mediates menu functionality
fun runMenu() {
    do {
        val input = mainMenu()
        when (input) {
            1 -> collectAssetDetails()
            2 -> collectAssetFileLinkDetails()

            0 -> exitApp()
            else -> println("Error: Please enter a Number within the range")
        }
    } while (true)
}

// Collects the asset information from user and builds the asset object
// add unique identifier, figure out how to synchronise it to the assetFileLink object
fun collectAssetDetails() {
    val assetName = readNextLine("Enter asset name: ")
    val assetDescription = readNextLine("Enter asset description: ")
    val assetType = readNextLine("Enter asset type: ")

    val isAdded = GameAssetAPI.createAsset(GameAsset(assetID = 0, assetName, assetDescription, assetType))
    confirmAdd(isAdded)
}


// Will need unique identifier input for correct asset, add a persistent counter later
// add file path logic, (OS Dependant)
fun collectAssetFileLinkDetails() {
    val fileName = readNextLine("Enter file name as it is named in its folder.")
    val fileDescription = readNextLine("Enter file description")

    val isAdded = AssetFileLinkAPI.createAssetFileLink(AssetFileLink(fileID = 0, fileName, fileDescription))
    confirmAdd(isAdded)
}

fun confirmAdd(isAdded: Boolean) {
    if (isAdded) {
        println(
            """
            .------------------------.
            |   Added Successfully   |
            '------------------------'
        """.trimIndent()
        )
    } else {
        println(
            """
            .----------------.
            |   Add Failed   |
            '----------------'
        """.trimIndent()
        )
    }
}

fun exitApp() {
    println("Exiting App")
    exitProcess(0)
}


