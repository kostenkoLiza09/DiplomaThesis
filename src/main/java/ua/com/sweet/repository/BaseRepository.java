package ua.com.sweet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.sweet.models.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity> extends CrudRepository<Entity, Long> {

}
