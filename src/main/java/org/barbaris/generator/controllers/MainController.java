package org.barbaris.generator.controllers;

import org.barbaris.generator.models.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    @PostMapping("/generate")
    public String generation(Model model, @RequestBody PageModel page) {
        File file = new File("/home/gleb/Downloads/example.html");
        if(file.delete()) {
            System.out.println("Deleted");
        }


        String response = "";

        if (page != null) {
            String headerType = page.header;
            String footerType = page.footer;

            String filePath = "/home/gleb/Downloads/example.html";

            String siteHeader = "<!DOCTYPE html>\n" +
                    "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx\" crossorigin=\"anonymous\">\n" +
                    "    <title>Site Generator</title>\n" +
                    "</head>\n" +
                    "<body>";

            try(FileOutputStream fos = new FileOutputStream(filePath)) {
                byte[] buffer = siteHeader.getBytes();
                fos.write(buffer, 0, buffer.length);
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }


            if(headerType.equals("1")) {
                String element = "<header class=\"p-3 bg-dark text-white\">\n" +
                        "                <div class=\"container\">\n" +
                        "                <div class=\"d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start\">\n" +
                        "                <a href=\"/\" class=\"d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none\">\n" +
                        "                <svg class=\"bi me-2\" width=\"40\" height=\"32\" role=\"img\" aria-label=\"Bootstrap\"><use xlink:href=\"#bootstrap\"></use></svg>\n" +
                        "                </a>\n" +
                        "\n" +
                        "                <ul class=\"nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0\">\n" +
                        "                <li>Пример текста</li>\n" +
                        "                </ul>\n" +
                        "                </div>\n" +
                        "                </div>\n" +
                        "                </header>";

                if(!FileWriter.writeFile(element, filePath)) {
                    response = "Could not create file";
                }
            } else if (headerType.equals("2")) {
                String element = "<header class=\"d-flex justify-content-center py-3\">\n" +
                        "      <ul class=\"nav nav-pills\">\n" +
                        "        <li class=\"nav-item\"><a class=\"nav-link\" aria-current=\"page\">Кнопка</a></li>\n" +
                        "        <li class=\"nav-item\"><a class=\"nav-link\">Кнопка))0)</a></li>\n" +
                        "        <li class=\"nav-item\"><a class=\"nav-link\">Кнопка еще</a></li>\n" +
                        "        <li class=\"nav-item\"><a class=\"nav-link\">Кнопка оаоаоаоа</a></li>\n" +
                        "        <li class=\"nav-item\"><a class=\"nav-link\">Knopka</a></li>\n" +
                        "        </ul>\n" +
                        "        </header>";

                if(!FileWriter.writeFile(element, filePath)) {
                    response = "Could not create file";
                }
            }



            if(footerType.equals("1")) {
                String element = "<footer class=\"d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top\">\n" +
                        "        <p class=\"col-md-4 mb-0 text-muted\">© 2022 Company, Inc</p>\n" +
                        "        </footer>";

                if(!FileWriter.writeFile(element, filePath)) {
                    response = "Could not create file";
                }
            } else if (footerType.equals("2")) {
                String element = "<div class=\"container\">\n" +
                        "        <footer class=\"py-3 my-4\">\n" +
                        "        <p class=\"text-center text-muted\">© 2022 Company, Inc</p>\n" +
                        "        </footer>\n" +
                        "        </div>";

                if(!FileWriter.writeFile(element, filePath)) {
                    response = "Could not create file";
                }
            }

            String pageEnd = "</body></html>";
            FileWriter.writeFile(pageEnd, filePath);
        }

        if(!response.equals("")) {
            model.addAttribute("error-message", response);
            return "error";
        }

        return "redirect:/index";
    }
}
