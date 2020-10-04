package com.yashinsergey.clinic.model.repos.network

import com.yashinsergey.clinic.model.repos.DoctorsRepository
import com.yashinsergey.clinic.model.repos.network.api.DoctorsApi
import com.yashinsergey.clinic.model.repos.network.json.Branch
import com.yashinsergey.clinic.model.repos.network.json.Doctor

class DoctorsRepositoryImpl(private val api: DoctorsApi): DoctorsRepository {

        override suspend fun getDoctorList(): List<Doctor> = api.getDoctorList()
//    override suspend fun getDoctorList(): List<Doctor> = doctorList

    override suspend fun getDoctorListByBranch(branchId: Int): List<Doctor> = api.getDoctorsListByBranch(branchId)

    private val doctorList = listOf(
        Doctor(0, 25, Branch(1, "Терапия"), 3,
            "Doctor1", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "First"
        ),
        Doctor(1, 25, Branch(2, "Хирургия"), 3,
            "Doctor2", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Second"
        ),
        Doctor(2, 25, Branch(3, "Интенсивная терапия"), 3,
            "Doctor3", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Third"
        ),
        Doctor(3, 25, Branch(1, "Терапия"), 3,
            "Doctor4", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Fourth"
        ),
        Doctor(4, 25, Branch(2, "Хирургия"), 3,
            "Doctor5", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Fifth"
        ),
        Doctor(5, 25, Branch(3, "Интенсивная терапия"), 3,
            "Doctor6", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Sixth"
        ),
        Doctor(6, 25, Branch(1, "Терапия"), 3,
            "Doctor7", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Seventh"
        ),
        Doctor(7, 25, Branch(2, "Хирургия"), 3,
            "Doctor8", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Eighth"
        ),
        Doctor(8, 25, Branch(3, "Интенсивная терапия"), 3,
            "Doctor9", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Ninth"
        ),
        Doctor(9, 25, Branch(1, "Терапия"), 3,
            "Doctor10", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Tenth"
        ),
        Doctor(10, 25, Branch(2, "Хирургия"), 3,
            "Doctor11", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Eleventh"
        ),
        Doctor(11, 25, Branch(3, "Интенсивная терапия"), 3,
            "Doctor12", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Twelfth"
        ),
        Doctor(12, 25, Branch(1, "Терапия"), 3,
            "Doctor13", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Thirteenth"
        ),
        Doctor(13, 25, Branch(2, "Хирургия"), 3,
            "Doctor14", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Fourteenth"
        ),
        Doctor(14, 25, Branch(3, "Интенсивная терапия"), 3,
            "Doctor15", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Sixteenth"
        ),
        Doctor(15, 25, Branch(1, "Терапия"), 3,
            "Doctor16", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Seventeens"
        ),
        Doctor(16, 25, Branch(2, "Хирургия"), 3,
            "Doctor17", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Eighteenth"
        ),
        Doctor(17, 25, Branch(3, "Интенсивная терапия"), 3,
            "Doctor18", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Nineteenth"
        ),
        Doctor(18, 25, Branch(1, "Терапия"), 3,
            "Doctor19", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "Twentieth"
        ),
        Doctor(19, 25, Branch(2, "Хирургия"), 3,
            "Doctor20", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
            "Заведующий отделением", "Терапевт", "TwentyFirst"
        ),
        Doctor(20, 25, Branch(3, "Интенсивная терапия"), 3,
        "Doctor21", "https://pbs.twimg.com/media/DHvRq3AXoAEIxx8.jpg",
        "Заведующий отделением", "Терапевт", "TwentySecond")
        )
}