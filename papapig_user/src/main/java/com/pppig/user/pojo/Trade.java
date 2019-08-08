package com.pppig.user.pojo;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name="trade")
public class Trade implements Serializable {

    @Id
    private String serial_number;//流水号

    private Integer user_ID;//用户标识
    private String message_ID;//报文标识
    private String request_message;//请求报文
    private String response_message;//响应报文
    private String request_trading_account;//请求交易账户
    private Double request_trading_amount;//请求交易金额
    private String response_trading_account;//响应交易账号
    private Double response_trading_amount;// 响应交易金额
    private String trade_status;//交易状态
    private java.util.Date trade_date;//交易时间
    private Integer loanid;//标信息
    private Integer loan_phase_id;//还款（借款标id）
    private Integer loan_investor_id;//债券转让（投标id）
    private Integer request_id;//请求id（用于取现）
    private String trxId;//第三方响应流水号
    private String freeze_trxId;//冻结资金流水号（支付生成）
    private String freeze_ordId;//冻结资金流水
    private String unfreeze_ordId;//解冻订单号（客户请求）
    private String debt_ord_id;//债权转让成功订单号（用在还款中）
    private java.util.Date debt_ord_date;//债权转让成功时间（用在还款中）
    private Integer version;//版本号
    private String qd;//投资渠道

}
