package dk.mk.is.rmi.dbserver;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class BankTransaction implements Serializable {
    @Id
    private String transactionUUID;

    @NonNull
    private Long accnumFrom;

    @NonNull
    private Long accnumTo;

    private String transactionMessage;

    @NonNull
    private Double amount;

}
