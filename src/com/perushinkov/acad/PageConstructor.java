package com.perushinkov.acad;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageConstructor {
	public static void login(HttpServletResponse response, String errorMessage) throws IOException {
		PrintWriter writer = response.getWriter();
		
		writer.append(
		"<!DOCTYPE html>\n"+
        "<html>\n"+
        "<head>\n"+
        "<meta charset=\"UTF-8\">\n"+
        "<title>Login Screen</title>\n"+
        "</head>\n"+
        "<body>\n"+
        "<p style=\"color:red;\">"+errorMessage+"</p>"+
        "<form method=\"post\" action=\"index.html\">\n"+
        "        <p><input type=\"text\" name=\"login\" value=\"\"></p>\n"+
        "        <p><input type=\"password\" name=\"password\" value=\"\"></p>\n"+
        "        <p><input type=\"hidden\" name=\"action\" value=\"login\"></p>"+
        "        <p class=\"submit\"><input type=\"submit\" name=\"commit\" value=\"Login\"></p>\n"+
        "      </form>\n"+
        "</body>\n"+
        "</html>");
	}
	
	public static void student_homepage(HttpServletRequest request,HttpServletResponse response) {
		
	}
	public static void teacher_homepage(HttpServletRequest request,HttpServletResponse response) {
		
	}
}
