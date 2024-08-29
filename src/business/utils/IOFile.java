package business.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile
{
	public static final String PATH_CATEGORIES = "src/business/data/categories.txt";
	
	public static final String PATH_PRODUCTS = "src/business/data/products.txt";
	
	public static <T> List<T> readObjectFromFile(String path)
	{
		List<T> list = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path)))
		{
			list = (List<T>) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public static <T> boolean writeObjectToFile(List<T> list, String path)
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)))
		{
			oos.writeObject(list);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
