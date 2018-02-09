package com.mark.test.framework.util.loaddata;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.*;
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
    public static String readExcel(String fileDir,String sheet,int columnIndex) {
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
                String columnvalue = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                logger.info("Column : {}",columnvalue);
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    try {
                        if(!cell.getStringCellValue().isEmpty()){
//                            logger.info("cell的值是 :{},cell的位置是 :{}",cell.getStringCellValue(),cell.getAddress());
//                            logger.info(String.valueOf(row.getRowNum()+1) + "," + cell.getAddress());
                            titleList.add(cell.getColumnIndex(),cell.getStringCellValue());
                        }else {
                            logger.info("test");
                        }
                    }catch (Exception ex){
                        logger.warn("不是string类型数据");
                    }

                }

            }

            logger.info("Title name list are : {}",titleList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    /**
     *
     * @param filepath
     * @return
     */
    private static String readFile(String filepath){
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try (InputStream stream = new FileInputStream(new File(filepath))) {
            parser.parse(stream, handler, metadata);
            String resultStr = handler.toString();
            logger.info("处理execl文本信息：{}",resultStr);
            if (!resultStr.isEmpty()){
                FileWriter fileWriter = new FileWriter("ratelt.txt");
                fileWriter.append(resultStr);
            }
            return resultStr;
        } catch (IOException | TikaException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

    }


}
