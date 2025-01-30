package app.s3.kt.example_S3Kt.controller

import app.s3.kt.example_S3Kt.service.IAwsS3Service
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("app/s3")
class AwsS3Controller(private var service: IAwsS3Service) {

    private val log = KotlinLogging.logger {}

    @PostMapping("/upload")
    fun upload(@RequestParam file: MultipartFile): ResponseEntity<String> {
        log.info { "Controller access to upload the file" }
        return ResponseEntity.ok(service.uploadFileAWS(file))
    }

    @GetMapping("/downloadFile")
    fun downloadFile(@RequestParam fileName: String): ResponseEntity<ByteArray> {
        log.info { "Controller access in the download file in Amazon S3. Name: {$fileName}" }

        val fileBytes = service.downloadFileAWS(fileName)

        val headers = HttpHeaders().apply {
            add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"$fileName\"")
            add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE) // Filtro para PNG
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(fileBytes)
    }

    @GetMapping("/bucketsAWS")
    fun bucketsAWS(): ResponseEntity<List<String>> {
        log.info { "Controller access to view the list of watch buckets" }
        return ResponseEntity.ok(service.listBuckets())
    }

    @GetMapping("/getListFileAWS")
    fun getListFileAWS(): ResponseEntity<List<String>> {
        log.info { "Controller access to view the list file name on bucket" }
        return ResponseEntity.ok(service.getListFileAWS())
    }


}