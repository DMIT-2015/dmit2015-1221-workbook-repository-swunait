package westwind.repository;

import org.junit.jupiter.api.Test;
import westwind.entity.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryJavaSeRepositoryTest {

    @Test
    void findAll() {
        var categoryRepository = new CategoryJavaSeRepository();
        // There should be 8 records
        var categories = categoryRepository.findAll();
        assertEquals(8, categories.size());
        // The first category name should be Beverages
        assertEquals("Beverages", categories.get(0).getName());
        // The last category name should be Seafood
        assertEquals("Seafood", categories.get( categories.size() - 1).getName());
    }

    @Test
    void findById() {
        var categoryRepository = new CategoryJavaSeRepository();
        // There should be a record with id of 4 with a name of "Dairy Products" and description of "Cheeses"
        var optionalCategory = categoryRepository.findById(4L);
        assertTrue(optionalCategory.isPresent());
        var querySingleResult = optionalCategory.get();
        assertEquals("Dairy Products", querySingleResult.getName());
        assertEquals("Cheeses", querySingleResult.getDescription());
    }

    @Test
    void add() {
        var categoryRepository = new CategoryJavaSeRepository();
        // Create a new Category to add
        var newCategory = new Category();
        newCategory.setName("Video Games");
        newCategory.setDescription("Console and PC games");
        var createdCategory = categoryRepository.add(newCategory);
        // Find the entity that created
        var optionalCategory = categoryRepository.findById(createdCategory.getId());
        assertTrue(optionalCategory.isPresent());
        var querySingleResult = optionalCategory.get();
        assertEquals(createdCategory.getName(), querySingleResult.getName());
        assertEquals(createdCategory.getDescription(), querySingleResult.getDescription());
    }

}