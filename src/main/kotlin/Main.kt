import utils.*
import models.*
import controllers.GameAssetAPI
import controllers.AssetFileLinkAPI
import persistence.JSONSerializer
import persistence.Serializer
import persistence.XMLSerializer
import java.io.File
import kotlin.system.exitProcess

//val GameAssetAPI = GameAssetAPI(chosenFileType(File("assetFile.json")))
//val AssetFileLinkAPI = AssetFileLinkAPI(chosenFileType(File("linkedFiles.json")))
val GameAssetAPI = GameAssetAPI(JSONSerializer(File("assetFile.json")))
val AssetFileLinkAPI = AssetFileLinkAPI(JSONSerializer(File("linkedFiles.json")))

fun main() {

    // no way to initialise GameAssetAPI and AssetFileLinkAPI with different save file types with this method
    // getFileType()

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
    |  |--------------------------|
    |  |  8) Load All             |
    |  |  9) Save All             |
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

            8 -> loadAll()
            9 -> saveAll()
            0 -> exitApp()
            else -> println("Error: Please enter a Number within the range")
        }
    } while (true)
}


// Collects the asset information from user and builds the asset object
// add unique identifier, figure out how to synchronise it to the assetFileLink object
fun collectAssetDetails() {
    val assetName = readNextLine("Enter asset name\n> ")
    val assetDescription = readNextLine("Enter asset description\n> ")
    val assetType = readNextLine("Enter asset type\n> ")

    val isAdded = GameAssetAPI.createAsset(GameAsset(assetID = 0, assetName, assetDescription, assetType))
    confirmAdd(isAdded)
}


// Will need unique identifier input for correct asset, add a persistent counter later
// add file path logic, (OS Dependant)
fun collectAssetFileLinkDetails() {
    var loop: Int = 1

    GameAssetAPI.listAssets()
    val parentID = readNextInt("Enter the ID of the asset you want to link this file to\n> ")

    while (loop == 1) {
        val fileName = readNextLine("Enter file name as it is named in its folder\n> ")
        val fileDescription = readNextLine("Enter file description\n>")
        val isAdded = AssetFileLinkAPI.createAssetFileLink(AssetFileLink(parentID, fileID = 0, fileName, fileDescription))
        confirmAdd(isAdded)

        loop = readNextInt("Add another file under this asset?\n1) Yes\n2) No\n> ")
        }
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
fun loadAll() {
    GameAssetAPI.loadAssets()
    AssetFileLinkAPI.loadFiles()
}
fun saveAll() {
    GameAssetAPI.saveAssets()
    AssetFileLinkAPI.saveFiles()
}

fun getFileType(): String {
    var chosenFileType: String = ""
    var fileType: Int
    while (true) {
        fileType = readNextInt("Choose save file format:\n1) JSON\n2) XML\n> ")
        if (fileType == 1) {
            chosenFileType = "JSONSerialiser"
            break
        } else if (fileType == 2) {
            chosenFileType = "XMLSerialiser"
            break
        } else {
            println("Incorrect entry, choose 1 or 2")
        }
    }
    return chosenFileType
}

// populate arrays for testing
fun populateArrays() {
    GameAssetAPI.createAsset(GameAsset(0, "Axe", "A chopping tool.", "Tool"))
    GameAssetAPI.createAsset(GameAsset(0, "BMW Coupe", "A powerful car with a high top speed.", "Vehicle"))
    GameAssetAPI.createAsset(GameAsset(0, "Bandage", "A sterile wrap for dressing wounds.", "Medical"))
    GameAssetAPI.createAsset(GameAsset(0, "Rifle", "A precision hunting rifle.", "RangedWeapon"))
    GameAssetAPI.createAsset(GameAsset(0, "Wooden Plank", "A plank of wood, used for construction.", "Material"))

    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(1, 0, "Axe 3D Model", "High res axe model"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(2, 0, "BMW Coupe Engine Sound", "BMW engine sound file"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(3, 0, "Bandage Texture", "Texture file for Bandage"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(4, 0, "Rifle Physics Properties", "XML Containing handling and bullet physics fo hunting rifle"))
    AssetFileLinkAPI.createAssetFileLink(AssetFileLink(5, 0, "Wooden Plank Thumbnail", "Low res wood plank thumbnail"))
}

fun exitApp() {
    println("Exiting App")
    saveAll()
    exitProcess(0)
}


