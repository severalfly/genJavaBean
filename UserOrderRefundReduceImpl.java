@Component("userOrderRefundReduceImpl")
public class UserOrderRefundReduceImpl
{
	@Autowired
	private BaseOperDao baseDao;
	public int insertUserOrderRefundReduce(UserOrderRefundReduce userOrderRefundReduce)
	{
		String sql= "insert into user_order_refund_reduce(order_id,sub_order_id,userid,uid,p,create_time,coupon_id,coupon_return_amount,coupon_return_time,random_amount,random_time,random_id,random_userid,)value(?, ?, ?, ?, ?, current_timestamp, ?, ?, ?, ?, ?, ?, ?, )";
		return this.baseDao.update(sql, new Object[]{userOrderRefundReduce.getOrderId(), userOrderRefundReduce.getSubOrderId(), userOrderRefundReduce.getUserid(), userOrderRefundReduce.getUid(), userOrderRefundReduce.getP(), userOrderRefundReduce.getCouponId(), userOrderRefundReduce.getCouponReturnAmount(), userOrderRefundReduce.getCouponReturnTime(), userOrderRefundReduce.getRandomAmount(), userOrderRefundReduce.getRandomTime(), userOrderRefundReduce.getRandomId(), userOrderRefundReduce.getRandomUserid(), });
	}
	private List<UserOrderRefundReduce> fillUserOrderRefundReduces(List<Map<String, Object>> tempList)
	{
		List<UserOrderRefundReduce> userOrderRefundReduces = new ArrayList<UserOrderRefundReduce>(tempList.size());
		for (Map<String, Object> map : tempList)
		{
			UserOrderRefundReduce userOrderRefundReduce = new UserOrderRefundReduce();
			userOrderRefundReduce.setOrderId(ObjectUtil.getString(map.get("order_id")));
			userOrderRefundReduce.setSubOrderId(ObjectUtil.getString(map.get("sub_order_id")));
			userOrderRefundReduce.setUserid(ObjectUtil.getLong(map.get("userid")));
			userOrderRefundReduce.setUid(ObjectUtil.getString(map.get("uid")));
			userOrderRefundReduce.setP(ObjectUtil.getString(map.get("p")));
			userOrderRefundReduce.setCreateTime(ObjectUtil.getTimestamp(map.get("create_time")));
			userOrderRefundReduce.setUpdateTime(ObjectUtil.getTimestamp(map.get("update_time")));
			userOrderRefundReduce.setCouponId(ObjectUtil.getString(map.get("coupon_id")));
			userOrderRefundReduce.setCouponReturnAmount(ObjectUtil.getBigDecimal(map.get("coupon_return_amount")));
			userOrderRefundReduce.setCouponReturnTime(ObjectUtil.getTimestamp(map.get("coupon_return_time")));
			userOrderRefundReduce.setRandomAmount(ObjectUtil.getBigDecimal(map.get("random_amount")));
			userOrderRefundReduce.setRandomTime(ObjectUtil.getTimestamp(map.get("random_time")));
			userOrderRefundReduce.setRandomId(ObjectUtil.getString(map.get("random_id")));
			userOrderRefundReduce.setRandomUserid(ObjectUtil.getLong(map.get("random_userid")));
			userOrderRefundReduces.add(userOrderRefundReduce);
		}
		return userOrderRefundReduces;
	}
	public UserOrderRefundReduce getUserOrderRefundReduce()
	{
		String sql = "select order_id,sub_order_id,userid,uid,p,create_time,update_time,coupon_id,coupon_return_amount,coupon_return_time,random_amount,random_time,random_id,random_userid, from user_order_refund_reduce where  ";
		List<Map<String, Object>> tempList = this.baseDao.queryForList(sql, new Object[] { });
		List<UserOrderRefundReduce> result = fillUserOrderRefundReduces(tempList);
		return ObjectUtil.isEmpty(result) ? null : result.get(0);
	}
}
