package cc.mrbird.febs.system.entity;

import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2022-05-28 16:06:27
 */
@Data
@TableName("t_customers_master")
public class Customer {

    /**
     * 
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private BigDecimal customerId;
    /**
     * 
     */
    @TableField("customer_name")
    private String customerName;

}
