package fr.norsys.ApiDoc.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
