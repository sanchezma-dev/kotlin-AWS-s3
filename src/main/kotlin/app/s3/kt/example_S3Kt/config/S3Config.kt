package app.s3.kt.example_S3Kt.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client


@Configuration
class S3Config {

    @Value("\${aws.accessKeyId}")
    lateinit var awsAccessKeyId: String;

    @Value("\${aws.secretKey}")
    lateinit var awsSecretKey: String;

    @Value("\${aws.region}")
    lateinit var awsRegion: String;

    @Bean
    fun configAwsS3(): S3Client {

        val credentials = AwsBasicCredentials.create(awsAccessKeyId, awsSecretKey)

        return S3Client.builder()
            .region(Region.EU_SOUTH_2)
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .build()
    }

}