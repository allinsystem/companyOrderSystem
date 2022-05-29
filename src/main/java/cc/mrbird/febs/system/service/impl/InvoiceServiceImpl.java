package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.system.entity.Invoice;
import cc.mrbird.febs.system.mapper.InvoiceMapper;
import cc.mrbird.febs.system.service.IInvoiceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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
}
