package com.example.javatest.stringutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * Created by bing.zhao on 2017/4/27.
 */

public class ExcelToXml {

    public static String xmlFilePath = "F:\\excel/string.xls";
    public static void main(String[] args){
        File file = new File(xmlFilePath);
        writeToXml(file);
    }

    public static void writeToXml(File file){
        FileWriter fw = null;
        PrintWriter out = null;
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            WorkbookSettings workbookSettings = new WorkbookSettings();
            workbookSettings.setEncoding("ISO-8859-1");
            Workbook wb = Workbook.getWorkbook(is,workbookSettings);
            // Excel的页签数量
            int sheetSize = wb.getNumberOfSheets();
            System.out.println("sheelSize:" + sheetSize);
            Sheet sheet = wb.getSheet(0);
            // sheet.getRows()返回该页的总行数
            for (int i = 1; i < sheet.getColumns(); i++) {
                // sheet.getColumns()返回该页的总列数
                for (int j = 0; j < sheet.getRows(); j++) {

                    String startRow = sheet.getCell(i, 0).getContents().trim();
                    String key = sheet.getCell(0, j).getContents();
                    String value = sheet.getCell(i, j).getContents();
                    if(!TranslateUtil.isExistInXMl(startRow)){
                        System.out.println("values-" + startRow + "文件夹不存在");
                        break;
                    }else{
                        if(j == 0){
                            System.out.println("values-" + startRow + "文件文件写入开始");
                            String folderName = TranslateUtil.getFolderNameByAb(startRow);
                            File   objFile = TranslateUtil.getStringFileInFolder(folderName);
                            fw  = new FileWriter(objFile);
                            out = new PrintWriter(fw);
                            TranslateUtil.writeXMLHead(out);
                        }else if(j == sheet.getRows() -1){
                            TranslateUtil.writeXMLEnd(out);
                            if(out != null){
                                out.flush();
                                out.close();
                            }
                            if(fw != null){
                                fw.close();
                            }
                            System.out.println("values-" + startRow + "文件写入完成，共"+ j + "行" );
                        }else{
                            if(TranslateUtil.isStringArrayByValue(value)){
                                TranslateUtil.writeItemArrayToXML(out,key,value);
                            }else{
                                TranslateUtil.writeItemToXML(out,key,value);
                            }
                        }
                    }

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
                if(fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
