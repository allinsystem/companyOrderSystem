package cc.mrbird.febs.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.system.entity.Invoice;
import cc.mrbird.febs.system.service.IInvoiceService;
import cc.mrbird.febs.system.service.impl.InvoiceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("invoice")
public class InvoiceController extends BaseController {

    private final IInvoiceService invoiceService;

    @GetMapping("{id}")
    @ResponseBody
//    @RequiresPermissions("order:list")
    public FebsResponse getAllInvoices(@PathVariable String id) {
        return new FebsResponse().success().data(invoiceService.searchInvoiceById(id));
    }

    @GetMapping("list")
    @ResponseBody
//    @RequiresPermissions("order:view")
    public FebsResponse invoiceList(QueryRequest request, Invoice invoice) {
        Map<String, Object> dataTable = getDataTable(this.invoiceService.findInvoice(request, invoice));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "請求書を作成します", exceptionMessage = "請求書の作成に失敗しました")
    @PostMapping("add")
    @ResponseBody
//    @RequiresPermissions("order:add")
    public FebsResponse addInvoice(@Valid Invoice invoice) {
        this.invoiceService.createInvoice(invoice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "請求書を削除します", exceptionMessage = "請求書の削除に失敗しました")
    @GetMapping("delete/{ids}")
    @ResponseBody
//    @RequiresPermissions("order:delete")
    public FebsResponse deleteInvoice(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.invoiceService.deleteInvoice(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "請求書を更新します", exceptionMessage = "請求書の更新に失敗しました")
    @PostMapping("update")
    @ResponseBody
//    @RequiresPermissions("order:update")
    public FebsResponse updateInvoice(Invoice invoice) {
        this.invoiceService.updateInvoice(invoice);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "請求書を出力します", exceptionMessage = "請求書の出力に失敗しました")
    @PostMapping("excel/{ids}")
    @ResponseBody
//    @RequiresPermissions("order:export")
    public void export(QueryRequest queryRequest, String ids, HttpServletResponse response) {
    }

    /**
     * Excel 出力
     */
    @Autowired

    InvoiceServiceImpl invoiceServiceImpl;
    @GetMapping("download/{id}")

    public void excel_down(@PathVariable String id,HttpServletResponse response)throws Exception{
    	invoiceServiceImpl.downLoadUserInfoByTemplate(id, response);

	}

}
