package io.haicheng.dashu.services;

import io.haicheng.dashu.domain.entity.Relation;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>Title: Stock</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2022/1/13 18:12
 */
public interface RelationService extends CrudRepository<Relation, Integer> {
}
