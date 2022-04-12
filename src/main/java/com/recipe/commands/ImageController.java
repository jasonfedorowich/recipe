package com.recipe.commands;

import com.recipe.services.ImageService;
import com.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Controller
@Slf4j
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @RequestMapping("/recipes/{rid}/image")
    public String getImage(@PathVariable String rid, Model model){

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(rid)));
        return "recipe/image/form";
    }
    @PostMapping("/recipes/{rid}/image")
    public String uploadImage(@PathVariable String rid, @RequestParam("imageFile")MultipartFile file){
        try {
            imageService.saveImageFile(Long.valueOf(rid), file);
        } catch (IOException e) {
            log.error("Cannot get image bytes");
        }
        return "redirect:/recipe/" + rid+  "/show";
    }

    @GetMapping("/recupes/{rid}/image")
    public void renderImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
        byte[] bytes = new byte[command.getImage().length];
        int i = 0;
       for(Byte _byte: command.getImage()){
           bytes[i++] = _byte;
       }
       response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(bytes);
        IOUtils.copy(is, response.getOutputStream());


    }

}
