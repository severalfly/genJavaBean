package com.leon.util;

public class ObjectUtils
{
	public static int getInt(Object obj)
	{
		if (isNull(obj))
		{
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}

	public static boolean isNull(Object obj)
	{
		if (null == obj || "".equals(obj))
		{
			return true;
		}
		return false;
	}

	public static boolean isNotNull(Object obj)
	{
		return !isNull(obj);
	}

	public static String getString(Object object)
	{

		return String.valueOf(object);
	}
}
