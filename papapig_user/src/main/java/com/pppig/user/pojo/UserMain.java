package com.pppig.user.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "user_main")
public class UserMain implements Serializable{

    @Id
    private Integer userId;//用户id

    private String email;//邮件，要求唯一
    private String nickName;//用户昵称，要求唯一
    private String realName;//真实姓名
    private String idCardNo;//身份证号
    private String passwd;//密码
    private String payPassword;//支付密码
    private Integer isPwdSet;//是否设置交易密码
    private String mobile;//已绑定的手机号码，不要求唯一
    private String jxmobile;//江西银行预留的手机号
    private String city;//所在城市,改为商户的企业证件号
    private Date registerTime;//注册时间
    private Integer roles;//角色，参见com.zkbc.core.consts.user.UserRolesType
    private Double cash;//可用现金余额  这条记录中的值才是用户真正可用的钱
    private Double currBal;//账面余额
    private Double frozenWithDrawCash;//已冻结的提现中现金
    private Double frozenBiddingCash;//已冻结的投标中现金
    private Integer  idVerifyLimit;//国政通验证次数，每次验证减1，默认每人最多验证3次
    private Integer  portrait;//头像id
    private Integer  status;//用户状态。用数值型的好处是今后可以扩充定义，参见com.zkbc.core.consts.user.UserStatusType
    private Integer  forbidStatus;//禁止状态。参见com.zkbc.core.consts.user.UserForbidStatusType
    private Integer  albumCapacity;//个人相册容量，单位：MB。用户所有的userpic加起来不能大于此数值
    private Integer  securityLevel;//安全等级
    private String weiboUId;//新浪微博uid
    private String weiboAccessToken;//新浪微博AccessToken
    private String qqUId;//腾讯uid
    private String qqAccessToken;//腾讯AccessToken
    private Integer origin;//借款用户的渠道
    private Integer  staffId;//所属客户经理,改为是否为员工,1为是员工
    private String userCode;//第三方支付账号
    private String referee;//推荐人，存储推荐人用户名
    private Date level_update_time;//级别更新时间
    private String level;//0 普通用户  1 普通猪粉  2 超级猪粉
    private Date bindtime;//
    private String loginkey;//天眼登录校验标识
    private String userkey;//天眼绑定标识
    private String cashChl;//取现方式ID
    private String jxmoblie;
}
