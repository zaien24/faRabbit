package com.work.step2

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

<<<<<<< HEAD
//@RestController
=======
@RestController
>>>>>>> d51761e2f81b5d74de6206411b5978b0952eca6c
@RequestMapping("/api")
class WorkQueuController(private val workQueueProducer: WorkQueueProducer) {
    @PostMapping("/workqueue")
    fun workQueue(@RequestParam message: String, @RequestParam duration: Int): String {
        workQueueProducer.sendWorkQueue(message, duration)
        return "Work queue sent = $message, ($duration)"
    }
}
