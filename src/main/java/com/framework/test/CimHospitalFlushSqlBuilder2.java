package com.framework.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * 功能描述：柳叶医院去重，以及业务数据表涉及医院信息修改，匹配sdm医院信息.<br/>
 * 
 * #date： 2018年6月22日 下午4:59:47<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
public class CimHospitalFlushSqlBuilder2{

    public static void main(String[] args) throws IOException {
        InputStream is = null;
        try {
            is = new FileInputStream(new File("D:/柳叶重复医院合并0626.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HSSFWorkbook workbook = new HSSFWorkbook(is);
        /** 得到第一个shell */
        HSSFSheet sheet = workbook.getSheetAt(1);
        /** 得到Excel的行数 */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (totalRows > 1 && sheet.getRow(0) != null) {
            int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();

            // 旧医院名称
            StringBuilder oldHosName = new StringBuilder();
            // 新医院名称
            StringBuilder newHosName = new StringBuilder();
            // 旧医院cid
            StringBuilder oldHosCid = new StringBuilder();

            /** 循环Excel的行 */
            for (int r = 1; r < totalRows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }

                StringBuilder cim_doc_recheck_info = new StringBuilder();
                // update cim_doc_recheck_info set hospital_name = '' where
                // hospital_name = '';
                cim_doc_recheck_info.append("update cim_doc_recheck_info set version = '2.0.0',hospital_name = ");

                StringBuilder cim_doctor_detail_info = new StringBuilder();
                // update cim_doctor_detail_info set hospital_name =
                // '上海市第六人民医院',hospital_cid =
                // '2016083009481526007817ae8a8fe561' where hospital_name =
                // '上海第六人民医院';
                cim_doctor_detail_info.append("update cim_doctor_detail_info set version = '2.0.0',hospital_name = ");

                StringBuilder cim_doctor_medicine_recommend_log = new StringBuilder();
                // update cim_doctor_medicine_recommend_log set hospital_name =
                // '上海市第六人民医院' where hospital_name = '上海第六人民医院';
                cim_doctor_medicine_recommend_log
                        .append("update cim_doctor_medicine_recommend_log set version = '2.0.0',hospital_name = ");

                StringBuilder cim_doctor_register_record = new StringBuilder();
                // update cim_doctor_register_record set hospital_name =
                // '上海市第六人民医院'
                // hospital_cid = '2016083009481526007817ae8a8fe561'
                // where hospital_name = '上海第六人民医院';
                cim_doctor_register_record
                        .append("update cim_doctor_register_record set version = '2.0.0',hospital_name = ");

                StringBuilder cim_doctor_section_office_kind_info = new StringBuilder();
                // update cim_doctor_section_office_kind_info set hos_cid =
                // '2016083009481526007817ae8a8fe561' where hos_cid
                // ='20161217131631613006723a11114414';
                cim_doctor_section_office_kind_info
                        .append("update cim_doctor_section_office_kind_info set version = '2.0.0',hos_cid = ");

                StringBuilder cim_hospital_info = new StringBuilder();
                StringBuilder cim_hospital_info_old_name = new StringBuilder();
                // update cim_hospital_info set name = '上海市第六人民医院',cid =
                // '2016083009481526007817ae8a8fe561' where
                // name = '上海第六人民医院';
                cim_hospital_info.append("update cim_hospital_info set version = '2.0.0',name = ");

                StringBuilder cim_my_patient_education_info = new StringBuilder();
                // update cim_my_patient_education_info set hospital_name = ''
                // where hospital_name = '';
                cim_my_patient_education_info
                        .append("update cim_my_patient_education_info set version = '2.0.0',hospital_name = ");

                StringBuilder cim_my_questionmaire_info = new StringBuilder();
                // update cim_my_questionmaire_info set hospital_name = '' where
                // hospital_name = '';
                cim_my_questionmaire_info
                        .append("update cim_my_questionmaire_info set version = '2.0.0',hospital_name = ");

                StringBuilder cim_patient_cases_offline_info = new StringBuilder();
                // update cim_patient_cases_offline_info set hospital_name =
                // '',hospital_cid = '' where hospital_name = '';
                cim_patient_cases_offline_info
                        .append("update cim_patient_cases_offline_info set version = '2.0.0',hospital_name = ");

                StringBuilder cim_patient_education_send_log = new StringBuilder();
                // update cim_patient_education_send_log set hospital_name = ''
                // where hospital_name = '';
                cim_patient_education_send_log
                        .append("update cim_patient_education_send_log set version = '2.0.0',hospital_name = ");

                StringBuilder cim_patient_education_template = new StringBuilder();
                // update cim_patient_education_template set hospital_name = ''
                // where hospital_name = '';
                cim_patient_education_template
                        .append("update cim_patient_education_template set version = '2.0.0',hospital_name = ");

                StringBuilder cim_questionnaire_template = new StringBuilder();
                // update cim_questionnaire_template set hospital_name = ''
                // where hospital_name = '';
                cim_questionnaire_template
                        .append("update cim_questionnaire_template set version = '2.0.0',hospital_name = ");

                StringBuilder cim_quick_service_book_reply_info = new StringBuilder();
                // update cim_quick_service_book_reply_info set hospital_name =
                // '' where hospital_name = '';
                cim_quick_service_book_reply_info
                        .append("update cim_quick_service_book_reply_info set version = '2.0.0',hospital_name = ");

                StringBuilder tt_prescription = new StringBuilder();
                // update tt_prescription set tu_hospital_name =
                // '上海市第六人民医院',tu_hospital_cid =
                // '2016083009481526007817ae8a8fe561' where tu_hospital_name =
                // '上海第六人民医院';
                tt_prescription.append("update tt_prescription set extend = '2.0.0',tu_hospital_name = ");

                StringBuilder tt_prescription_delete = new StringBuilder();
                // update tt_prescription_delete set tu_hospital_name =
                // '上海市第六人民医院',tu_hospital_cid =
                // '2016083009481526007817ae8a8fe561' where tu_hospital_name =
                // '上海第六人民医院';
                tt_prescription_delete.append("update tt_prescription_delete set extend = '2.0.0',tu_hospital_name = ");

                /** 循环Excel的列 */
                for (int c = 0; c < totalCells; c++) {
                    HSSFCell cell = row.getCell(c);
                    String cellValue = null;
                    if (null != cell) {
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = "'" + StringUtils.trimWhitespace(cell.getStringCellValue()) + "'";
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数值
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        default:
                            cellValue = "'未知类型'";
                            break;
                        }

                        if (c == 0) {
                            cim_doc_recheck_info.append(cellValue);
                            cim_doctor_detail_info.append(cellValue);
                            cim_doctor_medicine_recommend_log.append(cellValue);
                            cim_doctor_register_record.append(cellValue);
                            cim_hospital_info.append(cellValue);
                            cim_my_patient_education_info.append(cellValue);
                            cim_my_questionmaire_info.append(cellValue);
                            cim_patient_cases_offline_info.append(cellValue);
                            cim_patient_education_send_log.append(cellValue);
                            cim_patient_education_template.append(cellValue);
                            cim_questionnaire_template.append(cellValue);
                            cim_quick_service_book_reply_info.append(cellValue);

                            tt_prescription.append(cellValue);
                            tt_prescription_delete.append(cellValue);

                            newHosName.append(cellValue).append(",");
                        } else if (c == 1) {
                            cim_doctor_detail_info.append(",hospital_cid = ").append(cellValue);
                            cim_doctor_register_record.append(",hospital_cid = ").append(cellValue);
                            cim_doctor_section_office_kind_info.append(cellValue);
                            cim_hospital_info.append(",cid = ").append(cellValue);
                            cim_patient_cases_offline_info.append(",hospital_cid = ").append(cellValue);

                            tt_prescription.append(",tu_hospital_cid = ").append(cellValue);
                            tt_prescription_delete.append(",tu_hospital_cid = ").append(cellValue);
                        } else if (c == 2) {
                            cim_doc_recheck_info.append(" where hospital_name = ").append(cellValue);
                            cim_doctor_detail_info.append(" where hospital_name = ").append(cellValue);
                            cim_doctor_medicine_recommend_log.append(" where hospital_name = ").append(cellValue);
                            cim_doctor_register_record.append(" where hospital_name = ").append(cellValue);
                            
                            //匹配sdm信息
                            cim_hospital_info_old_name.append(" where name = ").append(cellValue);
                            
                            cim_my_patient_education_info.append(" where hospital_name = ").append(cellValue);
                            cim_my_questionmaire_info.append(" where hospital_name = ").append(cellValue);
                            cim_patient_cases_offline_info.append(" where hospital_name = ").append(cellValue);
                            cim_patient_education_send_log.append(" where hospital_name = ").append(cellValue);
                            cim_patient_education_template.append(" where hospital_name = ").append(cellValue);
                            cim_questionnaire_template.append(" where hospital_name = ").append(cellValue);
                            cim_quick_service_book_reply_info.append(" where hospital_name = ").append(cellValue);

                            tt_prescription.append(" where tu_hospital_name = ").append(cellValue);
                            tt_prescription_delete.append(" where tu_hospital_name = ").append(cellValue);

                            oldHosName.append(cellValue).append(",");
                        } else if (c == 3){
                            cim_doctor_section_office_kind_info.append(" where hos_cid = ").append(cellValue);

                            oldHosCid.append(cellValue).append(",");
                        }
                        
                        else if (c == 4){
                            cim_hospital_info.append(",sdm_id = ").append(cellValue);
                        }
                        
                        else if (c == 5){
                            cim_hospital_info.append(",sdm_name = ").append(cellValue).append(cim_hospital_info_old_name);
                        }
                        
                    }
                }

                // 输出更新语句
                System.out.println(cim_doc_recheck_info.append(";").toString());
                System.out.println(cim_doctor_detail_info.append(";").toString());
                System.out.println(cim_doctor_medicine_recommend_log.append(";").toString());
                System.out.println(cim_doctor_register_record.append(";").toString());
                System.out.println(cim_doctor_section_office_kind_info.append(";").toString());
                System.out.println(cim_hospital_info.append(";").toString());
                System.out.println(cim_my_patient_education_info.append(";").toString());
                System.out.println(cim_my_questionmaire_info.append(";").toString());
                System.out.println(cim_patient_cases_offline_info.append(";").toString());
                System.out.println(cim_patient_education_send_log.append(";").toString());
                System.out.println(cim_patient_education_template.append(";").toString());
                System.out.println(cim_questionnaire_template.append(";").toString());
                System.out.println(cim_quick_service_book_reply_info.append(";").toString());
                System.out.println(tt_prescription.append(";").toString());
                System.out.println(tt_prescription_delete.append(";").toString());
                System.out.println();
            }

            // 输出业务数据中医院查询
            StringBuilder select = new StringBuilder();
            select.append("SELECT * from cim_doc_recheck_info where hospital_name in(")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_doctor_detail_info where hospital_name  in(")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_doctor_medicine_recommend_log where hospital_name  in(")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_doctor_register_record where hospital_name  in(")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_doctor_section_office_kind_info where hos_cid  in(")
                    .append(oldHosCid.toString().substring(0, oldHosCid.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_hospital_info where name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_hospital_info where name in (")
                    .append(newHosName.toString().substring(0, newHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_my_patient_education_info where hospital_name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_my_questionmaire_info where hospital_name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_patient_cases_offline_info where hospital_cid in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_patient_education_send_log where hospital_name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_patient_education_template where hospital_name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_questionnaire_template where hospital_name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");
            select.append("SELECT * from cim_quick_service_book_reply_info where hospital_name in (")
                    .append(oldHosName.toString().substring(0, oldHosName.toString().length() - 1)).append(");")
                    .append("\n");

            System.out.println(select.toString());
        }
        workbook.close();
    }
}
