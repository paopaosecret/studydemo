package com.example.javatest.stringutil;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by bing.zhao on 2017/4/27.
 */

public class XmlToExcel {

    public static void main(String[] args){
        try {
            Map<String, Integer> keyMap = new HashMap<>();

            // 设置字体
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 12);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.BOLD);

            // 标题居中
            WritableCellFormat titleFormat = new WritableCellFormat(BoldFont);
            titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            titleFormat.setAlignment(Alignment.CENTRE); // 文字水平对齐
            titleFormat.setWrap(false); // 文字是否换行

            // 正文右对齐
            WritableCellFormat contentRightFormat = new WritableCellFormat(NormalFont);
            contentRightFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            contentRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            contentRightFormat.setAlignment(Alignment.LEFT);
            contentRightFormat.setWrap(true);

            WorkbookSettings workbookSettings = new WorkbookSettings();
            workbookSettings.setEncoding("ISO-8859-1");

            WritableWorkbook book = Workbook.createWorkbook(new File("F:/test.xls"),workbookSettings);
            WritableSheet sheet = book.createSheet("frist", 0);
            sheet.setColumnView(0,50);   //设置第0列宽度为50
            SheetSettings ss = sheet.getSettings();
            ss.setVerticalFreeze(1);// 冻结表头



            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            List<String> resFile = TranslateUtil.getStringFileList("F:\\excel" + File.separator + "res");
            int cloumnIndex = 0;
            for(String fileName : resFile) {
                Document document = db.parse(fileName);
                NodeList nodeList = document.getChildNodes();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node item = nodeList.item(i);
                    NodeList strList = item.getChildNodes();
                    System.out.println("Length = " + strList.getLength() + ",il =" + nodeList.getLength());
//                System.out.println("key = " + item.getAttributes().getNamedItem("name") + ",value" + item.getTextContent());
                    Label valueLabel = null;
                    Label keyLabel = null;
                    int index = 0;
                    for (int j = 0; j < strList.getLength(); j++) {
                        Node str = strList.item(j);

                        if (str.hasAttributes()) {
                            String key = str.getAttributes().getNamedItem("name").getNodeValue();
                            if (cloumnIndex == 0) {
                                keyLabel = new Label(0, index, str.getAttributes().getNamedItem("name").getNodeValue(), contentRightFormat);
                                sheet.addCell(keyLabel);
                                keyMap.put(key,index);
                            }

                            if(keyMap != null && key != null){
                                Integer row = keyMap.get(key);
                                if(row != null){
                                    valueLabel = new Label(cloumnIndex + 1, row, str.getTextContent(), contentRightFormat);
                                    sheet.addCell(valueLabel);
                                    index++;
                                    System.out.println("row = " + row + ",key = " + key + ",value=" + str.getTextContent());
                                }
                            }
                        }
                    }

                }
                cloumnIndex++ ;
            }

            // 写入Exel工作表
            book.write();
            // 关闭Excel工作薄对象
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
