package com.harry.base.db.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "harry.base.datasource")
public class DatabaseProperties {

  //MapperScanner扫描位置
  private String mapperLocation;
}
