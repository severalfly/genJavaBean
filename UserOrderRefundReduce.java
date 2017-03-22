public class UserOrderRefundReduce
{
// 订单号
	private String orderId;
// 子订单号
	private String subOrderId;

	private long userid;

	private String uid;

	private String p;

	private Timestamp createTime;

	private Timestamp updateTime;
// 红包id
	private String couponId;
// 使用的红包可返现金额
	private BigDecimal couponReturnAmount;
// 返现时间
	private Timestamp couponReturnTime;
// 拼手气金额
	private BigDecimal randomAmount;
// 拼手气金额时间
	private Timestamp randomTime;
// 拼手气id,用于返现，也可用于记录
	private String randomId;
// 拼手气的userid
	private long randomUserid;

	private null KEY;
}
