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
    |  |  4) Delete Data          |
    |  |                          |
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
        val choice = mainMenu()
        when (choice) {
            1 -> collectAssetDetails()
            2 -> collectAssetFileLinkDetails()
            3 -> listItems()
            4 -> deleteItem()

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


// figure out?????????

fun listItems() {
    val title = "  Choose what to list  "
    chooseType(title, true)
    println("Enter the ID number for the object you want to select")
}

fun deleteItem() {
    val title = " Choose type to delete "
    val input = chooseType(title, true)

    println("Enter the ID number for the item you would like to delete")
    val itemToDelete = readNextInt("\n > ")
    if (input == 1) {
        if (GameAssetAPI.assetIDRange(itemToDelete)) {
            GameAssetAPI.deleteAssetFromArray(itemToDelete)
        } else GameAssetAPI.noAssetFound()
    } else if (input == 2) {
        if (AssetFileLinkAPI.fileIDRange(itemToDelete)) {
            AssetFileLinkAPI.deleteFileFromArray(itemToDelete)
        } else {
            AssetFileLinkAPI.noFileFound()
        }
    } else {
        println("Please choose either 1 or 2")
    }
}


fun chooseType(title: String, triggerListByType: Boolean): Int {
    println(
        """
            |  .--------------------------.
            |  | $title  |
            |  |--------------------------|
            |  | 1) Assets                |
            |  | 2) Files                 |
            |  `--------------------------'
    """.trimMargin()
    )
    val type = readNextInt("\n > ")

    if (triggerListByType) {
        when (type) {
            1 -> GameAssetAPI.listAssets()
            2 -> AssetFileLinkAPI.listFiles()
        }
    }
    return type
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
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Rifle Physics Properties", "XML Containing handling and bullet physics fo hunting rifle"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(0, "Wooden Plank Thumbnail", "Low res wood plank thumbnail"))
}

fun exitApp() {
    println("Exiting App")
    exitProcess(0)
}


