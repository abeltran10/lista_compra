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

    private static TemplateEngine templateEngine = new TemplateEngine();

    private static void inicializa() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("/com/abeltran10/lista_compra/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setTemplateResolver(resolver);
    }

    public static void generarPDF(List<Producto> lista) throws IOException {
        Context context = new Context();
        String userHome = System.getProperty("user.home");
        File dir = new File(userHome, ".lista_compra");
        String archivoSalida = dir.getAbsolutePath() + "/lista_compra.pdf";
        ITextRenderer renderer = new ITextRenderer();

        if (!templateEngine.isInitialized()) {
            inicializa();
        }

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = hoy.format(formato);

        context.setVariable("listaProductos", lista);
        context.setVariable("fecha", fechaFormateada);

        String htmlProcesado = templateEngine.process("lista_compra", context);

        OutputStream os = new FileOutputStream(archivoSalida);

        renderer.setDocumentFromString(htmlProcesado);
        renderer.layout();
        renderer.createPDF(os);

        os.close();




    }
}
