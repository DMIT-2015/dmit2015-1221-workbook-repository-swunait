package dmit2015.view;

import dmit2015.entity.Product;
import dmit2015.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named("currentProductQueryController")
@ViewScoped
public class ProductQueryController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private ProductRepository _productRepository;

    @Getter @Setter
    private String searchValue;

    @Getter
    private List<Product> queryResultList;

    @Getter
    private Product querySingleResult;

    public List<String> completeSearch(String currentSearchValue) {
        return _productRepository.findProductNameByPattern(currentSearchValue);
    }

    public void onSearch() {
        queryResultList = _productRepository.findByProductNameByPattern(searchValue);
        if (queryResultList.size() > 0) {
            Messages.addGlobalInfo("Query returned {0} result(s).", queryResultList.size());
        } else {
            Messages.addGlobalInfo("No returned for {0}", searchValue);
            queryResultList = null;
            querySingleResult = null;
        }
    }

    public void selectProduct(Product selectedProduct) {
        querySingleResult = selectedProduct;
    }

    public void onClear() {
        searchValue = null;
        queryResultList = null;
        querySingleResult = null;
    }
    @Getter
    private List<Product> productList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            productList = _productRepository.findAll();
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}