package org.apache.ibatis.type;

public enum CustomEnumType implements BaseEnum {
  FIRST(1),
  SECOND(2);
  private int code;

  CustomEnumType(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return code;
  }
}
