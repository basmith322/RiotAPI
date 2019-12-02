package com.example.riotapi.Utilities

import com.merakianalytics.orianna.types.common.Region

class SetCurrentRegion {
    fun setCurrentRegion(position: Int): Region =
            when (position) {
                0 -> Region.BRAZIL
                1 -> Region.EUROPE_NORTH_EAST
                2 -> Region.EUROPE_WEST
                3 -> Region.JAPAN
                4 -> Region.KOREA
                5 -> Region.LATIN_AMERICA_NORTH
                6 -> Region.LATIN_AMERICA_SOUTH
                7 -> Region.NORTH_AMERICA
                8 -> Region.OCEANIA
                9 -> Region.RUSSIA
                10 -> Region.TURKEY
                else -> {
                    Region.EUROPE_WEST
                }
            }
}

