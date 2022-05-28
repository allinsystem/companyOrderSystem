package cc.mrbird.febs.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import cc.mrbird.febs.system.entity.Invoice;

/**
 *  Mapper
 *
 * @author MrBird
 * @date 2022-05-15 21:19:50
 */
public interface InvoiceMapper extends BaseMapper<Invoice> {

	List<Invoice> findInvoices(@Param("invoice")Invoice invoice);

	 IPage<Invoice> findInvoices(IPage<Invoice> page, @Param("invoice") Invoice invoice);

//	 List<Invoice> findById(@Param("invoice")Invoice invoice);

	 Invoice findByCustomerName(String customerName);

	 Invoice invoiceFindById(Long Id);




}
