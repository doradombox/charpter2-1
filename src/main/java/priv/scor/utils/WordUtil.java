package priv.scor.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordUtil {

    private Configuration configuration = null;
    Logger logger = org.slf4j.LoggerFactory.getLogger(WordUtil.class);

    public WordUtil() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    private Template getTemplate(String templatePath, String templateName) throws IOException {
        // configuration.setClassForTemplateLoading(this.getClass(), templatePath);
        // String path =
        // "E:\\Primeton\\Platform\\ide\\eclipse\\workspace\\PIMS\\com.rongwei.pims.pub.excel\\src\\com\\rongwei\\pims\\pub\\word\\service";
        // System.out.println(ApplicationContext.getInstance().getWarRealPath());

        String path1 = (this.getClass().getResource("").getPath() + "ftl").replaceFirst("/", "");
        String path = path1.substring(0, path1.lastIndexOf("WEB-INF"));
        path = path + "common/publish/template/word";
        System.out.println(path);
        configuration.setDirectoryForTemplateLoading(new File(path));
        System.out.println(path1);
        Template t = null;
        t = configuration.getTemplate(templateName);
        t.setEncoding("UTF-8");
        return t;
    }

    public void write(String templatePath, String templateName, Map dataMap, Writer out) {
        try {
            Template t = getTemplate(templatePath, templateName);
            t.process(dataMap, out);
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (TemplateException e) {
            logger.error(e.toString());
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
    }

    /**
    * 实体对象转换map
    */
    public static Map<String, Object> transBeanToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            System.out.println(propertyDescriptors.length);
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                try {
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        if (getter != null) {
                            Object value = getter.invoke(obj);
                            // 注意：此处是为word xml格式生成word文件换行问题做的特殊处理
                            if (value != null) {
                                if (value instanceof String) {
                                    value = value.toString().replaceAll("\r\n", "<w:br/>");
                                }
                                value = bigdecimalUtil(value);
                            }
                            map.put(key, value);
                            if (value == null) {
                                map.put(key, "");
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("transBean2Map Error " + e);
                    continue;
                }
            }
        } catch (IntrospectionException e1) {
            e1.printStackTrace();
        }
        return map;

    }

    /**
     * 浮点数ftl输出统一处理成字符串输出，截掉后面的0
     * @param value
     * @return
     */
    public static Object bigdecimalUtil(Object value) {
        Object strvalue = value;
        if (value instanceof BigDecimal) {
            BigDecimal bigvalue = (BigDecimal) value;
            strvalue = bigvalue.stripTrailingZeros().toPlainString();
        }
        return strvalue;
    }
    
    

    /**
     * 获得质量表单Word导出路径
     */
    public String getWordPath() {
        // BeanFactory factory = BeanFactory.newInstance();
        // MmFilefolderdefineService mmfilefolderdefineservice =
        // factory.getBean("MmFilefolderdefineBean");
        // MmFilefolderdefine resultmmfile =
        // mmfilefolderdefineservice.queryMmFilefolderdefinesByFileType(filetype);
        //String warRealPath = ApplicationContext.getInstance().getWarRealPath();
        //String filePath = warRealPath + "upload";
        logger.debug("文件路径为:{}",ApplicationContext.TEMPDIR);
        return "";
    }
    

    /**
        * 压缩。
        * 
        * @param src
        *            源文件或者目录
        * @param dest
        *            压缩文件路径
        * @throws IOException
        */
    public static void zip(String src, String dest) throws IOException {
        ZipOutputStream out = null;
        try {
            File outFile = new File(dest);
            out = new ZipOutputStream(new FileOutputStream(outFile));
            File fileOrDirectory = new File(src);

            if (fileOrDirectory.isFile()) {
                zipFileOrDirectory(out, fileOrDirectory, "");
            } else {
                File[] entries = fileOrDirectory.listFiles();
                for (int i = 0; i < entries.length; i++) {
                    // 递归压缩，更新curPaths
                    zipFileOrDirectory(out, entries[i], "");
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 递归压缩文件或目录
     * 
     * @param out
     *            压缩输出流对象
     * @param fileOrDirectory
     *            要压缩的文件或目录对象
     * @param curPath
     *            当前压缩条目的路径，用于指定条目名称的前缀
     * @throws IOException
     */
    private static void zipFileOrDirectory(ZipOutputStream out, File fileOrDirectory, String curPath)
            throws IOException {
        FileInputStream in = null;
        try {
            if (!fileOrDirectory.isDirectory()) {
                // 压缩文件
                byte[] buffer = new byte[4096];
                int bytes_read;
                in = new FileInputStream(fileOrDirectory);

                ZipEntry entry = new ZipEntry(curPath + fileOrDirectory.getName());
                out.putNextEntry(entry);

                while ((bytes_read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytes_read);
                }
                out.closeEntry();
            } else {
                // 压缩目录
                File[] entries = fileOrDirectory.listFiles();
                for (int i = 0; i < entries.length; i++) {
                    // 递归压缩，更新curPaths
                    zipFileOrDirectory(out, entries[i], curPath + fileOrDirectory.getName() + "/");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * 解压缩。
     * 
     * @param zipFileName
     *            源文件
     * @param outputDirectory
     *            解压缩后文件存放的目录
     * @throws IOException
     */
    public static void unzip(String zipFileName, String outputDirectory) throws IOException {

        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile(zipFileName);
            Enumeration<? extends ZipEntry> e = zipFile.entries();

            ZipEntry zipEntry = null;

            File dest = new File(outputDirectory);
            dest.mkdirs();

            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();

                String entryName = zipEntry.getName();

                InputStream in = null;
                FileOutputStream out = null;

                try {
                    if (zipEntry.isDirectory()) {
                        String name = zipEntry.getName();
                        name = name.substring(0, name.length() - 1);

                        File f = new File(outputDirectory + File.separator + name);
                        f.mkdirs();
                    } else {
                        int index = entryName.lastIndexOf("\\");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        index = entryName.lastIndexOf("/");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator + entryName.substring(0, index));
                            df.mkdirs();
                        }

                        File f = new File(outputDirectory + File.separator + zipEntry.getName());
                        // f.createNewFile();
                        in = zipFile.getInputStream(zipEntry);
                        out = new FileOutputStream(f);

                        int c;
                        byte[] by = new byte[1024];

                        while ((c = in.read(by)) != -1) {
                            out.write(by, 0, c);
                        }
                        out.flush();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new IOException("解压失败：" + ex.toString());
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ex) {
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("解压失败：" + ex.toString());
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // zip("D:\\ziptest","D:\\ziptest.zip");
            // ZipUtil.unzip("D:\\a\\010108012008-8-25.zip", "d:\\a\\tmp");
            // File filesrc11 = new
            // File("E:\\Primeton\\Platform\\apache-tomcat-5.5.20\\webapps\\default\\upload\\PM\\IndirectCatalog\\E-SY-SYGH-2014001-YSAA001(规划方案)\\项目策划\\策划表单");
            // Move(filesrc,"D:\\testmove");
            // filerename("D:\\hahah.docx","D:\\testabc.docx");
            // filesrc11.mkdirs();
            // File file1 = new
            // File("E:\\Primeton\\Platform\\apache-tomcat-5.5.20\\webapps\\default\\upload\\PM\\{pmProjectId}\\ProjectOutline\\20140821111812.doc");
            // File file2 = new File("D:\\testmove\\aaaa.doc");
            // copyFile(file1,file2);
            // String str1 = "600042";
            // Pattern p = Pattern.compile(".+"+str1+".+");
            // String str =
            // "E:\\Primeton\\Platform\\apache-tomcat-5.5.20\\webapps\\default\\upload\\PM\\600041\\ProjectOutline\\20140821111812.doc";
            // Matcher m = p.matcher(str);
            // System.out.println(m.matches());
            // File file = new File("D:\\abc\\def\\\\aa");
            // file.mkdirs();
            String teststr = "?asdsa*";
            Pattern p = Pattern.compile("[/*,/?,:,<,>,|,/,\\,]");
            Matcher m = p.matcher(teststr);
            System.out.println(m.replaceAll("-"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 移动文件
     * @param srcFile   源文件路径
     * @param destPath  目标文件路径
     * @return
     */
    public static boolean Move(File srcFile, String destPath) {
        // Destination directory
        File dir = new File(destPath);
        // Move file to new directory
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
        return success;
    }

    /**
     * 文件重命名
     */
    public static boolean filerename(String srcPath, String destPath) {
        boolean flag = false;
        File file = new File(srcPath);
        if (file.exists() && file.isFile()) {
            File destfile = new File(destPath);
            flag = file.renameTo(destfile);
        }
        return flag;
    }

    /**
     * 判断要移动的文件是否存在、如存在则移动至目标文件夹、并且重命名
     */
    public void movefile(String srcfile, String dirfile, String oldname, String newname) {
        File file = new File(srcfile);
        if (file.exists() && file.isFile()) {
            // 移动文件
            Move(file, dirfile);
            // 重命名文件
            if (newname != null && !"".equals(newname)) {
                filerename(dirfile + "\\" + oldname, dirfile + "\\" + newname);
            }
        }
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
