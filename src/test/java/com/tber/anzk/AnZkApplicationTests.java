package com.tber.anzk;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnZkApplicationTests {

    @Test
    public void contextLoads()  {

    }

    void execute()throws IOException{

        FileInputStream fis = new FileInputStream(new File("C:/Users/zlmqi/Desktop/GPS高风险省份.xlsx"));
        XSSFWorkbook xssfSheets = new XSSFWorkbook(fis);
        XSSFSheet sheet = xssfSheets.getSheet("sheet1");
        int lastRowNum = sheet.getLastRowNum();
        int beginRowNum = sheet.getFirstRowNum();
        System.out.println("共有"+(lastRowNum-1));
        File file = new File("C:/Users/zlmqi/Desktop/xinjiang.sql");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
        for(int i=beginRowNum+1;i<=lastRowNum;i++){
            Row row = sheet.getRow(i);
            if(row==null){
                continue;
            }
            Cell cell = row.getCell(1);
            String value = cell.getStringCellValue();
            System.out.println(valueAdd(value));
            bufferedWriter.write(valueAdd(value));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
//        bufferedWriter

    }

    private String valueAdd(String value){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ermas_id_number(id_no) values('");
        sb.append(value);
        sb.append("');");
        return sb.toString();
    }

}

