package fr.norsys.ApiDoc.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Metadata {
    private int id;
    private String keyName;
    private String value;
    private int documentId;
    public static Metadata baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Metadata metadata = new Metadata();
        metadata.setId(resultSet.getInt("ID_METADATA"));
        metadata.setKeyName(resultSet.getString("KEYNAME"));
        metadata.setValue(resultSet.getString("VALUE"));
        metadata.setDocumentId(resultSet.getInt("DocumentID"));
        return metadata;
    }
}
