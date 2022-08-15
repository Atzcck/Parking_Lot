class parkingLot() {
    lateinit var slotDetails: MutableList<MutableList<String>>
    lateinit var slotStatus: Array<Boolean>
    var created = false
    fun create(size: Int) {
        slotDetails = MutableList(size) { MutableList(3) { " " } }
        slotStatus = Array(size) { false }
        created = true
        println("Created a parking lot with $size spots.")
    }

    fun park(plate: String, color: String) {

        for (i in 0..slotStatus.size - 1) if (slotStatus[i] == false) {
            slotStatus[i] = true
            slotDetails[i][0] = (i + 1).toString()
            slotDetails[i][1] = plate
            slotDetails[i][2] = color
            println("$color car parked in spot ${i + 1}.")
            break
        } else if (slotStatus[slotStatus.size - 1]) {
            println("Sorry, the parking lot is full.")
            break
        }

    }

    fun leave(slot: Int) {
        if (slotStatus[slot - 1] == true) {
            slotStatus[slot - 1] = false
            slotDetails[slot - 1][0] = " "
            slotDetails[slot - 1][1] = " "
            slotDetails[slot - 1][2] = " "
            println("Spot $slot is free.")
        }
    }

    fun status() {
        for (i in 0..slotDetails.size - 1) {
            if (slotDetails[i][0] != " ") {
                println("${slotDetails[i][0]} ${slotDetails[i][1]} ${slotDetails[i][2]}")
            }
        }
    }

    fun regByColor(color: String) {
        val spotted: MutableList<String> = mutableListOf()
        var include = false
        for (i in 0..slotDetails.size - 1) {
            if (slotDetails[i][2].uppercase() == color.uppercase()) {
                spotted.add(slotDetails[i][1])
                include = true
            }
        }
        if (!include) println("No cars with color $color were found.") else println(spotted.joinToString(", "))

    }
    fun spotByColor(color: String) {
        val spotted: MutableList<String> = mutableListOf()
        var include = false
        for (i in 0.. slotDetails.size-1) {
            if (slotDetails[i][2].uppercase() == color.uppercase()) {
                spotted.add(slotDetails[i][0])
                include = true
            }
        }
        if (!include) println("No cars with color $color were found.") else println(spotted.joinToString(", "))

    }
    fun spotByReg(Reg: String) {
        val spotted: MutableList<String> = mutableListOf()
        var include = false
        for (i in 0..slotDetails.size-1) {
            if (slotDetails[i][1].uppercase() == Reg.uppercase()) {
                spotted.add(slotDetails[i][0])
                include = true
            }
        }
        if (!include) println("No cars with registration number $Reg were found.") else println(spotted.joinToString(", "))
    }
}

fun main() {
    val parking_lot = parkingLot()

    while (true) {
        val input = readln().split(" ")
        when (input[0]) {
            "create" -> parking_lot.create(input[1].toInt())
            "park" -> {
                if (!parking_lot.created) {
                    println("Sorry, a parking lot has not been created.")
                } else parking_lot.park(input[1], input[2])

            }
            "leave" -> {
                if (!parking_lot.created) {
                    println("Sorry, a parking lot has not been created.")
                } else parking_lot.leave(input[1].toInt())
            }
            "status" -> {
                if (!parking_lot.created) {
                    println("Sorry, a parking lot has not been created.")
                } else if (parking_lot.slotDetails[0].contains("1")) {
                    parking_lot.status()
                } else println("Parking lot is empty.")
            }
            "reg_by_color" -> {
                if (!parking_lot.created) {
                    println("Sorry, a parking lot has not been created.")
                } else parking_lot.regByColor(input[1])
            }
            "spot_by_color" -> {
                if (!parking_lot.created) {
                    println("Sorry, a parking lot has not been created.")
                } else parking_lot.spotByColor(input[1])
            }
            "spot_by_reg" -> {
                if (!parking_lot.created) {
                    println("Sorry, a parking lot has not been created.")
                } else parking_lot.spotByReg(input[1])
            }
            "exit" -> break
        }
    }
}
