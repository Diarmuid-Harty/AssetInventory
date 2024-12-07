package models

data class AssetFileLink (
    var parentID: Int,
    var fileID: Int,
    var fileName: String,
    var fileDescription: String
    // var pathToFile: String,
    // var fileType: String,
    // var fileSize: Long
)