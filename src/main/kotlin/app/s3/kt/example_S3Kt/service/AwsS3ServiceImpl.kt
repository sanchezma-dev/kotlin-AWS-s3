package app.s3.kt.example_S3Kt.service

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.ResponseInputStream
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*

@Service
class AwsS3ServiceImpl(private var s3Client: S3Client) : IAwsS3Service {

    private val log = KotlinLogging.logger {}

    @Value("\${aws.s3.bucket}")
    lateinit var bucketName: String

    override fun uploadFileAWS(multipartFile: MultipartFile): String {
        try {
            val putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("jsm - ${multipartFile.originalFilename}")
                .build()

            val requestBody = RequestBody.fromInputStream(multipartFile.inputStream, multipartFile.size)

            // subir el archivo a S3 con putObjectRequest
            s3Client.putObject(putObjectRequest, requestBody)

            return "${multipartFile.originalFilename} file uploaded!!"

        } catch (e: S3Exception) {
            log.error { "Error: uploading file" }
            return "Error in uploadFile!!"
        }
    }

    override fun downloadFileAWS(fileName: String): ByteArray {
        try {
            val getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build()

            val responseStream: ResponseInputStream<GetObjectResponse> = s3Client.getObject(getObjectRequest)

            return responseStream.use { it.readBytes() }
        } catch (e: S3Exception) {
            log.error { "Error in download file of S3: ${e.message}" }
            throw RuntimeException("Error downloading file from S3: ${e.message}")
        }
    }

    override fun listBuckets(): List<String> {
        val response = s3Client.listBuckets()
        val listBucketsAWS = response.buckets().map { bucket -> bucket.name() }
        log.info { "Numbers of available bucket: {${listBucketsAWS.size}}" }
        return listBucketsAWS
    }

    override fun getListFileAWS(): List<String> {
        // Petición
        val listObjectsBuckets = ListObjectsV2Request.builder()
            .bucket(bucketName)
            .build()

        // Respuesta de objetos del bucket
        val result: ListObjectsV2Response = s3Client.listObjectsV2(listObjectsBuckets)

        return result.contents()
            .map { fileName -> fileName.key() }
            .filter { filaName ->
                filaName.endsWith(".png")
                        || filaName.endsWith(".jpg")
            }
    }

}