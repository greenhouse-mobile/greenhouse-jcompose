package com.integradis.greenhouse.model.data.crops

enum class CropPhase {
    FORMULA {
        override fun getPhaseName() = "Formula"
        override fun getPhaseNumber() = "0"
        override fun getAmountOfFields() = 8
        override fun getFields() = listOf("Hay","Corn","Guano","Cotton Seed Cake",
            "Soybean Meal","Gypsum","Urea","Ammonium Sulfate")
        override fun getDatabaseName() = "formula"
        override fun getNextPhase() = "preparation_area"
    },
    PREPARATION_AREA {
        override fun getPhaseName() = "Preparation Area"
        override fun getPhaseNumber() = "1"
        override fun getAmountOfFields() = 3
        override fun getFields() = listOf("Activities", "Temperature", "Comment")
        override fun getDatabaseName() = "preparation_area"
        override fun getNextPhase() = "bunker"
    },
    BUNKER {
        override fun getPhaseName() = "Bunker"
        override fun getPhaseNumber() = "2"
        override fun getAmountOfFields() = 6
        override fun getFields() = listOf("T1", "T2", "T3", "Frequency", "Comment")
        override fun getDatabaseName() = "bunker"
        override fun getNextPhase() = "tunnel"
    },
    TUNNEL {
        override fun getPhaseName() = "Tunnel"
        override fun getPhaseNumber() = "3"
        override fun getAmountOfFields() = 10
        override fun getFields() = listOf("T1", "T2", "T3", "Frequency", "RT", "Fresh Air",
            "Recirculation", "Comment")
        override fun getDatabaseName() = "tunnel"
        override fun getNextPhase() = "incubation"
    },
    INCUBATION {
        override fun getPhaseName() = "Incubation"
        override fun getPhaseNumber() = "4.1"
        override fun getAmountOfFields() = 7
        override fun getFields() = listOf("Grow Room", "Air Temperature", "Compost Temperature",
            "Carbon Dioxide", "Air Humidity", "Setting", "Comment")
        override fun getDatabaseName() = "incubation"
        override fun getNextPhase() = "casing"
    },
    CASING {
        override fun getPhaseName() = "Casing"
        override fun getPhaseNumber() = "4.2"
        override fun getAmountOfFields() = 7
        override fun getFields() = listOf("Grow Room", "Air Temperature", "Compost Temperature",
            "Carbon Dioxide", "Air Humidity", "Setting", "Comment")
        override fun getDatabaseName() = "casing"
        override fun getNextPhase() = "induction"
    },
    INDUCTION {
        override fun getPhaseName() = "Induction"
        override fun getPhaseNumber() = "4.3"
        override fun getAmountOfFields() = 7
        override fun getFields() = listOf("Grow Room", "Air Temperature", "Compost Temperature",
            "Carbon Dioxide", "Air Humidity", "Setting", "Comment")
        override fun getDatabaseName() = "induction"
        override fun getNextPhase() = "harvest"
    },
    HARVEST {
        override fun getPhaseName() = "Harvest"
        override fun getPhaseNumber() = "4.4"
        override fun getAmountOfFields() = 7
        override fun getFields() = listOf("Grow Room", "Air Temperature", "Compost Temperature",
            "Carbon Dioxide", "Air Humidity", "Setting", "Comment")
        override fun getDatabaseName() = "harvest"
        override fun getNextPhase() = "harvest"
    };

    abstract fun getPhaseName() : String
    abstract fun getPhaseNumber() : String
    abstract fun getAmountOfFields(): Int
    abstract fun getFields(): List<String>
    abstract fun getDatabaseName(): String
    abstract fun getNextPhase(): String

    companion object {
        fun getValueOf(phase : String?) : CropPhase {
            if (phase.isNullOrBlank()) return FORMULA
            return CropPhase.valueOf(phase.uppercase().replace(" ", "_"))
        }
    }
}