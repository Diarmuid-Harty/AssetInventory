
import Utils.*
import Controllers.GameAssetAPI
import Models.GameAsset
import java.lang.System.exit
import kotlin.system.exitProcess

val GameAssetAPI = GameAssetAPI()

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
            1 -> collectAssetDetails() //
            //2 -> GameAssetAPI.printList() // print Assets

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

    // temp id assigned -- add self iterating id
    val isAdded = GameAssetAPI.createAsset(GameAsset(assetID = 0, assetName, assetDescription, assetType))

    if (isAdded) {
        println("""
            .------------------------------.
            |   Asset Added Successfully   |
            '------------------------------'
        """.trimIndent()
        )
    } else {
        println("""
            .----------------------.
            |   Add Asset Failed   |
            '----------------------'
        """.trimIndent()
        )
    }
}

fun exitApp() {
    println("Exiting App")
    exitProcess(0)
}


