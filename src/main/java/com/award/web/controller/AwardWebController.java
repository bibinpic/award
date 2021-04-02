package com.award.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class AwardWebController {
    @GetMapping("/award")
    public String index() {
        return "uploadfile";
    }
}
