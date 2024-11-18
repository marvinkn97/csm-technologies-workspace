package tech.csm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesSlave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slno")
    private Integer slno;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private SalesMaster salesMaster;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    @Column(name = "sales_qty")
    private Integer salesQty;
    
}

