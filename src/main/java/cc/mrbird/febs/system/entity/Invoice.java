package cc.mrbird.febs.system.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2022-05-15 21:19:50
 */
@Entity
@Data
//@TableName("t_invoice")
@Table(name="t_invoice")
public class Invoice {

    /**
     *
     */
    @TableField("count")
    private Integer count;

    /**
     *
     */
    @TableField("customer_id")
    private BigDecimal customerId;
    /**
     *
     */
    @TableField("customer_name")
    public String customerName;

    /**
     *
     */
    @TableField("employee_id")
    private BigDecimal employeeId;
    /**
     *
     */
    @TableField("employee_name")
    private String employeeName;

    /**
     *
     */


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     *
     */


	@JsonFormat(pattern = "yyyy年MM月dd日 ", timezone = "GMT+8")
    @TableField("invoice_date")
    private Date invoiceDate;

    /**
     *
     */
    @TableField("price")
    private Integer price;

    /**
     *
     */
    @TableField("project_name")
    private String projectName;




}
