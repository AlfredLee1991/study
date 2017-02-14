package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述：excle文件解析工具类.<br/>
 * 
 * #date： 2015年11月24日 上午11:31:13<br/>
 * #author lixu<br/>
 * #since 1.0.0<br/>
 */
public class ExcelReadUtil{

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReadUtil.class);

    /** 总行数 */
    private int totalRows = 0;

    /** 总列数 */
    private int totalCells = 0;

    /** 错误信息 */
    private String errorInfo;

    /** 构造方法 */
    public ExcelReadUtil() {
    }

    /**
     * 方法描述：得到总行数 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:32:08<br/>
     * #since 1.0.0<br/>
     * 
     * @return
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * 方法描述：得到总列数 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:32:17<br/>
     * #since 1.0.0<br/>
     * 
     * @return
     */
    public int getTotalCells() {
        return totalCells;
    }

    /**
     * 方法描述：得到错误信息 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:32:31<br/>
     * #since 1.0.0<br/>
     * 
     * @return
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * 方法描述：验证excel文件 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:32:44<br/>
     * #since 1.0.0<br/>
     * 
     * @param filePath:文件完整路径
     * @return
     */
    public boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
            errorInfo = "文件名不是excel格式";
            return false;
        }
        /** 检查文件是否存在 */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            errorInfo = "文件不存在";
            return false;
        }
        return true;
    }

    /**
     * 方法描述：根据文件名读取excel文件 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:33:05<br/>
     * #since 1.0.0<br/>
     * 
     * @param filePath:文件完整路径
     * @return
     */
    public List<List<String>> read(String filePath) {

        List<List<String>> dataLst = new ArrayList<List<String>>();
        InputStream is = null;

        try {
            /** 验证文件是否合法 */
            if (!validateExcel(filePath)) {
                return null;
            }

            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, filePath);
            is.close();
        } catch (FileNotFoundException ex) {
            LOGGER.error(filePath + "file not found!", ex);
        } catch (InvalidFormatException e) {
            LOGGER.error("Invalid Format!", e);
        } catch (IOException e) {
            LOGGER.error("IO流出错!", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    LOGGER.error("IO流出错!", e);
                }
            }
        }

        /** 返回最后读取的结果 */
        return dataLst;

    }

    /**
     * 方法描述：根据流读取Excel文件 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:33:50<br/>
     * #since 1.0.0<br/>
     * 
     * @param inputStream
     * @param address
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     */
    public List<List<String>> read(InputStream inputStream, String address) throws InvalidFormatException, IOException {
        List<List<String>> dataLst = null;
        Workbook wb = this.creatWorkBook(inputStream, address);
        dataLst = read(wb);
        return dataLst;

    }

    /**
     * 方法描述：创建Excel工作簿 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:34:11<br/>
     * #since 1.0.0<br/>
     * 
     * @param inputStream
     * @param address
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    private Workbook creatWorkBook(InputStream inputStream, String address) throws IOException, InvalidFormatException {
        Workbook wb = null;
        InputStream ins = inputStream;
        if (!inputStream.markSupported()) {
            ins = new PushbackInputStream(inputStream, 8);
        }
        if (address.endsWith("xls")) {
            wb = new HSSFWorkbook(ins);
        }
        if (address.endsWith("xlsx")) {
            OPCPackage opcPackage = OPCPackage.open(ins);
            wb = new XSSFWorkbook(opcPackage);
        }

        return wb;
    }

    /**
     * 方法描述：读取数据<br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:34:39<br/>
     * #since 1.0.0<br/>
     * 
     * @param wb
     * @return
     */
    private List<List<String>> read(Workbook wb) {

        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(0);

        /** 得到Excel的行数 */
        this.totalRows = sheet.getLastRowNum();

        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }

        /** 循环Excel的行 */
        List<String> rowLst = null;
        for (int r = 1; r <= this.totalRows; r++) {
            rowLst = new ArrayList<String>();
            Row row = sheet.getRow(r);
            if (row == null) {
                for (int c = 0; c < this.totalCells; c++) {
                    rowLst.add("");
                }
            } else {
                /** 循环Excel的列 */
                for (int c = 0; c < this.getTotalCells(); c++) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                            cellValue = (int) cell.getNumericCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue().trim();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;

                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                        }
                    }
                    rowLst.add(cellValue);
                }
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
        return dataLst;
    }

    /**
     * 方法描述：日志记录列表类型列表 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:35:34<br/>
     * #since 1.0.0<br/>
     * 
     * @param list
     */
    public void printout(List<List<String>> list) {

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                LOGGER.info("第(" + i + ")行");
                List<String> cellList = list.get(i);
                for (int j = 0; j < cellList.size(); j++) {
                    LOGGER.info(" " + cellList.get(j));
                }
            }
        }
    }

}

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2015年11月24日 上午11:37:32<br/>
 * #author lixu<br/>
 * #since 1.0.0<br/>
 */
class WDWUtil{

    private WDWUtil() {
    }

    /**
     * 方法描述：是否是2003的excel <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:37:18<br/>
     * #since 1.0.0<br/>
     * 
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 方法描述：是否是2007的excel <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:37:03<br/>
     * #since 1.0.0<br/>
     * 
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");

    }

    /**
     * 方法描述：创建工作簿 <br/>
     *
     * #author lixu<br/>
     * #date 2015年11月24日 上午11:36:47<br/>
     * #since 1.0.0<br/>
     * 
     * @param inp
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Workbook create(InputStream inp) throws IOException, InvalidFormatException {
        InputStream ins = inp;
        if (!inp.markSupported()) {
            ins = new PushbackInputStream(inp, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(ins)) {
            return new HSSFWorkbook(ins);
        }
        if (POIXMLDocument.hasOOXMLHeader(ins)) {
            return new XSSFWorkbook(OPCPackage.open(ins));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }
}