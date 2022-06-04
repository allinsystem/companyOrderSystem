package cc.mrbird.febs.system.service.impl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.system.entity.Invoice;
import cc.mrbird.febs.system.mapper.InvoiceMapper;
import cc.mrbird.febs.system.repository.ResponseUtil;
import cc.mrbird.febs.system.service.IInvoiceService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements IInvoiceService {

    private final InvoiceMapper invoiceMapper;

    @Override
    public IPage<Invoice> findInvoice(QueryRequest request, Invoice invoice) {
        if (StringUtils.isNotBlank(invoice.getDateFrom()) &&
                StringUtils.equals(invoice.getDateFrom(), invoice.getDateTo())) {
            invoice.setDateFrom(invoice.getDateFrom());
            invoice.setDateTo(invoice.getDateTo());
        }
        if (StringUtils.isNotBlank(invoice.getCustomerName())) {
            invoice.setCustomerName((invoice.getCustomerName()));
        }
        Page<Invoice> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countInvoiceDetail(invoice));
        SortUtil.handlePageSort(request, page, "id", FebsConstant.ORDER_ASC, false);
        return baseMapper.searchInvoiceByPage(page, invoice);
    }

    @Override
    public Invoice searchInvoiceById(String id) {
		return this.baseMapper.searchInvoiceById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createInvoice(Invoice invoice) {
        this.save(invoice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvoice(Invoice invoice) {
        this.saveOrUpdate(invoice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteInvoice(String[] invoiceIds) {
        List<String> list = Arrays.asList(invoiceIds);
        removeByIds(list);
	}


    public void downLoadUserInfoByTemplate( String id,HttpServletResponse response)throws Exception{
    	/**
    	 * 20220524
    	 * サンプル取得先更新
    	 */
    	ClassPathResource templateFile = new ClassPathResource("templates/excel" + File.separator + "請求書テンプレート.xlsx");
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
		Invoice user= searchInvoiceById(id);


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
		sheet.getRow(20).getCell(6).setCellValue(user.getPrice().toString());


		workbook.setForceFormulaRecalculation(true);


		ResponseUtil.export(response, workbook, "請求書テンプレート.xlsx");


    }

}
