package com.weeds.movie.event

import com.weeds.movie.domain.trend.TrendEvents
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import javax.transaction.Transactional

@Service
class TrendEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional
    fun listen(event: TrendEvents) {
        var hitCount = event.movie.hitCount
        hitCount += 1
    }
}