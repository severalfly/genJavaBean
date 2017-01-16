create table user_statistic_all_2016_share_record(
  userid bigint(20) primary key, // 主键而已
  uid varchar(50),
  uuid varchar(50), 
  lottery_id bigint, // 发奖id
  send_result varchar(50),// 发券结果
  create_time datetime,
  update_time datetime,
  ext1 int,
  ext2 int,
  ext3 varchar(100),
  ext4 varchar(500),
  ext5 varchar(1000)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index index_usa2r_ct on user_statistic_all_2016_share_record(create_time)