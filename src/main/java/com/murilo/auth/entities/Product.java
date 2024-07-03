package com.murilo.auth.entities;
import com.murilo.auth.dtos.product.PostProductDTO;
import com.murilo.auth.dtos.product.PutProductDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "product")
@Entity(name = "product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Boolean active;

    public Product(PostProductDTO data){
        this.price = data.price();
        this.name = data.name();
    }

    public void atualizarInformacoes(@Valid PutProductDTO dados){
        if (dados.name() != null){
            this.name = dados.name();

        }

        if (dados.price() != null){
            this.price = dados.price();
        }

    }

    public void inactivate(){
        this.active = false;
    }

    public void activate(){
        this.active = true;
    }
}
