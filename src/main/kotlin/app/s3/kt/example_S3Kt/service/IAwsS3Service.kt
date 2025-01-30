package app.s3.kt.example_S3Kt.service

import org.springframework.web.multipart.MultipartFile

interface IAwsS3Service {

    fun uploadFileAWS(multipartFile: MultipartFile): String

    fun downloadFileAWS(fileName: String): ByteArray

    fun listBuckets(): List<String>

    fun getListFileAWS(): List<String>
}