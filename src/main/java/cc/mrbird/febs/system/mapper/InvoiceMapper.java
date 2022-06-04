package cc.mrbird.febs.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cc.mrbird.febs.system.entity.Invoice;

/**
 *  Mapper
 *
 * @author MrBird
 */
public interface InvoiceMapper extends BaseMapper<Invoice> {
    Invoice searchInvoiceById(@Param("id") String id);
    IPage<Invoice> searchInvoiceByPage(Page<Invoice> page, Invoice invoice);
    long countInvoiceDetail(@Param("invoice") Invoice invoice);

}
