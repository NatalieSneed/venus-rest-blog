package sneed.venusrestblog.data.respository;

import sneed.venusrestblog.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long>{
}
