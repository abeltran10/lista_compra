package com.abeltran10.lista_compra.utils;

import com.abeltran10.lista_compra.model.Producto;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class GenerarPDF {

    private TemplateEngine templateEngine;
    private Context context;

    public GenerarPDF() {
        templateEngine = new TemplateEngine();
        context = new Context();

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("/com/abeltran10/lista_compra/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setTemplateResolver(resolver);
    }

    public String setTemplateVariables(List<Producto> lista) {
        // 1. Obtener la fecha de hoy
        LocalDate hoy = LocalDate.now();

        // 2. Definir el formato deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3. Formatear la fecha
        String fechaFormateada = hoy.format(formato);

        context.setVariable("listaProductos", lista);
        context.setVariable("fecha", fechaFormateada);

        return templateEngine.process("lista_compra", context);

    }

    public void generarPDF(String htmlProcesado) throws IOException {
        String userHome = System.getProperty("user.home");
        File dir = new File(userHome, ".lista_compra");
        String archivoSalida = dir.getAbsolutePath() + "/lista_compra.pdf";
        ITextRenderer renderer = new ITextRenderer();

        OutputStream os = new FileOutputStream(archivoSalida);

        renderer.setDocumentFromString(htmlProcesado);
        renderer.layout();
        renderer.createPDF(os);

        os.close();
    }
}
