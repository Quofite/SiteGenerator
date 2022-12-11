package org.barbaris.generator.models;

public class HtmlTemplates {


    /*
        public static String header2(String siteName) {
            return "";
        }
     */



    // ОСНОВНЫЕ ВЕЩИ -----------------------------------

    public static String siteHeader(String siteName) {

        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx\" crossorigin=\"anonymous\">\n" +
                "    <title>" + siteName + "</title>\n" +
                "</head>\n" +
                "<body>";
    }

    public static String contentBlock(String rightContent, String leftContent) {

        return "<div class=\"row\">" +
                    "<div class=\"col-7\">" + leftContent + "</div>" +
                    "<div class=\"col-2\">" + rightContent + "</div>" +
                "</div>";
    }

    public static String pageEnd() {
        return "</body></html>";
    }



    // ЗАГОЛОВКИ ---------------------------------------


    public static String header1(String siteName) {

        return "<header class=\"p-3 bg-dark text-white\">" +
                "                <div class=\"container\">" +
                "                <div class=\"d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start\">" +
                "                <a href=\"/\" class=\"d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none\">" +
                "                <svg class=\"bi me-2\" width=\"40\" height=\"32\" role=\"img\" aria-label=\"Bootstrap\"><use xlink:href=\"#bootstrap\"></use></svg>" +
                "                </a>" +
                "                <ul class=\"nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0\">" +
                "                <li>" + siteName + "</li>" +
                "                </ul>" +
                "                </div>" +
                "                </div>" +
                "                </header>";
    }

    public static String header2() {

        return "<header class=\"d-flex justify-content-center py-3\">\n" +
                "      <ul class=\"nav nav-pills\">\n" +
                "        <li class=\"nav-item\"><a class=\"nav-link\" aria-current=\"page\">Кнопка</a></li>\n" +
                "        <li class=\"nav-item\"><a class=\"nav-link\">Кнопка))0)</a></li>\n" +
                "        <li class=\"nav-item\"><a class=\"nav-link\">Кнопка еще</a></li>\n" +
                "        <li class=\"nav-item\"><a class=\"nav-link\">Кнопка оаоаоаоа</a></li>\n" +
                "        <li class=\"nav-item\"><a class=\"nav-link\">Knopka</a></li>\n" +
                "        </ul>\n" +
                "        </header>";
    }


    // ФУТЕРЫ --------------------------------------------------------------

    public static String footer1(String siteName) {
        return "<footer class=\"d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top\">\n" +
                "        <p class=\"col-md-4 mb-0 text-muted\">© 2022 " + siteName + "</p>\n" +
                "        </footer>";
    }

    public static String footer2(String siteName) {
        return "<div class=\"container\">\n" +
                "        <footer class=\"py-3 my-4\">\n" +
                "        <p class=\"text-center text-muted\">© 2022 " + siteName + "</p>\n" +
                "        </footer>\n" +
                "        </div>";
    }

}
