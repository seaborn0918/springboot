package com.bitc.jpatest.service;

import com.bitc.jpatest.data.entity.ProductEntity;
import com.bitc.jpatest.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;

  @Override
  public void finds() throws Exception {
    System.out.println("------------------- findBy() 쿼리 메소드 실행 ---------------\n");
    Optional<ProductEntity> prod1 = productRepository.findByNumber(1L);
    Optional<ProductEntity> prod2 = productRepository.findProductEntityByNumber(2L);
    List<ProductEntity> prod3 = productRepository.findAllByName("볼펜");
    ProductEntity prod4 = productRepository.queryByName("볼펜");
  }

  @Override
  public void exists() throws Exception {
    boolean isProductNumber = productRepository.existsByNumber(2L);
  }

  @Override
  public void count() throws Exception {
    int count = productRepository.countByName("만년필");
  }

  @Override
  public void delete() throws Exception {

  }

  @Override
  public void limit() throws Exception {

  }

  @Override
  public void equals() throws Exception {

  }

  @Override
  public void isNot() throws Exception {

  }

  @Override
  public void isNull() throws Exception {

  }

  @Override
  public void isNotNull() throws Exception {

  }

  @Override
  public void and() throws Exception {
    ProductEntity prod1 = productRepository.findByNumberAndName(5L, "만년필");
    ProductEntity prod2 = productRepository.findByNumberAndName(1L, "만년필");
  }

  @Override
  public void or() throws Exception {

  }

  @Override
  public void between() throws Exception {

  }

  @Override
  public void like() throws Exception {

  }

  @Override
  public void orderBy() throws Exception {

  }

  @Override
  public void querySelectAll() throws Exception {
    List<ProductEntity> prod1 = productRepository.querySelectAll();
  }

  @Override
  public void querySelectName() throws Exception {
    ProductEntity prod1 = productRepository.querySelectName();
    ProductEntity prod2 = productRepository.querySelectName("사인펜");
    ProductEntity prod3 = productRepository.querySelectNamePrice("샤프", 1200);
  }
}
