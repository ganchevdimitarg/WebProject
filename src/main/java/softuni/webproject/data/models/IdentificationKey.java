package softuni.webproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "identification_keys")
@Getter
@Setter
@NoArgsConstructor
public class IdentificationKey extends BaseEntity{
    private String logKey;
    private boolean isFree;
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "logInKey")
    private Doctor doctor;
}
