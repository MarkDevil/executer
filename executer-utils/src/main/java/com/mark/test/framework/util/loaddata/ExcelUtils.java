package com.mark.test.framework.util.loaddata;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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
import java.util.*;

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
            Row row = sheet.createRow(0);
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
     *
     * @param fileDir
     * @param sheet
     * @return
     */
    public static List<Map<String, Object>> readExcel(String fileDir, String sheet) {

        FileInputStream inputStream;
        HSSFWorkbook hssfWorkbook;
        List<String> titleList = Lists.newLinkedList();
        List<Map<String, Object>> retDataList = Lists.newLinkedList();
        try {
            inputStream = new FileInputStream(new File(fileDir));
            hssfWorkbook = new HSSFWorkbook(inputStream);
            int sheets = hssfWorkbook.getNumberOfSheets();
            logger.info("sheet number : {} ", sheets);
            HSSFSheet xssfSheet = hssfWorkbook.getSheet(sheet);
            Iterator<Row> it = xssfSheet.rowIterator();
            xssfSheet.getRow(0).cellIterator().forEachRemaining(
                    cell -> titleList.add(cell.getColumnIndex(),
                            cell.getStringCellValue()));
            while (it.hasNext()) {
                Map<String, Object> cellData = new HashMap<>();
                Row row = it.next();
                logger.info("开始读取第{}行", row.getRowNum());
                //跳过表头
                if (row.getRowNum() == 0) {
                    continue;
                }
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    try {
                        if (!cell.getStringCellValue().isEmpty()) {
                            logger.info("cell的值是 :{} \t cell的位置是 :{}", cell.getStringCellValue(), cell.getAddress());
                            cellData.put(titleList.get(cell.getColumnIndex()), cell.getStringCellValue());
                        } else {
                            logger.debug("cell值为空");
                        }
                    } catch (Exception ex) {
                        logger.debug("不是string类型数据:{} ,实际类型为：{}", cell.getAddress(), cell.getCellTypeEnum().name());
                    }
                }
                retDataList.add(cellData);
            }
            logger.info("\n表头数据:{}\n返回行数据:{}", titleList, retDataList);
            return retDataList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
