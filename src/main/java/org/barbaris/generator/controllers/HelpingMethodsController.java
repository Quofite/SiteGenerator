package com.barbaris.site.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpingMethodsController {

    public static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern HTML_REGEX = Pattern.compile("\"/\\/[a-z]*>/i\"", Pattern.CASE_INSENSITIVE);

    public static String[] forbidden = new String[] {"login_error", "pass_error", "admin", "account", "login", "pass", "password",
            "error_details", "error", "No ID", "email", "role", "id", "asker", "question", "is_email", "ask_id",
            "answer", "request", "response", "login_value", "model", "asking_type", "type", "helping_text",
            "id_placeholder", "is_readonly", "is_hidden"};

    public static String getApiData(int day, int month) throws Exception {
        URL url = new URL("http://numbersapi.com/" + month + "/" + day + "/date");
        URLConnection connection = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine  = in.readLine();
        in.close();
        return inputLine;
    }

    public static Boolean isEmail(String str) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isValidLogin(String login) {
        for(String word : forbidden) {
            if (login.equals(word)) {
                return false;
            }
        }

        return true;
    }
}
