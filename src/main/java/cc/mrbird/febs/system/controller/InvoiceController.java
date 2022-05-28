package cc.mrbird.febs.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wuwenze.poi.ExcelKit;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.system.entity.Invoice;
import cc.mrbird.febs.system.service.IInvoiceService;
import cc.mrbird.febs.system.service.impl.UserServiceDL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  Controller
 *
 * @author MrBird
 * @date 2022-05-15 21:19:50
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("order")

public class InvoiceController extends BaseController {

    private final IInvoiceService invoiceService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "invoice")
    public String invoiceIndex(){
        return FebsUtil.view("invoice/invoice");
    }

    @GetMapping("invoice")
    @ResponseBody
    @RequiresPermissions("invoice:list")
    public FebsResponse getAllInvoices(Invoice invoice) {
        return new FebsResponse().success().data(invoiceService.findInvoices(invoice));
    }

    @GetMapping("list")
    @RequiresPermissions("order:view")
    public FebsResponse userList(Invoice invoice, QueryRequest request) {
        return new FebsResponse().success()
                .data(getDataTable(invoiceService.findInvoices(invoice, request)));   //0516
    }

    @GetMapping("order/list")
    @ResponseBody
    @RequiresPermissions("order:view")
    public FebsResponse invoiceList(QueryRequest request, Invoice invoice) {
        Map<String, Object> dataTable = getDataTable(this.invoiceService.findInvoices(invoice,request));
        return new FebsResponse().success().data(dataTable);
    }




    @ControllerEndpoint(operation = "新增Invoice", exceptionMessage = "新增Invoice失败")
    @PostMapping("invoice")
    @ResponseBody
    @RequiresPermissions("invoice:add")
    public FebsResponse addInvoice(@Valid Invoice invoice) {
        this.invoiceService.createInvoice(invoice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Invoice", exceptionMessage = "删除Invoice失败")
    @GetMapping("invoice/delete")
    @ResponseBody
    @RequiresPermissions("invoice:delete")
    public FebsResponse deleteInvoice(Invoice invoice) {
        this.invoiceService.deleteInvoice(invoice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Invoice", exceptionMessage = "修改Invoice失败")
    @PostMapping("invoice/update")
    @ResponseBody
    @RequiresPermissions("invoice:update")
    public FebsResponse updateInvoice(Invoice invoice) {
        this.invoiceService.updateInvoice(invoice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Invoice", exceptionMessage = "导出Excel失败")
    @PostMapping("invoice/excel")
    @ResponseBody
    @RequiresPermissions("invoice:export")
    public void export(QueryRequest queryRequest, Invoice invoice, HttpServletResponse response) {
        List<Invoice> invoices = this.invoiceService.findInvoices(invoice,queryRequest).getRecords();
        ExcelKit.$Export(Invoice.class, response).downXlsx(invoices, false);
    }


    /**
     * Excel 出力
     */
    @Autowired

	UserServiceDL userServiceDL;
    @GetMapping("download/{id}")

    public void excel_down(@PathVariable Long id,HttpServletResponse response)throws Exception{
		userServiceDL.downLoadUserInfoByTemplate(id,response);

	}

}
