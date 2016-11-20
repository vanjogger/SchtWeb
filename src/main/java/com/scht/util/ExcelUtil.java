package com.scht.util;

import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * Created by Administrator on 2016/2/20.
 */
public class ExcelUtil {

    public  static Workbook createWorkBook(List<JSONObject> data,String[] columns,String[] keys){
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
       for(int i=0;i<columns.length;i++){
            sheet.setColumnWidth(i,5000);
        }

        CellStyle css1 = workbook.createCellStyle();
        CellStyle css2 = workbook.createCellStyle();

        Font f1 = workbook.createFont();
        f1.setFontHeightInPoints((short) 10);
        f1.setColor(IndexedColors.BLACK.getIndex());
        f1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        Font f2 = workbook.createFont();
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        css1.setFont(f1);
        css1.setAlignment(CellStyle.ALIGN_CENTER);
        css2.setFont(f2);
        css2.setAlignment(CellStyle.ALIGN_CENTER);

        JSONObject total = data.get(data.size() - 1);
        data.remove(data.size() - 1);

        Row totalRow = sheet.createRow(0);
        Cell sucCell = totalRow.createCell(0);
        sucCell.setCellValue("成功订单:"+total.get("sucOrder")+"  金额："+total.get("suc").toString());
        Cell failCell = totalRow.createCell(1);
        failCell.setCellValue("失败订单:"+total.get("failOrder")+"  金额："+total.get("fail").toString());



        Row row = sheet.createRow(1);
        for(int i=0;i<columns.length;i++){
          //  sheet.setColumnWidth(i,120);
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(css1);
        }



        for(int i=0;i<data.size();i++){
            Row row1 = sheet.createRow((short) i+2);
            JSONObject json = data.get(i);
            // 在row行上创建一个方格
             for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(json.get(keys[j]) == null?" ": json.get(keys[j]).toString());
                cell.setCellStyle(css2);
            }
        }

        return workbook;
    }


}
