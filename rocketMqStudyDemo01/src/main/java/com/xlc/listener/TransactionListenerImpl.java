package com.xlc.listener;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // … local transaction process, return bollback, commit or unknown
        return RocketMQLocalTransactionState.UNKNOWN;
    }
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // … check transaction status and return bollback, commit or unknown
        return RocketMQLocalTransactionState.COMMIT;
    }

}