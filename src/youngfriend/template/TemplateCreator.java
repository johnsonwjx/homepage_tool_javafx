package youngfriend.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 14/12/30.
 */
public class TemplateCreator {
    private static TemplateEngine templateEngine;

    static {
        initializeTemplateEngine();
    }

    private static void initializeTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        // XHTML is the default mode, but we set it anyway for better understanding of code
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("templates/");
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setSuffix(".tpl");
        // Template cache TTL=1h. If not set, entries would be cached until expelled by LRU
        templateResolver.setCacheable(false);
        templateEngine = new TemplateEngine();
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);
    }

    public static void export(String filepath, String tempName, String name, Object obj) throws Exception {
        Context cxt = new Context();
        cxt.setVariable(name, obj);
        List<String> heads = new ArrayList<String>();
        heads.add("<%@ page language=\"java\"  pageEncoding=\"UTF-8\" isELIgnored=\"false\"%>");
        export(filepath, tempName, cxt, heads);
    }

    public static void export(String filepath, String tempName, Context cxt, List<String> headers) throws Exception {
        OutputStreamWriter out = null;
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            if (headers != null && !headers.isEmpty()) {
                for (String header : headers) {
                    out.write(header + "\r\n");
                }
            }
            templateEngine.process(tempName, cxt, out);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

//    public static void export_head(Header header) throws Exception {
//        String filepath = Global.getRootDir() + File.separator + Global.FILENAME_HEAD;
//        export(filepath, "head", "header", header);
//    }
//
//    public static void export_flash(Flash flash) throws Exception {
//        String filepath = Global.getRootDir() + File.separator + Global.FILENAME_ADV;
//        export(filepath, "adv", "flash", flash);
//    }
//
//    public static void export_foot(Foot foot) throws Exception {
//        String filepath = Global.getRootDir() + File.separator + Global.FILENAME_FOOT;
//        export(filepath, "foot", "foot", foot);
//    }
//
//    public static void export_index(Main main, List<Btn> btns) throws Exception {
//        String filepath = Global.getRootDir() + File.separator + Global.FILENAME_INDEX;
//        export(filepath, "index", "main", main);
//        File classDir = new File(Global.getRootDir() + File.separator + ".." + File.separator + "WEB-INF" + File.separator + "classes");
//        filepath = classDir.getAbsolutePath() + File.separator + Global.FILENAME_BTN;
//        if (!classDir.exists()) {
//            CommUtils.showMsg(null, "web目录错误，生成" + Global.FILENAME_BTN + "失败");
//        } else {
//            Context cxt = new Context();
//            cxt.setVariable("btns", btns);
//            export(filepath, "btns", cxt, null);
//        }
//
//    }
//
//
//    public static String export_demo(Header header, Flash flash, Foot foot, Main main) throws Exception {
//        Context cxt = new Context();
//        cxt.setVariable("header", header);
//        cxt.setVariable("flash", flash);
//        cxt.setVariable("foot", foot);
//        cxt.setVariable("index", main);
//        StringWriter out =null;
//        try {
//            out= new StringWriter();
//            templateEngine.process("demo", cxt, out);
//            return out.toString() ;
//        } finally {
//            if (out != null) {
//                out.flush();
//                out.close();
//            }
//        }
//    }
}
