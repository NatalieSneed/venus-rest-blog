package sneed.venusrestblog.data.respository;

import sneed.venusrestblog.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);

}
