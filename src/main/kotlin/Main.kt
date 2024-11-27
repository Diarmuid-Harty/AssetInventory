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
    |  |--------------------------|
    |  | Main Menu                |  
    |  |                          | 
    |  |  1) Create Asset         |
    |  |  2) Create file links    |
    |  |  3) List Data            |
    |  |                          |
    |  |  99) Populate Lists      |
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
            3 -> listItems()

            99 -> populateArrays()
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
         |  .------------------------.
         |  |   Added Successfully   |
         |  '------------------------'
        """.trimMargin()
        )
    } else {
        println(
            """
         |  .----------------.
         |  |   Add Failed   |
         |  '----------------'
        """.trimMargin()
        )
    }
}

fun listItems() {
    println(
        """
            |  .--------------------------.
            |  |   Choose what to list    |
            |  |--------------------------|
            |  |   1) Assets              |
            |  |   2) Files               |
            |  `--------------------------'
    """.trimMargin()
    )
    var input = readNextInt("\n> ")

    when (input) {
        1 -> GameAssetAPI.listAssets()
        2 -> AssetFileLinkAPI.listFiles()
    }
}

// populate arrays for testing
fun populateArrays() {
    GameAssetAPI.createAsset(GameAsset(0, "Axe", "A chopping tool.", "Tool"))
    GameAssetAPI.createAsset(GameAsset(0, "BMW Coupe", "A powerful car with a high top speed.", "Vehicle"))
    GameAssetAPI.createAsset(GameAsset(0, "Bandage", "A sterile wrap for dressing wounds.", "Medical"))
    GameAssetAPI.createAsset(GameAsset(0, "Rifle", "A precision hunting rifle.", "RangedWeapon"))
    GameAssetAPI.createAsset(GameAsset(0, "Wooden Plank", "A plank of wood, used for construction.", "Material"))

    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Axe 3D Model", "High res axe model"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "BMW Coupe Engine Sound", "BMW engine sound file"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Bandage Texture", "Texture file for Bandage"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Rifle Physics Properties", "XML Containing handling and bullet physics fo hungitng rifle"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Wooden Plank Thumbnail", "Low res wood plank thumbnail"))

}

fun exitApp() {
    println("Exiting App")
    exitProcess(0)
}


