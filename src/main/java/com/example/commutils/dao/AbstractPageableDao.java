/**
 * AbstractPageDao.java created 2018年10月19日
 *
 * \$LastChangedBy\$
 * \$Date\$
 * \$Revision\$
 */
package com.example.commutils.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author brian
 *
 */
@Repository
public abstract class AbstractPageableDao {

   @Autowired
   protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

   protected <T> BeanPropertyRowMapper<T> getRowMapper(Class<T> clazz) {
      return new BeanPropertyRowMapper<T>(clazz);
   }
   
   protected <T> Page<T> toPaging(String sql, Map<String, Object> paramMap, Pageable pageable, Class<T> clazz) {
      return this.toPaging(sql, paramMap, pageable, getRowMapper(clazz));
   }

   protected <T> Page<T> toPaging(String sql, Map<String, Object> paramMap, Pageable pageable, RowMapper<T> rowMapper) {
      StringBuffer sbCount = new StringBuffer();
      sbCount.append("Select count(1) from (");
      sbCount.append(sql).append(") a ");
      int total = this.namedParameterJdbcTemplate.queryForObject(sbCount.toString(), paramMap, Integer.class);

      StringBuffer sb = new StringBuffer();
      sb.append(sql).append(" order by ");
      if (pageable.getSort().isSorted()) {
         for (Order order : pageable.getSort()) {
            sb.append(order.getProperty()).append(order.getDirection().isAscending() ? " asc" : " desc").append(",");
         }
         sb = new StringBuffer().append(sb.substring(0, sb.length() - 1));
      } else {
         sb.append(" 1");
      }
      sb.append(" offset :start rows fetch next :limit rows only ");
      paramMap.put("start", pageable.getOffset());
      paramMap.put("limit", pageable.getPageSize());
      List<T> content = this.namedParameterJdbcTemplate.query(sb.toString(), paramMap, rowMapper);
      return new PageImpl<T>(content, pageable, total);
   }
}
