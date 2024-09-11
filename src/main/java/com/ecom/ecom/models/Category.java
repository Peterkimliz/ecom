package com.ecom.ecom.models;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "category")

public class Category {
  @Id
    private String id;
    private String name ;
    private String image;
    private Date createdAt;

}
