package com.tuxt.mytest;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

public class WordTest {

    @Test
    public void test1() throws IOException {
        FileInputStream inputStream=new FileInputStream(new File("D:\\temp","输送临时工协议.docx"));
        XWPFDocument xwpfDocument=new XWPFDocument(inputStream);
        List<XWPFParagraph> paragraphList = xwpfDocument.getParagraphs();
        for (XWPFParagraph paragraph : paragraphList) {
            System.out.println(paragraph.getParagraphText());
        }

    }

    private String parseAllTextFromWordFile(File file){
        //获取word文档中的全部文字数据
        String wordText = "";
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));//文件后缀（格式）
        try {//.doc和.docx的word获取方式不一样
            InputStream is = new FileInputStream(file);
            switch (suffix) {
                case ".doc":
                    WordExtractor wordExtractor = new WordExtractor(is);
                    wordText = wordExtractor.getText();
                    is.close();
                    break;
                case ".docx":
                    OPCPackage opcPackage = POIXMLDocument.openPackage(file.getAbsolutePath());
                    POIXMLTextExtractor poixmlTextExtractor = new XWPFWordExtractor(opcPackage);
                    wordText = poixmlTextExtractor.getText();
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordText;
    }

    @Test
    public void test2(){
        File file = new File("D:\\temp", "输送临时工协议.doc");
        String s = parseAllTextFromWordFile(file);
        System.out.println(s);
    }

}
