package org.knowm.xchange.bequant.v2.dto;

public enum BequantSort {
  SORT_ASCENDING("asc"),
  SORT_DESCENDING("desc");

  private final String sort;

  BequantSort(String sort) {

    this.sort = sort;
  }

  @Override
  public String toString() {

    return sort;
  }
}
