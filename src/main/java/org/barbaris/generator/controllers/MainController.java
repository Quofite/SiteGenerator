package org.barbaris.generator.controllers;

import org.barbaris.generator.models.HtmlTemplates;
import org.barbaris.generator.models.PageModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/downloadpage")
    public String downloadPage(Model model) {
        return "download";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity download(Model model, @PathVariable("filename") String fileName) {
        // скачивание файла пользователем
        try {
            File file = new File("/home/gleb/Documents/GeneratedFiles/" + fileName + ".html");
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            HttpHeaders headers = new HttpHeaders();
            System.out.println(file.getAbsolutePath());
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getAbsolutePath());
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.TEXT_HTML).body(resource);
        } catch(FileNotFoundException ex) {
            return (ResponseEntity) ResponseEntity.notFound();
        }
    }

    @PostMapping("/generate")
    public String generation(@RequestBody PageModel page) {
        if (page != null) {
            // генерация файла

            String headerType = page.header;
            String footerType = page.footer;
            String siteName = page.siteName;

            String filePath = "/home/gleb/Documents/GeneratedFiles/" + siteName + ".html";

            try(FileOutputStream fos = new FileOutputStream(filePath)) {
                byte[] buffer = HtmlTemplates.siteHeader(siteName).getBytes();
                fos.write(buffer, 0, buffer.length);
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            // ------------------- ШАПКИ

            if(headerType.equals("1")) {

                if(!FileWriter.writeFile(HtmlTemplates.header1(siteName), filePath)) {
                    return "error";
                }
            } else if (headerType.equals("2")) {

                if(!FileWriter.writeFile(HtmlTemplates.header2(), filePath)) {
                    return "error";
                }
            }

            // -------------------- ФУТЕРЫ

            if(footerType.equals("1")) {
                if(!FileWriter.writeFile(HtmlTemplates.footer1(siteName), filePath)) {
                    return "error";
                }
            } else if (footerType.equals("2")) {
                if(!FileWriter.writeFile(HtmlTemplates.footer2(siteName), filePath)) {
                    return "error";
                }
            }


            // ----------------------

            FileWriter.writeFile(HtmlTemplates.pageEnd(), filePath);

            return "index";
        } else {
            return "error";
        }
    }
}
