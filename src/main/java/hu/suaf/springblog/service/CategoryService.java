package hu.suaf.springblog.service;

import hu.suaf.springblog.model.Category;
import hu.suaf.springblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(String name) {
        categoryRepository.deleteByName(name);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

}
