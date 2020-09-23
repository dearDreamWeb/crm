package com.example.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author: pengjia
 * @date: 2020/9/23
 * @description:文件工具类
 */
public class MyFileUtils {
    public static boolean isExcel(String fileName) {
        String sufName = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(sufName) || ".xlsx".equals(sufName)) {
            return true;
        }
        return false;
    }

    public static boolean isImage(File file) {
        if (!file.exists()) {
            return false;
        }
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
            if (image == null || image.getWidth() <= 0 || image.getHeight() <= 0) {
                return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void downLoad(HttpServletResponse response,String excelPath) {
        File file = new File(excelPath);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition",
                    String.format("attachment;filename=\"%s\"",file.getName()));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (1 != -1){
                    os.write(buffer,0,i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
