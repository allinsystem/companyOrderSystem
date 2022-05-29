package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.Customer;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author MrBird
 * @date 2022-05-28 16:06:27
 */
public interface ICustomerService extends IService<Customer> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param customer customer
     * @return IPage<customer>
     */
    IPage<Customer> findCustomer(QueryRequest request, Customer customer);

    /**
     * 查询（所有）
     *
     * @param customer customersMaster
     * @return List<CustomersMaster>
     */
    List<Customer> findCustomer(Customer customer);

    /**
     * 新增
     *
     * @param customer customersMaster
     */
    void createCustomer(Customer customer);

    /**
     * 修改
     *
     * @param customer customersMaster
     */
    void updateCustomer(Customer customer);

    /**
     * 删除
     *
     * @param customer customersMaster
     */
    void deleteCustomer(Customer customer);
    String findAllCustomer();
}
