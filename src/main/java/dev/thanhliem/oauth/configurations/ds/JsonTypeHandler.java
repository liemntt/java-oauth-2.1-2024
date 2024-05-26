package dev.thanhliem.oauth.configurations.ds;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.*;

public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private final ObjectMapper objectMapper = JsonMapper.builder()
        .findAndAddModules()
        .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, true)
        .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
        .build();
    private final Class<T> type;

    public JsonTypeHandler(Class<T> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setObject(i, objectMapper.writeValueAsString(parameter), Types.OTHER);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting parameter to JSON", e);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toObject(rs.getString(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toObject(rs.getString(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toObject(cs.getString(columnIndex));
    }

    private T toObject(String content) throws SQLException {
        if (content != null) {
            try {
                return objectMapper.readValue(content, type);
            } catch (IOException e) {
                throw new SQLException("Error converting JSON to Object", e);
            }
        }
        return null;
    }
}
