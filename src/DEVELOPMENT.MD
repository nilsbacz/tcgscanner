# tcgscanner
tcgscanner is a Spring Boot based API for analyzing images of Pokemon TCG cards and retrieving their information.

## commands

`./gradlew bootRun` to start the application (runs on [http://localhost:8080/](http://localhost:8080/))

## prerequisites

For optical character recognition, this project uses [Tesseract OCR](https://github.com/tesseract-ocr/tesseract).
Install Tesseract: `sudo apt-get install tesseract-ocr`