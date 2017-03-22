package leon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeneralBean
{
	public static void main(String[] args)
	{
		BufferedReader reader = null;
		FileOutputStream out = null;
		try
		{
			File file = null;
			if (args.length <= 0)
			{
				try
				{
					file = new File("D:\\learning\\openLearn\\genJavaBen\\test.sql");
				}
				catch (Exception e)
				{
					System.out.println("please input your sql File!!!");
					return;
				}
			}
			else
			{
				file = new File(args[0]);
			}

			String filePath = file.getCanonicalPath();
			filePath = filePath.replace("\\", "/");
			String path = filePath.substring(0, filePath.lastIndexOf("/"));
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			boolean start = false;
			boolean end = false;
			Map<String, String> name = new LinkedHashMap<String, String>();
			Map<String, String> oriName = new LinkedHashMap<String, String>();
			String oriBeanName = "";
			String beanName = "";
			while ((line = reader.readLine()) != null)
			{
				if (line.trim().equals(""))
				{
					continue;
				}
				if (!start && line.trim().endsWith("("))
				{
					oriBeanName = line.split("[ ]+")[2].trim().replaceAll("[\\W]{1,}", "");
					beanName = addMoreInfoInName(oriBeanName);
					beanName = toUpperCaseFirst(beanName);
					out = new FileOutputStream(new File(path + "/" + beanName + ".java"));
					out.write(("public class " + beanName + "\n").getBytes("utf-8"));
					out.write("{\n".getBytes("utf-8"));
					start = true;
					continue;
				}
				if (!end && line.trim().startsWith(")"))
				{
					end = true;
				}
				if (!start || end)
				{
					continue;
				}
				String jcomment = "";
				int s = line.indexOf("/*");
				int e = line.indexOf("*/");
				if (e > s)
				{
					jcomment += line.substring(s, e);
				}
				int m = line.indexOf("//");
				if (m > 0)
				{
					jcomment += line.substring(m);
				}
				line = line.trim().replaceAll("/\\*.*\\*/", "").replaceAll("//.*", ""); // 去掉注释
				String[] duans = line.split("[ ]{1,}");
				if (duans.length < 2)
				{
					out.close();
					throw new Exception("行错误" + line);
				}
				String duan = duans[0].trim().replaceAll("[\\W]{1,}", "");
				String oriDuan = duan;
				duan = addMoreInfoInName(duan);
				if (duans[1].contains("char"))
				{
					oriName.put(oriDuan, "String");
					name.put(duan, "String");
				}
				else if (duans[1].contains("bigint"))
				{
					oriName.put(oriDuan, "long");
					name.put(duan, "long");
				}
				else if (duans[1].contains("int"))
				{
					oriName.put(oriDuan, "int");
					name.put(duan, "int");
				}
				else if (duans[1].contains("decimal"))
				{
					oriName.put(oriDuan, "BigDecimal");
					name.put(duan, "BigDecimal");
				}
				else if (duans[1].contains("datetime"))
				{
					oriName.put(oriDuan, "Timestamp");
					name.put(duan, "Timestamp");
				}
				out.write((jcomment + "\n").getBytes("utf-8"));
				line = "\tprivate " + name.get(duan) + " " + duan + ";\n";
				out.write(line.getBytes("utf-8"));
			}
			reader.close();
			out.write("}\n".getBytes("utf-8"));
			out.close();

			out = new FileOutputStream(new File(path + "/" + beanName + "Impl.java"));
			write(out, "@Component(\"" + toLowwerCaseFirst(beanName) + "Impl\")");
			write(out, "public class " + beanName + "Impl");
			write(out, "{");
			write(out, "\t@Autowired");
			write(out, "\tprivate BaseOperDao baseDao;");
			write(out, "\tpublic int insert" + beanName + "(" + beanName + " " + toLowwerCaseFirst(beanName) + ")");
			write(out, "\t{");
			StringBuilder sql = new StringBuilder("\t\tString sql= \"insert into " + oriBeanName + "(");
			for (Map.Entry<String, String> me : oriName.entrySet())
			{
				if (me.getKey().contains("update_time"))
				{
					continue;// ��ʱ��Ĳ��Զ�����
				}
				sql.append(me.getKey() + ",");
			}
			sql.append(")value(");
			for (Map.Entry<String, String> me : oriName.entrySet())
			{
				if (me.getKey().contains("update_time"))
				{
					continue;// ��ʱ��Ĳ��Զ�����
				}
				if (me.getKey().contains("create_time"))
				{
					sql.append("current_timestamp, ");
					continue;
				}
				sql.append("?, ");
			}

			sql.append(")\";");
			write(out, sql.toString());
			StringBuilder insert = new StringBuilder("\t\treturn this.baseDao.update(sql, new Object[]{");
			for (Map.Entry<String, String> me : name.entrySet())
			{
				if (me.getKey().contains("updateTime"))
				{
					continue;// ��ʱ��Ĳ��Զ�����
				}
				if (me.getKey().contains("createTime"))
				{
					continue;
				}
				else
				{
					insert.append(toLowwerCaseFirst(beanName) + ".get" + toUpperCaseFirst(me.getKey()) + "(), ");
				}
			}
			insert.append("});");
			write(out, insert.toString());
			write(out, "\t}");

			String beanObj = toLowwerCaseFirst(beanName);
			write(out, "\tprivate List<" + beanName + "> fill" + beanName + "s(List<Map<String, Object>> tempList)");
			write(out, "\t{");
			write(out, "\t\tList<" + beanName + "> " + toLowwerCaseFirst(beanName) + "s = new ArrayList<" + beanName + ">(tempList.size());");
			write(out, "\t\tfor (Map<String, Object> map : tempList)");
			write(out, "\t\t{");
			write(out, "\t\t\t" + beanName + " " + beanObj + " = new " + beanName + "();");
			for (Map.Entry<String, String> me : oriName.entrySet())
			{
				write(out, "\t\t\t" + beanObj + ".set" + toUpperCaseFirst(addMoreInfoInName(me.getKey())) + "(ObjectUtil.get" + toUpperCaseFirst(me.getValue()) + "(map.get(\"" + me.getKey() + "\")));");
			}
			write(out, "\t\t\t" + beanObj + "s.add(" + beanObj + ");");
			write(out, "\t\t}");
			write(out, "\t\treturn " + toLowwerCaseFirst(beanName) + "s;");
			write(out, "\t}");

			write(out, "\tpublic " + beanName + " get" + beanName + "()");
			write(out, "\t{");
			StringBuilder select = new StringBuilder("String sql = \"select ");
			for (Map.Entry<String, String> me : oriName.entrySet())
			{
				select.append(me.getKey() + ",");
			}
			select.append(" from " + oriBeanName + " where  \";");
			write(out, "\t\t" + select.toString());
			write(out, "\t\tList<Map<String, Object>> tempList = this.baseDao.queryForList(sql, new Object[] { });");
			write(out, "\t\tList<" + beanName + "> result = fill" + beanName + "s(tempList);");
			write(out, "\t\treturn ObjectUtil.isEmpty(result) ? null : result.get(0);");
			write(out, "\t}");

			write(out, "}");
			out.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (reader != null)
				{
					reader.close();
				}
				if (out != null)
				{
					out.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("success");

	}

	public static void write(FileOutputStream out, String line) throws UnsupportedEncodingException, IOException
	{
		if (line.endsWith("\n"))
			out.write(line.getBytes("utf-8"));
		else
		{
			out.write((line + "\n").getBytes("utf-8"));
		}
	}

	public static String addMoreInfoInName(String oriName)
	{
		if (!oriName.contains("_"))
		{
			return oriName;
		}
		String[] names = oriName.split("_");
		String name = "";
		for (int i = 0; i < names.length; ++i)
		{
			String string = names[i];
			if (i == 0)
			{
				name += string;
				continue;
			}
			name += toUpperCaseFirst(string);
		}
		return name;
	}

	public static String toUpperCaseFirst(String str)
	{
		if (null == str || "".equals(str))
		{
			return str;
		}
		if (str.length() < 1)
		{
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}

	public static String toLowwerCaseFirst(String str)
	{
		if (null == str || "".equals(str))
		{
			return str;
		}
		if (str.length() < 1)
		{
			return str;
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
	}

}
