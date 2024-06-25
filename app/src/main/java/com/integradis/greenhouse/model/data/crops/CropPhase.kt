package com.integradis.greenhouse.model.data.crops

enum class CropPhase {
    FORMULA {
        override fun getPhaseName() = "Formula"
        override fun getPhaseNumber() = "0"
    },
    PREPARATION_AREA {
        override fun getPhaseName() = "Preparation Area"
        override fun getPhaseNumber() = "1"
    },
    BUNKER {
        override fun getPhaseName() = "Bunker"
        override fun getPhaseNumber() = "2"
    },
    TUNNEL {
        override fun getPhaseName() = "Tunnel"
        override fun getPhaseNumber() = "3"
    },
    INCUBATION {
        override fun getPhaseName() = "Incubation"
        override fun getPhaseNumber() = "4.1"
    },
    CASING {
        override fun getPhaseName() = "Casing"
        override fun getPhaseNumber() = "4.2"
    },
    INDUCTION {
        override fun getPhaseName() = "Induction"
        override fun getPhaseNumber() = "4.3"
    },
    HARVEST {
        override fun getPhaseName() = "Harvest"
        override fun getPhaseNumber() = "4.4"
    };

    abstract fun getPhaseName() : String
    abstract fun getPhaseNumber() : String

    companion object {
        fun getValueOf(phase : String?) : CropPhase {
            if (phase.isNullOrBlank()) return FORMULA
            return CropPhase.valueOf(phase.uppercase())
        }
    }
}