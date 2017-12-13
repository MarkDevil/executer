package com.mark.test.framework.util;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by MingfengMa .
 * Data   : 2017/5/5
 * Author : mark
 * Desc   :
 */

public class ExcelUtils {
    private HSSFWorkbook workbook = null;
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);


    /**
     * 创建excel文件
     * @param fileDir
     * @param filename
     * @param sheetName
     * @param titleRow
     * @return
     */
    public String createExcel(String fileDir,String filename,String sheetName,String[] titleRow){
        if (Objects.equals(fileDir, "") || sheetName.equals("") || titleRow.length<=0){
            throw new RuntimeException("Input parameter is incorrect");
        }
        String filepath = fileDir.concat(filename).concat(".xls");
        workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        FileOutputStream outputStream = null;
        try {
            Row row = workbook.getSheet(sheetName).createRow(0);
            for (int i = 0 ; i<titleRow.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            outputStream = new FileOutputStream(filepath);
            workbook.write(outputStream);
            logger.debug("file path {}",filepath);
            File file = new File(filepath);
            if (file.exists()){
                logger.info("file is created at {}",filepath);
                return filepath;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream!=null){
                    outputStream.close();
                }
                if (workbook!= null){
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据文件目录和表读取数据
     * @param fileDir
     * @param sheet
     * @return
     */
    public static String readExcel(String fileDir,String sheet) {
        FileInputStream inputStream;
        XSSFWorkbook xssfWorkbook;
        List<String> titleList = Lists.newLinkedList();
        try {
            inputStream = new FileInputStream(new File(fileDir));
            xssfWorkbook = new XSSFWorkbook(inputStream);
            int sheets = xssfWorkbook.getNumberOfSheets();
            logger.info("sheet number : {} " , sheets);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheet);

            Iterator<Row> it = xssfSheet.rowIterator();
            while (it.hasNext()){
                Row row = it.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    if(!cell.getStringCellValue().isEmpty()){
                        logger.info("cell的值是 :{},cell的位置是 :{}",cell.getStringCellValue(),cell.getAddress());
                        logger.info(String.valueOf(row.getRowNum()+1) + "," + cell.getAddress());
                        titleList.add(cell.getColumnIndex(),cell.getStringCellValue());
                    }else {
                        logger.info("test");
                    }
                }

            }

            logger.info("Title name list are : {}",titleList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    public static void main(String[] args) {
        readExcel("/Users/mark/Desktop/随手记贷后需求/随手记测算表-20171212终稿.xlsx","test");
    }


}
