package cc.mrbird.febs.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Invoice;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2022-05-15 21:19:50
 */
public interface IInvoiceService extends IService<Invoice> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param invoice invoice
     * @return IPage<Invoice>
     */
    IPage<Invoice> findInvoices(Invoice invoice,QueryRequest request);

    /**
     * 查询（所有）
     *
     * @param invoice invoice
     * @return List<Invoice>
     */
    Invoice findInvoices(Invoice invoice);

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
     * @param invoice invoice
     */
    void deleteInvoice(Invoice invoice);

//	List<Invoice> findInvoices(String customerName);

	Invoice findByCustomerName(String customerName);

	Invoice invoiceFindById(Long Id);


}
