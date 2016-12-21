package  com.leon.util;
import com.leon.util.Page;


public class PageUtil
{
	public static Page createPage(int everyPage, int totalCount, int currentPage)
	{
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(everyPage, totalCount);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasPrePage = getHasPrePage(currentPage);
		boolean hasNextPage = getHasNextPage(totalCount, currentPage);
		return new Page(everyPage, totalCount, totalPage, currentPage, beginIndex, hasPrePage, hasNextPage);
	}

	public static int getEveryPage(int everyPage)
	{
		return everyPage <= 0? 10: everyPage;
	}

	public static int getCurrentPage(int currentPage)
	{
		return currentPage <= 0? 1: currentPage;
	}

	public static int getTotalPage(int everyPage, int totalCount)
	{
		if(totalCount <= 0 || everyPage <= 0)
		{
			return 0;
		}
		else if(totalCount % everyPage == 0)
		{
			return totalCount / everyPage;
		}
		else
		{
			return totalCount / everyPage + 1;
		}
	}

	public static int getBeginIndex(int everyPage, int currentPage)
	{
		return (currentPage - 1) * everyPage;
	}

	public static boolean getHasPrePage(int currentPage)
	{
		return currentPage > 1;
	}

	public static boolean getHasNextPage( int totalPage, int currentPage)
	{
		return totalPage <= 0 || currentPage == totalPage ? false: true;
	}


}