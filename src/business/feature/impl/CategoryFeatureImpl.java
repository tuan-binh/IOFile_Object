package business.feature.impl;

import business.entity.Category;
import business.feature.ICategoryFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class CategoryFeatureImpl implements ICategoryFeature
{
	
	public static List<Category> categories = IOFile.readObjectFromFile(IOFile.PATH_CATEGORIES);
	
	@Override
	public void addOrUpdate(Category category)
	{
		int index = findIndexByID(category.getCatalogId());
		if (index != -1)
		{
			categories.set(index, category);
		}
		else
		{
			categories.add(category);
		}
		IOFile.writeObjectToFile(categories, IOFile.PATH_CATEGORIES);
	}
	
	@Override
	public void delete(Integer id)
	{
		categories.remove(findIndexByID(id));
		IOFile.writeObjectToFile(categories, IOFile.PATH_CATEGORIES);
	}
	
	@Override
	public int findIndexByID(Integer id)
	{
		return categories.stream()
				  .map(Category::getCatalogId).toList().indexOf(id);
	}
}
