package Aplicacion.Controllers;

import Aplicacion.Files.VideoConverter;
import Aplicacion.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/**
 * Creado por Mauricio el 28/10/2017.
 **/
@Controller
public class FileController {
    @GetMapping("/")
    public String getInicio(){
        return "Inicio";
    }

    @GetMapping("/video")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(String name) {
        String carpeta = name.substring(0, name.indexOf("."));
        String path = Main.path+"\\"+carpeta+"\\"+name;
        Resource file = new ClassPathXmlApplicationContext().getResource("file:"+path);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String subirVideo(@RequestParam("file") MultipartFile file, Model model) {
        VideoConverter video = new VideoConverter();
        String path = video.guardarVideo(file);
        String fileName = file.getOriginalFilename();
        String nombre = fileName.substring(0, fileName.indexOf("."));
        String extension = fileName.substring(
                fileName.indexOf("."),
                fileName.length()
        );
        boolean exito = video.transformarVideo(path,nombre,extension);
        if(exito){
            model.addAttribute("message","Exito al subir el video: " + fileName);
            String[] lista = video.obtenerListaDeVideos(path);
            model.addAttribute("files",lista);
        }else{
            model.addAttribute("message","Error al subir el video: " + fileName);
        }
        return "Inicio";
    }
}