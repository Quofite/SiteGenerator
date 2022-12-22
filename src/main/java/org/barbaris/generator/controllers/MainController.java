package org.barbaris.generator.controllers;

import org.barbaris.generator.models.HtmlTemplates;
import org.barbaris.generator.models.PageModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity download(Model model, @PathVariable("filename") String fileName) {
        // скачивание файла пользователем
        try {
            File file = new File("/home/gleb/Documents/GeneratedFiles/" + fileName + ".html");
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            HttpHeaders headers = new HttpHeaders();
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
    public String generation(@RequestBody PageModel page, Model model) {
        if (page != null) {
            // генерация файла

            String headerType = page.header;
            String footerType = page.footer;
            String siteName = page.siteName;
            String leftContent = page.leftContent;
            String rightContent = page.rightContent;

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
                    model.addAttribute("error_message", "could not add element");
                    return "error";
                }
            } else if (headerType.equals("2")) {

                if(!FileWriter.writeFile(HtmlTemplates.header2(), filePath)) {
                    model.addAttribute("error_message", "could not add element");
                    return "error";
                }
            }

            // -------------------- КОНТЕНТ

            if(rightContent == null) {
                rightContent = "";
            }

            if(leftContent == null) {
                leftContent = "";
            }

            FileWriter.writeFile(HtmlTemplates.contentBlock(rightContent, leftContent), filePath);

            // -------------------- ФУТЕРЫ

            if(footerType.equals("1")) {
                if(!FileWriter.writeFile(HtmlTemplates.footer1(siteName), filePath)) {
                    model.addAttribute("error_message", "could not add element");
                    return "error";
                }
            } else if (footerType.equals("2")) {
                if(!FileWriter.writeFile(HtmlTemplates.footer2(siteName), filePath)) {
                    model.addAttribute("error_message", "could not add element");
                    return "error";
                }
            }


            // ----------------------

            FileWriter.writeFile(HtmlTemplates.pageEnd(), filePath);

            return "index";
        } else {
            model.addAttribute("error_message", "page is null");
            return "error";
        }
    }

    @PostMapping("/image")
    public void image(@RequestParam("image")MultipartFile file, @RequestParam("site_name") String siteName) {

    }
}
