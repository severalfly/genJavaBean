CREATE TABLE `user_order_refund_reduce` (
order_id varchar(30), // 订单号
sub_order_id varchar(30),// 子订单号
userid bigint,
uid  varchar(30),
p varchar(100),
create_time datetime,
update_time datetime,
coupon_id varchar(50),// 红包id
coupon_return_amount decimal(10, 2), // 使用的红包可返现金额
coupon_return_time datetime,// 返现时间
random_amount  decimal(10,2),// 拼手气金额
random_time datetime,  // 拼手气金额时间
random_id varchar(30),// 拼手气id,用于返现，也可用于记录
random_userid bigint,// 拼手气的userid
  KEY `index_uorr_ct` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

  