package Aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * Creado por Mauricio el 28/10/2017.
 **/

@SpringBootApplication
public class Main {

    public static String path = "D:\\Escritorio\\Video_Test";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}