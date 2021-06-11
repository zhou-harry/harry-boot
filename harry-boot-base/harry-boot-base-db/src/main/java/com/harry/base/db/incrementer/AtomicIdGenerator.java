package com.harry.base.db.incrementer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * 自定义ID生成器
 */
@Slf4j
@Component
@ConditionalOnMissingBean(IdentifierGenerator.class)
public class AtomicIdGenerator implements IdentifierGenerator {

  private final AtomicLong al = new AtomicLong(1);

  @Override
  public Long nextId(Object entity) {
    //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
    String bizKey = entity.getClass().getName();
    log.info("bizKey:{}", bizKey);
    MetaObject metaObject = SystemMetaObject.forObject(entity);
    String name = (String) metaObject.getValue("name");
    final long id = al.getAndAdd(1);
    log.info("为{}生成主键值->:{}", name, id);
    return id;
  }
}
