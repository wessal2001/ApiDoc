package fr.norsys.ApiDoc.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    private int idDocument;
    private String nom;
    private String type;
    private Date dateCreation;
    private String urlDocument;
    public static Document baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Document document = new Document();
        document.setIdDocument(resultSet.getInt("ID_DOCUMENT"));
        document.setNom(resultSet.getString("NOM"));
        document.setType(resultSet.getString("TYPE"));
        document.setDateCreation(resultSet.getDate("DATE_CREATION"));
        document.setUrlDocument(resultSet.getString("URL_DOCUMENT"));

        return document;
    }
}
