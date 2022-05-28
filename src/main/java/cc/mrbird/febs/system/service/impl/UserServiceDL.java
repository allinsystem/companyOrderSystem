package cc.mrbird.febs.system.service.impl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import cc.mrbird.febs.system.entity.Invoice;
import cc.mrbird.febs.system.repository.ResponseUtil;
import cc.mrbird.febs.system.repository.UserDetailRepository;




@Service
public class UserServiceDL {

    @Autowired
    private UserDetailRepository userDetailRepository;

    /**
     * ユーザー情報 主キー検索
     * @return 検索結果
     */


    public Invoice userfindById(Long id) {
      return userDetailRepository.findById(id).get();

    }


    public void downLoadUserInfoByTemplate( Long id,HttpServletResponse response)throws Exception{
    	/**
    	 * 20220524
    	 * サンプル取得先更新
    	 */
    	ClassPathResource templateFile = new ClassPathResource("templates" + File.separator + "spexcel.xlsx");
    	InputStream in = templateFile.getInputStream();
    	Workbook workbook = new XSSFWorkbook(in);
    	Sheet sheet =workbook.getSheetAt(0);

		CellStyle style = workbook.createCellStyle();
		CellStyle style2 = workbook.createCellStyle();
		Font font = workbook.createFont();

		font.setFontHeightInPoints((short) 14);
		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		style2.setFont(font);

    	/**
    	 *
    	 */
		Invoice user= userfindById(id);


    	sheet.getRow(4).getCell(0).setCellStyle(style);
    	sheet.getRow(4).getCell(0).setCellStyle(style2);
    	sheet.getRow(4).getCell(0).setCellValue(user.getCustomerName());
    	sheet.getRow(2).getCell(6).setCellStyle(style);
    	sheet.getRow(2).getCell(6).setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(user.getInvoiceDate()));
    	sheet.getRow(17).getCell(1).setCellStyle(style);
    	sheet.getRow(17).getCell(1).setCellValue(user.getEmployeeName());
    	sheet.getRow(20).getCell(0).setCellStyle(style);
    	sheet.getRow(20).getCell(0).setCellValue(user.getProjectName());
    	sheet.getRow(20).getCell(5).setCellStyle(style);
		sheet.getRow(20).getCell(5).setCellValue(user.getCount());
		sheet.getRow(20).getCell(6).setCellStyle(style);
		sheet.getRow(20).getCell(6).setCellValue(user.getPrice());


		workbook.setForceFormulaRecalculation(true);


		ResponseUtil.export(response, workbook, "ABC.xlsx");


    }



}
