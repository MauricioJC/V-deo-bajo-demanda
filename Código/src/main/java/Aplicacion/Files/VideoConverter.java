package Aplicacion.Files;

import Aplicacion.Main;
import groovy.lang.Singleton;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
/**
 * Creado por Mauricio el 28/10/2017.
 **/
@Singleton
public class VideoConverter {

    public String guardarVideo(MultipartFile originalFile){
        String nombreVideo = originalFile.getOriginalFilename().substring(0,
                originalFile.getOriginalFilename().indexOf("."));
        File temp = new File(Main.path +"\\"+ nombreVideo);
        temp.mkdirs();
        String rutaCarpeta = temp.getAbsolutePath();

        File video = new File(rutaCarpeta+"\\"+originalFile.getOriginalFilename());
        try {
            originalFile.transferTo(video);
        } catch (IOException e) {
            return null;
        }
        return rutaCarpeta;
    }

    public boolean transformarVideo(String ruta, String nombre, String extension){
        try{
            String command = "ffmpeg -i";
            command += " \""+ ruta +"\\"+ nombre + extension;
            command += "\" \""+ ruta +"\\"+ nombre + ".avi";
            command += "\" \""+ ruta +"\\"+ nombre + ".ogv";
            command += "\" \""+ ruta +"\\"+ nombre + ".mov\"";
            Process tarea = Runtime.getRuntime().exec("cmd /c start /wait "+command);
            tarea.waitFor();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public  String[] obtenerListaDeVideos(String ruta){
        return new File(ruta).list();
    }

    public File obtenerVideo(String ruta){
        return new File(ruta);
    }
}
