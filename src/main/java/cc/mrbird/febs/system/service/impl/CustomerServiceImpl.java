package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Strings;
import cc.mrbird.febs.system.entity.Customer;
import cc.mrbird.febs.system.mapper.CustomerMapper;
import cc.mrbird.febs.system.service.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Service实现
 *
 * @author MrBird
 * @date 2022-05-28 16:06:27
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    private final CustomerMapper customerMapper;

    @Override
    public IPage<Customer> findCustomer(QueryRequest request, Customer customer) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Customer> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Customer> findCustomer(Customer customer) {
	    LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCustomer(Customer customer) {
        this.save(customer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomer(Customer customer) {
        this.saveOrUpdate(customer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCustomer(Customer customer) {
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
    public String findAllCustomer(){
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper).stream().map(Customer::getCustomerName).collect(Collectors.joining(Strings.COMMA));
    };
}
