package dmit2015.view;

import dmit2015.entity.Category;
import dmit2015.repository.CategoryRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named("currentCategoryQueryListController")
@ViewScoped
public class CategoryQueryListController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private CategoryRepository _categoryRepository;

    @Getter @Setter
    private String querySearchValue;

    @Getter
    private Category querySingleResult;

    public List<String> completeText(String query) {
//        return _categoryRepository
//                .findAll()
//                .stream()
//                .filter(currentCategory -> currentCategory.getCategoryName().toLowerCase().contains(query.toLowerCase()))
//                .map(currentCategory -> currentCategory.getCategoryName())
//                .toList();

        // The code above was a temporary workaround the issue where we didn't have the time to code a method in the respository to return the required data

        return _categoryRepository.findNamesByPattern(query);
    }

    public void onSearch() {
        try {
//            querySingleResult = _categoryRepository
//                    .findAll()
//                    .stream()
//                    .filter(currentCategory -> currentCategory.getCategoryName().equalsIgnoreCase(querySearchValue))
//                    .findFirst()
//                    .orElseThrow();

            // The code above was a temporary workaround the issue where we didn't have the time to code a method in the respository to return the required data

            querySingleResult = _categoryRepository.findByName(querySearchValue).orElseThrow();
        } catch(Exception ex) {
            Messages.addGlobalError("There are no result matching {0}", querySearchValue);
        }
    }

    public void onClear() {
        querySearchValue = null;
        querySingleResult = null;
    }


}