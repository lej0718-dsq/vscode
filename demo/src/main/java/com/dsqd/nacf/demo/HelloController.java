package com.dsqd.nacf.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@RestController
public class HelloController {

    @GetMapping("/")
    public RedirectView index() {
        // 정적 파일 hello.html로 리다이렉트
        return new RedirectView("/hello.html");
    }

    // POST로 전송된 name을 메서드 내부에서 직접 읽어 처리 (Spring WebRequest 사용)
    @PostMapping("/")
    public String greet(WebRequest request) {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            name = "world";
        }
        return "Hello " + name + ".";
    }

    // 변경된 엔드포인트: /user
    // - GET: 쿼리스트링에서 읽음 -> 출처: GET parameter
    // - POST: Content-Type을 보고 폼 제출인지 판별 -> 출처: Form submission 또는 Body
    @GetMapping("/user")
    public String user(WebRequest request) {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            name = "world";
        }
        return "Hello~ " + name + ".\nSource: GET parameter\nI'm vscode. Nice meet you.";
    }

    @PostMapping("/user")
    public String userPost(WebRequest request, HttpServletRequest servletRequest) {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            name = "world";
        }
        String contentType = servletRequest.getContentType();
        String source;
        if (contentType != null && (contentType.startsWith("application/x-www-form-urlencoded")
                || contentType.startsWith("multipart/form-data"))) {
            source = "Form submission";
        } else {
            source = "Request body";
        }
        return "Hello~ " + name + ".\nSource: " + source + "\nI'm vscode. Nice meet you.";
    }

    // 추가된 엔드포인트: /test?value=숫자
    @GetMapping("/test")
    public String test(WebRequest request) {
        String value = request.getParameter("value");
        if (value == null || value.trim().isEmpty()) {
            return "값을 value 쿼리로 전달하세요. 예: /test?value=1000";
        }
        try {
            BigDecimal num = new BigDecimal(value.trim());
            BigDecimal result = num.multiply(BigDecimal.valueOf(123));
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            DecimalFormat df = new DecimalFormat("#,##0.00;(#,##0.00)", symbols);
            return df.format(result);
        } catch (NumberFormatException ex) {
            return "입력값이 숫자가 아닙니다.";
        }
    }
}