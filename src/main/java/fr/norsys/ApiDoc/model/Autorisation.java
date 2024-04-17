package fr.norsys.ApiDoc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autorisation {
    private int idUser;
    private int idDocument;
    private String droitAcces;

    public static Autorisation baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Autorisation autorisation=new Autorisation();
        autorisation.setIdDocument(resultSet.getInt("ID_DOCUMENT"));
        autorisation.setIdUser(resultSet.getInt("ID_USER"));
        autorisation.setDroitAcces(resultSet.getString("DROIT_ACCES"));
        return autorisation;
    }
}
