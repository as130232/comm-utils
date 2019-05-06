///**
// * AbstractPageRepository.java created 2018年9月17日
// *
// * \$LastChangedBy\$
// * \$Date\$
// * \$Revision\$
// */
//package com.example.commutils.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Tuple;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Fetch;
//import javax.persistence.criteria.From;
//import javax.persistence.criteria.Join;
//import javax.persistence.criteria.Order;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Root;
//
//import org.springframework.stereotype.Repository;
//
///**
// * @author brian
// *
// */
//@Repository
//public abstract class AbstractPageRepository {
//
//   @PersistenceContext
//   protected EntityManager entityManager;
//
//   public <T> long count(final CriteriaBuilder cb, final CriteriaQuery<T> selectQuery, Root<T> root) {
//      CriteriaQuery<Long> query = createCountQuery(cb, selectQuery, root);
//      return this.entityManager.createQuery(query).getSingleResult();
//   }
//
//   public <T> long countByTuple(final CriteriaBuilder cb, final CriteriaQuery<Tuple> selectQuery, Root<T> root, Class<T> clazz) {
//      CriteriaQuery<Long> query = createCountQueryByTuple(cb, selectQuery, root, clazz);
//      return this.entityManager.createQuery(query).getSingleResult();
//   }
//
//   private <T> CriteriaQuery<Long> createCountQueryByTuple(final CriteriaBuilder cb, final CriteriaQuery<Tuple> criteria, final Root<T> root,
//         final Class<T> clazz) {
//      final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//      final Root<T> countRoot = countQuery.from(clazz);
//
//      doJoins(root.getJoins(), countRoot);
//      doJoinsOnFetches(root.getFetches(), countRoot);
//
//      countQuery.select(cb.count(countRoot));
//      countQuery.where(criteria.getRestriction());
//
//      countRoot.alias(root.getAlias());
//
//      return countQuery.distinct(criteria.isDistinct());
//   }
//
//   private <T> CriteriaQuery<Long> createCountQuery(final CriteriaBuilder cb, final CriteriaQuery<T> criteria, final Root<T> root) {
//
//      final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//      final Root<T> countRoot = countQuery.from(criteria.getResultType());
//
//      doJoins(root.getJoins(), countRoot);
//      doJoinsOnFetches(root.getFetches(), countRoot);
//
//      countQuery.select(cb.count(countRoot));
//      countQuery.where(criteria.getRestriction());
//
//      countRoot.alias(root.getAlias());
//
//      return countQuery.distinct(criteria.isDistinct());
//   }
//
//   @SuppressWarnings("unchecked")
//   private void doJoinsOnFetches(Set<? extends Fetch<?, ?>> joins, Root<?> root) {
//      doJoins((Set<? extends Join<?, ?>>) joins, root);
//   }
//
//   private void doJoins(Set<? extends Join<?, ?>> joins, Root<?> root) {
//      for (Join<?, ?> join : joins) {
//         Join<?, ?> joined = root.join(join.getAttribute().getName(), join.getJoinType());
//         joined.alias(join.getAlias());
//         doJoins(join.getJoins(), joined);
//      }
//   }
//
//   private void doJoins(Set<? extends Join<?, ?>> joins, Join<?, ?> root) {
//      for (Join<?, ?> join : joins) {
//         Join<?, ?> joined = root.join(join.getAttribute().getName(), join.getJoinType());
//         joined.alias(join.getAlias());
//         doJoins(join.getJoins(), joined);
//      }
//   }
//
//   protected <T, X> Path<T> getPath(Root<X> root, String attributeName, Class<T> clazz) {
//      Path<T> path = root.get(attributeName);
//      path.alias(attributeName);
//      return path;
//   }
//
//   protected <T, X, Y> Path<T> getJoinPath(Join<X, Y> join, String attributeName, Class<T> clazz) {
//      Path<T> path = join.get(attributeName);
//      path.alias(attributeName);
//      return path;
//   }
//
//   protected List<Order> toJpaOrders(CriteriaBuilder cb, From<?, ?> from, org.springframework.data.domain.Sort sort) {
//      List<Order> orders = null;
//      if (sort.isSorted()) {
//         orders = new ArrayList<>();
//         for (org.springframework.data.domain.Sort.Order order : sort) {
//            orders.add(toJpaOrder(cb, from, order));
//         }
//      }
//      return orders;
//   }
//   
//   protected Order toJpaOrder(CriteriaBuilder cb, From<?, ?> from, org.springframework.data.domain.Sort.Order order) {
//      Order jpaOrder = null;
//      if (order.isAscending()) {
//         jpaOrder = cb.asc(from.get(order.getProperty()));
//      } else {
//         jpaOrder = cb.desc(from.get(order.getProperty()));
//      }
//      return jpaOrder;
//   }
//}
