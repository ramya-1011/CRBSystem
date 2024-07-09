package com.example.demo_room.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull(message = "name of city cannot be empty")
  private String name;
//    @NotEmpty
//    @Size(max = 20, message = "The attribute must be at most 20 characters long.")
    private String state;
//    @NotEmpty
//    @Min(value = 1,message = "minimum value should be 1")
    private int totalSites;
@JsonIgnore
   @OneToMany  (mappedBy = "city",cascade = CascadeType.ALL,orphanRemoval = true)

   private List<Site> sites;


}
