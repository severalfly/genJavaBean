create table qp_failed_send_record(
  id bigint(20) primary key, // 主键而已
  userid bigint(20), //用户id
  status tinyint, // 此条状态，1. 新增，需要发push   11.push 成功 12.push 失败；21.短信成功 22.短信失败
  vip int, // 用户航班vip 等级
  coupon_id varchar(10), // 航班券id, 用来分类的 如 482/484/472
  cou_id varchar(100), // 发给用户的优惠券id
  coupon_amount decimal(10,5),// 发的优惠券金额
  msg_id varchar(30), // 发push 消息的id，用来确定用户是否已读
  create_time datetime,
  update_time datetime,
  push_time datetime, // push 发送时间
  push_msg varchar(300), // psuh 消息内容
  sms_time datetime, // 发短信时间
  sms_msg varchar(300), // 短信内容
  uid varchar(50),
  p varchar(100),
  ext1 int,
  ext2 varchar(50),
  ext3 varchar(200),
  ext4 varchar(500),
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;