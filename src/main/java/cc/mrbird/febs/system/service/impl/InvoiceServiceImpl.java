package cc.mrbird.febs.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Invoice;
import cc.mrbird.febs.system.mapper.InvoiceMapper;
import cc.mrbird.febs.system.service.IInvoiceService;
import lombok.RequiredArgsConstructor;

/**
 *  Service实现
 *
 * @author MrBird
 * @date 2022-05-15 21:19:50
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InvoiceServiceImpl extends ServiceImpl<InvoiceMapper, Invoice> implements IInvoiceService {

    private final InvoiceMapper invoiceMapper;

    @Override
    public IPage<Invoice> findInvoices( Invoice invoice,QueryRequest request) {

        Page<Invoice> page = new Page<>(request.getPageNum(), request.getPageSize());

        return baseMapper.findInvoices(page, invoice);
    }

    @Override
    public Invoice findInvoices(Invoice invoice) {


    	Invoice param = new Invoice();
        param.setCustomerName(null);
        List<Invoice> users = baseMapper.findInvoices(param);
        return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
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
    public void deleteInvoice(Invoice invoice) {
        LambdaQueryWrapper<Invoice> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}

    /**
     *
     */
	@Override
	public Invoice findByCustomerName(String customerName) {

		Invoice param = new Invoice();
        param.setCustomerName(null);
        Invoice users = baseMapper.findByCustomerName(customerName);

        return users;

	}
	@Override
	public Invoice invoiceFindById(Long Id) {

		Invoice param = new Invoice();
        param.setId(Id);
        Invoice users = baseMapper.invoiceFindById(Id);

        return users;

	}

}
