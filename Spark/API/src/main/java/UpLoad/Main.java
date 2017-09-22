package UpLoad;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import spark.Request;

public class Main {

    public static void main(String[] args) {
        enableDebugScreen();

        File uploadDir = new File("src/main/java/UpLoad/upload");
        uploadDir.mkdir(); // cria o diretório, caso não exista

        staticFiles.externalLocation("src/main/java/UpLoad/upload");

        get("/", (req, res) ->
                "<form method='post' enctype='multipart/form-data'>" // enctype: especifica como os dados do formulário devem ser codificados p/ enviá-lo ao servidor. ex: text/plain
                        + "    <input type='file' name='arquivo_upload' accept='.png'>"
                        + "    <button>Upload</button>"
                        + "</form>"
        );

        post("/", (req, res) -> {

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
            System.out.println(tempFile);
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp")); // Para o Spark ser capaz de extrair o arquivo carregado
            System.out.println(req.raw().getPart("arquivo_upload"));
            try (InputStream input = req.raw().getPart("arquivo_upload").getInputStream()) { // getPart precisa do mesmo "nome" do campo do formulário
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            logInfo(req, tempFile);
            return "<h1>Você fez o upload desta imagem:<h1><img src='" + tempFile.getFileName() + "'>";

        });

    }

    // Métodos para logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Upload do arquivo '" + getFileName(req.raw().getPart("arquivo_upload")) + "' salvo como '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}