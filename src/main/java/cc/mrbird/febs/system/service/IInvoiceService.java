package cc.mrbird.febs.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Invoice;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2022-05-17 12:36:58
 */
public interface IInvoiceService extends IService<Invoice> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param invoice invoice
     * @return IPage<Invoice>
     */
    IPage<Invoice> findInvoice(QueryRequest request, Invoice invoice);

    /**
     * search by Id(Pk)
     *
     * @param invoiceId invoiceId
     * @return List<Invoice>
     */
    Invoice searchInvoiceById(String invoiceId);

    /**
     * 新增
     *
     * @param invoice invoice
     */
    void createInvoice(Invoice invoice);

    /**
     * 修改
     *
     * @param invoice invoice
     */
    void updateInvoice(Invoice invoice);

    /**
     * 删除
     *
     * @param invoiceIds invoice
     */
    void deleteInvoice(String[] invoiceIds);




}
