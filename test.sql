CREATE TABLE order_export_hbgj(
        order_id varchar(30),  /* 订单或子订单ID */
        main_order_id varchar(30),  /* 订单ID */
        sub_order_id varchar(30),  /* 子订单ID */
        bs_type  tinyint DEFAULT 0 NOT NULL, /** 1火车票   7汽车票   21送餐*/
        order_type tinyint DEFAULT 0 NOT NULL, /**  1主订单  2子订单 */
        order_change tinyint DEFAULT 0 NOT NULL, /**  1创建  2修改 */
        export_status tinyint DEFAULT 0 NOT NULL, /**  0 需要处理  1成功  2失败 */
        platid varchar(50), /** 平台订单id */
        shortid varchar(15), /** 短订单号 */
        create_time datetime,
        update_time datetime,
        primary key(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
CREATE INDEX index_ct_up  ON order_export_hbgj(export_status, update_time);
CREATE INDEX index_ct  ON order_export_hbgj(create_time);
CREATE INDEX index_oeh_pi on order_export_hbgj(platid);
