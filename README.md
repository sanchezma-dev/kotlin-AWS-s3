# 📌 Proyecto: API de Prueba con Spring Boot, Kotlin y AWS S3

## 📖 Descripción
Este es un proyecto de prueba desarrollado con **Spring Boot** y **Kotlin**, que utiliza **AWS S3** como servicio de almacenamiento. La aplicación permite interactuar con un bucket S3 mediante un cliente de AWS, proporcionando endpoints REST para subir, descargar y listar archivos.

## ✅ Prerrequisitos
Antes de ejecutar la aplicación, asegúrate de cumplir con los siguientes requisitos:
- Tener una cuenta de AWS con **S3 habilitado**.
- Haber creado un **bucket** en S3.
- Haber generado **credenciales de acceso** (Access Key y Secret Key) en AWS IAM.
- Configurar las credenciales en el archivo **application.properties** (las credenciales han sido eliminadas por seguridad, por lo que deberás agregarlas manualmente).

## 🔧 Tecnologías Utilizadas
- **Spring Boot 3.4.2**
- **Kotlin 1.9.25**
- **AWS SDK para Java v2 (S3)**
- **Gradle**

## 🚀 Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-repo.git
   ```
2. Configurar las credenciales de AWS en `src/main/resources/application.properties`:
   ```properties
   aws.accessKeyId=TU_ACCESS_KEY
   aws.secretAccessKey=TU_SECRET_KEY
   aws.bucketName=TU_BUCKET
   aws.region=TU_REGION
   ```
3. Construir el proyecto con Gradle:
   ```bash
   ./gradlew build
   ```
4. Ejecutar la aplicación:
   ```bash
   ./gradlew bootRun
   ```

## 📡 Endpoints Disponibles
### 📤 Subir un archivo a S3
```http
POST /upload
```
- **Parámetros:** `multipartFile` (archivo a subir)


### 📥 Descargar un archivo desde S3
```http
GET /downloadFile?fileName={nombre_archivo}
```
- **Parámetros:** `fileName` (nombre del archivo en el bucket)


### 📂 Listar archivos en el bucket
```http
GET /getListFileAWS
```
- **Respuesta:** Lista de nombres de archivos almacenados en el bucket

### 🗂️ Listar buckets disponibles en la cuenta
```http
GET /bucketsAWS
```
- **Respuesta:** Lista de nombres de los buckets en la cuenta de AWS

## 🛠️ Colección Postman
Se adjunta una colección de **Postman** con las solicitudes preconfiguradas para facilitar las pruebas. Puedes importarla en **Postman** y probar los diferentes endpoints sin necesidad de configurarlos manualmente.

## 📌 Notas Adicionales
- La aplicación ha sido desarrollada con **Spring Boot y Kotlin**, aprovechando la integración con **AWS SDK v2** para gestionar S3.
- Se han implementado logs para registrar las operaciones realizadas.
- La descarga de archivos devuelve imágenes en formato **PNG** para facilitar su visualización.

## 📜 Licencia
Este proyecto es solo una prueba y no tiene una licencia específica. Se recomienda **no exponer credenciales sensibles en repositorios públicos**.

---
🎯 **Desarrollado como prueba de integración con AWS S3 usando Kotlin y Spring Boot.** 🚀

