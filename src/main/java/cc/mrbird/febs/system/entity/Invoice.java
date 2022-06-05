package cc.mrbird.febs.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2022-05-17 12:36:58
 */
@Entity
@Data
@TableName("t_invoice_form")
public class Invoice implements Serializable {

    private static final long serialVersionUID = -4312368070794165001L;
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
    @TableField("employee_id")
    private BigDecimal employeeId;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 ", timezone = "Asia/Tokyo")
    @TableField("invoice_date")
    private Date invoiceDate;

    /**
     *
     */
	@Id
	@Column(name = "invoice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "invoice_id", type = IdType.AUTO)
    private BigDecimal id;
    /**
     *
     */
    @TableField("price")
    private BigDecimal price;
    /**
     *
     */
    @TableField("project_name")
    private String projectName;

    @TableField(exist = false)
    private String dateFrom;
    @TableField(exist = false)
    private String dateTo;
    @TableField(exist = false)
    private String customerName;
    @TableField(exist = false)
    private String employeeName;
    @TableField(exist = false)
    private String employeeType;
}
