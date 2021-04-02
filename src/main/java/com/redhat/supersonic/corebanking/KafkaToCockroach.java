package com.redhat.supersonic.corebanking;

import java.util.Date;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import com.redhat.supersonic.corebanking.model.Transfer;
import com.redhat.supersonic.corebanking.model.TransferResult;
import com.redhat.supersonic.corebanking.entity.Account;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Blocking;


@ApplicationScoped
public class KafkaToCockroach {

    @Incoming("external-events")               
    @Outgoing("internal-events")      
    @Broadcast
    @Blocking
    @Transactional                       
    public TransferResult process(Transfer transfer) {
        // 口座の残高を確認する(ロックも行う)
        Account sourceAccount = Account.findById(transfer.sourceId,LockModeType.PESSIMISTIC_WRITE);
        Account destinationAccount = Account.findById(transfer.destinationId,LockModeType.PESSIMISTIC_WRITE);
        if(Objects.isNull(sourceAccount) || Objects.isNull(destinationAccount) || sourceAccount.balance.compareTo(transfer.amount) == 1)
        {
            return new TransferResult(transfer,new Date().toString(),"NG");
        }
        
        // 残高が振込額以上であれば振り込みを実施する
        Account.update("balance = ?1 where id = ?2", sourceAccount.balance.subtract(transfer.amount),transfer.sourceId);
        Account.update("balance = ?1 where id = ?2", destinationAccount.balance.add(transfer.amount),transfer.destinationId);

        return new TransferResult(transfer,new Date().toString(),"OK");
    }
}
