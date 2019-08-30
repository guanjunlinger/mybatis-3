package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(BaseEnum.class)
public class CustomEnumTypeHandler<T extends Enum & BaseEnum> extends BaseTypeHandler<T> {
  private Class<T> type;

  public CustomEnumTypeHandler(Class<T> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int code = rs.getInt(columnName);
    for (T t : type.getEnumConstants()) {
      if (t.getCode() == code)
        return t;
    }
    return null;
  }

  @Override
  public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return null;
  }

  @Override
  public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return null;
  }
}
